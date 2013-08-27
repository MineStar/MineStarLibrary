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

/**
 * Extension of the {@link DefaultConfig} which loads the default config after
 * coping it
 * 
 */
public class DefaultLoadConfig extends DefaultConfig {

    /**
     * See {@link DefaultConfig#DefaultConfig(File)}. Load the default
     * configuration after copying it
     * 
     * @param configFile
     *            The file where the config is. To load a default one the
     *            default config must be have the same name as this
     * @throws ConfigurationLoadException
     *             When something went wrong while loading
     */
    public DefaultLoadConfig(File configFile) throws ConfigurationLoadException {
        super(configFile);
    }

    @Override
    protected void copyConfig(File configFile) throws ConfigurationLoadException {

        super.copyConfig(configFile);
        super.loadConfig(configFile);
    }

}
