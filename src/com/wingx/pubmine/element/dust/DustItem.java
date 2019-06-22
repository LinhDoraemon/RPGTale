package com.wingx.pubmine.element.dust;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wingx.pubmine.util.RomanNumerals;

public class DustItem {
	public enum PowderParameter {
		//
		FIRE(new double[] { 1, 2.5, 1.5, -0.5 }, new double[] { 2, 4, 2.5, -1 }, new double[] { 3, 5, 4.5, -1.5 },
				new double[] { 4.5, 6.5, 8, -2.5 }, new double[] { 6, 8, 12.5, -4.5 }),
		//
		WATER(new double[] { 1.5, 2, 1.5, -0.5 }, new double[] { 2, 3.5, 3, -0.5 }, new double[] { 3, 5, 5.5, -1.5 },
				new double[] { 4, 6, 9, -2 }, new double[] { 5.5, 7, 14, -3.5 }),
		//
		EARTH(new double[] { 1.5, 3, 1, -0.5 }, new double[] { 3, 4.5, 2, -1 }, new double[] { 4, 7, 4, -1.5 },
				new double[] { 5.5, 8, 7, -2.5 }, new double[] { 7.5, 9, 11, -4.5 }),
		//
		WIND(new double[] { 1, 3, 1.5, -0.5 }, new double[] { 2, 4.5, 3, -1 }, new double[] { 3.5, 5, 5, -1.5 },
				new double[] { 4.5, 6.5, 5, -2.5 }, new double[] { 6.5, 8.5, 12, -4.5 }),
		//
		THUNDER(new double[] { 0.5, 4, 1.5, -0.5 }, new double[] { 0.5, 6.5, 2.5, -0.5 },
				new double[] { 1, 9, 4.5, -1 }, new double[] { 1.5, 12, 7, -2 }, new double[] { 1.5, 13, 10, -3 });

		private double[] level1;
		private double[] level2;
		private double[] level3;
		private double[] level4;
		private double[] level5;

		private PowderParameter(double[] level1, double[] level2, double[] level3, double[] level4, double[] level5) {
			this.level1 = level1;
			this.level2 = level2;
			this.level3 = level3;
			this.level4 = level4;
			this.level5 = level5;
		}

		public double[] getParameter(int level) {
			switch (level) {
			case 1:
				return level1;
			case 2:
				return level2;
			case 3:
				return level3;
			case 4:
				return level4;
			default:
				return level5;
			}
		}

	}

	private static final String PART = "●●●●●";

	public static ItemStack FIRE_POWDER(int level) {
		ItemStack i = new ItemStack(Material.GUNPOWDER);
		ItemMeta mt = i.getItemMeta();

		double MIN_WEAPON = PowderParameter.FIRE.getParameter(level)[0];
		double MAX_WEAPON = PowderParameter.FIRE.getParameter(level)[1];
		double ARMOR_DEFENSE = PowderParameter.FIRE.getParameter(level)[2];
		double ARMOR_SUBSTRACT = Double
				.parseDouble(Double.toString(PowderParameter.FIRE.getParameter(level)[3]).replaceAll("-", ""));

		mt.setDisplayName("§f§lBột nâng cấp " + RomanNumerals.toNumerals(level) + " §8[§c§l✹ Fire§8]");
		if (level != 5) {
			mt.setLore(Arrays.asList(
					"§7Cấp độ " + level + " <§c" + PART.substring(0, level) + "§7" + PART.substring(level) + ">", "",
					"§4§lKhi ép vào vũ khí :", "§4- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §c§l✹ Fire §7Damage", "",
					"§4§lKhi ép vào áo giáp :", "§4- §7+" + ARMOR_DEFENSE + " §c§l✹ Fire §7Defense",
					"§4- §7-" + ARMOR_SUBSTRACT + " §b§l❉ Water §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §c§l✹ Fire §8cho một item.", "", "§9§lRare Item"));
		} else {
			mt.setLore(Arrays.asList("§7Cấp độ " + level + " <§c" + PART + "§7>", "", "§4§lKhi ép vào vũ khí :",
					"§4- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §c§l✹ Fire §7Damage", "", "§4§lKhi ép vào áo giáp :",
					"§4- §7+" + ARMOR_DEFENSE + " §c§l✹ Fire §7Defense",
					"§4- §7-" + ARMOR_SUBSTRACT + " §b§l❉ Water §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §c§l✹ Fire §8cho một item.", "", "§9§lRare Item"));
		}
		i.setItemMeta(mt);
		return i;
	}

