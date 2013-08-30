/*
 * Copyright (C) 2013 MineStar.de 
 * 
 * This file is part of MinestarLibrary.
 * 
 * MinestarLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MinestarLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MinestarLibrary.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import de.minestar.library.message.DebugMessage;
import de.minestar.library.message.Message;

public abstract class BukkitDatabase extends Database {

    private static final String DEFAULT_CONFIG_NAME = "database.yml";

    public BukkitDatabase(YamlConfiguration config) {
        super();
        openConnection(config);
    }

    public BukkitDatabase(YamlConfiguration databaseConfig, InputStream structureSource) {
        super();
        openConnection(databaseConfig);
        createStructureIfNeeded(structureSource);
    }

    public BukkitDatabase(Plugin plugin) {
        super();
        File connectionInfoFile = new File(plugin.getDataFolder(), DEFAULT_CONFIG_NAME);
        this.openConnection(YamlConfiguration.loadConfiguration(connectionInfoFile));
        if (isAlive()) {
            createStructureIfNeeded(plugin.getResource(DEFAULT_CONFIG_NAME));
            loadQueries(plugin);
        } else {
            createDefaultConfig(plugin);
        }
    }

    @Override
    public void openConnection(String... args) {
        // Do Nothing
    }

    public abstract void openConnection(YamlConfiguration config);

    public abstract String[] getDefaultConfig();

    public abstract String getDefaultStructureName();

    private void loadQueries(Plugin plugin) {

        QueryLoader loader = new YMLQueryLoader();
        InputStream queries = plugin.getResource(loader.getDefaultFileName());
        if (queries != null) {
            Map<String, String> rawQueries = loader.loadQueries(queries);
            for (Entry<String, String> entry : rawQueries.entrySet()) {
                try {
                    prepareStatement(entry.getKey(), entry.getValue());
                } catch (SQLException e) {
                    DebugMessage.send("Query creation failed: Name:" + entry.getKey(), Message.CONSOLE);
                    DebugMessage.send(e.toString(), Message.CONSOLE);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createDefaultConfig(Plugin plugin) {

        YamlConfiguration defaultConfig = new YamlConfiguration();
        for (String config : getDefaultConfig())
            defaultConfig.set(config, "");
        File defaultConfigFile = new File(plugin.getDataFolder(), DEFAULT_CONFIG_NAME);
        try {
            defaultConfig.save(defaultConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
