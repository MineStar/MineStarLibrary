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

package de.minestar.library.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Class for retrieving information about an player, which is given by plugins
 * implementing {@link PlayerInformationPlugin}.
 * 
 */
public class PlayerInformationGainer {

    private List<PlayerInformationPlugin> plugins;

    /**
     * Construct a helper object to get information about a player. It will
     * search for all enabled plugins implementing
     * {@link PlayerInformationPlugin} <br>
     * It is important to construct this object <b>NOT</b> in the start up
     * phase, some plugins can have information but not loaded yet.
     */
    public PlayerInformationGainer() {
        this.searchForPlugins();
    }

    private void searchForPlugins() {
        plugins = new ArrayList<PlayerInformationPlugin>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.isEnabled() && plugin instanceof PlayerInformationPlugin) {
                this.plugins.add((PlayerInformationPlugin) plugin);
            }
        }
    }

    /**
     * Ask all plugins implementing {@link PlayerInformationPlugin} for
     * information about the player and collect them in a by plugin ordered list
     * 
     * @param source
     *            The source asking for information
     * @param playerName
     *            The requested information object
     * @return A list of strings ordered by plugin (in plugin intertall ordered
     *         by plugin himself)
     */
    public List<String> getInformationAbout(CommandSender source, String playerName) {
        List<String> information = new ArrayList<String>();
        for (PlayerInformationPlugin plugin : plugins) {
            information.addAll(plugin.getInformationAbout(source, playerName));
        }
        return information;
    }
}
