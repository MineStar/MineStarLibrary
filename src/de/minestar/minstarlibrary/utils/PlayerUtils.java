/*
 * Copyright (C) 2011 MineStar.de 
 * 
 * This file is part of ContaoPlugin.
 * 
 * ContaoPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * ContaoPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ContaoPlugin.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minstarlibrary.utils;

import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;

public class PlayerUtils {

    // Used for binary search
    private static Comparator<OfflinePlayer> c = new Comparator<OfflinePlayer>() {
        @Override
        public int compare(OfflinePlayer o1, OfflinePlayer o2) {
            return o1.getName().toLowerCase()
                    .compareTo(o2.getName().toLowerCase());
        }
    };

    /**
     * Use binary search to find the account name of the player.
     * 
     * @param name
     *            Name of the player. Must be complete, but isn't case sensitive
     * @return Name of the player case sensitive. If player is not existing,
     *         return null
     */
    public static String getOfflinePlayerName(String name) {
        Server server = Bukkit.getServer();
        // get all players in a sorted , ascending list
        OfflinePlayer[] existingPlayer = server.getOfflinePlayers();
        // creates a OfflinePlayer object ignoring player is existing or not
        OfflinePlayer target = server.getOfflinePlayer(name.toLowerCase());
        // returns -1 when player is not found
        int i = Arrays.binarySearch(existingPlayer, target, c);
        return i >= 0 ? existingPlayer[i].getName() : null;
    }
}
