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

package de.minestar.minestarlibrary.messages;

import java.util.HashMap;
import java.util.Map;

import de.minestar.minestarlibrary.utils.ChatUtils;
import de.minestar.minestarlibrary.utils.ConsoleUtils;
import de.minestar.minestarlibrary.utils.PlayerUtils;

/**
 * The message type of the message. It is used to use the right functions by
 * {@link ConsoleUtils}, {@link PlayerUtils} and {@link ChatUtils}. Also used by
 * VinciCode for formatting the message
 * 
 */
public enum MessageType {

    //@formatter:off
    /** Default messages without any special formatations */
    DEFAULT,
    
    /** Success messages used for sending positive success messages */
    SUCCESS,
    
    /** Info messages used to send general addional information messages */
    INFO,
    
    /** Error messages used to send an error, when something went really wrong */
    ERROR,
    
    /** Official messages from the team to the user */
    OFFICIAL;
    //@formatter:on

    private static Map<Integer, MessageType> mapByID;

    static {
        mapByID = new HashMap<Integer, MessageType>();

        for (MessageType type : MessageType.values()) {
            mapByID.put(type.ordinal(), type);
        }
    }

    public static MessageType get(int id) {
        return mapByID.get(id);
    }
}
