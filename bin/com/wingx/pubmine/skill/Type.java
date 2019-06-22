package com.wingx.pubmine.skill;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Type
{
  public Type() {}
  
  public static void setDamage(Player p, int amount)
  {
    p.getWorld().spawnParticle(Particle.SPELL_WITCH, p.getLocation(), 2);
    p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, p.getLocation(), 2);
    p.setHealth(p.getHealth() - amount);
  }
  
  public static void setDamage(Entity p, int amount) {
    p.getWorld().spawnParticle(Particle.SPELL_WITCH, p.getLocation(), 2);
    p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, p.getLocation(), 2);
    ((org.bukkit.entity.Damageable)p).setHealth(((org.bukkit.entity.Damageable)p).getHealth() - amount);
  }
  






























  public static boolean shootLinePlayer(final Player p, Location loc, final Particle e, final int distance, final boolean damage_entity)
  {
    new org.bukkit.scheduler.BukkitRunnable()
    {
      double t = 0.0D;
      Vector direction = getDirection().normalize();
      
      public void run()
      {
        t += 0.5D;
        double x = direction.getX() * t;
        double y = direction.getY() * t + 1.5D;
        double z = direction.getZ() * t;
        add(x, y, z);
        getWorld().spawnParticle(e, Type.this, 0, 0.0D, 0.0D, 0.0D, 1.0D);
        subtract(x, y, z);
        
        if (damage_entity) {
          for (Entity e : getChunk().getEntities()) {
            if ((e.getLocation().distance(Type.this) < distance) && 
              (e != p)) {
              e.setFireTicks(100);
            }
          }
        }
        

        if (t > distance) {
          cancel();
        }
        
      }
      
    }.runTaskTimer(org.bukkit.Bukkit.getPluginManager().getPlugin("RPGTale"), 0L, 1L);
    
    return true;
  }
}
