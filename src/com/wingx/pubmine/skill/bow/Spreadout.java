package com.wingx.pubmine.skill.bow;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.wingx.pubmine.skill.Skills;

public class Spreadout extends Skills {

	private static final int CONE_DEGREES = 45; // The volley will spawn a cone of CONE_DEGREES in front of the player.

	public Spreadout() {
		super("Spreadout", "§f§oCó khả năng bắn ra nhiều mũi", "§f§otên cùng lúc");
	}

	public void onApply(EntityShootBowEvent e) {
		spread(e);
	}

	// SOURCE :
	// https://github.com/Taiterio/ce/blob/master/src/com/taiter/ce/Enchantments/Bow/Volley.java
	private void spread(EntityShootBowEvent e) {
		Player p = (Player) e.getEntity();
		int amount = 3; // Keep amount of arrows uneven, 2 extra arrows in a volley per level.

		Arrow oldArrow = (Arrow) e.getProjectile();
		int fireTicks = oldArrow.getFireTicks();
		int knockbackStrength = oldArrow.getKnockbackStrength();
		boolean critical = oldArrow.isCritical();

		double angleBetweenArrows = (CONE_DEGREES / (amount - 1)) * Math.PI / 180;
		double pitch = (p.getLocation().getPitch() + 90) * Math.PI / 180;
		double yaw = (p.getLocation().getYaw() + 90 - CONE_DEGREES / 2) * Math.PI / 180;

		// Starting direction values for the cone, each arrow increments it's direction
		// on these values.
		double sZ = Math.cos(pitch);

		for (int i = 0; i < amount; i++) { // spawn all arrows in a cone of 90 degrees (equally distributed).;
			double nX = Math.sin(pitch) * Math.cos(yaw + angleBetweenArrows * i);
			double nY = Math.sin(pitch) * Math.sin(yaw + angleBetweenArrows * i);
			Vector newDir = new Vector(nX, sZ, nY);

			Arrow arrow = p.launchProjectile(Arrow.class);
			arrow.setShooter(p);
			arrow.setVelocity(newDir.normalize().multiply(oldArrow.getVelocity().length())); // Need to make sure arrow
																								// has same speed as
																								// original arrow.
			arrow.setFireTicks(fireTicks); // Set the new arrows on fire if the original one was
			arrow.setKnockbackStrength(knockbackStrength);
			arrow.setCritical(critical);

			if (i == 0) {
				if (p.getGameMode().equals(GameMode.CREATIVE))
					arrow.setMetadata("ce.Volley", new FixedMetadataValue(getPlugin(), null)); // Control metadata to
																								// prevent players from
																								// duplicating arrows
			} else {
				arrow.setMetadata("ce.Volley", new FixedMetadataValue(getPlugin(), null)); // Control metadata to
																							// prevent players from
																							// duplicating arrows
			}
			arrow.setMetadata("ce.bow.enchantment", new FixedMetadataValue(getPlugin(), UUID.randomUUID()));
		}
		oldArrow.remove(); // Remove original arrow.
	}

	private Plugin getPlugin() {
		return Bukkit.getPluginManager().getPlugin("RPGTale");
	}

}
