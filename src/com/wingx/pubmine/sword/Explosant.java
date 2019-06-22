package com.wingx.pubmine.sword;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.wingx.pubmine.skill.Skills;

public class Explosant extends Skills {

	public Explosant() {
		super("Explosant", "§f§oCó cơ hội tạo ra một vụ nổ", "§f§otrên người kẻ thù.");

		setManaCost(18);
	}

	public void onApply(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}

		Player p = (Player) e.getDamager();

		if (!p.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("sword")) {
			return;
		}

		if (new Random().nextInt(100) < 10) {
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			p.sendTitle("", "§a§lEXPLOSANT");
			Location loc = e.getEntity().getLocation();
			p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
			if (e.getEntity() instanceof Player) {
				((Player) e.getEntity()).playSound(e.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
			}
			loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 2);
			e.setDamage(e.getDamage() + 2.4);
			return;
		}
	}

}
