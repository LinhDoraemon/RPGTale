package com.wingx.pubmine.skill;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.wingx.pubmine.skill.bow.Poisount;
import com.wingx.pubmine.skill.bow.Snowingx;
import com.wingx.pubmine.skill.bow.Spreadout;
import com.wingx.pubmine.sword.Assasault;
import com.wingx.pubmine.sword.Daxusagi;
import com.wingx.pubmine.sword.Explosant;
import com.wingx.pubmine.sword.Piercing;
import com.wingx.pubmine.sword.Repulossom;
import com.wingx.pubmine.sword.Sweptious;
import com.wingx.pubmine.sword.Thorider;

public class SkillsGUI {
	public SkillsGUI() {
	}

	public static HashMap<String, Inventory> SKILLS_GUI = new HashMap();

	private static int[] GUI_LINE = { 1, 10, 19, 28, 37, 46 };

	public static Inventory getSkillsGUI(Player p) {
		Inventory i = (Inventory) SKILLS_GUI.get(p.getName());

		if (i == null) {
			i = Bukkit.createInventory(null, 54, "§0§lKĩ năng >>");
			for (int so : GUI_LINE) {
				i.setItem(so, new org.bukkit.inventory.ItemStack(Material.IRON_BARS));
			}

			i.setItem(2, new Piercing().getIcon(p, new Piercing()));
			i.setItem(3, new Sweptious().getIcon(p, new Sweptious()));
			i.setItem(4, new Explosant().getIcon(p, new Explosant()));
			i.setItem(5, new Thorider().getIcon(p, new Thorider()));
			i.setItem(6, new Daxusagi().getIcon(p, new Daxusagi()));
			i.setItem(7, new Assasault().getIcon(p, new Assasault()));
			i.setItem(8, new Repulossom().getIcon(p, new Repulossom()));
			
			i.setItem(11, new Spreadout().getIcon(p, new Spreadout()));
			i.setItem(12, new Snowingx().getIcon(p, new Snowingx()));
			i.setItem(13, new Poisount().getIcon(p, new Poisount()));
			
		}

		return i;
	}
}
