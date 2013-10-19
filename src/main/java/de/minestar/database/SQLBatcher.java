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
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class SQLBatcher {

    private Connection databaseConnection;

    private boolean oldCommitStatus = false;

    public SQLBatcher(Connection connection) {
        this.databaseConnection = connection;
    }

    public void run(InputStream source, boolean autocommit) throws Exception {
        this.batch(new BufferedReader(new InputStreamReader(source)), autocommit);
    }

    public void run(File source, boolean autocommit) throws Exception {
        this.batch(new BufferedReader(new FileReader(source)), autocommit);
    }

    private void batch(BufferedReader source, boolean autocommit) throws Exception {
        oldCommitStatus = databaseConnection.getAutoCommit();
        databaseConnection.setAutoCommit(autocommit);

        StringBuilder query = new StringBuilder(512);

        String line = "";
        boolean multiLineComment = false;
        Statement statement = databaseConnection.createStatement();
        while ((line = source.readLine()) != null) {
            // Ignore single line comments
            if (line.isEmpty() || line.startsWith("#") || line.startsWith("--"))
                continue;
            // Ignore multiline comments
            if (!multiLineComment && line.startsWith("/*")) {
                multiLineComment = true;
                continue;
            }
            // End of a multiline comment
            if (multiLineComment && line.startsWith("*/")) {
                multiLineComment = false;
            }
            // Add Query to buffer
            query.append(line);
            // Query end - add to batch and reset buffer
            if (line.endsWith(";")) {
                statement.addBatch(query.toString());
                query.setLength(0); // Reset StringBuilder
            }

        }

        // Execute the complete batch
        try {
            statement.executeBatch();
            if (autocommit)
                databaseConnection.commit();
        } catch (Exception e) {
            if (autocommit)
                databaseConnection.rollback();

        }
        // Restore the old commit status
        databaseConnection.setAutoCommit(oldCommitStatus);
    }
}
