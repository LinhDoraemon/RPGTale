package com.wingx.pubmine.potion;

import java.util.HashMap;

public enum Effect
{
  IMMORTAL("§eBất diệt"), 
  GUARDIAN_ANGEL("§aHộ mệnh"), 
  STUN("§cChoáng");
  
  public static HashMap<String, Effect> EFFECT_DATA = new HashMap();
  private String lore;
  
  private Effect(String lore)
  {
    this.lore = lore;
  }
  
  public String getLore() {
    return lore;
  }
  
  public static void init() {
    for (Effect e : ) {
      EFFECT_DATA.put(e.getLore(), e);
    }
  }
}
