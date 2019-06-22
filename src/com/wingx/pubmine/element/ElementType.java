package com.wingx.pubmine.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum ElementType {

	// FIRE ELEMENT
	FIRE("§c§l✹ Fire §8[§7§l○§8] §8<§c§l • §8>", new String[] { "EARTH", "WIND" }, new String[] { "WATER", "THUNDER" }),
	// NATURAL AND LAND ELEMENT
	EARTH("§a§l✤ Earth §8[§7§l○§8] §8<§a§l • §8>", new String[] { "WATER", "WIND" }, new String[] { "THUNDER", "FIRE" }),
	// RIVER AND SEA ELEMENT
	WATER("§b§l❉ Water §8[§7§l○§8] §8<§b§l • §8>", new String[] { "FIRE", "THUNDER" }, new String[] { "WIND", "EARTH" }),
	// AIR ELEMENT
	WIND("§7§l❋ Wind §8[§7§l○§8] §8<§7§l • §8>", new String[] { "THUNDER", "FIRE" }, new String[] { "WATER", "EARTH" }),
	// WEATHER ELEMENT
	THUNDER("§e§l✦ Thunder §8[§7§l○§8] §8<§e§l • §8>", new String[] { "EARTH", "WATER" }, new String[] { "FIRE", "WIND" });

	private String icon;
	private String[] opposite;
	private String[] likely;

	public String getIcon() {
		return icon;
	}

	ElementType(String icon, String[] opposite, String[] likely) {
		this.icon = icon;
		this.opposite = opposite;
		this.likely = likely;
	}

	public List<ElementType> getOpposite() {
		List<ElementType> list = new ArrayList<>();
		for (String s : opposite) {
			list.add(ElementType.valueOf(s));
		}
		return list;
	}

	public List<ElementType> getLikely() {
		List<ElementType> list = new ArrayList<>();
		for (String s : likely) {
			list.add(ElementType.valueOf(s));
		}
		return list;
	}

	public static List<ElementType> getElementsInItem(ItemStack i) {
		if (i.hasItemMeta() == false || i.getItemMeta().hasLore() == false) {
			return null;
		}

		List<ElementType> list = new ArrayList<>();
		for (String s : i.getItemMeta().getLore()) {
			if (s.startsWith(FIRE.getIcon())) {
				list.add(FIRE);
				continue;
			}
			if (s.startsWith(WATER.getIcon())) {
				list.add(WATER);
				continue;
			}
			if (s.startsWith(WIND.getIcon())) {
				list.add(WIND);
				continue;
			}
			if (s.startsWith(EARTH.getIcon())) {
				list.add(EARTH);
				continue;
			}
			if (s.startsWith(THUNDER.getIcon())) {
				list.add(THUNDER);
				continue;
			}
		}
		return list;
	}

	public static boolean hasElementsInItem(ItemStack i) {
		if (getElementsInItem(i) == null || getElementsInItem(i).size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void addElementLore(Player p, ItemStack i, int serial_min, int serial_max, boolean def) {
		
		String defe;
		
		if(def == true) {
			defe = "Defense";
		}else {
			defe = "Attack";
		}
		
		ItemMeta mt = i.getItemMeta();
		if(mt.hasLore() == false) {
			mt.setLore(Arrays.asList(getIcon().replaceAll("○", defe).replaceAll("•", serial_min + "-" + serial_max)));
		}else {
			List<String> lores = mt.getLore();
			lores.add(getIcon().replaceAll("○", defe).replaceAll("•", serial_min + "-" + serial_max)); 
			mt.setLore(lores);
		}
		i.setItemMeta(mt);
		p.updateInventory();
	}

	public int getSerial(ItemStack i) {
		if (i.hasItemMeta() == false || i.getItemMeta().hasLore() == false) {
			return 0;
		}

		for (String s : i.getItemMeta().getLore()) {
			if (s.startsWith(getIcon())) {
				String seri = s.split(" ")[4];
				int min = Integer.parseInt(seri.split("-")[0]);
				int max = Integer.parseInt(seri.split("-")[1]);
				return new Random().nextInt(max-min) + min;
			}
			continue;
		}

		return 0;
	}
}