	public static ItemStack WATER_POWDER(int level) {
		ItemStack i = new ItemStack(Material.GUNPOWDER);
		ItemMeta mt = i.getItemMeta();

		double MIN_WEAPON = PowderParameter.WATER.getParameter(level)[0];
		double MAX_WEAPON = PowderParameter.WATER.getParameter(level)[1];
		double ARMOR_DEFENSE = PowderParameter.WATER.getParameter(level)[2];
		double ARMOR_SUBSTRACT = Double
				.parseDouble(Double.toString(PowderParameter.WATER.getParameter(level)[3]).replaceAll("-", ""));

		mt.setDisplayName("§f§lBột nâng cấp " + RomanNumerals.toNumerals(level) + " §8[§b§l❉ Water§8]");
		if (level != 5) {
			mt.setLore(Arrays.asList(
					"§7Cấp độ " + level + " <§3" + PART.substring(0, level) + "§7" + PART.substring(level) + ">", "",
					"§3§lKhi ép vào vũ khí :", "§3- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §b§l❉ Water §7Damage", "",
					"§3§lKhi ép vào áo giáp :", "§3- §7+" + ARMOR_DEFENSE + " §b§l❉ Water §7Defense",
					"§3- §7-" + ARMOR_SUBSTRACT + " §e§l✦ Thunder §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §b§l❉ Water §8cho một item.", "", "§9§lRare Item"));
		} else {
			mt.setLore(Arrays.asList("§7Cấp độ " + level + " <§3" + PART + "§7>", "", "§3§lKhi ép vào vũ khí :",
					"§3- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §b§l❉ Water §7Damage", "", "§3§lKhi ép vào áo giáp :",
					"§3- §7+" + ARMOR_DEFENSE + " §b§l❉ Water §7Defense",
					"§3- §7-" + ARMOR_SUBSTRACT + " §e§l✦ Thunder §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §b§l❉ Water §8cho một item.", "", "§9§lRare Item"));
		}
		i.setItemMeta(mt);
		return i;
	}

	public static ItemStack EARTH_POWDER(int level) {
		ItemStack i = new ItemStack(Material.GUNPOWDER);
		ItemMeta mt = i.getItemMeta();

		double MIN_WEAPON = PowderParameter.EARTH.getParameter(level)[0];
		double MAX_WEAPON = PowderParameter.EARTH.getParameter(level)[1];
		double ARMOR_DEFENSE = PowderParameter.EARTH.getParameter(level)[2];
		double ARMOR_SUBSTRACT = Double
				.parseDouble(Double.toString(PowderParameter.EARTH.getParameter(level)[3]).replaceAll("-", ""));

		mt.setDisplayName("§f§lBột nâng cấp " + RomanNumerals.toNumerals(level) + " §8[§A§l✤ Earth§8]");
		if (level != 5) {
			mt.setLore(Arrays.asList(
					"§7Cấp độ " + level + " <§2" + PART.substring(0, level) + "§7" + PART.substring(level) + ">", "",
					"§2§lKhi ép vào vũ khí :", "§2- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §a§l✤ Earth §7Damage", "",
					"§2§lKhi ép vào áo giáp :", "§2- §7+" + ARMOR_DEFENSE + " §A§l✤ Earth §7Defense",
					"§2- §7-" + ARMOR_SUBSTRACT + " §7§l❋ Wind §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §a§l✤ Earth §8cho một item.", "", "§9§lRare Item"));
		} else {
			mt.setLore(Arrays.asList("§7Cấp độ " + level + " <§2" + PART + "§7>", "", "§2§lKhi ép vào vũ khí :",
					"§2- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §a§l✤ Earth §7Damage", "", "§2§lKhi ép vào áo giáp :",
					"§2- §7+" + ARMOR_DEFENSE + " §A§l✤ Earth §7Defense",
					"§2- §7-" + ARMOR_SUBSTRACT + " §7§l❋ Wind §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §a§l✤ Earth §8cho một item.", "", "§9§lRare Item"));
		}
		i.setItemMeta(mt);
		return i;
	}

