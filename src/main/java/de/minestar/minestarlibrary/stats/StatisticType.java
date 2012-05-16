/*
 * Copyright (C) 2012 MineStar.de 
 * 
 * This file is part of Illuminati.
 * 
 * Illuminati is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * Illuminati is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Illuminati.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.stats;

public enum StatisticType {

    // @formatter:off
    INT     (Integer.class,     "INT"),
    FLOAT   (Float.class,       "FLOAT"),
    DOUBLE  (Double.class,      "DOUBLE"),
    LONG    (Long.class,        "BIGINT"),
    STRING  (String.class,      "TEXT"),
    CHAR    (Character.class,   "CHAR"),
    DATE    (String.class,      "DATE"),
    TIME    (String.class,      "TIME"),
    DATETIME(String.class,      "DATETIME");
    // @formatter:on

    /** The class representing the data in Java */
    private final Class<?> clazz;
    /** The type name which is used in a SQL database */
    private final String databaseTypeName;

    private StatisticType(Class<?> clazz, String databaseDatatype) {
        this.clazz = clazz;
        this.databaseTypeName = databaseDatatype;
    }

    /** @return The type name which is used in a SQL database for this datatype */
    public String getName() {
        return databaseTypeName;
    }

    /** @return The class representing the data in Java */
    public Class<?> getClazz() {
        return clazz;
    }
}
