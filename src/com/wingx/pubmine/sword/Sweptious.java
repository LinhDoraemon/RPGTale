package com.wingx.pubmine.sword;

import com.wingx.pubmine.mana.ManaBar;
import com.wingx.pubmine.skill.Skills;
import com.wingx.pubmine.skill.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Sweptious extends Skills {
	public static List<UUID> SWEPTIOUS_ACTIVATOR = new ArrayList<>();

	public Sweptious() {
		super("Sweptious", new String[] { "§e§lKĨ NĂNG KÍCH HOẠT", "§f§oQuét một đường kiếm tạo ra một loạt",
				"§f§ocác tia lửa thiêu đốt mục tiêu." });
		setManaCost(25);
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

		if (SWEPTIOUS_ACTIVATOR.contains(p.getUniqueId())) {
			ManaBar.removeMana(p, 25);
			p.sendTitle("", "§6§lSWEPTIOUS", 10, 60, 10);
			Type.shootLinePlayer(p, p.getLocation(), Particle.FLAME, 10, true);
			Type.shootLinePlayer(p, p.getLocation().add(0.0D, 2.0D, 0.0D), Particle.FLAME, 10, true);
			Type.shootLinePlayer(p, p.getLocation().add(0.0D, 1.0D, 0.0D), Particle.FLAME, 10, true);
			p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
			SWEPTIOUS_ACTIVATOR.remove(p.getUniqueId());
		}
	}
}
