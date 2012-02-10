/*
 * Copyright (C) 2011 MineStar.de 
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

package de.minestar.minestarlibrary.database;

import java.sql.Connection;
import java.sql.DriverManager;

import de.minestar.minestarlibrary.utils.ConsoleUtils;

public class DatabaseConnection {

    private Connection con;

    private final String pluginName;

    private DatabaseConnection(String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * Creates a connection to a MySQL Connection.
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param host
     *            Hosting the MySQL Database
     * @param port
     *            Port for MySQL Client
     * @param database
     *            Name of the database
     * @param userName
     *            User with enough permission to access the database
     * @param password
     *            Password for the user. It will deleted by this
     */
    public DatabaseConnection(String pluginName, String host, String port, String database, String userName, String password) {
        this(pluginName);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, userName, password);
        } catch (Exception e) {
            ConsoleUtils.printException(e, "Can't create a MySQL connection! Please check your connection information in the sql.config and your database connection!", pluginName);
        }
        userName = null;
        password = null;
        System.gc();
    }

    /**
     * Creates a connection to a SQLLite database. When the database is not
     * existing in moment of creating a connection to it, a new database will be
     * created
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param folder
     *            Where the database will be stored
     * @param databaseName
     *            Name of the file. Do not use the file ending '.db', it will
     *            added automatically
     */
    public DatabaseConnection(String pluginName, String folder, String databaseName) {
        this(pluginName);
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + folder + "/" + databaseName + ".db");
        } catch (Exception e) {
            ConsoleUtils.printException(e, "Can't create a SQLLite connection to " + folder + "/" + databaseName + ".db! Please check your system rights!", pluginName);
        }
    }

    /**
     * @return Connection to the database to create statements etc.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * @return True, when a connection to the database is existing.
     */
    public boolean hasConnection() {
        return con != null;
    }

    /**
     * Call this method always before deleting the DatabaseConnection object
     */
    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (Exception e) {
            ConsoleUtils.printException(e, "Can't close connection!", pluginName);
            e.printStackTrace();
        }
    }
    @Override
    protected void finalize() throws Throwable {
        // Try to close connection in every case!
        closeConnection();
    }
}
