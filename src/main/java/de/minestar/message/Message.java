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

package de.minestar.message;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Message {

    /**
     * The type of the message
     */
    public enum Type {

        // @formatter:off
        
        /**
         * Normal infos, nothing special
         */
        INFO(Level.INFO),
        /**
         * Something was successfull and ok 
         */
        SUCCESS(Level.FINE),
        /**
         * Something non critical is not working
         */
        WARNING(Level.WARNING),
        /**
         * Something went terrible wrong
         */
        ERROR(Level.SEVERE);
        // @formatter:on

        private final Level level;

        private Type(Level level) {
            this.level = level;
        }
    }

    /**
     * Used for the {@link #send(CommandSender...)} as a Shortcut
     */
    public static final CommandSender CONSOLE = Bukkit.getConsoleSender();

    /**
     * Container of the message
     */
    protected StringBuilder sBuilder;

    /**
     * The type of the message
     * 
     * @see Type
     */
    private Type type;

    /**
     * A normal message
     */
    public Message() {
        this(Type.INFO);
    }

    /**
     * A message with the certain type
     * 
     * @param type
     *            The type
     */
    protected Message(Type type) {
        this.type = type;
        this.sBuilder = new StringBuilder(255);
    }

    protected Message(String pluginName, Type type) {
        this(type);
        add('[').add(pluginName).add("] ");
    }

    /**
     * Add a single char to the message
     * 
     * @param c
     *            Single char
     * @return This message with new content
     * @see StringBuilder#append(char)
     */
    public Message add(char c) {
        sBuilder.append(c);
        return this;
    }

    /**
     * Add a single integer to the message
     * 
     * @param i
     *            Single integer
     * @return This message with new content
     * @see StringBuilder#append(int)
     */
    public Message add(int i) {
        sBuilder.append(i);
        return this;
    }

    /**
     * Add a single byte to the message
     * 
     * @param b
     *            Single bytge
     * @return This message with new content
     * @see StringBuilder#append(byte)
     */
    public Message add(byte b) {
        sBuilder.append(b);
        return this;
    }

    /**
     * Add a single short to the message
     * 
     * @param s
     *            Single short
     * @return This message with new content
     * @see StringBuilder#append(short)
     */
    public Message add(short s) {
        sBuilder.append(s);
        return this;
    }

    /**
     * Add a single long to the message
     * 
     * @param l
     *            Single long
     * @return This message with new content
     * @see StringBuilder#append(char)
     */
    public Message add(long l) {
        sBuilder.append(l);
        return this;
    }

    /**
     * Add a single float to the message
     * 
     * @param f
     *            Single float
     * @return This message with new content
     * @see StringBuilder#append(float)
     */
    public Message add(float f) {
        sBuilder.append(f);
        return this;
    }

    /**
     * Add a single double to the message
     * 
     * @param d
     *            Single double
     * @return This message with new content
     * @see StringBuilder#append(double)
     */
    public Message add(double d) {
        sBuilder.append(d);
        return this;
    }

    /**
     * Add a single boolean as true or false to the message
     * 
     * @param b
     *            Single boolean
     * @return This message with new content
     * @see StringBuilder#append(boolean)
     */
    public Message add(boolean b) {
        sBuilder.append(b);
        return this;
    }

    /**
     * Add the string to the message
     * 
     * @param string
     *            A text
     * @return This message with new content
     * @see StringBuilder#append(String)
     */
    public Message add(String string) {
        sBuilder.append(string);
        return this;
    }

    /**
     * Add the string representation of the object to the message by using
     * {@link Object#toString()}
     * 
     * @param o
     *            Object
     * @return This message with new content
     * @see StringBuilder#append(Object)
     */
    public Message add(Object b) {
        sBuilder.append(b);
        return this;
    }

    /**
     * Set the color of the followning text to color. This does not change the
     * color of the text before the call!
     * 
     * @param color
     *            Color of the followning text
     * @return This message with new content
     */
    public Message color(ChatColor color) {
        return this.add(color);
    }

    /**
     * Send the message to the receiver. To send something to the console, use
     * {@link #CONSOLE} as an parameter!
     * 
     * @param receiver
     *            Collection of receiver, can be players or the console.
     * @return This message
     */
    public Message send(CommandSender... receiver) {
        if (receiver == null || receiver.length == 0) {
            DebugMessage.debug("Receivers are empty!");
        }
        if (sBuilder.length() == 0) {
            DebugMessage.debug("Message is empty!");
            return this;
        }
        for (CommandSender rec : receiver) {
            if (rec instanceof Player)
                sendToPlayer((Player) rec);
            else if (rec instanceof ConsoleCommandSender)
                sendToConsole();
        }
        return this;

    }

    /**
     * Used to send a message to a single player
     * 
     * @param p
     *            The player to receive the message
     */
    private void sendToPlayer(Player p) {
        // TODO: Maybe implement an offline message system here?
        p.sendMessage(sBuilder.toString());
    }

    private final static Logger LOG = Bukkit.getLogger();

    /**
     * Send the content of the message to the console
     */
    private void sendToConsole() {
        LOG.log(type.level, sBuilder.toString());
    }
}
