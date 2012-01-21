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

package de.minestar.minstarlibrary.utils;

import org.bukkit.map.MapFont;

public class MapUtils {

    /**
     * Formats a string from normal input into a string that can presented on
     * the map, which is limit by 128x128 pixel.
     * 
     * @param input
     *            The text to format
     * @param font
     *            The font used
     * @param x
     *            The x coord where text is starting
     * @param y
     *            The y coord where text is starting
     * @return <code>null</code> when text is too long to render, otherwise
     *         formatted text with \n as linebreaker
     */
    public static String convertString(String input, MapFont font, int x, int y) {

        // x and y coords values : 0 - 127
        // don't know why but we have only the 3/4 of the size :/
        int xMax = (int) ((127 - x) * 0.75);
        int yMax = 127 - y;
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("X or Y is too high to draw a text on the map! X=" + x + ",Y=" + y);

        // String is short enough to fit in one line
        if (xMax > font.getWidth(input))
            return input;

        // too many lines -> text will never fit
        if (font.getWidth(input) / xMax * font.getHeight() >= yMax)
            return null;

        StringBuilder text = new StringBuilder(input.length() + 10);
        StringBuilder line = new StringBuilder(50);
        int sum = 0;
        for (char c : input.toCharArray()) {
            sum += font.getChar(c).getWidth();
            // maximum size of line reached -> add a \n
            if (sum >= xMax) {
                text.append(line);
                text.append("\n");
                line = new StringBuilder(50);
                sum = 0;
            }
            line.append(c);
        }
        if (sum != 0)
            text.append(line);

        return text.toString();
    }
}
