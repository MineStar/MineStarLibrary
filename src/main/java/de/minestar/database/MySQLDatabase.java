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

import java.sql.Connection;
import java.sql.DriverManager;

import org.bukkit.configuration.file.YamlConfiguration;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public class MySQLDatabase extends Database {

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

    @Override
    public void openConnection(String... args) {
        try {
            dbConnection = new MySQLDatabaseConnection(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void openConnection(YamlConfiguration config) {

        String host = config.getString(CONFIG_HOST);
        String port = config.getString(CONFIG_PORT);
        String databaseName = config.getString(CONFIG_DATABASE);
        String username = config.getString(CONFIG_USERNAME);
        String password = config.getString(CONFIG_PASSWORD);

        if (host == null || port == null || databaseName == null)
            return;
        if (username == null && password == null) {
            openConnection(host, port, databaseName);
        } else {
            openConnection(host, port, databaseName, username, password);
        }

    }

    @Override
    public String[] getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    private class MySQLDatabaseConnection extends DatabaseConnection {

        public MySQLDatabaseConnection(String... args) throws Exception {
            super(args);
        }

        @Override
        protected Connection openConnection(String... args) throws Exception {

            int argc = args.length;
            switch (argc) {
            // Create Connection without security information
                case 3 :
                    return createUnauthorizedConnection(args[0], args[1], args[2]);
                    // Create Connection with security information
                case 5 :
                    return createAuthorizedConnection(args[0], args[2], args[3], args[4], args[5]);
                    // Wrong count of arguments
                default :
                    throw new WrongNumberArgsException("3 or 5 arguments expected!");
            }

        }

        private Connection createUnauthorizedConnection(String host, String port, String databaseName) throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?autoReconnect=true");
            return con;
        }

        private Connection createAuthorizedConnection(String host, String port, String databaseName, String username, String password) throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?autoReconnect=true", username, password);
            return con;
        }

    }
}
