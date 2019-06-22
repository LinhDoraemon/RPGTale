package com.wingx.pubmine.skill;

import com.wingx.pubmine.sword.Piercing;
import com.wingx.pubmine.sword.Sweptious;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SkillsGUI
{
  public SkillsGUI() {}
  
  public static HashMap<String, Inventory> SKILLS_GUI = new HashMap();
  
  private static int[] GUI_LINE = { 1, 10, 19, 28, 37, 46 };
  
  public static Inventory getSkillsGUI(Player p) {
    Inventory i = (Inventory)SKILLS_GUI.get(p.getName());
    
    if (i == null) {
      i = Bukkit.createInventory(null, 54, "§0§lKĩ năng >>");
      for (int so : GUI_LINE) {
        i.setItem(so, new org.bukkit.inventory.ItemStack(Material.IRON_BARS));
      }
      



      i.setItem(2, new Piercing().getIcon(p, new Piercing()));
      Bukkit.getConsoleSender().sendMessage("PIERCING : " + new SkillManager(p.getName()).hasSkill(new Piercing()));
      i.setItem(3, new Sweptious().getIcon(p, new Sweptious()));
      Bukkit.getConsoleSender().sendMessage("SWEPTIOUS : " + new SkillManager(p.getName()).hasSkill(new Sweptious()));
    }
    
    return i;
  }
}
