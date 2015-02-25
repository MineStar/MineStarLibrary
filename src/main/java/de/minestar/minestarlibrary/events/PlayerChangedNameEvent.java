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

public class PlayerChangedNameEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final String UUID;
    private final String oldName;
    private final String newName;
    private final String commandSender;

    public PlayerChangedNameEvent(String UUID, String oldName, String newName, String commandSender) {
        this.UUID = UUID;
        this.oldName = oldName;
        this.newName = newName;
        this.commandSender = commandSender;
    }

    /**
     * @return the players UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @return the old playername
     */
    public String getOldName() {
        return oldName;
    }

    public String getCommandSender() {
        return commandSender;
    }

    /**
     * @return the new playername
     */
    public String getNewName() {
        return newName;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
