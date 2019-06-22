package com.wingx.pubmine.mana;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;










public class ManaBar
{
  public static HashMap<UUID, BossBar> MANA_BARS = new HashMap();
  

  public ManaBar() {}
  

  public static BossBar getManaBar(Player p)
  {
    BossBar i = (BossBar)MANA_BARS.get(p.getUniqueId());
    
    if (i == null) {
      i = Bukkit.createBossBar("§b§lNĂNG LƯỢNG §7[§c100§7/§6100§7]", BarColor.BLUE, BarStyle.SOLID, new BarFlag[] {
        BarFlag.PLAY_BOSS_MUSIC });
      i.addPlayer(p);
      MANA_BARS.put(p.getUniqueId(), i);
    }
    
    return i;
  }
  




  public static double getMana(Player p)
  {
    return getManaBar(p).getProgress() * 100.0D;
  }
  




  public static void addMana(Player p, double amount)
  {
    double phanthem = amount / 100.0D;
    getManaBar(p).setProgress(getManaBar(p).getProgress() + phanthem);
    update(p);
  }
  






  public static boolean removeMana(Player p, double amount)
  {
    double phanthem = amount / 100.0D;
    if (getManaBar(p).getProgress() > phanthem) {
      getManaBar(p).setProgress(getManaBar(p).getProgress() - phanthem);
      update(p);
    } else {
      p.sendMessage("§c§lKHÔNG ĐỦ NĂNG LƯỢNG ĐỂ THI TRIỂN KỸ NĂNG.");
      return false;
    }
    return true;
  }
  



  public static void resetMana(Player p)
  {
    getManaBar(p).setProgress(1.0D);
    update(p);
  }
  



  private static void update(Player p)
  {
    Double d = new Double(getManaBar(p).getProgress() * 100.0D);
    int n = d.intValue();
    getManaBar(p).setTitle("§b§lNĂNG LƯỢNG §7[§c" + n + "§7/§6100§7]");
  }
}
