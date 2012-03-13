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

package de.minestar.minestarlibrary.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.YamlConfiguration;

public class MinestarConfig extends YamlConfiguration {

    private final File configFile;

    public MinestarConfig(File file) throws Exception {
        super();
        this.configFile = file;
        load(configFile);
    }

    public MinestarConfig(String fileName) throws Exception {
        super();
        this.configFile = new File(fileName);
        load(configFile);
    }

    public void save() throws Exception {
        save(configFile);
    }

    // *************************************
    // ********** UTIL METHODS *************
    // *************************************
    public static MinestarConfig copyDefault(File source, File target) throws Exception {
        return copyDefault(new BufferedReader(new FileReader(source)), target);
    }

    public static MinestarConfig copyDefault(InputStream source, File target) throws Exception {
        return copyDefault(new BufferedReader(new InputStreamReader(source)), target);
    }

    private static MinestarConfig copyDefault(BufferedReader bReader, File target) throws Exception {
        String line = "";
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(target));
        while ((line = bReader.readLine()) != null)
            bWriter.write(line);

        bReader.close();
        bWriter.close();

        return new MinestarConfig(target);
    }
}
