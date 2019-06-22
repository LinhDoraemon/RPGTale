package com.wingx.pubmine.potion;

import com.wingx.pubmine.potion.type.GuardianAngelPotion;
import com.wingx.pubmine.potion.type.ImmortalPotion;
import com.wingx.pubmine.potion.type.StunPotion;
import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PotionEvents implements org.bukkit.event.Listener
{
  public PotionEvents() {}
  
  @EventHandler
  public void POTION_ACTIVATED_DAMAGE_CALL_EVENT(EntityDamageByEntityEvent e)
  {
    GuardianAngelPotion.onActivate(e);
  }
  
  @EventHandler
  public void POTION_CONSUME_CALL_EVENT(PlayerItemConsumeEvent e) {
    Player p = e.getPlayer();
    ItemStack i = e.getItem();
    
    if (i == null) {
      return;
    }
    
    if ((!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
      return;
    }
    
    if (Arrays.asList(GuardianAngelPotion.NAMES).contains(i.getItemMeta().getDisplayName())) {
      GuardianAngelPotion.onApply(e);
      return;
    }
    
    if (Arrays.asList(ImmortalPotion.NAMES).contains(i.getItemMeta().getDisplayName())) {
      ImmortalPotion.onApply(e);
      return;
    }
  }
  
  @EventHandler
  public void POTION_SPLASH_CALL_EVENT(PotionSplashEvent e) {
    org.bukkit.projectiles.ProjectileSource source = e.getEntity().getShooter();
    
    if (!(source instanceof Player)) {
      return;
    }
    
    Player p = (Player)source;
    ItemStack i = e.getPotion().getItem();
    
    if (i == null) {
      return;
    }
    
    if ((!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
      return;
    }
    
    if (Arrays.asList(StunPotion.NAMES).contains(i.getItemMeta().getDisplayName())) {
      StunPotion po = new StunPotion();
      po.onApply(e);
      return;
    }
  }
}
