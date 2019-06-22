package com.wingx.pubmine.skill;

import com.wingx.pubmine.sword.Piercing;
import com.wingx.pubmine.sword.Sweptious;
import java.util.HashMap;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SkillsEvent implements org.bukkit.event.Listener
{
  public SkillsEvent() {}
  
  public HashMap<String, Long> SKILLS_COOLDOWN = new HashMap();
  
  @EventHandler
  public void SKILL_ACTIVATE_SKILL_CALL_EVENT(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    ItemStack i = p.getInventory().getItemInMainHand();
    
    if (i == null) {
      return;
    }
    
    if ((i.getType().toString().toLowerCase().contains("sword")) && 
      (new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase("SWEPTIOUS"))) {
      if ((e.getAction() != Action.RIGHT_CLICK_AIR) || (e.getAction() != Action.RIGHT_CLICK_BLOCK)) {
        return;
      }
      if (checkCooldown(p)) {
        Sweptious.SWEPTIOUS_ACTIVATOR.add(p.getUniqueId());
        p.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
          "§6§lKỸ NĂNG §A§L" + new SkillManager(p.getName()).getChooseSkill() + " §6§lĐÃ KÍCH HOẠT"));
        return;
      }
    }
  }
  

  @EventHandler
  public void SKILL_ENTITY_DAMAGE_CALL_EVENT(EntityDamageByEntityEvent e)
  {
    SkillManager mn = new SkillManager(e.getDamager().getName());
    
    if (mn.getChooseSkill().equalsIgnoreCase("PIERCING")) {
      new Piercing().onApply(e);
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
    if (SKILLS_COOLDOWN.containsKey(p.getName()))
    {
      long secondsLeft = ((Long)SKILLS_COOLDOWN.get(p.getName())).longValue() / 1000L + cooldownTime - 
        System.currentTimeMillis() / 1000L;
      
      if (secondsLeft > 0L) {
        p.sendMessage(ChatColor.RED + "Chưa thể kích hoạt kỹ năng §a§l" + 
          new SkillManager(p.getName()).getChooseSkill() + " §c.Vui lòng chờ thêm §e§l" + secondsLeft + 
          ChatColor.RED + " giây để dùng tiếp!");
        return false;
      }
    }
    SKILLS_COOLDOWN.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
    return true;
  }
}
