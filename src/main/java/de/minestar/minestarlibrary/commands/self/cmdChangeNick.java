/*
 * Copyright (C) 2012 MineStar.de 
 * 
 * This file is part of FifthElement.
 * 
 * FifthElement is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * FifthElement is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with FifthElement.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.commands.self;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.minestar.minestarlibrary.Core;
import de.minestar.minestarlibrary.commands.AbstractCommand;
import de.minestar.minestarlibrary.events.PlayerChangedNameEvent;
import de.minestar.minestarlibrary.utils.PlayerUtils;

public class cmdChangeNick extends AbstractCommand {

    public cmdChangeNick(String syntax, String arguments, String node) {
        super(Core.NAME, syntax, arguments, node);
    }

    @Override
    public void execute(String[] args, Player player) {
        String UUID = PlayerUtils.getUUIDFromMojang(args[1]);
        if (UUID == null) {
            PlayerUtils.sendError(player, Core.NAME, "UUID für Spieler '" + args[1] + "' nicht gefunden!");
            PlayerUtils.sendInfo(player, "Achtung: Die Namen müssen VOLLSTÄNDIG angegeben werden!");
            PlayerUtils.sendInfo(player, "Auf Groß- und Kleinschreibung achten!");
            return;
        }

        // rename Minestar-Player first

        // create & call event
        PlayerChangedNameEvent event = new PlayerChangedNameEvent(UUID, args[0], args[1]);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
