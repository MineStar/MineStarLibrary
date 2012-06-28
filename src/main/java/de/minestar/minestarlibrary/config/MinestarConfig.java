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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import de.minestar.minestarlibrary.utils.ConsoleUtils;

public class MinestarConfig extends YamlConfiguration {

    private final File configFile;

    /**
     * Creates a {@link YamlConfiguration} object and open the file.
     * 
     * @param configFile
     *            The configuration file in YAML format
     * @throws Exception
     *             Any exception thrown by YamlConfiguration
     */
    public MinestarConfig(File configFile) throws Exception {
        super();
        this.configFile = configFile;
        load(configFile);
    }

    /**
     * Creates a {@link YamlConfiguration} object and open the file.<br>
     * Also check the version tag from the configuration with the plugin version
     * and eventually give an out date message
     * 
     * @param configFile
     *            The configuration file in YAML format
     * @throws Exception
     *             Any exception thrown by YamlConfiguration
     */
    public MinestarConfig(File configFile, String pluginName, String pluginVersion) throws Exception {
        this(configFile);
        checkVersion(pluginName, pluginVersion);
    }

    /**
     * Saves the configuration
     * 
     * @throws Exception
     *             Any exception thrown by YamlConfiguration
     */
    public void save() throws Exception {
        save(configFile);
    }

    /**
     * Compare the version tag from the config with the plugin version. If their
     * differ, an outdate warning is printed on console
     * 
     * @param pluginName
     *            The name of the plugin using this configuration file
     * @param pluginVersion
     *            The version of the plugin using this configuration file
     */
    private void checkVersion(String pluginName, String pluginVersion) {
        String confVersion = getString("VERSION");
        if (confVersion != null && !confVersion.equals(pluginVersion))
            ConsoleUtils.printWarning(pluginName, "The config '" + configFile + "' is out of date! Plugin Version is " + pluginVersion + " but Config Version is " + confVersion + " !");
    }

    // *************************************
    // ********** UTIL METHODS *************
    // *************************************
    /**
     * Creates a copy of the configuration
     * 
     * @param source
     *            The source file
     * @param target
     *            The target config file
     * @return A MinestarConfig object from the target file
     * @throws Exception
     *             Exceptions by reading and writing objects
     */
    public static MinestarConfig copyDefault(File source, File target) throws Exception {
        return copyDefault(new FileInputStream(source), target);
    }

    /**
     * Creates a copy of the configuration. <br>
     * This method is usefull to copy files from the jar to the plugins dir
     * 
     * @param source
     *            The inputstream to the source file
     * @param target
     *            The target config file
     * @return A MinestarConfig object from the target file
     * @throws Exception
     *             Exceptions by reading and writing objects
     */
    public static MinestarConfig copyDefault(InputStream source, File target) throws Exception {

        // 64 KB buffer
        byte[] buffer = new byte[0xFFFF];
        OutputStream out = new FileOutputStream(target);
        // copy file
        for (int len; (len = source.read(buffer)) != -1;)
            out.write(buffer, 0, len);
        out.close();
        source.close();
        return new MinestarConfig(target);
    }
}
