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
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import de.minestar.minestarlibrary.chat.ChatMessage.ChatMessageBuilder;
import de.minestar.minestarlibrary.chat.ClickEvent.SuggestTextClickEvent;
import de.minestar.minestarlibrary.chat.HoverEvent.InvalidLineCharException;

public class ChatAPITest {

    @Test
    public void showCaseTest() {
        // Create message builder
        ChatMessageBuilder messageBuilder = ChatMessage.create();

        // Create hover events
        HoverEvent localHover = HoverEvent.create("This").addLine("is").addLine("Sparta").build();
        HoverEvent globalHover = HoverEvent.create("Not").addLine("Sparta").build();

        // Create local click event - only accessable when clicking its textpart
        // SuggestTextClickEvent: Copy text to text field, not running a command
        ClickEvent localClickEvent = new SuggestTextClickEvent("click me");
        // Create one text part and set it as bold
        TextPart firstText = TextPart.create("Hello ").setBold(true).setClickEvent(localClickEvent).build();

        // Create second text part aqua colored and an hover event only for this
        // part
        TextPart secondText = TextPart.create("World!").setColor(ChatColor.AQUA).setHoverEvent(localHover).build();

        // Create global click event - every textpart without a click event use
        // this event
        ClickEvent globalClickEvent = new SuggestTextClickEvent("run me");

        // build message
        messageBuilder.addTextPart(firstText).addTextPart(secondText).setGlobalClickEvent(globalClickEvent).setGlobalHoverEvent(globalHover);

        // Finalize the message
        ChatMessage message = messageBuilder.build();
        String jsonMessage = message.toJSONString();

        // Check if the message contains information
        Assert.assertThat(jsonMessage, Matchers.containsString("clickEvent:{action:\"suggest_command\",value:\"run me\"}"));
        Assert.assertThat(jsonMessage, Matchers.containsString("Lore:[\\\"Sparta\\\"]"));
        Assert.assertThat(jsonMessage, Matchers.containsString("text:\"Hello \",bold:true"));
        Assert.assertThat(jsonMessage, Matchers.containsString("color:\"aqua\""));
    }

    @Test
    public void showCaseAutoDelimeterTest() {
        // Create message builder with auto delimeter
        ChatMessageBuilder messageBuilder = ChatMessage.create(" ");

        // Build sentence
        TextPart firstText = TextPart.create("Das").build();
        TextPart secondText = TextPart.create("Pferd").build();
        TextPart thirdText = TextPart.create("frisst").build();
        TextPart fourthText = TextPart.create("keinen").build();
        TextPart fifthText = TextPart.create("Gurkensalat!").build();
        ChatMessage message = messageBuilder.addTextPart(firstText).addTextPart(secondText).addTextPart(thirdText).addTextPart(fourthText).addTextPart(fifthText).build();

        // Create json string
        String jsonMessage = message.toJSONString();

        Assert.assertEquals("{text:\"\",extra:[{text:\"Das\"},{text:\" \"},{text:\"Pferd\"},{text:\" \"},{text:\"frisst\"},{text:\" \"},{text:\"keinen\"},{text:\" \"},{text:\"Gurkensalat!\"}]}", jsonMessage);
    }

    @Test(expected = InvalidLineCharException.class)
    public void testInvalidChar() {
        // Create message builder with auto delimeter
        ChatMessageBuilder messageBuilder = ChatMessage.create(" ");
        messageBuilder.addTextPart(TextPart.create("Test").build()).setGlobalHoverEvent(HoverEvent.create("asd").addLine("\"").build());
        // Create json string
        String jsonMessage = messageBuilder.build().toJSONString();
        System.out.println("tellraw Meldanor " + jsonMessage);

    }
}
