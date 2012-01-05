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
import org.bukkit.entity.Player;

public class PlayerUtils {

    // Used for binary search
    private static Comparator<OfflinePlayer> c = new Comparator<OfflinePlayer>() {
        @Override
        public int compare(OfflinePlayer o1, OfflinePlayer o2) {
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
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

    /**
     * Searching for a player having the case insensitive name. If not found,
     * the first player that contains the name is returned.
     * 
     * @param name
     *            The name of the player
     * @return Null if no player who contains the name or have the case
     *         insensitive name
     */
    public static Player getOnlinePlayer(String name) {

        Player[] onlinePlayer = Bukkit.getOnlinePlayers();

        Player result = null;
        int delta = Integer.MAX_VALUE;
        int curDelta = Integer.MAX_VALUE;
        String tempName = "";
        name = name.toLowerCase();

        for (Player player : onlinePlayer) {

            tempName = player.getName().toLowerCase();
            curDelta = tempName.length() - name.length();
            if (tempName.startsWith(name) && curDelta < delta) {
                delta = curDelta;
                result = player;
            } else {
                tempName = player.getDisplayName().toLowerCase();
                curDelta = tempName.length() - name.length();
                if (tempName.startsWith(name) && curDelta < delta) {
                    delta = curDelta;
                    result = player;
                }
            }
            if (delta == 0)
                return result;
        }

        return result;
    }
}
