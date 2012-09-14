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

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import de.minestar.vincicode.core.VinciCodeCore;

public class MessageHandler {

    private final static boolean vinciCodeEnabled;

    static {
        // LOOK FOR VINCI CODE AT FIRST ACCESS
        Plugin plugin = Bukkit.getPluginManager().getPlugin("VinciCode");
        vinciCodeEnabled = plugin != null && plugin.isEnabled();
    }

    private MessageHandler() {
        // SINGLE TON
    }

    /**
     * Give VinciCode the message to handle
     * 
     * @param message
     *            The message to deliver
     */
    public static void sendMessage(Message message) {
        if (vinciCodeEnabled)
            VinciCodeCore.sendMessage(message);
    }
}
