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

import org.bukkit.command.CommandSender;

/**
 * A debug message for useful information while developing and testing. This
 * messages do nothing on a live system!
 * 
 */
public class DebugMessage extends Message {

    /**
     * A shortcut for creating a new debug message and adding the message and
     * send it to the console
     * 
     * @param message
     *            The message to debug
     */
    public static void debug(String message) {
        new DebugMessage().add(message).send(CONSOLE);
    }

    /**
     * Create a new debug message
     */
    public DebugMessage() {
        super("DEBUG", Type.INFO);
    }

    // TODO: Implement debug option
    @Override
    public Message send(CommandSender... receiver) {
        // IF IS IN DEBUG MODE
        return super.send(receiver);
    }
}
