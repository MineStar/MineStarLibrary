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

package de.minestar.library.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * A success message when something went ok and as expected
 * 
 */
public class SuccessMessage extends Message {

    /**
     * Shortcut for sending a success message
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
        return new SuccessMessage(pluginName).add(text).send(receiver);
    }

    private final static ChatColor SUCCESS_COLOR = ChatColor.GREEN;

    /**
     * Creates a new success message green colored
     * 
     * @param pluginName
     *            The source of the success
     */
    public SuccessMessage(String pluginName) {
        super(pluginName, Type.SUCCESS);
        color(SUCCESS_COLOR);
    }

}
