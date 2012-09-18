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

import java.util.Comparator;

public class Message implements Comparable<Message> {

    public class MessageComparator implements Comparator<Message> {
        @Override
        public int compare(Message message, Message otherMessage) {
            if (message.isRead) {
                if (otherMessage.isRead) {
                    return (int) (message.timestamp - otherMessage.timestamp);
                } else {
                    return 1;
                }
            } else {
                if (otherMessage.isRead) {
                    return -1;
                } else {
                    return (int) (message.timestamp - otherMessage.timestamp);
                }
            }
        }
    }

    private final String sender;
    private final String receiver;

    private final String text;
    private final long timestamp;

    private final MessageType type;

    private boolean isRead;

    /**
     * Constructor load from database
     * 
     * @param sender
     *            Who sent the message
     * @param receiver
     *            Who receives the message
     * @param text
     *            The text of the message
     * @param timestamp
     *            When the message was sent
     * @param type
     * 
     * @param isRead
     *            Is the messages read
     */
    public Message(String sender, String receiver, String text, MessageType type, long timestamp, boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.timestamp = timestamp;
        this.isRead = isRead;
        this.type = type;
    }

    /**
     * Constructor for creating a new ingame message
     * 
     * @param sender
     *            Who sent the message
     * @param receiver
     *            Who receives the message
     * @param text
     *            The text of the message
     * @param type
     *            The type of the messages. This is used to format the message.
     *            See {@link MessageType}
     */
    public Message(String sender, String receiver, String text, MessageType type) {
        this(sender, receiver, text, type, System.currentTimeMillis(), false);
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message={Sender=" + sender + ";Target=" + receiver + ";Type=" + type + ";Read=" + isRead + ";Text=" + text + "}";
    }

    @Override
    public int compareTo(Message otherMessage) {
        if (this.isRead) {
            if (otherMessage.isRead) {
                return (int) (this.timestamp - otherMessage.timestamp);
            } else {
                return 1;
            }
        } else {
            if (otherMessage.isRead) {
                return -1;
            } else {
                return (int) (this.timestamp - otherMessage.timestamp);
            }
        }
    }
}
