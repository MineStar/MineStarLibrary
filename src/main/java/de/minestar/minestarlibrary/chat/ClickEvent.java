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

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ClickEvent {

    @JsonProperty
    private String action;
    @JsonProperty
    private String value;

    private ClickEvent(String action, String value) {
        this.action = action;
        this.value = value;
    }

    protected ClickEvent copy() {
        return new ClickEvent(this.action, this.value) {
        };

    }

    public static class RunCommandClickEvent extends ClickEvent {

        /**
         * Runs the command when the user clicks on it. User needs the
         * permission to use it
         * 
         * @param command
         *            The command to run
         */
        public RunCommandClickEvent(String command) {
            super("run_command", command);
        }
    }

    public static class SuggestTextClickEvent extends ClickEvent {

        /**
         * Suggest a text for the user. Can be a command or anything else. The
         * text will be copied to the chat windows
         * 
         * @param text
         *            The text to suggest
         */
        public SuggestTextClickEvent(String text) {
            super("suggest_command", text);
        }
    }

    public static class OpenURLClickEvent extends ClickEvent {

        /**
         * Opens the URL when the player clicks
         * 
         * @param url
         *            A valid URL to open
         */
        public OpenURLClickEvent(String url) {
            super("openURL", url);
        }
    }

}
