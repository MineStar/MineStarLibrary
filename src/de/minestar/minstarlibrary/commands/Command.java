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
import org.bukkit.entity.Player;

import com.bukkit.gemo.utils.UtilPermissions;

/**
 * Represents a command with a fixed number of arguments and no subcommands. If
 * you want a command with at least number of arguments, use ExtendedCommand. If
 * you want a command which has a function/or not AND subcommands, use
 * SuperCommand.
 * 
 * You have to register the command by creating a command list
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public abstract class Command {

    public final static String NO_RIGHT = "You musn't use this command!";

    // Add this in every command to add an description
    protected String description = "";
    // Example : /warp create
    private String syntax;
    // Example : <Name>
    private String arguments;
    // Example : contao.create
    protected String permissionNode;

    private final int argumentCount;

    /**
     * Just call super() in the inhertited classes. <br>
     * 
     * @param syntax
     *            Example : /warp create
     * @param arguments
     *            Example : <Name>
     * @param node
     *            Example : contao.create
     */
    public Command(String syntax, String arguments, String node) {
        this.syntax = syntax;
        this.arguments = arguments;
        this.permissionNode = node;
        this.argumentCount = countArguments();
    }

    /**
     * Call this command to run it functions. It checks at first whether the
     * player has enough rights to use this. Also it checks whether it uses the
     * correct snytax. If both is correct, the real function of the command is
     * called
     * 
     * @param args
     *            The arguments of this command
     * @param player
     *            The command caller
     */
    public void run(String[] args, Player player) {
        if (!hasRights(player)) {
            player.sendMessage(NO_RIGHT);
            return;
        }

        if (!hasCorrectSyntax(args)) {
            player.sendMessage(getHelpMessage());
            return;
        }

        execute(args, player);
    }

    /**
     * Implement the effect of the command
     * 
     * @param args
     *            The arguments of the command
     * @param player
     *            The command caller
     */
    public abstract void execute(String[] args, Player player);

    /**
     * @param player
     *            The command caller
     * @return True when the player has enough rights to use the command Or the permissionnode is empty, so everybody can use it
     */
    protected boolean hasRights(Player player) {
        return permissionNode.length() == 0
                || UtilPermissions.playerCanUseCommand(player,
                        getPermissionNode());
    }

    /**
     * Compares the count of arguments has and the count of arguments the
     * command should have.
     * 
     * @param args
     *            The arguments of the command given by the command caller
     * @return True when both number of arugments are equal
     */
    protected boolean hasCorrectSyntax(String[] args) {
        return args.length == argumentCount;
    }

    /**
     * @return The description of the Command, useful for help
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Syntax + Arguments + Description
     */
    public String getHelpMessage() {
        return ChatColor.BLUE + getSyntax() + " " + getArguments() + " "
                + getDescription();
    }

    /**
     * @return The syntax (or label) of the Command
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * @return The arguments in one string. Every argument is labeld in '<' and '>'
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * @return The permission node, like contao.create
     */
    public String getPermissionNode() {
        return permissionNode;
    }

    /**
     * @return The number of possible arguments
     */
    public int getArgumentCount() {
        return argumentCount;
    }

    /**
     * @return The number of '<' in the argument String
     */
    private int countArguments() {

        if (arguments.isEmpty())
            return 0;

        int counter = 0;
        for (int i = 0; i < arguments.length(); ++i)
            if (arguments.charAt(i) == '<')
                ++counter;
        return counter;
    }
}
