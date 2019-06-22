package com.wingx.pubmine.potion;

import org.bukkit.Material;

public enum CustomPotionType
{
  POTION(Material.POTION),  LINGERING(Material.LINGERING_POTION),  SPLASH(Material.SPLASH_POTION);
  
  private Material material;
  
  private CustomPotionType(Material material) {
    this.material = material;
  }
  
  public Material getMaterial() {
    return material;
  }
}
