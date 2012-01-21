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

import de.minestar.minestarlibrary.utils.ChatUtils;

/**
 * Class to support commands with sub commands
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public abstract class SuperCommand extends Command {

    private Command[] subCommands;
    private boolean hasFunction;
    private String pluginName = "";

    public SuperCommand(String syntax, String arguments, String node, boolean hasFunction, Command... subCommands) {
        super(syntax, arguments, node);
        this.hasFunction = hasFunction;
        this.subCommands = subCommands;
    }

    public SuperCommand(String pluginName, String syntax, String arguments, String node, boolean hasFunction, Command... subCommands) {
        this(syntax, arguments, node, hasFunction, subCommands);
        if (pluginName != null) {
            this.pluginName = pluginName;
        }
    }

    public SuperCommand(String syntax, String arguments, String node, Command... subCommands) {
        this(syntax, arguments, node, false, subCommands);
    }

    @Override
    public void run(String[] args, CommandSender sender) {
        if (args.length == 0) {
            if (hasFunction)
                ChatUtils.printInfo(sender, pluginName, ChatColor.GRAY, getHelpMessage());
            else
                printSubcommands(sender);
            return;
        }

        if (!runSubCommand(args, sender))
            super.run(args, sender);

    }

    private void printSubcommands(CommandSender sender) {
        ChatUtils.printInfo(sender, pluginName, ChatColor.GOLD, "Possible subcommands:");
        for (Command command : getSubCommands())
            ChatUtils.printLine(sender, pluginName, ChatColor.GRAY, command.getHelpMessage());
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

        for (Command com : subCommands) {
            if (com.getSyntax().equalsIgnoreCase(args[0])) {
                com.run(Arrays.copyOfRange(args, 1, args.length), sender);
                return true;
            }
        }
        return false;
    }

    protected Command[] getSubCommands() {
        return subCommands;
    }
}
