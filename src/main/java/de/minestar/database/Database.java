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

package de.minestar.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import de.minestar.minestarlibrary.message.DebugMessage;
import de.minestar.minestarlibrary.message.Message;

public abstract class Database {

    private static final String DEFAULT_CONFIG_NAME = "database.yml";

    protected DatabaseConnection dbConnection;

    private Map<String, PreparedStatement> queries;

    public Database(String... args) {
        openConnection(args);
    }

    public Database(YamlConfiguration config) {
        openConnection(config);
    }

    public Database(Plugin plugin) {
        File connectionInfoFile = new File(plugin.getDataFolder(), DEFAULT_CONFIG_NAME);
        openConnection(YamlConfiguration.loadConfiguration(connectionInfoFile));
        if (isAlive()) {
            loadQueries(plugin);
        } else {
            createDefaultConfig(plugin);
        }
    }

    public abstract void openConnection(String... args);

    public abstract void openConnection(YamlConfiguration config);

    public abstract String[] getDefaultConfig();

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

    public void close() {
        if (this.dbConnection != null && this.dbConnection.isOpen())
            this.dbConnection.closeConnection();
    }

    public boolean isAlive() {
        return this.dbConnection != null && this.dbConnection.isOpen();
    }

    public void prepareStatement(String name, String query) throws Exception {
        PreparedStatement statement = this.dbConnection.getConnection().prepareStatement(query);
        this.queries.put(name, statement);
    }

    public PreparedStatement getQuery(String name) {
        return queries.get(name);
    }
}
