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

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public abstract class Database {

    private static final String DEFAULT_CONFIG_NAME = "database.yml";

    protected DatabaseConnection dbConnection;

    public Database(String... args) {
        openConnection(args);
    }

    public Database(YamlConfiguration config) {
        openConnection(config);
    }

    public Database(Plugin plugin) {
        File connectionInfoFile = new File(plugin.getDataFolder(), DEFAULT_CONFIG_NAME);
        openConnection(YamlConfiguration.loadConfiguration(connectionInfoFile));
    }

    public abstract void openConnection(String... args);

    public abstract void openConnection(YamlConfiguration config);

    public abstract String[] getDefaultConfig();

    public void close() {
        if (this.dbConnection != null && this.dbConnection.isOpen())
            this.dbConnection.closeConnection();
    }

    public boolean isAlive() {
        return this.dbConnection != null && this.dbConnection.isOpen();
    }
}
