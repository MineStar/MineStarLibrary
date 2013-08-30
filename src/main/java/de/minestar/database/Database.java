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

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Map;

public abstract class Database {

    protected DatabaseConnection dbConnection;

    private Map<String, PreparedStatement> queries;

    public Database(String... args) {
        openConnection(args);
    }

    public abstract void openConnection(String... args);

    public abstract void createStructureIfNeeded(InputStream source);

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
