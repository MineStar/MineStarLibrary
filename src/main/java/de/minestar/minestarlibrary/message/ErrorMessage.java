/*
 * Copyright (C) 2013 MineStar.de 
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

package de.minestar.minestarlibrary.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * An error message for information when something went terrible wrong.
 */
public class ErrorMessage extends Message {

    /**
     * Shortcut for sending an error message
     * 
     * @param text
     *            The text to send
     * @param pluginName
     *            The name of the plugin the message is from
     * @param receiver
     *            List of receiver, for console use {@link Message#CONSOLE} as
     *            an argument
     * @return Created message
     */
    public static Message send(String text, String pluginName, CommandSender... receiver) {
        return new ErrorMessage(pluginName).add(text).send(receiver);
    }

    private final static ChatColor ERROR_COLOR = ChatColor.RED;

    /**
     * Create a new error message colored red
     * 
     * @param pluginName
     *            The source of the error
     */
    public ErrorMessage(String pluginName) {
        super(pluginName, Type.SUCCESS);
        color(ERROR_COLOR);
    }
}
