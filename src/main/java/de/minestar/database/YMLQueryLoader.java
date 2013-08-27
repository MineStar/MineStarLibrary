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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Parses the format: <br>
 * queries.yml: <br>
 * name: query <br>
 * name: query <br>
 * ... <br>
 * 
 */
public class YMLQueryLoader implements QueryLoader {

    @Override
    public Map<String, String> loadQueries(InputStream source) {

        Map<String, String> queries = new HashMap<String, String>();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(source);

        Set<String> keys = config.getKeys(false);
        String query = null;
        for (String queryName : keys) {
            query = config.getString(queryName);
            if (query == null) {

            } else {
                queries.put(queryName, query);
            }
        }
        return queries;
    }

    private String DEFAULT_FILE_NAME = "queries.yml";

    @Override
    public String getDefaultFileName() {
        return DEFAULT_FILE_NAME;
    }
}
