package com.wingx.pubmine.element.dust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wingx.pubmine.element.ElementType;
import com.wingx.pubmine.util.RomanNumerals;

public class ElementDust {

	public static ElementType getDustElement(ItemStack i) {
		if (i.hasItemMeta() == false || i.getItemMeta().hasDisplayName() == false) {
			return null;
		}

		if (i.getItemMeta().getDisplayName().contains("§c§l✹ Fire")) {
			return ElementType.FIRE;
		}
		if (i.getItemMeta().getDisplayName().contains("§a§l✤ Earth")) {
			return ElementType.EARTH;
		}
		if (i.getItemMeta().getDisplayName().contains("§b§l❉ Water")) {
			return ElementType.WATER;
		}
		if (i.getItemMeta().getDisplayName().contains("§7§l❋ Wind")) {
			return ElementType.WIND;
		}
		if (i.getItemMeta().getDisplayName().contains("§e§l✦ Thunder")) {
			return ElementType.THUNDER;
		}
		return null;
	}

	public static ItemStack getDust(ElementType type, int level) {
		switch (type) {
		case FIRE:
			return DustItem.FIRE_POWDER(level);
		case EARTH:
			return DustItem.EARTH_POWDER(level);
		case WIND:
			return DustItem.WIND_POWDER(level);
		case WATER:
			return DustItem.WATER_POWDER(level);
		default:
			return DustItem.THUNDER_POWDER(level);
		}
	}

	public static void addDustSlot(Player p, ItemStack i) {
		ItemMeta mt = i.getItemMeta();
		if (mt.hasLore() == false) {
			mt.setLore(Arrays.asList("§f- <Ô chứa bột nâng cấp>"));
		} else {
			List<String> lores = mt.getLore();
			lores.add("§f- <Ô chứa bột nâng cấp>");
			mt.setLore(lores);
		}
		i.setItemMeta(mt);
		p.updateInventory();
	}

	public static boolean hasDustSlot(ItemStack i) {
		if (i.hasItemMeta() == false || i.getItemMeta().hasLore() == false) {
			return false;
		}

		if (i.getItemMeta().getLore().contains("§f- <Ô chứa bột nâng cấp>")) {
			return true;
		}

		return false;
	}


	public static boolean isElementDust(ItemStack dust) {
		if (dust.hasItemMeta() == false || dust.getItemMeta().hasDisplayName() == false) {
			return false;
		}

		return dust.getItemMeta().getDisplayName().contains("Bột nâng cấp");
	}

	public static int getDustLevel(ItemStack dust) {
		if (dust.hasItemMeta() == false || dust.getItemMeta().hasDisplayName() == false) {
			return 0;
		}

		if (isElementDust(dust) == false) {
			return 0;
		}

		String name = dust.getItemMeta().getDisplayName();
		String roman = name.split(" ")[3];

		return RomanNumerals.fromNumerals(roman);
	}

	public static boolean applyDust(Player p, ItemStack i, ItemStack dust) {
		if (hasDustSlot(i) == false) {
			return false;
		}

		if (dust.hasItemMeta() == false || dust.getItemMeta().hasDisplayName() == false) {
			return false;
		}

		if (i.hasItemMeta() == false || dust.getItemMeta().hasLore() == false) {
			return false;
		}

		if (isElementDust(dust) == false) {
			return false;
		}

		ItemMeta mt = i.getItemMeta();
		List<String> lores = mt.getLore();

		List<String> new_lores = new ArrayList<String>();

		if (lores.contains("§f- " + dust.getItemMeta().getDisplayName())) {
			p.sendMessage("§cVật phẩm đã ép bột nâng cấp này rồi !");
			return false;
		}

		for (String s : lores) {
			if (s.contains("- <Ô chứa bột nâng cấp>")) {

				boolean is_yet = false;
				boolean is_yett = false;

				for (String ss : lores) {
					if (ss.contains("<Ô chứa bột nâng cấp>") && is_yett == false) {
						is_yet = true;
					}

					if (is_yet == true) {
						new_lores.add(ss.replaceAll("§f- <Ô chứa bột nâng cấp>",
								"§f- " + dust.getItemMeta().getDisplayName()));
						is_yet = false;
						is_yett = true;
						continue;
					}

					if (is_yet == false) {
						new_lores.add(ss);
					}

				}

				break;

			} else {
				continue;
			}
		}

		mt.setLore(new_lores);
		i.setItemMeta(mt);
		p.updateInventory();

		return true;
	}

	public static boolean removeDust(Player p, ItemStack i, ItemStack dust) {
		if (dust.hasItemMeta() == false || dust.getItemMeta().hasDisplayName() == false) {
			return false;
		}

		if (isElementDust(dust) == false) {
			p.sendMessage("§cĐó không phải là bột nâng cấp. Vui lòng xem lại");
			return false;
		}

		ItemMeta mt = i.getItemMeta();
		List<String> lores = mt.getLore();

		List<String> new_lores = new ArrayList();

		if (lores.contains("§f- " + dust.getItemMeta().getDisplayName()) == false) {
			p.sendMessage("§cVật phẩm chưa được ép bột nâng cấp " + dust.getItemMeta().getDisplayName() + " §c!");
			return false;
		}

		for (String s : lores) {

			if(s.contains(dust.getItemMeta().getDisplayName())) {
				new_lores.add(s.replaceAll(dust.getItemMeta().getDisplayName(), "<Ô chứa bột nâng cấp>"));
			}else {
				new_lores.add(s);
			}
			
		}

		mt.setLore(new_lores);
		i.setItemMeta(mt);
		p.updateInventory();

		return true;

	}

	public static List<String> replaceArgs(List<String> strings, String[][] args) {
		List<String> list = new ArrayList<>();
		for (String s : strings) {
			list.add(replaceArgs(s, args));
		}
		return list;
	}

	public static String replaceArgs(String string, String[][] args) {
		String s = string;
		for (String[] arg : args) {
			s = s.replace(arg[0], arg[1]);
		}
		return s;
	}
}
