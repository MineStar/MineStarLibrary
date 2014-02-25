/*
 * Copyright (C) 2014 MineStar.de 
 * 
 * This file is part of MinestarLibrary.
 * 
 * MinestarLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MinestarLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MinestarLibrary.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
public class ChatMessage {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.configure(Feature.QUOTE_FIELD_NAMES, false);
    }

    @JsonProperty
    // This has to be an empty string, because of the ***** message format
    private final String text = "";

    @JsonProperty(value = "clickEvent")
    private ClickEvent globalClickEvent;

    @JsonProperty(value = "hoverEvent")
    private HoverEvent globalHoverEvent;

    @JsonProperty(value = "extra")
    private List<TextPart> textParts;

    private String delimeter;

    private ChatMessage() {
        this.textParts = new ArrayList<TextPart>();
        this.delimeter = null;
    }

    public String toJSONString() {
        try {
            return JSON_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected ChatMessage copy() {
        ChatMessage copy = new ChatMessage();

        copy.delimeter = delimeter;
        List<TextPart> copyList = new ArrayList<TextPart>(this.textParts.size());
        for (TextPart textPart : this.textParts) {
            copyList.add(textPart.copy());
        }
        copy.textParts = copyList;
        if (globalClickEvent != null)
            copy.globalClickEvent = globalClickEvent.copy();
        if (globalHoverEvent != null)
            copy.globalHoverEvent = globalHoverEvent.copy();

        return copy;
    }

    /**
     * Send the json formatted string to the player using the tellraw command
     * 
     * @param player
     *            The player to send the message
     */
    public void sendTo(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + toJSONString());
    }

    /**
     * @return Message without any delimeter
     */
    public static ChatMessageBuilder create() {
        return new ChatMessageBuilder();
    }

    /**
     * @param delimeter
     *            Seperate two text parts
     * @return Message with delimeter between text parts
     */
    public static ChatMessageBuilder create(String delimeter) {
        return new ChatMessageBuilder(delimeter);
    }

    public static class ChatMessageBuilder {

        private ChatMessage product;

        private ChatMessageBuilder() {
            this.product = new ChatMessage();

        }

        private ChatMessageBuilder(String delimeter) {
            this();
            this.product.delimeter = delimeter;
        }

        /**
         * Set the global click event for the complete message. Local click
         * events of text parts will override this, so this is the default click
         * event.
         * 
         * @param event
         *            The event to trigger on click
         * @return This builder
         */
        public ChatMessageBuilder setGlobalClickEvent(ClickEvent event) {
            product.globalClickEvent = event;
            return this;
        }

        /**
         * Set the global hover event for the complete message. Local hover
         * events of text parts will override this, so this is the default hover
         * event.
         * 
         * @param event
         *            The event to trigger on hover
         * @return This builder
         */
        public ChatMessageBuilder setGlobalHoverEvent(HoverEvent event) {
            product.globalHoverEvent = event;
            return this;
        }

        /**
         * Add a single text part to the message. The text will be appended on
         * the end. If a delimeter is set, it will also create another textpart
         * containing the delimeter without any formatting
         * 
         * @param text
         *            The textpart
         * @return This builder
         */
        public ChatMessageBuilder addTextPart(TextPart text) {
            product.textParts.add(text);
            if (product.delimeter != null) {
                product.textParts.add(TextPart.create(product.delimeter).build());
            }
            return this;
        }

        /**
         * Set/Remove the delimeter between the message. TextParts added before
         * this call are note effected, they are also seperated by the old
         * delimeter or not, when no delimeter was set.
         * 
         * @param delimeter
         *            The delimeter to seperate the textparta
         * @return This builder
         */
        public ChatMessageBuilder setDelimeter(String delimeter) {
            product.delimeter = delimeter;
            return this;
        }

        /**
         * @return Same as <code>build(false)</code> {@link #build(boolean)}
         */
        public ChatMessage build() {
            return this.build(false);
        }

        /**
         * Construct the chat message
         * 
         * @param keepDelimeter
         *            If <code>false</code> it will remove the last delimeter
         *            text part, if <code>true</code> not
         * @return A deep copy of the builded product
         */
        public ChatMessage build(boolean keepDelimeter) {
            // Delete last text part if the end is the delimeter
            if (!keepDelimeter) {
                if (product.delimeter != null) {
                    if (product.textParts.size() > 1) {
                        int pos = product.textParts.size() - 1;
                        if (product.textParts.get(pos).isText(product.delimeter)) {
                            product.textParts.remove(pos);
                        }
                    }
                }
            }
            return product.copy();
        }

    }
}
