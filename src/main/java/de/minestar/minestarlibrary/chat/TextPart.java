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

import org.bukkit.ChatColor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.minestar.minestarlibrary.chat.ChatMessage.ChatMessageBuilder;

@JsonInclude(Include.NON_NULL)
public class TextPart {

    @JsonProperty
    private ClickEvent clickEvent;
    @JsonProperty
    private HoverEvent hoverEvent;

    @JsonProperty
    private String text;
    @JsonProperty
    private String color;
    @JsonProperty
    private Boolean bold;
    @JsonProperty
    private Boolean underlined;
    @JsonProperty
    private Boolean italic;
    @JsonProperty
    private Boolean obfuscated;
    @JsonProperty
    private Boolean strikethrough;

    private TextPart() {

    }

    /**
     * Creates an deep copy
     */
    protected TextPart copy() {
        TextPart copy = new TextPart();

        if (hoverEvent != null)
            copy.hoverEvent = hoverEvent.copy();
        if (clickEvent != null)
            copy.clickEvent = clickEvent.copy();
        copy.text = text;
        copy.color = color;
        copy.bold = bold;
        copy.underlined = underlined;
        copy.italic = italic;
        copy.obfuscated = obfuscated;
        copy.strikethrough = strikethrough;

        return copy;
    }

    /**
     * Used in {@link ChatMessageBuilder#build()} for deleting the last element
     * when a automatic delimeter is set
     */
    protected boolean isText(String text) {
        return this.text != null && this.text.equals(text);
    }

    protected HoverEvent getHoverEvent() {
        return this.hoverEvent;
    }

    protected void setHoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }

    protected ClickEvent getClickEvent() {
        return this.clickEvent;
    }

    protected void setClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    /**
     * @return An empty message without any formatting
     */
    public static TextPartBuilder create() {
        return new TextPartBuilder();
    }

    /**
     * @param text
     *            Plain text
     * @return A message with the text without any formatting
     */
    public static TextPartBuilder create(String text) {
        return new TextPartBuilder(text);
    }

    public static class TextPartBuilder {

        private TextPart product;

        private TextPartBuilder() {
            this.product = new TextPart();
        }

        private TextPartBuilder(String text) {
            this.product = new TextPart();
            setText(text);
        }

        /**
         * Enable/Disable boldness of the text
         * 
         * @param bold
         *            <code>true</code> to enable, <code>false</code> to remove
         * @return This builder
         */
        public TextPartBuilder setBold(boolean bold) {
            if (bold == false) {
                this.product.bold = null;
            } else {
                this.product.bold = true;
            }

            return this;
        }

        /**
         * Set/Remove the color of the text
         * 
         * @param color
         *            <code>null</code> to remove the color, otherwise the color
         *            will be used
         * @return This builder
         */
        public TextPartBuilder setColor(ChatColor color) {
            if (color == null) {
                this.product.color = null;
            } else {
                this.product.color = color.name().toLowerCase();
            }
            return this;
        }

        /**
         * Enable/Disable italic of the text
         * 
         * @param color
         *            <code>true</code> to enable, <code>false</code> to remove
         * @return This builder
         */
        public TextPartBuilder setItalic(boolean italic) {
            if (italic == false) {
                this.product.italic = null;
            } else {
                this.product.italic = true;
            }
            return this;
        }

        /**
         * Enable/Disable obfuscating of the text
         * 
         * @param obfuscated
         *            <code>true</code> to enable, <code>false</code> to remove
         * @return This builder
         */
        public TextPartBuilder setObfuscated(boolean obfuscated) {
            if (obfuscated == false) {
                this.product.obfuscated = null;
            } else {
                this.product.obfuscated = true;
            }
            return this;
        }

        /**
         * Enable/Disable whether the text is strike through or not
         * 
         * @param strikethrough
         *            <code>true</code> to enable, <code>false</code> to remove
         * @return This builder
         */
        public TextPartBuilder setStrikethrough(boolean strikethrough) {
            if (strikethrough == false) {
                this.product.strikethrough = null;
            } else {
                this.product.strikethrough = true;
            }
            return this;
        }

        /**
         * Set the text of the textpart
         * 
         * @param text
         *            The plain text
         * @return This builder
         */
        public TextPartBuilder setText(String text) {
            this.product.text = text;
            return this;
        }

        /**
         * Enable/Disable whether the text is underlined or not
         * 
         * @param underlined
         *            <code>true</code> to enable, <code>false</code> to remove
         * @return This builder
         */
        public TextPartBuilder setUnderlined(boolean underlined) {
            if (underlined == false) {
                this.product.underlined = null;
            } else {
                this.product.underlined = true;
            }
            return this;
        }

        /**
         * Set the local click event for this text part. This click event is
         * only valid when the user clicks on this text part of the message.
         * Will override global click events
         * 
         * @param event
         *            The click event to trigger. <code>null</code> to remove
         *            the click event
         * @return This builder
         */
        public TextPartBuilder setClickEvent(ClickEvent event) {
            this.product.clickEvent = event;
            return this;
        }

        /**
         * Set the local hover event for this text part. This hover event is
         * only valid when the user hovers over this text part of the message.
         * Will override global hover events
         * 
         * @param event
         *            The hover event to trigger. <code>null</code> to remove
         *            the hover event
         * @return This builder
         */
        public TextPartBuilder setHoverEvent(HoverEvent event) {
            this.product.hoverEvent = event;
            return this;
        }

        /**
         * @return Finalize the building and return a deep copy of the product
         */
        public TextPart build() {
            return product.copy();
        }
    }
}
