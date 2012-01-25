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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

import de.minestar.minestarlibrary.utils.ChatUtils;

public class DatabaseUtils {

    /**
     * Read a <code>*.sql</code> batch containing SQL statements. This methods
     * reading and executing them and creats a database structure.
     * 
     * @param bReader
     *            The BufferedReader of the resource
     * @param con
     *            The connection to the database
     * @throws Exception
     *             When file cannot read or statement has wrong syntax
     */
    private static void createStructure(BufferedReader bReader, Connection con, String pluginName) throws Exception {
        ChatUtils.printConsoleInfo("Start importing database structure", pluginName);
        String line = "";
        StringBuilder sBuilder = new StringBuilder(500);
        while ((line = bReader.readLine()) != null) {
            // ignore empty lines and comments
            if (line.startsWith("#") || line.startsWith("-") || line.isEmpty())
                continue;

            sBuilder.append(line);
            if (line.endsWith(";")) {
                con.createStatement().execute(sBuilder.toString());
                // reset the string builder
                sBuilder.setLength(0);
            }
        }
        bReader.close();
        ChatUtils.printConsoleInfo("Finished importing database structure", pluginName);
    }
    /**
     * Read a <code>*.sql</code> batch containing SQL statements. This methods
     * reading and executing them and creats a database structure.
     * 
     * @param ressource
     *            The InputStream to the file in the <code>*.jar</code> which
     *            can get by <br>
     *            <code>getClass().getResourceAsStream()</code>
     * @param con
     *            The connection to the database
     * @throws Exception
     *             When file cannot read or statement has wrong syntax
     */
    public static void createStructure(InputStream ressource, Connection con, String pluginName) throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(ressource));
        createStructure(bReader, con, pluginName);
    }

    /**
     * Read a <code>*.sql</code> batch containing SQL statements. This methods
     * reading and executing them and creats a database structure.
     * 
     * @param file
     *            The *.sql batch file
     * @param con
     *            The connection to the database
     * @throws Exception
     *             When file cannot read or statement has wrong syntax
     */
    public static void createStructure(File file, Connection con, String pluginName) throws Exception {
        createStructure(new BufferedReader(new FileReader(file)), con, pluginName);
    }

    /**
     * Read a <code>*.sql</code> batch containing SQL statements. This methods
     * reading and executing them and creats a database structure.
     * 
     * @param filePath
     *            The path to the .sql batch file
     * @param con
     *            The connection to the database
     * @throws Exception
     *             When file cannot read or statement has wrong syntax
     */
    public static void createStructure(String filePath, Connection con, String pluginName) throws Exception {
        createStructure(new File(filePath), con, pluginName);
    }
}
