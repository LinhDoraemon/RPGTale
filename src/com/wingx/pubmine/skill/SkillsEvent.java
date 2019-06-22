package com.wingx.pubmine.skill;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.wingx.pubmine.skill.bow.Poisount;
import com.wingx.pubmine.skill.bow.Snowingx;
import com.wingx.pubmine.skill.bow.Spreadout;
import com.wingx.pubmine.sword.Assasault;
import com.wingx.pubmine.sword.Daxusagi;
import com.wingx.pubmine.sword.Explosant;
import com.wingx.pubmine.sword.Piercing;
import com.wingx.pubmine.sword.Repulossom;
import com.wingx.pubmine.sword.Sweptious;
import com.wingx.pubmine.sword.Thorider;

import net.md_5.bungee.api.chat.TextComponent;

public class SkillsEvent implements org.bukkit.event.Listener {
	public SkillsEvent() {
	}

	public HashMap<String, Long> SKILLS_COOLDOWN = new HashMap();

	@EventHandler
	public void SKILL_PROJECTILE_HIT_CALL_EVENT(ProjectileHitEvent e) {
		new Snowingx().onActivate(e);
	
		SkillManager mn = new SkillManager(e.getEntity().getName());
		if (mn.getChooseSkill().equalsIgnoreCase("POISOUNT")) {
			new Poisount().onApply(e);
		}
	}
	
	@EventHandler
	public void SKILL_SHOOT_BOW_CALL_EVENT(EntityShootBowEvent e) {
		SkillManager mn = new SkillManager(e.getEntity().getName());

		if (mn.getChooseSkill().equalsIgnoreCase("SPREADOUT")) {
			new Spreadout().onApply(e);
			return;
		}
		
		if (mn.getChooseSkill().equalsIgnoreCase("SNOWINGX")) {
			new Snowingx().onApply(e);
			return;
		}
	}
	
	@EventHandler
    public void SKILL_PICK_UP_ARROW_CALL_EVENT(PlayerPickupItemEvent event) {
        if (event.getItem().hasMetadata("ce.Volley")) {
            event.getItem().remove();
            event.setCancelled(true);
        }
    }
	
	@EventHandler
	public void SKILL_ACTIVATE_SKILL_INTERACT_ENTITY_CALL_EVENT(PlayerInteractAtEntityEvent e) {
		SkillManager mn = new SkillManager(e.getPlayer().getName());

		if (mn.getChooseSkill().equalsIgnoreCase("THORIDER")) {
			new Thorider().onActivate(e);
			return;
		}
	}
	
	@EventHandler
	public void SKILL_ACTIVATE_SKILL_RIGHT_CALL_EVENT(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();

		if (i == null) {
			return;
		}

		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
		}

		if ((i.getType().toString().toLowerCase().contains("sword"))
				&& (new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase("SWEPTIOUS"))) {

			if (checkCooldown(p)) {
				Sweptious.SWEPTIOUS_ACTIVATOR.add(p.getUniqueId());
				p.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
						"§6§lKỸ NĂNG §A§L" + new SkillManager(p.getName()).getChooseSkill() + " §6§lĐÃ KÍCH HOẠT"));
				return;
			}
		}
		
		if ((i.getType().toString().toLowerCase().contains("sword"))
				&& (new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase("ASSASAULT"))) {

			if (checkCooldown(p)) {
				new Assasault().onActivate(e);
				return;
			}
		}
		
		if ((i.getType().toString().toLowerCase().contains("sword"))
				&& (new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase("REPULOSSOM"))) {

			if (checkCooldown(p)) {
				new Repulossom().onActivate(e);
				return;
			}
		}

		if ((i.getType().toString().toLowerCase().contains("sword"))
				&& (new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase("THORIDER"))) {

			if (Thorider.THORIDER_ACTIVATOR.contains(p.getUniqueId())) {
				p.sendMessage("§aKỹ năng §E§LTHORIDER §ađã sẵn sàng, phải chuột vào mục tiêu để kích hoạt.");
				return;
			}
			if (checkCooldown(p)) {
				Thorider.THORIDER_ACTIVATOR.add(p.getUniqueId());
				p.sendMessage("§aKỹ năng §E§LTHORIDER §ađã sẵn sàng, phải chuột vào mục tiêu để kích hoạt.");
				return;
			}
		}
	}

	@EventHandler
	public void SKILL_ENTITY_DAMAGE_CALL_EVENT(EntityDamageByEntityEvent e) {
		SkillManager mn = new SkillManager(e.getDamager().getName());

		if (mn.getChooseSkill().equalsIgnoreCase("PIERCING")) {
			new Piercing().onApply(e);
		}

		if (mn.getChooseSkill().equalsIgnoreCase("EXPLOSANT")) {
			new Explosant().onApply(e);
		}
		
		if (mn.getChooseSkill().equalsIgnoreCase("DAXUSAGI")) {
			new Daxusagi().onApply(e);
		}
	}

	@EventHandler
	public void SKILL_INTERACT_CALL_EVENT(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		SkillManager mn = new SkillManager(p.getName());

		if (mn.getChooseSkill().equalsIgnoreCase("SWEPTIOUS")) {
			new Sweptious().onActivate(e);
		}
	}

	public boolean checkCooldown(Player p) {
		int cooldownTime = 60;
		if (SKILLS_COOLDOWN.containsKey(p.getName())) {
			long secondsLeft = ((Long) SKILLS_COOLDOWN.get(p.getName())).longValue() / 1000L + cooldownTime
					- System.currentTimeMillis() / 1000L;

			if (secondsLeft > 0L) {
				p.sendMessage(ChatColor.RED + "Chưa thể kích hoạt kỹ năng §a§l"
						+ new SkillManager(p.getName()).getChooseSkill() + " §c.Vui lòng chờ thêm §e§l" + secondsLeft
						+ ChatColor.RED + " giây để dùng tiếp!");
				return false;
			}
		}
		SKILLS_COOLDOWN.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
		return true;
	}
}
