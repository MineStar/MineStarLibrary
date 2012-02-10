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

package de.minestar.minestarlibrary.utils;

public class ConsoleUtils {

    /**
     * Prints an information to the console with the prefix
     * <code>[ PLUGIN ] :</code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printInfo(String pluginName, String message) {
        if (pluginName == null || pluginName.length() == 0)
            throw new IllegalArgumentException("Pluginname can't be null nor empty!");
        System.out.println("[ " + pluginName + " ] : " + message);
    }

    /**
     * Prints an warning to the console with the prefix
     * <code>[ PLUGIN ] : Warning! </code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printWarning(String pluginName, String message) {
        printInfo("Warning! " + message, pluginName);
    }

    /**
     * Prints an error to the console with the prefix
     * <code>[ PLUGIN ] : ERROR</code>
     * 
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printError(String pluginName, String message) {
        printInfo("ERROR! " + message, pluginName);
    }

    /**
     * Prints an error message with the exception stacktrace to the console with
     * the prefix <code>[ PLUGIN ] : EXCEPTION </code>
     * 
     * @param e
     *            The exeption that was catched!
     * @param message
     *            The message
     * @param pluginName
     *            The name of the plugin, can't be empty or <code>null</code>!
     * @throws IllegalArgumentException
     *             Thrown when pluginName is empty or <code>null</code>!
     */
    public static void printException(Exception e, String pluginName, String message) {
        printInfo("EXCEPTION! " + message, pluginName);
        e.printStackTrace();
    }
}
