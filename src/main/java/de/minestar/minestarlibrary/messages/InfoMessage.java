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

package de.minestar.minestarlibrary.messages;

public class InfoMessage extends Message {

    /**
     * Create an info message. This is normally used by plugins
     * 
     * @param pluginName
     *            The name of the plugin sending the message
     * @param receiver
     *            The receiver
     * @param text
     *            The text
     */
    public InfoMessage(String pluginName, String receiver, String text) {
        super(pluginName, receiver, text, MessageType.INFO);
    }

}
