package com.wingx.pubmine.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wingx.pubmine.element.ElementType;
import com.wingx.pubmine.util.RomanNumerals;

public class ItemGenerator {

	private ItemType type;
	private Material mat;

	public ItemGenerator(ItemType type, Material mat) {
		this.type = type;
		this.mat = mat;
	}

	private String RANDOM_CODE_BOLD;

	public ItemStack getRandomItem() {
		ItemStack i = new ItemStack(mat);
		ItemMeta mt = i.getItemMeta();

		List<Enchanter> enchant_list = getRandomEnchant(i.getType());

		String ran1 = Arrays.asList(ItemName.NAMES).get(new Random().nextInt(ItemName.NAMES.length));
		String ran2 = Arrays.asList(ItemName.NAMES).get(new Random().nextInt(ItemName.NAMES.length));

		if (ran1 == ran2) {
			ran2 = Arrays.asList(ItemName.NAMES).get(new Random().nextInt(ItemName.NAMES.length));
		}

		String random_name = ran1 + randomOf() + ran2;

		String RANDOM_CODE_BOLDd = randomColorCoded();
		this.RANDOM_CODE_BOLD = RANDOM_CODE_BOLDd;
		String RANDOM_CODE_NORMAL = RANDOM_CODE_BOLD.substring(0, 2);

		mt.setDisplayName(RANDOM_CODE_BOLD + random_name);

		List<String> lores = new ArrayList<>();
		lores.add(type.getDescription());
		lores.add("");
		for (Enchanter enchant : enchant_list) {
			int lvl = new Random().nextInt(enchant.getMaxLevel()) + 1;
			if (i.containsEnchantment(enchant.getRootEnchantment())) {
				continue;
			}
			i.addUnsafeEnchantment(enchant.getRootEnchantment(), lvl);
			mt.addEnchant(enchant.getRootEnchantment(), lvl, true);
			lores.add(" §7" + enchant.getLore() + RomanNumerals.toNumerals(lvl));
		}
		lores.add("");
		lores.add(RANDOM_CODE_BOLD + "NGUYÊN TỐ " + RANDOM_CODE_NORMAL.concat("§f§o") + "< Elements >");
		lores.addAll(getRandomElements());
		lores.add("");

		mt.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		mt.setLore(lores);
		i.setItemMeta(mt);

		return i;
	}

	private String randomOf() {
		if (new Random().nextInt(100) < 20) {
			return " of ";
		} else {
			return " ";
		}
	}

	private String[] COLOR_CODE = { "§a§l", "§b§l", "§c§l", "§d§l", "§e§l", "§3§l", "§6§l", "§7§l", "§9§l" };

	public String randomColorCoded() {
		return Arrays.asList(COLOR_CODE).get(new Random().nextInt(COLOR_CODE.length));
	}

	public List<String> getRandomElements() {
		int so = new Random().nextInt(5) + 1;
		String RANDOM_CODE_NORMAL = RANDOM_CODE_BOLD.substring(0, 2);

		switch (so) {
		case 2:
			return Arrays.asList(
					RANDOM_CODE_NORMAL + "- "
							+ ElementType.FIRE.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
									getRandomSerial(8, 30)),
					RANDOM_CODE_NORMAL + "- " + ElementType.WATER.getIcon().replaceAll("○", getRandomDefense())
							.replaceAll("•", getRandomSerial(8, 30)));
		case 3:
			String one = ElementType.FIRE.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String two = ElementType.WATER.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String three = ElementType.EARTH.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			return Arrays.asList(RANDOM_CODE_NORMAL + "- " + one, RANDOM_CODE_NORMAL + "- " + two,
					RANDOM_CODE_NORMAL + "- " + three);
		case 4:
			String one1 = ElementType.FIRE.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String two1 = ElementType.WATER.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String three1 = ElementType.EARTH.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String four1 = ElementType.WIND.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			return Arrays.asList(RANDOM_CODE_NORMAL + "- " + one1, RANDOM_CODE_NORMAL + "- " + two1,
					RANDOM_CODE_NORMAL + "- " + three1, RANDOM_CODE_NORMAL + "- " + four1);
		case 5:
			String one2 = ElementType.FIRE.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String two2 = ElementType.WATER.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String three2 = ElementType.EARTH.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String four2 = ElementType.WIND.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			String five2 = ElementType.THUNDER.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•",
					getRandomSerial(4, 30));
			return Arrays.asList(RANDOM_CODE_NORMAL + "- " + one2, RANDOM_CODE_NORMAL + "- " + two2,
					RANDOM_CODE_NORMAL + "- " + three2, RANDOM_CODE_NORMAL + "- " + four2,
					RANDOM_CODE_NORMAL + "- " + five2);
		default:
			ElementType type = Arrays.asList(ElementType.values())
					.get(new Random().nextInt(ElementType.values().length));
			String typee = type.getIcon().replaceAll("○", getRandomDefense()).replaceAll("•", getRandomSerial(4, 30));
			return Arrays.asList(RANDOM_CODE_NORMAL + "- " + typee);
		}
	}

	public String getRandomSerial(int min, int max) {
		int so1 = new Random().nextInt(max - min) + min;
		int so2 = new Random().nextInt(max - min) + min;

		if (so1 > so2) {
			return so2 + "-" + so1;
		}

		if (so1 < so2) {
			return so1 + "-" + so2;
		}

		if (so1 == so2) {
			getRandomSerial(min, max);
		}

		return "";
	}

	public String getRandomDefense() {
		String[] df = { "Defense", "Attack" };
		return Arrays.asList(df).get(new Random().nextInt(df.length));
	}

	private List<Enchanter> getRandomEnchant(Material mat) {
		List<Enchanter> list = new ArrayList<>();
		String type = mat.toString().toLowerCase();

		if (type.contains("sword")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getSwordEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getSwordEnchantments().size())));
			}
			return list;
		}

		if (type.equalsIgnoreCase("bow")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getBowEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getBowEnchantments().size())));
			}
			return list;
		}

		if (type.contains("axe")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getAxeEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getAxeEnchantments().size())));
			}
			return list;
		}

		if (type.equalsIgnoreCase("helmet") || type.equalsIgnoreCase("chestplate")
				|| type.equalsIgnoreCase("leggings")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getArmorEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getArmorEnchantments().size())));
			}
			return list;
		}

		if (type.contains("boots")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getBootEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getBootEnchantments().size())));
			}
			return list;
		}

		if (type.contains("shield")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getShieldEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getShieldEnchantments().size())));
			}
			return list;
		}

		if (type.contains("stick")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getSpearEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getSpearEnchantments().size())));
			}
			return list;
		}

		if (type.contains("crossbow")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getCrossbowEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getCrossbowEnchantments().size())));
			}
			return list;
		}

		if (type.contains("trident")) {
			for (int i = 0; i < 4; i++) {
				list.add(ItemEnchantment.getTridentEnchantments()
						.get(new Random().nextInt(ItemEnchantment.getTridentEnchantments().size())));
			}
			return list;
		}

		return null;
	}

}
