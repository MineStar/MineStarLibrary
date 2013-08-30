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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.common.begin.clinton.ScriptRunner;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public class MySQLDatabase extends Database {

    public MySQLDatabase(String... args) {
        super(args);
    }

    @Override
    public void openConnection(String... args) {
        try {
            dbConnection = new MySQLDatabaseConnection(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                    // Ignore null arguments
                    if (args[3] == null || args[4] == null)
                        return createUnauthorizedConnection(args[0], args[1], args[2]);
                    else
                        return createAuthorizedConnection(args[0], args[1], args[2], args[3], args[4]);
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

    @Override
    public void createStructureIfNeeded(InputStream source) {
        if (source == null)
            return;
        // Thanks
        // http://stackoverflow.com/questions/1044194/running-a-sql-script-using-mysql-with-jdbc/1044837#1044837
        // for this hint
        BufferedReader bReader = new BufferedReader(new InputStreamReader(source));
        ScriptRunner runner = new ScriptRunner(this.dbConnection.getConnection(), true, true);
        try {
            runner.runScript(bReader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
