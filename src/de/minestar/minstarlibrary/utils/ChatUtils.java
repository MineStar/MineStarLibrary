package de.minestar.minstarlibrary.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {
    // PRINT SIMPLE LINE
    public static void printLine(Player player, ChatColor color, String message) {
        player.sendMessage(color + message);
    }

    // PRINT INFO
    public static void printInfo(Player player, String pluginName, ChatColor color, String message) {
        player.sendMessage(ChatColor.AQUA + pluginName + (pluginName.length() > 0 ? " " : "") + color + message);
    }

    // PRINT ERROR
    public static void printError(Player player, String pluginName, String message) {
        printInfo(player, pluginName, ChatColor.RED, message);
    }

    // PRINT A SUCCESS
    public static void printSuccess(Player player, String pluginName, String message) {
        printInfo(player, pluginName, ChatColor.GREEN, message);
    }

    // WRONG SYNTAX
    public static void printWrongSyntax(Player player, String pluginName, String Syntax, String[] Examples) {
        ChatUtils.printError(player, pluginName, "Wrong Syntax! Use: " + Syntax);

        if (Examples.length == 1)
            ChatUtils.printInfo(player, pluginName, ChatColor.GRAY, "Example:");
        else if (Examples.length > 1)
            ChatUtils.printInfo(player, pluginName, ChatColor.DARK_RED, "Examples:");

        for (int i = 0; i < Examples.length; i++) {
            ChatUtils.printInfo(player, pluginName, ChatColor.GRAY, Examples[i]);
        }
        return;
    }
}
