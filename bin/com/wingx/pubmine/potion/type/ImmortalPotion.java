package com.wingx.pubmine.potion.type;

import com.wingx.pubmine.potion.CustomPotion;
import com.wingx.pubmine.potion.CustomPotionType;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.util.CountdownBossbar;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ImmortalPotion extends CustomPotion
{
  public static String[] NAMES = { "§aThuốc trường sinh" };
  
  public ImmortalPotion(String displayname) {
    super(CustomPotionType.POTION);
    
    setDisplayName(displayname);
    setBaseColor(Color.ORANGE);
    setDuration(8);
    setCustomEffect(Effect.IMMORTAL);
  }
  
  public static void onApply(PlayerItemConsumeEvent e) {
    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160, 255));
    new CountdownBossbar().showBossbar("§eBất diệt §a[00:08]", BarColor.GREEN, BarStyle.SOLID, 8, new Player[] { e.getPlayer() });
  }
}
