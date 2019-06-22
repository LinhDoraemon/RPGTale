package com.wingx.pubmine.sword;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.wingx.pubmine.mana.ManaBar;
import com.wingx.pubmine.skill.Skills;
import com.wingx.pubmine.util.Nearby;

public class Thorider extends Skills {

	public static List<UUID> THORIDER_ACTIVATOR = new ArrayList<>();

	public Thorider() {
		super("Thorider", "§e§lKĨ NĂNG KÍCH HOẠT", "§f§oVẽ một vòng tròn xung quanh kẻ thù, sau đó",
				"§f§oliên tục triệu hồi sấm sét xuống tâm vòng tròn");

		setManaCost(30);
	}

	public void onActivate(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();

		Entity en = e.getRightClicked();

		if (i == null) {
			return;
		}

		if (!i.getType().toString().toLowerCase().contains("sword")) {
			return;
		}

		if (THORIDER_ACTIVATOR.contains(p.getUniqueId())) {
			ManaBar.removeMana(p, 30);
			p.sendTitle("", "§6§lTHORIDER", 10, 60, 10);
			p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0F, 1.0F);
			new ThoriderActivate(Particle.CRIT_MAGIC, en.getLocation(), 3, 3)
					.runTaskTimer(Bukkit.getPluginManager().getPlugin("RPGTale"), 20, 20);
			THORIDER_ACTIVATOR.remove(p.getUniqueId());
			return;
		}
	}

	public class ThoriderActivate extends BukkitRunnable {

		private Location loc;
		private int duration;
		private int radius;
		private Particle par;

		public ThoriderActivate(Particle par, Location loc, int duration, int radius) {
			this.loc = loc;
			this.par = par;
			this.duration = duration;
			this.radius = radius;
		}

		@Override
		public void run() {
			duration--;
			for (double t = 0; t < 50; t += 0.5) {
				float x = radius * (float) Math.sin(t);
				float z = radius * (float) Math.cos(t);
				loc.getWorld().spawnParticle(par,
						new Location(loc.getWorld(), loc.getX() + x, loc.getY(), loc.getZ() + z), 1);
			}
			loc.getWorld().strikeLightningEffect(loc);
			for (Entity en : Nearby.getEntitiesAroundPoint(loc, radius)) {
				en.setFireTicks(60);
			}

			if (duration == 0) {
				cancel();
			}
		}

	}

}
