package com.github.Ablockalypse.JamesNorris.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.Ablockalypse.JamesNorris.Data.Data;
import com.github.Ablockalypse.JamesNorris.Implementation.ZAGame;
import com.github.Ablockalypse.JamesNorris.Implementation.ZAPlayer;
import com.github.Ablockalypse.JamesNorris.Threading.RespawnThread;

public class PlayerDeath implements Listener {
	/*
	 * Called when a player is killed.
	 * 
	 * Used for respawning the player after the current level.
	 */
	@EventHandler public void PDE(final PlayerDeathEvent event) {
		final Player p = event.getEntity();
		if (Data.players.containsKey(p)) {
			final ZAPlayer zap = Data.players.get(p);
			final ZAGame zag = zap.getGame();
			final int level = zap.getGame().getLevel();
			if (zag.getRemainingPlayers() > 0) {
				p.sendMessage(ChatColor.GRAY + "You will respawn at the start of the next level!");
				new RespawnThread(zap, level, true);
			} else {
				for (final String name : zag.getPlayers()) {
					final Player player = Bukkit.getServer().getPlayer(name);
					zag.removePlayer(player);
					player.sendMessage(ChatColor.GRAY + "The game has ended. You made it to level: " + level);
				}
			}
		}
	}
}
