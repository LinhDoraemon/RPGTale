package com.wingx.pubmine.potion.type;

import com.wingx.pubmine.potion.CustomPotion;
import com.wingx.pubmine.potion.CustomPotionType;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.util.CountdownBossbar;
import com.wingx.pubmine.util.Nearby;
import com.wingx.pubmine.util.TimeParser;
import java.util.Arrays;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class StunPotion
  extends CustomPotion
{
  public static String[] NAMES = { "§dThuốc nhật thực" };
  
  public StunPotion() {
    super(CustomPotionType.SPLASH);
    PotionMeta mt = (PotionMeta)getItemMeta();
    mt.setDisplayName("§dThuốc nhật thực");
    mt.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    mt.setLore(Arrays.asList(new String[] { Effect.STUN.getLore() + " (" + TimeParser.secondToMinutes(3) + ")" }));
    mt.setColor(Color.RED);
    setItemMeta(mt);
  }
  
  public void onApply(PotionSplashEvent e) {
    for (Entity en : Nearby.getEntitiesAroundPoint(e.getEntity().getLocation(), 3.0D)) {
      if ((en instanceof ThrownPotion)) {
        return;
      }
      ((LivingEntity)en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255));
      if ((en instanceof Player)) {
        ((Player)en).spigot().sendMessage(ChatMessageType.ACTION_BAR, 
          TextComponent.fromLegacyText("§eBạn vừa dính hiệu ứng §c§lChoáng"));
        new CountdownBossbar().showBossbar("§eChoáng I §a[00:03]", BarColor.RED, BarStyle.SOLID, 3, new Player[] {
          (Player)en });
      }
    }
  }
}
