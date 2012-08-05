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

package de.minestar.minestarlibrary.stats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import de.minestar.illuminati.IlluminatiCore;

public class StatisticHandler {

    private final static boolean illuminatiEnabled;

    static {

        Plugin plugin = Bukkit.getPluginManager().getPlugin("Illuminati");
        illuminatiEnabled = plugin != null && plugin.isEnabled();
    }

    private StatisticHandler() {
    }

    /**
     * Persist a statistic using Illuminati.
     * 
     * @param statistic
     *            The statistic to send to Illuminati
     */
    public static void handleStatistic(Statistic statistic) {
        if (illuminatiEnabled)
            IlluminatiCore.handleStatistic(statistic);
    }

    /**
     * Register a statistic to Illuminati
     * 
     * @param statistic
     *            A class which implements the the interface Statistic
     */
    public static void registerStatistic(Class<? extends Statistic> statistic) {
        if (illuminatiEnabled)
            IlluminatiCore.registerStatistic(statistic);
    }
}
