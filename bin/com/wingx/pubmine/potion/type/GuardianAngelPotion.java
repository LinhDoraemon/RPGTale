package com.wingx.pubmine.potion.type;

import com.wingx.pubmine.potion.CustomPotion;
import com.wingx.pubmine.potion.CustomPotionType;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.util.CountdownBossbar;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class GuardianAngelPotion extends CustomPotion
{
  public static String[] NAMES = { "§bThuốc thiên thần hộ mệnh" };
  public static List<java.util.UUID> GUARDIAN_ANGEL_LIST = new ArrayList();
  
  public GuardianAngelPotion(String displayname) {
    super(CustomPotionType.POTION);
    
    setDisplayName(displayname);
    setBaseColor(Color.GREEN);
    setDuration(15);
    setCustomEffect(Effect.GUARDIAN_ANGEL);
  }
  
  public static void onApply(PlayerItemConsumeEvent e) {
    if (!GUARDIAN_ANGEL_LIST.contains(e.getPlayer().getUniqueId())) {
      GUARDIAN_ANGEL_LIST.add(e.getPlayer().getUniqueId());
      new CountdownBossbar().showBossbar("§fHộ mạng §a[00:15]", org.bukkit.boss.BarColor.GREEN, BarStyle.SOLID, 15, new Player[] {
        e.getPlayer() });
      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("RPGTale"), 
        new Runnable() {
          public void run() {
            if (GuardianAngelPotion.GUARDIAN_ANGEL_LIST.contains(getPlayer().getUniqueId())) {
              GuardianAngelPotion.GUARDIAN_ANGEL_LIST.remove(getPlayer().getUniqueId());
            }
          }
        }, 300L);
    } else {
      e.setCancelled(true);
      e.getPlayer().sendMessage("§cBạn đang có sẵn buff §f§lHộ mạng §crồi !");
    }
  }
  
  public static void onActivate(EntityDamageByEntityEvent e)
  {
    if (!(e.getEntity() instanceof Player)) {
      return;
    }
    
    final Player p = (Player)e.getEntity();
    
    if ((p.getHealth() - e.getFinalDamage() <= 0.0D) && 
      (GUARDIAN_ANGEL_LIST.contains(p.getUniqueId())))
    {
      e.setCancelled(true);
      
      Location loc = p.getLocation();
      
      loc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc.add(0.0D, 0.0D, 0.0D), 100, 0.3D, 0.3D, 0.3D);
      p.setFlySpeed(0.0F);
      p.setGameMode(GameMode.SPECTATOR);
      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 255));
      
      p.sendTitle("§c§lBẠN ĐANG ĐƯỢC HỒI SINH...", "§aThiên Thần Hộ Mệnh", 10, 60, 20);
      
      Bukkit.getServer().getScheduler()
        .scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("RPGTale"), new Runnable() {
          public void run() {
            getWorld().spawnParticle(Particle.VILLAGER_HAPPY, add(0.0D, 0.0D, 0.0D), 100, 0.3D, 0.3D, 
              0.3D);
            p.setHealth(10.0D);
            p.setFlySpeed(0.3F);
            p.setGameMode(GameMode.SURVIVAL);
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3), true);
            
            GuardianAngelPotion.GUARDIAN_ANGEL_LIST.remove(p.getUniqueId());
          }
          
        }, 60L);
    }
  }
}
