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

package de.minestar.minstarlibrary.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Class to support commands with sub commands
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public abstract class SuperCommand extends Command {

    private Command[] subCommands;
    private boolean hasFunction;

    public SuperCommand(String syntax, String arguments, String node,
            boolean hasFunction, Command... subCommands) {
        super(syntax, arguments, node);
        this.hasFunction = hasFunction;
        this.subCommands = subCommands;
    }

    public SuperCommand(String syntax, String arguments, String node,
            Command... subCommands) {
        this(syntax, arguments, node, false, subCommands);
    }

    @Override
    public abstract void execute(String[] args, Player player);

    @Override
    public void run(String[] args, Player player) {

        if (args.length == 0) {
            if (hasFunction)
                player.sendMessage(getHelpMessage());
            else
                printSubcommands(player);
            return;
        }

        if (!runSubCommand(args, player))
            super.run(args, player);

    }

    private void printSubcommands(Player player) {

        player.sendMessage(ChatColor.GOLD + "Possible sub commands");
        for (Command command : getSubCommands())
            player.sendMessage(command.getHelpMessage());
    }

    /**
     * Searches for sub commands by comparing the first argument with the syntax
     * of the sub commands.
     * 
     * @param args
     *            The arguments that may contains the syntax
     * @param player
     *            The command caller
     * @return True when a sub command is found, false if not
     */
    protected boolean runSubCommand(String[] args, Player player) {

        if (args != null && args.length == 0)
            return false;

        for (Command com : subCommands) {
            if (com.getSyntax().equalsIgnoreCase(args[0])) {
                com.run(Arrays.copyOfRange(args, 1, args.length), player);
                return true;
            }
        }
        return false;
    }

    protected Command[] getSubCommands() {
        return subCommands;
    }
}
