package com.github.Ablockalypse.JamesNorris.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.Ablockalypse.JamesNorris.Data.Data;
import com.github.Ablockalypse.JamesNorris.Implementation.ZAGameBase;
import com.github.Ablockalypse.JamesNorris.Implementation.ZAPlayerBase;

public class PlayerDeath implements Listener {
	/*
	 * Called when a player is killed.
	 * 
	 * Used for respawning the player after the current level.
	 */
	@EventHandler public void PDE(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if (Data.players.containsKey(p)) {
			ZAPlayerBase zap = Data.players.get(p);
			ZAGameBase zag = zap.getGame();
			if (zag.getRemainingPlayers() > 0) {
				if (zap.isInLastStand())
					zap.toggleLastStand();
				if (!zap.isInLimbo())
					zap.toggleLimbo();
			} else {
				zag.endGame();
			}
		}
	}
}
