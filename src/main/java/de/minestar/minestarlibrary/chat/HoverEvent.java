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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = HoverEvent.HoverEventSerializer.class)
@JsonInclude(Include.NON_NULL)
public class HoverEvent {

    private int id = 331;
    private String text;
    private List<String> lines;

    private HoverEvent() {
        this.lines = new ArrayList<String>();
    }

    protected HoverEvent copy() {
        HoverEvent copy = new HoverEvent();
        copy.id = id;
        copy.text = text;
        copy.lines = new ArrayList<String>(lines);

        return copy;
    }

    /**
     * @return An empty HoverEvent with standard id 331
     */
    public static HoverEventBuilder create() {
        return new HoverEventBuilder();
    }

    /**
     * @param Text
     *            The text to show at the head of the mouse over
     * @return An HoverEvent with standard id 331 and the text
     */
    public static HoverEventBuilder create(String text) {
        return new HoverEventBuilder(text);
    }

    /**
     * Builder class for the product
     */
    public static class HoverEventBuilder {

        private HoverEvent product;

        private HoverEventBuilder() {
            this.product = new HoverEvent();
        }

        private HoverEventBuilder(String text) {
            this.product = new HoverEvent();
            setText(text);
        }

        /**
         * Set the ID of the hover event. Don't know if this is neccessary,
         * default value is 331. If the text is not set, the item's name with
         * the id will be used
         * 
         * @param id
         *            Minecraft valid item ID
         * @return This builder
         */
        public HoverEventBuilder setID(int id) {
            this.product.id = id;
            return this;
        }

        /**
         * Set the text which will be shown at the head of the mouse overlay
         * 
         * @param text
         *            Plain text without any formatting
         * @return This builder
         */
        public HoverEventBuilder setText(String text) {
            this.product.text = text;
            return this;
        }

        /**
         * Add a single line to the hover event.
         * 
         * @param line
         *            Plain text without any formatting.
         * @return This builder
         * @throws InvalidLineCharException
         *             Thrown when the line contains a colon : or a quotation
         *             mark "
         */
        public HoverEventBuilder addLine(String line) throws InvalidLineCharException {
            if (line.contains(":")) {
                throw new InvalidLineCharException(':', line);
            }
            if (line.contains("\"")) {
                throw new InvalidLineCharException('"', line);
            }
            this.product.lines.add(line);
            return this;
        }

        /**
         * @return Finalize the building and return a deep copy of the product
         */
        public HoverEvent build() {
            return product.copy();
        }
    }

    /**
     * Custom serializer of the hover event. Used for the automatic json
     * serialization of the object
     */
    private static class HoverEventSerializer extends JsonSerializer<HoverEvent> {

        @Override
        public void serialize(HoverEvent value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeStartObject();

            jgen.configure(Feature.QUOTE_FIELD_NAMES, false);
            jgen.writeFieldName("action");
            jgen.writeRawValue("show_item");
            jgen.writeFieldName("value");
            jgen.writeRawValue(buildValueString(value));
            jgen.writeEndObject();

        }

        /**
         * Build the value string
         */
        private String buildValueString(HoverEvent event) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("\"{id:");
            sBuilder.append(event.id);
            sBuilder.append(",tag:{display:{Name:");
            sBuilder.append(event.text);
            if (!event.lines.isEmpty()) {
                sBuilder.append(",Lore:[");

                sBuilder.append(buildLoreString(event.lines));
                sBuilder.append(']');

            }
            sBuilder.append("}}}\"");

            return sBuilder.toString();

        }

        /**
         * Build the lore part of the value string
         */
        private String buildLoreString(List<String> loreLines) {
            StringBuilder sBuilder = new StringBuilder();
            int i = 0;
            for (; i < loreLines.size() - 1; ++i) {
                sBuilder.append("\\\"" + loreLines.get(i) + "\\\"");
                sBuilder.append(',');
            }

            sBuilder.append("\\\"" + loreLines.get(i) + "\\\"");
            return sBuilder.toString();
        }

    }

    /**
     * Exception class for invalid chars in
     * {@link HoverEventBuilder#addLine(String)}
     */
    public static class InvalidLineCharException extends RuntimeException {

        private static final long serialVersionUID = -8112932828008526010L;

        public InvalidLineCharException(char character, String line) {
            super("Can't use " + character + "as line in hover event!\nLine:" + line);
        }
    }

}
