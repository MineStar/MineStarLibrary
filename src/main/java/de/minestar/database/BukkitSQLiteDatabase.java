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

public class BukkitSQLiteDatabase extends BukkitDatabase {

    private SQLiteDatabase database;

    private static final String CONFIG_FILE = "DatabaseFile";

    //@formatter:off
    private final static String[] DEFAULT_CONFIG = {
        CONFIG_FILE
    };
    //@formatter:on

    public BukkitSQLiteDatabase(YamlConfiguration config) {
        super(config);
    }

    public BukkitSQLiteDatabase(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void openConnection(YamlConfiguration config) {

        String file = config.getString(CONFIG_FILE);

        if (file == null)
            return;
        else {
            openConnection(file);
        }
    }

    @Override
    public String[] getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    @Override
    public void openConnection(String... args) {
        database = new SQLiteDatabase(args);
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
