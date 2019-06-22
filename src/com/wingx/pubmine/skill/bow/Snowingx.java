package com.wingx.pubmine.skill.bow;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wingx.pubmine.skill.Skills;

public class Snowingx extends Skills {

	public Snowingx() {
		super("Snowingx", "§f§oBắn ra một loạt cầu tuyết cùng với mũi", "§f§otên làm chậm mục tiêu");

		setManaCost(4);
	}

	public void onApply(EntityShootBowEvent e) {
		for (int i = 0; i < 5; i++) {
			Snowball ball = e.getEntity().launchProjectile(Snowball.class);
			ball.setGlowing(true);
			ball.setMetadata("snowingx_ball", new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("RPGTale"),
					e.getEntity().getUniqueId()));
			ball.setShooter(e.getEntity());
		}
	}

	public void onActivate(ProjectileHitEvent e) {
		Entity en = e.getHitEntity();

		if (en == null) {
			return;
		}

		Projectile pro = e.getEntity();

		if (!(pro instanceof Snowball)) {
			return;
		}

		Snowball s = (Snowball) pro;
		Entity source = (Entity) s.getShooter();

		if (s.hasMetadata("snowingx_ball")) {
			((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 2));
			if (source instanceof Player) {
				en.sendMessage("§eBạn vừa bị làm chậm bởi §b§lSnowingx §ecủa §a§l" + source.getName());
				((Player) source).playSound(source.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 3);
			}
			return;
		}

	}

}
