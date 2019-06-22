package com.wingx.pubmine.boss;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.ArmorStand;

public abstract class ArmorstandBoss implements ArmorStand {

	private String displayname;
	private int health;
	private KeyedBossBar bar;

	public ArmorstandBoss(String displayname, int health) {
		this.displayname = displayname;
		this.health = health;
		this.bar = Bukkit.createBossBar(new NamespacedKey(Bukkit.getPluginManager().getPlugin("RPGTale"), displayname),
				displayname + " §f[§c" + (int) getHealth() + "§7/§e" + health + "§f]", BarColor.RED,
				BarStyle.SEGMENTED_6, BarFlag.PLAY_BOSS_MUSIC);
	}

	public KeyedBossBar getHealthBar() {
		return bar;
	}
	
	

}
