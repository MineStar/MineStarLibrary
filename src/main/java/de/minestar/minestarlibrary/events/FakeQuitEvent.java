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

package de.minestar.minestarlibrary.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Thrown when player fake a quit
 * 
 * @author Meldanor
 * 
 */
public class FakeQuitEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String quitMessage;

    /**
     * Fakes a players quit
     * 
     * @param player
     *            The player who fakes
     */
    public FakeQuitEvent(Player player) {
        this.player = player;
        this.quitMessage = player.getName() + " left the game.";
    }

    /**
     * Fakes a players quit
     * 
     * @param player
     *            The player who fakes
     * @param quitMessage
     *            The message which shall broadcasted
     */
    public FakeQuitEvent(Player player, String quitMessage) {
        this.player = player;
        this.quitMessage = quitMessage;
    }

    /**
     * @return The player who fakes
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param quitMessage
     *            The message which shall broadcasted
     */
    public String getQuitMessage() {
        return quitMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