	public static ItemStack WIND_POWDER(int level) {
		ItemStack i = new ItemStack(Material.GUNPOWDER);
		ItemMeta mt = i.getItemMeta();

		double MIN_WEAPON = PowderParameter.WIND.getParameter(level)[0];
		double MAX_WEAPON = PowderParameter.WIND.getParameter(level)[1];
		double ARMOR_DEFENSE = PowderParameter.WIND.getParameter(level)[2];
		double ARMOR_SUBSTRACT = Double
				.parseDouble(Double.toString(PowderParameter.WIND.getParameter(level)[3]).replaceAll("-", ""));

		mt.setDisplayName("§f§lBột nâng cấp " + RomanNumerals.toNumerals(level) + " §8[§7§l❋ Wind§8]");
		if (level != 5) {
			mt.setLore(Arrays.asList(
					"§7Cấp độ " + level + " <§8" + PART.substring(0, level) + "§7" + PART.substring(level) + ">", "",
					"§8§lKhi ép vào vũ khí :", "§8- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §7§l❋ Wind §7Damage", "",
					"§8§lKhi ép vào áo giáp :", "§8- §7+" + ARMOR_DEFENSE + " §7§l❋ Wind §7Defense",
					"§8- §7-" + ARMOR_SUBSTRACT + " §c§l✹ Fire §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §7§l❋ Wind §8cho một item.", "", "§9§lRare Item"));
		} else {
			mt.setLore(Arrays.asList("§7Cấp độ " + level + " <§8" + PART + "§7>", "", "§8§lKhi ép vào vũ khí :",
					"§8- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §7§l❋ Wind §7Damage", "", "§8§lKhi ép vào áo giáp :",
					"§8- §7+" + ARMOR_DEFENSE + " §7§l❋ Wind §7Defense",
					"§8- §7-" + ARMOR_SUBSTRACT + " §c§l✹ Fire §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §7§l❋ Wind §8cho một item.", "", "§9§lRare Item"));
		}
		i.setItemMeta(mt);
		return i;
	}

	public static ItemStack THUNDER_POWDER(int level) {
		ItemStack i = new ItemStack(Material.GUNPOWDER);
		ItemMeta mt = i.getItemMeta();

		double MIN_WEAPON = PowderParameter.THUNDER.getParameter(level)[0];
		double MAX_WEAPON = PowderParameter.THUNDER.getParameter(level)[1];
		double ARMOR_DEFENSE = PowderParameter.THUNDER.getParameter(level)[2];
		double ARMOR_SUBSTRACT = Double
				.parseDouble(Double.toString(PowderParameter.THUNDER.getParameter(level)[3]).replaceAll("-", ""));

		mt.setDisplayName("§f§lBột nâng cấp " + RomanNumerals.toNumerals(level) + " §8[§e§l✦ Thunder§8]");
		if (level != 5) {
			mt.setLore(Arrays.asList(
					"§7Cấp độ " + level + " <§6" + PART.substring(0, level) + "§7" + PART.substring(level) + ">", "",
					"§6§lKhi ép vào vũ khí :", "§6- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §e§l✦ Thunder §7Damage",
					"", "§6§lKhi ép vào áo giáp :", "§6- §7+" + ARMOR_DEFENSE + " §e§l✦ Thunder §7Defense",
					"§6- §7-" + ARMOR_SUBSTRACT + " §a§l✤ Earth §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §e§l✦ Thunder §8cho một item.", "", "§9§lRare Item"));
		} else {
			mt.setLore(Arrays.asList("§7Cấp độ " + level + " <§6" + PART + "§7>", "", "§6§lKhi ép vào vũ khí :",
					"§6- §7+" + MIN_WEAPON + "-" + MAX_WEAPON + " §e§l✦ Thunder §7Damage", "",
					"§6§lKhi ép vào áo giáp :", "§6- §7+" + ARMOR_DEFENSE + " §e§l✦ Thunder §7Defense",
					"§6- §7-" + ARMOR_SUBSTRACT + " §a§l✤ Earth §7Defense", "",
					"§8Thêm một vài chỉ số của các nguyên tố", "§8vào vật phẩm của bạn. Chỉ ép được một",
					"§8bột nâng cấp loại §e§l✦ Thunder §8cho một item.", "", "§9§lRare Item"));
		}
		i.setItemMeta(mt);
		return i;
	}

}
