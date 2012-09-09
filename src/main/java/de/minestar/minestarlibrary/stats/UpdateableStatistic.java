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

public interface UpdateableStatistic extends Statistic {

    /**
     * The name of the unique key. The key is also used for adding an index. The
     * key must not be part of the getData() or getHead() method!
     * 
     * @return The key name
     */
    public String getKeyName();

    /**
     * The key type of the unique key. The key must not be part of the getData()
     * or getHead() method!
     * 
     * @return The key type
     */
    public StatisticType getKeyType();

    /**
     * The value of the key. The key must not be part of the getData() or
     * getHead() method!
     * 
     * @return Value of the key
     */
    public Object getKey();
}
