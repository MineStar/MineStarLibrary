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

import java.io.InputStream;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class BukkitMySQLDatabase extends BukkitDatabase {

    private MySQLDatabase database;

    private static final String CONFIG_HOST = "Host";
    private static final String CONFIG_PORT = "Port";
    private static final String CONFIG_DATABASE = "Database";
    private static final String CONFIG_USERNAME = "Username";
    private static final String CONFIG_PASSWORD = "Password";

    //@formatter:off
    private final static String[] DEFAULT_CONFIG = {
        CONFIG_HOST,
        CONFIG_PORT,
        CONFIG_DATABASE,
        CONFIG_USERNAME,
        CONFIG_PASSWORD,
    };
    //@formatter:on

    public BukkitMySQLDatabase(YamlConfiguration config) {
        super(config);
    }

    public BukkitMySQLDatabase(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String[] getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    @Override
    public void openConnection(YamlConfiguration config) {

        String host = config.getString(CONFIG_HOST);
        String port = config.getString(CONFIG_PORT);
        String databaseName = config.getString(CONFIG_DATABASE);
        String username = config.getString(CONFIG_USERNAME);
        String password = config.getString(CONFIG_PASSWORD);
        if (host == null || port == null || databaseName == null) {
            return;
        }

        database = new MySQLDatabase(host, port, databaseName, username, password);

        if (database != null)
            this.dbConnection = database.dbConnection;

    }

    private final static String DEFAULT_STRUCTURE_NAME = "structure.sql";

    @Override
    public String getDefaultStructureName() {
        return DEFAULT_STRUCTURE_NAME;
    }

    @Override
    public void createStructureIfNeeded(InputStream source) {
        database.createStructureIfNeeded(source);
    }
}
