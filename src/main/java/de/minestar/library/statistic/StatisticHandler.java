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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import de.minestar.library.message.Message;
import de.minestar.library.message.WarningMessage;

/**
 * Class to simplify storing statistics. Create one per plugin, will search for
 * the statistic plugins for itself and store them using
 * {@link #addStatistic(String, Object...)}
 */
public class StatisticHandler {

    private static List<StatisticPlugin> statPlugins;

    // Search for plugins which implements the interface
    static {
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        statPlugins = new ArrayList<StatisticPlugin>();
        for (Plugin plugin : plugins) {
            if (plugin instanceof StatisticPlugin && plugin.isEnabled()) {
                statPlugins.add((StatisticPlugin) plugin);
            }
        }
        if (statPlugins.isEmpty())
            new WarningMessage("StatisticPlugin").add("No statistic plugins found, no statistic will be collected!").send(Message.CONSOLE);
    }

    private Plugin plugin;

    /**
     * Creates a statistic handler and register all statistics from the plugin
     * to all statistic handler
     * 
     * @param plugin
     *            The plugin whiches to register
     */
    public StatisticHandler(Plugin plugin) {
        for (StatisticPlugin statPlugin : statPlugins) {
            statPlugin.registerStatistics(plugin);
        }
        this.plugin = plugin;
    }

    /**
     * Store a statistic. How dependens on the statistic plugin. If no statistic
     * plugin was found , this will take no effect
     * 
     * @param statisticName
     *            The name of the statistic
     * @param data
     *            The data of the statistic
     */
    public void addStatistic(String statisticName, Object... data) {
        for (StatisticPlugin statPlugin : statPlugins) {
            statPlugin.storeStatistic(plugin, statisticName, data);
        }
    }

}
