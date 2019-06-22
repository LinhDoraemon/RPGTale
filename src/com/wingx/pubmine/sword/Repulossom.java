package com.wingx.pubmine.sword;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.wingx.pubmine.mana.ManaBar;
import com.wingx.pubmine.skill.Skills;
import com.wingx.pubmine.skill.Type;
import com.wingx.pubmine.util.Nearby;

public class Repulossom extends Skills {

	public Repulossom() {
		super("Repulossom", "§e§lKỸ NĂNG KÍCH HOẠT", "§f§oĐẩy lùi tất cả mục tiêu trong một", "§f§okhoảng nhất định");
		
		setManaCost(23);
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

		ManaBar.removeMana(p, 23);
		p.sendTitle("", "§6§lREPULOSSOM");
		Type.drawCircle(p.getLocation(), 5, Particle.TOTEM);
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
		for(Entity en : Nearby.getEntitiesAroundPoint(p.getLocation(), 5)) {
			final Vector velocity = en.getLocation().subtract(p.getLocation()).toVector();
            velocity.setY(velocity.getY() / 3);
            en.setVelocity(velocity.multiply(8 / (1 + velocity.lengthSquared())));
		}
		
	}
	
}
