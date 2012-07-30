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

package de.minestar.minestarlibrary.commands;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

import de.minestar.minestarlibrary.stats.Statistic;
import de.minestar.minestarlibrary.stats.StatisticType;

/**
 * A general statistic every plugin can use to log command errors caused by
 * user(not caused by the program itself!)
 */
public class CommandErrorStatistic implements Statistic {

    private String pluginName;
    private String command;
    private String[] args;
    private String reason;
    private Timestamp date;

    /**
     * Used for reflection access and registering the statistic
     */
    public CommandErrorStatistic() {
        // EMPTY CONSTRUCTOR FOR REFLECTION ACCESS
    }

    /**
     * Creates a temponary statistic to log command errors caused by user
     * 
     * @param pluginName
     *            The plugin name
     * @param command
     *            The commands syntax to identify the command
     * @parama args The arguments of the command
     * @param reason
     *            The reason why the command failed
     */
    public CommandErrorStatistic(String pluginName, String command, String[] args, String reason) {
        this.pluginName = pluginName;
        this.command = command;
        this.args = args;
        this.reason = reason;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String getPluginName() {
        return "MinestarLibrary";
    }

    @Override
    public String getName() {
        return "CommandError";
    }

    @Override
    public LinkedHashMap<String, StatisticType> getHead() {
        LinkedHashMap<String, StatisticType> head = new LinkedHashMap<String, StatisticType>();

        head.put("plugin", StatisticType.STRING);
        head.put("Command", StatisticType.STRING);
        head.put("Reason", StatisticType.STRING);
        head.put("Arguments", StatisticType.STRING);
        head.put("Date", StatisticType.DATETIME);

        return head;
    }

    @Override
    public Queue<Object> getData() {
        Queue<Object> data = new LinkedList<Object>();

        data.add(pluginName);
        data.add(command);
        data.add(Arrays.toString(args));
        data.add(reason);
        data.add(date);

        return data;
    }
}
