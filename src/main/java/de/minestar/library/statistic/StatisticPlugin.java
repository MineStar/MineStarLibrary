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

package de.minestar.library.statistic;

import org.bukkit.plugin.Plugin;

/**
 * Every plugin want to store and handle statistic have to implement this
 * interface. There can be multiple plugins with this interface , everyone gets
 * the necessary information
 */
public interface StatisticPlugin {

    /**
     * Register statistics based on information in the plugin. For example an
     * XML document descriping the statistics of the plugin is stored in the
     * plugins archive
     * 
     * @param plugin
     *            The plugin to store statstics about
     */
    public void registerStatistics(Plugin plugin);

    /**
     * Handle and store a single statistic
     * 
     * @param plugin
     *            The plugin to store the statistic is about
     * @param statisticName
     *            The name of the plugin. The name must be unique in the plugin
     * @param data
     *            The data of the statistic
     */
    public void storeStatistic(Plugin plugin, String statisticName, Object... data);
}
