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

import org.bukkit.ChatColor;

public class Message {

    private final String sender;
    private final String target;

    private final String prefix, message;
    private final ChatColor prefixColor, messageColor;
    private final long timestamp;

    protected boolean isOfficial;

    private boolean isRead;

    public Message(String sender, String target, String prefix, String message, ChatColor prefixColor, ChatColor messageColor, long timestamp, boolean isOfficial, boolean isRead) {
        this.sender = sender;
        this.target = target;
        this.prefix = prefix;
        this.message = message;
        this.prefixColor = prefixColor;
        this.messageColor = messageColor;
        this.timestamp = timestamp;
        this.isOfficial = isOfficial;
        this.isRead = isRead;
    }

    public Message(String sender, String target, ChatColor prefixColor, String prefix, ChatColor messageColor, String message) {
        if (prefix != null && !prefix.isEmpty())
            this.prefix = "[" + prefix + "]";
        else
            this.prefix = "";

        if (message != null)
            this.message = message;
        else
            this.message = "";

        this.prefixColor = prefixColor;
        this.messageColor = messageColor;
        this.timestamp = System.currentTimeMillis();

        this.isOfficial = false;

        this.sender = sender;
        this.target = target;

        this.isRead = false;
    }

    public Message(String sender, String target, ChatColor messageColor, String message) {
        this(sender, target, ChatColor.WHITE, "", messageColor, message);
    }

    public String getCompleteMessage() {
        if (this.prefix.length() > 0) {
            return this.prefixColor + this.prefix + " " + this.messageColor + this.message;
        } else {
            return this.messageColor + this.message;
        }
    }

    public String getMessage() {
        return message;
    }

    public ChatColor getMessageColor() {
        return messageColor;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getPrefixColor() {
        return prefixColor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public String getSender() {
        return sender;
    }

    public String getTarget() {
        return target;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
