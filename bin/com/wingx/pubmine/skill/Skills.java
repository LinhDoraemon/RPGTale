package com.wingx.pubmine.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Skills
{
  private String name;
  private String[] description;
  private int mana_cost;
  
  public Skills(String name, String... description)
  {
    this.description = description;
    this.name = name;
  }
  
  public ItemStack getIcon(Player p, Skills skill)
  {
    if (!new SkillManager(p.getName()).hasSkill(skill)) {
      ItemStack i = new ItemStack(Material.BARRIER);
      ItemMeta mt = i.getItemMeta();
      mt.setDisplayName("§c" + name.toUpperCase());
      List<String> lore = new ArrayList();
      lore.add("§c§lKĩ năng chưa học");
      lore.addAll(Arrays.asList(description));
      lore.add("");
      lore.add("§b§lNăng lượng : §c" + mana_cost + " §eMana");
      lore.add("");
      lore.add("§eClick để học với §a§l30 cấp độ >>");
      mt.setLore(lore);
      i.setItemMeta(mt);
      return i; }
    ItemStack i;
    ItemStack i;
    if (!new SkillManager(p.getName()).getChooseSkill().equalsIgnoreCase(skill.getSkillName())) {
      i = new ItemStack(Material.BOOK);
    } else {
      i = new ItemStack(Material.ENCHANTED_BOOK);
    }
    ItemMeta mt = i.getItemMeta();
    mt.setDisplayName("§e" + name.toUpperCase());
    
    List<String> lore = new ArrayList();
    lore.add("");
    lore.addAll(Arrays.asList(description));
    lore.add("");
    lore.add("§b§lNăng lượng : §c" + mana_cost + " §eMana");
    lore.add("");
    
    mt.setLore(lore);
    i.setItemMeta(mt);
    
    return i;
  }
  
  public String[] getDescription()
  {
    return description;
  }
  
  public String getSkillName() {
    return name;
  }
  
  public void setManaCost(int cost) {
    mana_cost = cost;
  }
}
