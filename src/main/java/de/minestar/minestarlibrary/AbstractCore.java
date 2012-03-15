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

package de.minestar.minestarlibrary;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import de.minestar.minestarlibrary.commands.CommandList;
import de.minestar.minestarlibrary.utils.ConsoleUtils;

public abstract class AbstractCore extends JavaPlugin {

    protected CommandList cmdList;
    public static String NAME;

    public AbstractCore() {
        this("");
    }

    public AbstractCore(String name) {
        NAME = name;
    }

    @Override
    public void onDisable() {
        if (!commonDisable()) {
            ConsoleUtils.printError(NAME, "Can't handle command disable action! Possible data loss!");
        }
        ConsoleUtils.printInfo(NAME, "Disabled v" + getDescription().getVersion() + "!");
    }

    @Override
    public void onEnable() {

        // create plugins datafolder
        getDataFolder().mkdirs();

        if (!createManager()) {
            ConsoleUtils.printError(NAME, "Can't create manager! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }
        if (!createListener()) {
            ConsoleUtils.printError(NAME, "Can't create listener! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }
        if (!createCommands()) {
            ConsoleUtils.printError(NAME, "Can't create commands! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }

        if (!registerEvents(getServer().getPluginManager())) {
            ConsoleUtils.printError(NAME, "Can't register events! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }

        if (!createThreads()) {
            ConsoleUtils.printError(NAME, "Can't create threads! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }
        if (!startThreads(getServer().getScheduler())) {
            ConsoleUtils.printError(NAME, "Can't start threads! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }

        if (!commonEnable()) {
            ConsoleUtils.printError(NAME, "Can't initiate common things! Plugin is not enabled!");
            this.setEnabled(false);
            return;
        }

        ConsoleUtils.printInfo(NAME, "Enabled v" + getDescription().getVersion() + " !");
    }

    /**
     * Creating the manager and handler of the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean createManager() {
        return true;
    }

    /**
     * Creating the listers for the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean createListener() {
        return true;
    }

    /**
     * Creating the commands of the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean createCommands() {
        return true;
    }

    /**
     * Register the created listers to the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean registerEvents(PluginManager pm) {
        return true;
    }

    /**
     * Creating the threads running in the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean createThreads() {
        return true;
    }

    /**
     * Start the threads running in the plugin
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean startThreads(BukkitScheduler scheduler) {
        return true;
    }

    /**
     * Handle common things on plugin enable
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean commonEnable() {
        return true;
    }

    /**
     * Handle common things on plugin disable
     * 
     * @return <code>True</code> when it was sucessfull without errors
     */
    protected boolean commonDisable() {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (cmdList != null)
            cmdList.handleCommand(sender, label, args);
        return true;
    }
}
