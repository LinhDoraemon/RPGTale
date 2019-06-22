package com.wingx.pubmine.sword;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.wingx.pubmine.mana.ManaBar;
import com.wingx.pubmine.skill.Skills;

public class Assasault extends Skills {

	public Assasault() {
		super("Assasault", "§e§lKỸ NĂNG KÍCH HOẠT", "§f§oThoáng biến mất, tăng tốc, tăng sát thương");

		setManaCost(14);
	}

	public void onActivate(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();

		if (i == null) {
			return;
		}

		if (!i.getType().toString().toLowerCase().contains("sword")) {
			return;
		}

		ManaBar.removeMana(p, 14);
		
		p.sendTitle("", "§6§lASSASAULT");
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 4*20, 255), true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4*20, 3), true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4*20, 1), true);
		
		new BukkitRunnable() {
			double t = 0;

			public void run() {
				t = t + Math.PI / 8;
				double x, y, z;

				Location location1 = p.getLocation();
				for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 5) {
					for (double i = 0; i <= 1; i = i + 1) {
						x = 0.75 * (Math.PI - t) * Math.cos(t + phi);
						y = t;
						z = 0.75 * (Math.PI - t) * Math.sin(t + phi);
						location1.add(x, y, z);
						location1.getWorld().spawnParticle(Particle.SNOW_SHOVEL, location1, 0, 0, 0, 0, 1);
						location1.subtract(x, y, z);

						if (t >= 4 * Math.PI) {
							location1.add(x, y, z);
							location1.getWorld().spawnParticle(Particle.SNOW_SHOVEL, location1, 0, 0, 0, 0, 1);
							this.cancel();
						}

					}

				}

			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("RPGTale"), 3, 1);
	}

}
