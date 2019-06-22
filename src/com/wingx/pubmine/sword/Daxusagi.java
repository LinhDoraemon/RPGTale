package com.wingx.pubmine.sword;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.wingx.pubmine.skill.Skills;

public class Daxusagi extends Skills {

	public Daxusagi() {
		super("Daxusagi", "§f§oCó khả năng hất tung đối thủ lên trời");
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
			p.sendTitle("", "§a§lDAXUSAGI");
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			e.getEntity().setVelocity(new Vector(0.0F, 8F, 0.0F));
			return;
		}
	}
	
}
