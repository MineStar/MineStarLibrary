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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.minestar.minstarlibrary.utils.ChatUtils;

/**
 * Represents a command with a varible number of Arguments. The only difference
 * to Command is, that the argumentcount given by the constructor is the
 * argumentcount the command must have atleast!
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public abstract class ExtendedCommand extends Command {

/**
     * Creates an ExtendedCommand, which can have more arguments than given
     * 
     * @param syntax
     *            When this is a subCommand, do not use a / . If not, use a / at
     *            first
     * @param arguments
     *            The minimum count of arguments. Use "<" to start an argument
     *            and ">" to finish it! Without that the plugin can't see how
     *            many arguments are necessary!
     * @param node The permission node to use this command. When empty, everyone can use the command!
     */
    public ExtendedCommand(String syntax, String arguments, String node) {
        super(syntax, arguments, node);
    }

/**
     * Creates an ExtendedCommand, which can have more arguments than given
     * 
     * @param syntax 
     *            When this is a subCommand, do not use a / . If not, use a / at
     *            first
     * @param arguments
     *            The minimum count of arguments. Use "<" to start an argument
     *            and ">" to finish it! Without that the plugin can't see how
     *            many arguments are necessary!
     * @param node The permission node to use this command. When empty, everyone can use the command!
     */
    public ExtendedCommand(String pluginName, String syntax, String arguments, String node) {
        super(pluginName, syntax, arguments, node);
    }

    public abstract void execute(String[] args, CommandSender sender);

    @Override
    public void run(String[] args, CommandSender sender) {
        if (!super.hasRights(sender)) {
            ChatUtils.printError(sender, pluginName, NO_RIGHT);
            return;
        }

        if (!this.hasCorrectSyntax(args)) {
            ChatUtils.printInfo(sender, pluginName, ChatColor.GRAY, getHelpMessage());
            return;
        }

        execute(args, sender);
    }

    @Override
    protected boolean hasCorrectSyntax(String[] args) {
        return args.length >= super.getArgumentCount();
    }

}
