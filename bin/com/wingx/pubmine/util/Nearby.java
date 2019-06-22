package com.wingx.pubmine.util;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.v1_14_R1.MathHelper;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class Nearby
{
  public Nearby() {}
  
  public static List<Entity> getEntitiesAroundPoint(Location location, double radius)
  {
    List<Entity> entities = new java.util.ArrayList();
    World world = location.getWorld();
    

    int smallX = MathHelper.floor((location.getX() - radius) / 16.0D);
    int bigX = MathHelper.floor((location.getX() + radius) / 16.0D);
    int smallZ = MathHelper.floor((location.getZ() - radius) / 16.0D);
    int bigZ = MathHelper.floor((location.getZ() + radius) / 16.0D);
    
    for (int x = smallX; x <= bigX; x++) {
      for (int z = smallZ; z <= bigZ; z++) {
        if (world.isChunkLoaded(x, z)) {
          entities.addAll(java.util.Arrays.asList(world.getChunkAt(x, z).getEntities()));
        }
      }
    }
    




    Iterator<Entity> entityIterator = entities.iterator();
    
    while (entityIterator.hasNext()) {
      if (((Entity)entityIterator.next()).getLocation().distanceSquared(location) > radius * radius)
      {

        entityIterator.remove();
      }
    }
    return entities;
  }
}
