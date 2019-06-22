package com.wingx.pubmine.tale;

import com.wingx.pubmine.data.PlayerRegisterDataEvent;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.potion.PotionEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class RPGTale extends org.bukkit.plugin.java.JavaPlugin
{
  public RPGTale() {}
  
  public void onEnable()
  {
    Effect.init();
    Bukkit.getPluginManager().registerEvents(new PotionEvents(), this);
    Bukkit.getPluginManager().registerEvents(new com.wingx.pubmine.skill.SkillsEvent(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerRegisterDataEvent(), this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    
    if (cmd.getName().equalsIgnoreCase("choang"))
    {





      p.openInventory(com.wingx.pubmine.skill.SkillsGUI.getSkillsGUI(p));
    }
    
    return true;
  }
}
