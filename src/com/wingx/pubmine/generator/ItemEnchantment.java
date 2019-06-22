package com.wingx.pubmine.generator;

import java.util.Arrays;
import java.util.List;

public class ItemEnchantment {

	public static List<Enchanter> getSwordEnchantments() {
		Enchanter[] en = { Enchanter.FIRE_ASPECT, Enchanter.KNOCKBACK, Enchanter.LOOTING, Enchanter.MENDING,
				Enchanter.SHARPNESS, Enchanter.SMITE, Enchanter.SWEEPING_EDGE, Enchanter.UNBREAKING };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getBowEnchantments() {
		Enchanter[] en = { Enchanter.FLAME, Enchanter.INFINITY, Enchanter.MENDING, Enchanter.LOOTING, Enchanter.POWER,
				Enchanter.PUNCH, Enchanter.UNBREAKING };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getAxeEnchantments() {
		Enchanter[] en = { Enchanter.LOOTING, Enchanter.MENDING, Enchanter.BANE_OF_ARTHROPODS, Enchanter.EFFICIENCY,
				Enchanter.SHARPNESS, Enchanter.SMITE, Enchanter.SWEEPING_EDGE, Enchanter.UNBREAKING,
				Enchanter.SILK_TOUCH };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getArmorEnchantments() {
		Enchanter[] en = { Enchanter.AQUA_AFFINITY, Enchanter.BLAST_PROTECTION, Enchanter.FIRE_PROTECTION,
				Enchanter.MENDING, Enchanter.PROJECTILE_PROTECTION, Enchanter.PROTECTION, Enchanter.UNBREAKING,
				Enchanter.THORNS, Enchanter.RESPIRATION };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getBootEnchantments() {
		Enchanter[] en = { Enchanter.AQUA_AFFINITY, Enchanter.BLAST_PROTECTION, Enchanter.DEPTH_STRIDER,
				Enchanter.FEATHER_FALLING, Enchanter.FIRE_PROTECTION, Enchanter.FROST_WALKER, Enchanter.MENDING,
				Enchanter.PROJECTILE_PROTECTION, Enchanter.PROTECTION, Enchanter.UNBREAKING, Enchanter.THORNS,
				Enchanter.RESPIRATION };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getShieldEnchantments() {
		Enchanter[] en = { Enchanter.BLAST_PROTECTION, Enchanter.PROJECTILE_PROTECTION, Enchanter.FIRE_PROTECTION,
				Enchanter.PROTECTION, Enchanter.MENDING, Enchanter.UNBREAKING };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getSpearEnchantments() {
		Enchanter[] en = { Enchanter.SHARPNESS, Enchanter.SMITE, Enchanter.KNOCKBACK, Enchanter.FIRE_ASPECT,
				Enchanter.LOOTING, Enchanter.BANE_OF_ARTHROPODS };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getTridentEnchantments() {
		Enchanter[] en = { Enchanter.CHANNELING, Enchanter.IMPALING, Enchanter.LOOTING, Enchanter.LOYALTY,
				Enchanter.MENDING, Enchanter.POWER, Enchanter.RIPTIDE, Enchanter.UNBREAKING };
		return Arrays.asList(en);
	}

	public static List<Enchanter> getCrossbowEnchantments() {
		Enchanter[] en = { Enchanter.QUICK_CHARGE, Enchanter.MULTISHOT, Enchanter.PIERCING, Enchanter.UNBREAKING,
				Enchanter.MENDING, Enchanter.LOOTING };
		return Arrays.asList(en);
	}
}
