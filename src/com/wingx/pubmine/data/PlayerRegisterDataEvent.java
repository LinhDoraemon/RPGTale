package com.wingx.pubmine.data;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerRegisterDataEvent implements org.bukkit.event.Listener {
	public PlayerRegisterDataEvent() {
	}

	@EventHandler
	public void REGISTER_DATA_ON_JOIN(AsyncPlayerPreLoginEvent e) {
		String name = e.getName();

		File file = new File(
				"plugins" + File.separator + "RPGTale" + File.separator + "players" + File.separator + name + ".yml");

		if (!file.exists()) {
			try {
				Bukkit.getLogger().info("Registering data file for player has UUID : " + e.getUniqueId().toString());
				file.createNewFile();
				Bukkit.getLogger().info("Registered data successfully ! Setting up config file ....");
				FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
				config.set("CHOOSE_SKILL", "PIERCING");
				config.set("SKILLS.PIERCING", Boolean.valueOf(true));
				config.set("SKILLS.SWEPTIOUS", Boolean.valueOf(false));
				config.set("SKILLS.EXPLOSANT", Boolean.valueOf(false));
				config.set("SKILLS.THORIDER", Boolean.valueOf(false));
				config.set("SKILLS.DAXUSAGI", Boolean.valueOf(false));
				config.set("SKILLS.ASSASAULT", Boolean.valueOf(false));
				config.set("SKILLS.REPULOSSOM", Boolean.valueOf(false));
				config.set("SKILLS.SPREADOUT", Boolean.valueOf(false));
				config.set("SKILLS.SNOWINGX", Boolean.valueOf(false));
				config.set("SKILLS.POISOUNT", Boolean.valueOf(false));
				config.save(file);
				Bukkit.getLogger().info("Setting done !");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
