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

package de.minestar.library.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * A YAML based configuration which tries to load a file. If the file to load is
 * not existing, it will try to copy a default configuration file from the
 * plugin.jar. The configuration file must have the same name and must be placed
 * in the root dir of the .jar <br>
 * The default configuration will <b>NOT</b> be loaded! For this, see
 * {@link DefaultLoadConfig}
 * 
 */
public class DefaultConfig extends YamlConfiguration {

    /** The config file */
    private File configFile;

    private boolean isLoaded = false;

    /**
     * Creates a default configuration and tries to load the config file. If the
     * file is not existing, the configfile will <b>NOT</b> be loaded! For this,
     * see {@link DefaultLoadConfig#DefaultLoadConfig(File)}
     * 
     * @param configFile
     *            The file where the config is. To copy a default one the
     *            default config must be have the same name as this
     * @throws ConfigurationLoadException
     *             When something went wrong while loading
     */
    public DefaultConfig(File configFile) throws ConfigurationLoadException {
        if (configFile == null) {
            throw new ConfigurationLoadException("ConfigFile was null!");
        }
        if (configFile.exists()) {
            loadConfig(configFile);
        } else {
            copyConfig(configFile);
        }

        this.configFile = configFile;
    }

    protected void loadConfig(File configFile) throws ConfigurationLoadException {
        try {
            super.load(configFile);
        } catch (Exception e) {
            throw new ConfigurationLoadException(configFile.toString(), e);
        }
        this.isLoaded = true;
    }

    protected void copyConfig(File configFile) throws ConfigurationLoadException {
        String fileName = configFile.getName();
        InputStream resourceStream = getClass().getResourceAsStream("/".concat(fileName));
        if (resourceStream == null) {
            throw new ConfigurationLoadException("No default configuration file found for " + fileName);
        }

        try {
            copyFile(resourceStream, configFile);
        } catch (Exception e) {
            throw new ConfigurationLoadException(configFile.toString(), e);
        }
    }

    private final static int BUFFER_SIZE = 64 * 1024;

    private void copyFile(InputStream source, File target) throws Exception {
        byte[] buffer = new byte[BUFFER_SIZE];
        OutputStream out = new FileOutputStream(target);

        // copy file
        int len = 0;
        while ((len = source.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }

        out.close();
        source.close();
    }

    /**
     * Wrapper to save the configuration in the file
     */
    public void save() {
        // Do nothing
        if (configFile == null) {
            return;
        }

        try {
            save(configFile);
        } catch (IOException e) {
            // supress warning
        }
    }

    /**
     * @return <code>True</code>,if and only if, the configuration was found and
     *         successfully loaded
     */
    public boolean isLoaded() {
        return isLoaded;
    }

}
