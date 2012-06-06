/*
 * Copyright (C) 2012 MineStar.de 
 * 
 * This file is part of Contao2.
 * 
 * Contao2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * Contao2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Contao2.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangedGroupEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final String playerName;
    private final String oldGroupName;
    private final String newGroupName;

    public PlayerChangedGroupEvent(String playerName, String oldGroupName, String newGroupName) {
        this.playerName = playerName;
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the oldGroupName
     */
    public String getOldGroupName() {
        return oldGroupName;
    }

    /**
     * @return the newGroupName
     */
    public String getNewGroupName() {
        return newGroupName;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
