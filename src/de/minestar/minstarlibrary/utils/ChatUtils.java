/*
 * Copyright (C) 2011 MineStar.de 
 * 
 * This file is part of 'ContaoPlugin'.
 * 
 * 'ContaoPlugin' is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * 'ContaoPlugin' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with 'ContaoPlugin'.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * AUTHOR: GeMoschen
 * 
 */

package de.minestar.minstarlibrary.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    private final static ChatColor COLOR_INFO = ChatColor.AQUA;
    private final static ChatColor COLOR_ERROR = ChatColor.RED;
    private final static ChatColor COLOR_SUCCESS = ChatColor.GREEN;

    // TODO: Insert your plugin name
    private final static String PREFIX = "[ PLUGIN NAME ] : ";

    // PLAYER CHAT

    public static void sendMessage(Player player, ChatColor color, String msg) {
        player.sendMessage(color + msg);
    }

    public static void sendNormalMessage(Player player, String msg) {
        player.sendMessage(msg);
    }

    public static void sendInfo(Player player, String msg) {
        sendMessage(player, COLOR_INFO, msg);
    }

    public static void sendError(Player player, String msg) {
        sendMessage(player, COLOR_ERROR, msg);
    }

    public static void sendSuccess(Player player, String msg) {
        sendMessage(player, COLOR_SUCCESS, msg);
    }

    // CONSOLE COMMANDS
    public static void printConsoleInfo(String msg) {
        System.out.println(PREFIX + msg);
    }

    public static void printConsoleWarning(String msg) {
        printConsoleInfo("Warning! " + msg);
    }

    public static void printConsoleError(String msg) {
        printConsoleInfo("ERROR! " + msg);
    }

    public static void printConsoleException(Exception e, String msg) {
        printConsoleInfo("EXCEPTION! " + msg);
        e.printStackTrace();
    }
}
