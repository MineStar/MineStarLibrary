/*
 * Copyright (C) 2012 MineStar.de 
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

import java.io.File;
import java.sql.Connection;

import de.minestar.minestarlibrary.utils.ConsoleUtils;

/**
 * A super class for all database handler which has the same base structure like
 * establishing a database connection, check the structure and creating
 * statements.
 * 
 * @author Meldanor
 * 
 */
public abstract class AbstractDatabaseHandler {

    /**
     * The connection to the database you want to
     */
    protected DatabaseConnection dbConnection;

    /**
     * Creates a new DatabaseHandler and do the followning things: <br>
     * 1. Create a database connection depending on your implementation of
     * <code>createConnection</code> <br>
     * 2. Create a basic database structure like creating tables. This can be
     * done by reading a sql file or executing some single statements Depends on
     * your implementation of <code>createStructure</code> <br>
     * 3. Initiate Prepare Statements depending on your implementation of
     * <code>createStatements</code>
     * 
     * @param pluginName
     *            The name of the plugin using the class
     * @param dataFolder
     *            The datafolder of the plugin
     */
    public AbstractDatabaseHandler(String pluginName, File dataFolder) {
        try {
            init(pluginName, dataFolder);
        } catch (Exception e) {
            ConsoleUtils.printException(e, pluginName, "Can't initiate the database!");
        }
    }

    /**
     * Initiate the database handler calling the functions
     * <code>createConnection, createStructure and createStatements</code>
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param dataFolder
     *            The datafolder of the plugin
     * @throws Exception
     */
    private void init(String pluginName, File dataFolder) throws Exception {
        dbConnection = createConnection(pluginName, dataFolder);
        if (dbConnection != null) {
            createStructure(pluginName, dbConnection.getConnection());
            updateDatabase(pluginName, dbConnection.getConnection(), dataFolder);
            createStatements(pluginName, dbConnection.getConnection());
        } else
            ConsoleUtils.printError(pluginName, "Can't initiate the database structure and statements because of missing connection!");

    }
    /**
     * This methods establish a connection to your database. The dataFolder
     * object can be used to read a config(which is highly recommended)
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param dataFolder
     *            The datafolder of the plugin
     * @return The database connection object which is automatically assigned to
     *         the private variable <code>dbConnection</code>
     */
    protected abstract DatabaseConnection createConnection(String pluginName, File dataFolder) throws Exception;

    /**
     * Method for establishing a basis table structure of the database
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param con
     *            The connection to the database
     */
    protected abstract void createStructure(String pluginName, Connection con) throws Exception;

    /**
     * Method for initiating prepare statements
     * 
     * @param pluginName
     *            Name of the plugin which uses the class
     * @param con
     *            The connection to the database
     */
    protected abstract void createStatements(String pluginName, Connection con) throws Exception;

    /**
     * Override this method to import an update. Use this to import updates from
     * a SQL Batch file, which will only executed once. Remember to delete the
     * update.sql, it will loaded after reload again!
     * 
     * @param pluginName
     *            Name of plugin
     * @param con
     *            Connection to database
     * @param dataFolder
     *            Plugin folder. Recommended to place the batch file here
     */
    public void updateDatabase(String pluginName, Connection con, File dataFolder) throws Exception {

    }

    /**
     * Close the connection to the database. Call this method in "onDisable"
     * method
     */
    public void closeConnection() {
        if (dbConnection != null)
            dbConnection.closeConnection();
    }

    /**
     * @return <code>True</code> when there is a database connection, otherwise
     *         <code>false</code>
     */
    public boolean hasConnection() {
        return dbConnection != null;
    }

}
