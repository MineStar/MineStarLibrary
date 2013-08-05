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

package de.minestar.message;

import org.bukkit.ChatColor;

/**
 * A colored chat message
 */
public class ColoredMessage extends Message {

    /**
     * Creates a colored message which text is colored with the given color
     * 
     * @param color
     *            The color
     */
    public ColoredMessage(ChatColor color) {
        super(Type.INFO);
        color(color);
    }

    /**
     * Creates a colored message starting with a plugin name prefix (uncolored)
     * and after this the colored message
     * 
     * @param pluginName
     *            The uncolored plugin prefix
     * @param color
     *            The color
     */
    public ColoredMessage(String pluginName, ChatColor color) {
        super(pluginName, Type.INFO);
        color(color);
    }
}
