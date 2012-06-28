/*
 * Copyright (C) 2011 MineStar.de 
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

package de.minestar.minestarlibrary.localization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Handle all texts that needs to localized.
 * 
 * @author Meldanor, GeMoschen
 * 
 */
public class Localization {

    /**
     * The key is the node and the entry is the text
     */
    private HashMap<String, String> texts = new HashMap<String, String>();

    /**
     * Creates a Localization loading all data from a file. If this file is not
     * existing, it will try to load the defaults from the .jar/res/default.txt
     * 
     * @param fileName
     *            Path to the file
     * @throws FileNotFoundException
     *             When the default.txt in .jar/res/ is not found! Please always
     *             put on in its
     */
    public Localization(String fileName) throws Exception {
        this(new File(fileName));
    }

    /**
     * Creates a Localization loading all data from a file. If this file is not
     * existing, it will try to load the defaults from the .jar/res/default.txt
     * 
     * @param file
     *            Localization file
     * @throws FileNotFoundException
     *             When the default.txt in .jar/res/ is not found! Please always
     *             put on in its
     */
    public Localization(File file) throws Exception {

        BufferedReader bReader = null;
        if (!file.exists()) {
            System.out.println("File not found, use standard file!");
            InputStream in = getClass().getResourceAsStream("/res/default.txt");
            if (in == null)
                throw new FileNotFoundException("Plugin.jar/res/default.txt");

            bReader = new BufferedReader(new InputStreamReader(in));
        } else
            bReader = new BufferedReader(new FileReader(file));

        loadMeldanorFormat(bReader);

        bReader.close();
    }

    /**
     * To load localized texts from a file, use a file in that format: <br>
     * #node1<br>
     * entry1 = text1<br>
     * entry2 = text2<br>
     * #node2<br>
     * entry1 = text3<br>
     * entry2 = text4<br>
     * 
     * Example:
     * 
     * #console<br>
     * enable = Plugin was enabled<br>
     * disable = Plugin was disabled<br>
     * 
     * If you want to use variables in your text, use <br>
     * <code>%s</code> and to get the text <br>
     * <code>Localization.getText(node,arguments)</code>
     * 
     * @param bReader
     * @throws Exception
     */
    private void loadMeldanorFormat(BufferedReader bReader) throws Exception {

        String line = "";
        String node = "";
        while ((line = bReader.readLine()) != null) {
            if (line.trim().length() == 0)
                continue;
            if (line.startsWith("#")) {
                node = line.substring(1);
                continue;
            }
            String[] split = line.split(" = ");
            texts.put(node + "." + split[0], split[1]);
        }
    }

    /**
     * 
     * @param node
     *            Supernode.node to get the text. <br>
     *            Example: console.enable to get the text for it.
     * @return <code>null</code> if the node is not existing or the text without
     *         any format
     */
    public String getText(String node) {
        return texts.get(node);
    }

    /**
     * 
     * @param node
     *            Supernode.node to get the text. <br>
     *            Example: console.enable to get the text for it.
     * @param args
     *            The arguments that will replace all %s in the text
     * @return <code>null</code> if the node is not existing or the text
     */
    public String getText(String node, String... args) {
        return String.format(texts.get(node), (Object[]) args);
    }
}
