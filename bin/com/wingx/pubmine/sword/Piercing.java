package com.wingx.pubmine.sword;

import com.wingx.pubmine.skill.Skills;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Piercing extends Skills
{
  public Piercing()
  {
    super("Piercing", new String[] { "§f§oCó cơ hội sát thương xuyên giáp mục tiêu." });
    setManaCost(0);
  }
  
  public void onApply(EntityDamageByEntityEvent e)
  {
    if (!(e.getDamager() instanceof Player)) {
      return;
    }
    
    Player p = (Player)e.getDamager();
    double dmg = e.getDamage();
    
    if (!p.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("sword")) {
      return;
    }
    
    if (new Random().nextInt(100) < 10) {
      e.setDamage(dmg + 3.0D);
      p.sendMessage("§a§lBạn đã gây sát thương xuyên giáp mục tiêu.");
      e.getEntity().getWorld().spawnParticle(org.bukkit.Particle.CRIT, e.getEntity().getLocation(), 1);
      return;
    }
  }
}
