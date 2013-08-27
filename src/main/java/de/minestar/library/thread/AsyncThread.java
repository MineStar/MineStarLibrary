/*
 * Copyright (C) 2013 MineStar.de 
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

package de.minestar.library.thread;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Does an asynchronous task and after that is done it does a synchronous task.
 * Usefull for database queries to start the query to the database and display
 * the result without running on the main thread
 */
public abstract class AsyncThread {

    /**
     * The plugin, necessary for the schedular calls
     */
    private Plugin plugin;

    /**
     * Creates an thread and start the asynchronous task
     * 
     * @param plugin
     *            The plugin calling creating the thread
     */
    public AsyncThread(Plugin plugin) {
        this.plugin = plugin;
        startAsynchronous();
    }

    private final void startAsynchronous() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new AsyncWorkerThread());
    }

    /**
     * This method is the asychronous task of the thread. It will be execute
     * besides the mainthread and will not pause the mainthread at the mean
     * time. <br>
     * Use this method to do long task, but never access the Bukkit API!
     */
    public abstract void doAsynchronous();

    /**
     * Helper class for doing the asychronous work
     */
    private final class AsyncWorkerThread implements Runnable {

        @Override
        public void run() {
            doAsynchronous();

            Bukkit.getScheduler().runTask(plugin, new SyncWorkerThread());
        }
    }

    /**
     * This method is the synchronous task of the thread. It will be executed
     * right AFTER the asychronours task and not before! <br>
     * Use this method to access the Bukkit API
     */
    public abstract void doSynchronous();

    /**
     * Helper class for doing the sychronous work
     */
    private final class SyncWorkerThread implements Runnable {

        @Override
        public void run() {
            doSynchronous();
        }
    }

}
