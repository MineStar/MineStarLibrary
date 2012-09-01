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

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.minestar.minestarlibrary.utils.ConsoleUtils;

public class PingThread implements Runnable {

    private String pluginName;
    private Connection connection;
    private PreparedStatement pingStatement;

    public PingThread(String pluginName, Connection connection) throws Exception {
        this.pluginName = pluginName;
        this.connection = connection;
        this.pingStatement = connection.prepareStatement("SELECT 1");
    }

    @Override
    public void run() {
        try {
            if (!connection.isClosed())
                pingStatement.execute();
        } catch (Exception e) {
            ConsoleUtils.printException(e, pluginName, "Can't ping! Restart ping thread!");
            try {
                if (!connection.isClosed())
                    this.pingStatement = connection.prepareStatement("SELECT 1");
            } catch (Exception ex) {
                ConsoleUtils.printException(ex, pluginName, "Can't recreate ping thread!");
            }
        }
    }
}
