package com.wingx.pubmine.mana;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaRegeneration extends BukkitRunnable {

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(ManaBar.getMana(p) < 100) {
				ManaBar.addMana(p, 1);
			}
		}
	}

	
	
}
