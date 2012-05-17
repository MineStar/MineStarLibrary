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

package de.minestar.minestarlibrary.stats;

import java.util.List;
import java.util.Map;

/**
 * An interface for statistic to persistate. The server has to run Illuminati
 * and call the function registerStatistic() to register statistics. <br>
 * IMPORTANT: This class must provide an empty constructor!
 * 
 */
public interface Statistic {

    /** @return The name of the plugin the statistic is called from */
    public String getPluginName();

    /**
     * @return The name of the statistic. This is used to identify what the
     *         statistic is about
     */
    public String getName();

    /**
     * @return A Map holding the structure of the statistic. The key is the name
     *         of the attribute and the statisticType object refering to the key
     *         is the type of data to store
     * 
     * @see StatisticType StatisticType
     * */
    public Map<String, StatisticType> getHead();

    /**
     * @return A list of objects. These are the data of the statistic. They must
     *         fit with the datatypes give in getHead()
     */
    public List<Object> getData();

}
