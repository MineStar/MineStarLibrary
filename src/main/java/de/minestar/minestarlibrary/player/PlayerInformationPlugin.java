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

package de.minestar.minestarlibrary.player;

import java.util.List;

/**
 * Plugin which hold interesting, displayable information about a certain player
 * 
 */
public interface PlayerInformationPlugin {

    /**
     * Retrieve interesting, displayable information about a player which has
     * the
     * 
     * @param playerName
     *            The name of the player, without any limition to capitalize
     * @return A list(can be empty) containing single lines of displayable
     *         information
     */
    public List<String> getInformation(String playerName);

}
