package com.wingx.pubmine.skill.bow;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import com.wingx.pubmine.skill.Skills;

public class Poisount extends Skills{

	public Poisount() {
		super("Poisount", "§f§oCó khả năng tẩm độc chóng mặt và", "§f§omù vào mũi tên.");
	}

	public void onApply(ProjectileHitEvent e) {
		Entity en = e.getHitEntity();
		
		if(en == null) {
			return;
		}
		
		Projectile tile = e.getEntity();
		ProjectileSource source = tile.getShooter();
		
		if(!(source instanceof Player)) {
			return;
		}
		
		Player p = (Player) source;
		
		if(new Random().nextInt(100) < 12) {
			((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3*20, 2));
			((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 3*20, 2));
			en.sendMessage("§eBạn vừa bị mù và chóng mặt bởi §c§lPoisount §ecủa §a§l" + p.getName());
			((Player) source).playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 3);
			p.sendTitle("", "§a§lPOISOUNT");
			return;
		}
	}
	
}
