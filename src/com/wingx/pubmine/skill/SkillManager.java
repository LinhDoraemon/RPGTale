package com.wingx.pubmine.skill;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SkillManager
{
  private String p;
  
  public SkillManager(String p)
  {
    this.p = p;
  }
  
  public String getChooseSkill() {
    File file = new File(
      "plugins" + File.separator + "RPGTale" + File.separator + "players" + File.separator + p + ".yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    return config.getString("CHOOSE_SKILL");
  }
  
  public void setChooseSkill(Skills skill) {
    File file = new File(
      "plugins" + File.separator + "RPGTale" + File.separator + "players" + File.separator + p + ".yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    config.set("CHOOSE_SKILL", skill.getSkillName().toUpperCase().replace(' ', '_'));
    try {
      config.save(file);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean hasSkill(Skills skill) {
    File file = new File(
      "plugins" + File.separator + "RPGTale" + File.separator + "players" + File.separator + p + ".yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    return config.getBoolean("SKILLS." + skill.getSkillName().toUpperCase());
  }
  
  public void setSkill(Skills skill, boolean learnt) {
    File file = new File(
      "plugins" + File.separator + "RPGTale" + File.separator + "players" + File.separator + p + ".yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    config.set("SKILLS." + skill.getSkillName().toUpperCase().replace(' ', '_'), Boolean.valueOf(learnt));
    try {
      config.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
