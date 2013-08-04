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
 * A warning message when something went not as expected but nothing special
 */
public class WarningMessage extends Message {

    private final static ChatColor WARNING_COLOR = ChatColor.GOLD;

    /**
     * Creates a new warning message golden colored
     * 
     * @param pluginName
     *            The source of the warning
     */
    public WarningMessage(String pluginName) {
        super(pluginName, Type.WARNING);
        add(WARNING_COLOR);
    }
}
