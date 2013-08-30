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

public class SQLiteDatabase extends Database {

    public SQLiteDatabase(String... args) {
        super(args);
    }

    @Override
    public void openConnection(String... args) {
        try {
            dbConnection = new SQLiteDatabaseConnection(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class SQLiteDatabaseConnection extends DatabaseConnection {

        public SQLiteDatabaseConnection(String... args) throws Exception {
            super(args);
        }

        @Override
        protected Connection openConnection(String... args) throws Exception {

            int argc = args.length;
            switch (argc) {
                case 1 :
                    return createConnection(args[0]);
                default :
                    throw new WrongNumberArgsException("1 argument expected!");
            }

        }

        private Connection createConnection(String fileName) throws Exception {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:" + fileName + ".db");
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
