package com.github.JamesNorris.Event.Bukkit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;

import com.github.JamesNorris.Data.Data;
import com.github.JamesNorris.Implementation.GameUndead;

public class EntityBreakDoor implements Listener {
	@EventHandler public void EBDE(EntityBreakDoorEvent event) {
		LivingEntity e = event.getEntity();
		if (e instanceof Zombie) {
			Zombie z = (Zombie) e;
			for (GameUndead gz : Data.undead)
				if (gz.getZombie() == z)
					event.setCancelled(true);
		}
	}
}