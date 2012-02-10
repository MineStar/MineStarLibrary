/*
 * Copyright (C) 2011 MineStar.de 
 * 
 * This file is part of MineStarLibrary.
 * 
 * MineStarLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MineStarLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MineStarLibrary.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.minestar.minestarlibrary.utils.ConsoleUtils;
import de.minestar.minestarlibrary.utils.PlayerUtils;

/**
 * Class to support commands with sub commands
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public abstract class AbstractSuperCommand extends AbstractCommand {

    private AbstractCommand[] subCommands;
    private boolean hasFunction;

    public AbstractSuperCommand(String syntax, String arguments, String node, boolean hasFunction, AbstractCommand... subCommands) {
        super(syntax, arguments, node);
        this.hasFunction = hasFunction;
        this.subCommands = subCommands;
    }

    public AbstractSuperCommand(String pluginName, String syntax, String arguments, String node, boolean hasFunction, AbstractCommand... subCommands) {
        this(syntax, arguments, node, hasFunction, subCommands);
        this.pluginName = pluginName;
    }

    public AbstractSuperCommand(String pluginName, String syntax, String arguments, String node, AbstractCommand... subCommands) {
        this(pluginName, syntax, arguments, node, false, subCommands);
    }

    public AbstractSuperCommand(String syntax, String arguments, String node, AbstractCommand... subCommands) {
        this(syntax, arguments, node, false, subCommands);
    }

    @Override
    public void run(String[] args, CommandSender sender) {

        if (args.length == 0) {
            if (hasFunction) {
                // Player executed command
                if (sender instanceof Player)
                    PlayerUtils.sendInfo((Player) sender, pluginName, getHelpMessage());
                // Console executed command
                else if (sender instanceof ConsoleCommandSender)
                    ConsoleUtils.printInfo(pluginName, getHelpMessage());
            } else
                printSubcommands(sender);
            return;
        }

        // When no sub command was found then call the run method
        if (!runSubCommand(args, sender))
            super.run(args, sender);

    }

    private void printSubcommands(CommandSender sender) {
        // Player executed command
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerUtils.sendMessage(player, ChatColor.GOLD, pluginName, "Possible subcommands:");
            for (AbstractCommand command : getSubCommands())
                PlayerUtils.sendInfo(player, pluginName, command.getHelpMessage());
        }
        // Console executed command
        else if (sender instanceof ConsoleCommandSender) {
            ConsoleUtils.printInfo("Possible subcommands:", pluginName);
            for (AbstractCommand command : getSubCommands())
                ConsoleUtils.printInfo(pluginName, command.getHelpMessage());
        }
    }
    /**
     * Searches for sub commands by comparing the first argument with the syntax
     * of the sub commands.
     * 
     * @param args
     *            The arguments that may contains the syntax
     * @param sender
     *            The command caller
     * @return True when a sub command is found, false if not
     */
    protected boolean runSubCommand(String[] args, CommandSender sender) {
        if (args != null && args.length == 0)
            return false;

        for (AbstractCommand com : subCommands) {
            if (com.getSyntax().equalsIgnoreCase(args[0])) {
                com.run(Arrays.copyOfRange(args, 1, args.length), sender);
                return true;
            }
        }
        return false;
    }

    protected AbstractCommand[] getSubCommands() {
        return subCommands;
    }
}
