/*
 * Copyright (C) 2012 MineStar.de 
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

package de.minestar.minestarlibrary.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    /**
     * Sends a simple message without plugin prefix to the player
     * 
     * @param sender
     *            The recipient
     * @param color
     *            Color of message, see {@link org.bukkit.ChatColor ChatColor}
     *            for possible values
     * @param message
     *            The message
     */
    public static void printLine(CommandSender sender, String pluginName, ChatColor color, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.AQUA + pluginName + (pluginName.length() > 0 ? " " : "") + color + message);
        } else {
            sender.sendMessage(pluginName + (pluginName.length() > 0 ? " " : "") + message);
        }
    }

    /**
     * Sends a simple message without plugin prefix to the player
     * 
     * @param player
     *            The recipient
     * @param color
     *            Color of message, see {@link org.bukkit.ChatColor ChatColor}
     *            for possible values
     * @param message
     *            The message
     */
    public static void printLine(Player player, String pluginName, ChatColor color, String message) {
        printLine((CommandSender) player, pluginName, color, message);
    }

    /**
     * Sends a colored message with possible plugin prefix to the sender.
     * 
     * @param sender
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param color
     *            Color of message, see {@link org.bukkit.ChatColor ChatColor}
     *            for possible values
     * @param message
     *            The message
     * 
     */
    public static void printInfo(CommandSender sender, String pluginName, ChatColor color, String message) {
        pluginName = pluginName != null ? pluginName : "";
        printLine(sender, pluginName, color, message);
    }

    /**
     * Sends a colored message with possible plugin prefix to the player.
     * 
     * @param player
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param color
     *            Color of message, see {@link org.bukkit.ChatColor ChatColor}
     *            for possible values
     * @param message
     *            The message
     * 
     */
    public static void printInfo(Player player, String pluginName, ChatColor color, String message) {
        printInfo((CommandSender) player, pluginName, color, message);
    }

    /**
     * Sends a red colored with possible plugin prefix to the sender.
     * 
     * @param sender
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param message
     *            The message
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printError(CommandSender sender, String pluginName, String message) {
        printInfo(sender, pluginName, ChatColor.RED, message);
    }

    /**
     * Sends a red colored with possible plugin prefix to the player.
     * 
     * @param player
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param message
     *            The message
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printError(Player player, String pluginName, String message) {
        printError((CommandSender) player, pluginName, message);
    }

    /**
     * Sends a green colored with possible plugin prefix to the sender.
     * 
     * @param sender
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param message
     *            The message
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printSuccess(CommandSender sender, String pluginName, String message) {
        printInfo(sender, pluginName, ChatColor.GREEN, message);
    }

    /**
     * Sends a green colored with possible plugin prefix to the player.
     * 
     * @param player
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param message
     *            The message
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printSuccess(Player player, String pluginName, String message) {
        printSuccess((CommandSender) player, pluginName, message);
    }

    /**
     * Sends a list of dark red colored examples for commands with possible
     * pluginname to the player
     * 
     * @param sender
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param syntax
     *            Syntax of the commands, beginns normally with a
     *            <code>"/"</code>
     * @param examples
     *            List of examples for the Command
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printWrongSyntax(CommandSender sender, String pluginName, String syntax, String[] examples) {
        ChatUtils.printError(sender, pluginName, "Wrong Syntax! Use: " + syntax);

        if (examples.length == 1)
            ChatUtils.printInfo(sender, pluginName, ChatColor.GRAY, "Example:");
        else if (examples.length > 1)
            ChatUtils.printInfo(sender, pluginName, ChatColor.DARK_RED, "Examples:");

        for (int i = 0; i < examples.length; ++i)
            printInfo(sender, pluginName, ChatColor.GRAY, examples[i]);
    }

    /**
     * Sends a list of dark red colored examples for commands with possible
     * pluginname to the player
     * 
     * @param player
     *            The recipient
     * @param pluginName
     *            Name of the plugin sending the message to the sender. Ignored,
     *            if empty <code>""</code> or <code>null</code>
     * @param syntax
     *            Syntax of the commands, beginns normally with a
     *            <code>"/"</code>
     * @param examples
     *            List of examples for the Command
     * @see #printInfo(Player, String, ChatColor, String)
     */
    public static void printWrongSyntax(Player player, String pluginName, String syntax, String[] examples) {
        printWrongSyntax((CommandSender) player, pluginName, syntax, examples);
    }

    /**
     * Prints an information to the console with the prefix
     * <code>[ PLUGIN ] :</code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printConsoleInfo(String message, String pluginName) {
        if (pluginName == null || pluginName.length() == 0)
            throw new IllegalArgumentException("Pluginname can't be null or empty!");
        System.out.println("[ " + pluginName + " ] : " + message);
    }

    /**
     * Prints an warning to the console with the prefix
     * <code>[ PLUGIN ] : Warning! </code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printConsoleWarning(String message, String pluginName) {
        printConsoleInfo("Warning! " + message, pluginName);
    }

    /**
     * Prints an error to the console with the prefix
     * <code>[ PLUGIN ] : ERROR</code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printConsoleError(String message, String pluginName) {
        printConsoleInfo("ERROR! " + message, pluginName);
    }

    /**
     * Prints an error message with the exception stacktrace to the console with
     * the prefix <code>[ PLUGIN ] : EXCEPTION </code>
     * 
     * @param e
     *            The exeption that was catched!
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printConsoleException(Exception e, String message, String pluginName) {
        printConsoleError("EXCEPTION! " + message, pluginName);
        e.printStackTrace();
    }
}
