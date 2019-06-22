package com.wingx.pubmine.generator;

import org.bukkit.enchantments.Enchantment;

public enum Enchanter {

	AQUA_AFFINITY(Enchantment.WATER_WORKER, "Thở dưới nước ", 5),
	BANE_OF_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS, "Hại chân đốt ", 6),
	BLAST_PROTECTION(Enchantment.PROTECTION_EXPLOSIONS, "Bảo vệ khỏi vụ nổ ", 7),
	CHANNELING(Enchantment.CHANNELING, "Triệu hồi sấm ", 4),
	DEPTH_STRIDER(Enchantment.DEPTH_STRIDER, "Đi dưới nước ", 5),
	EFFICIENCY(Enchantment.DIG_SPEED, "Hiệu suất ", 8),
	FEATHER_FALLING(Enchantment.PROTECTION_FALL, "Rơi nhẹ nhàng ", 6),
	FIRE_ASPECT(Enchantment.FIRE_ASPECT, "Chém lửa ", 3),
	FIRE_PROTECTION(Enchantment.PROTECTION_FIRE, "Bảo vệ khỏi lửa ", 7),
	FLAME(Enchantment.ARROW_FIRE, "Mũi tên lửa ", 3),
	FORTUNE(Enchantment.LOOT_BONUS_BLOCKS, "Gia tài ", 4),
	FROST_WALKER(Enchantment.FROST_WALKER, "Chân lạnh ", 2),
	IMPALING(Enchantment.IMPALING, "Hại thủy quái ", 5),
	INFINITY(Enchantment.ARROW_INFINITE, "Vô hạn ", 1),
	KNOCKBACK(Enchantment.KNOCKBACK, "Đánh bật lùi ", 3),
	LOOTING(Enchantment.LOOT_BONUS_MOBS, "Nhặt ", 4),
	LOYALTY(Enchantment.LOYALTY, "Trả lại ", 5),
	MENDING(Enchantment.MENDING, "Sửa chữa ", 5),
	MULTISHOT(Enchantment.MULTISHOT, "Đa mũi tên ", 3),
	PIERCING(Enchantment.PIERCING, "Xuyên thấu ", 4),
	POWER(Enchantment.ARROW_DAMAGE, "Sức mạnh ", 6),
	PROJECTILE_PROTECTION(Enchantment.PROTECTION_PROJECTILE, "Bảo vệ khỏi mũi tên ", 7),
	PROTECTION(Enchantment.PROTECTION_ENVIRONMENTAL, "Bảo vệ chung ", 8),
	PUNCH(Enchantment.ARROW_KNOCKBACK, "Mũi tên đẩy lùi ", 3),
	QUICK_CHARGE(Enchantment.QUICK_CHARGE, "Nạp nhanh ", 5),
	RESPIRATION(Enchantment.OXYGEN, "Hô hấp ", 6),
	RIPTIDE(Enchantment.RIPTIDE, "Phi thân ", 3),
	SHARPNESS(Enchantment.DAMAGE_ALL, "Sắc bén ", 7),
	SILK_TOUCH(Enchantment.SILK_TOUCH, "Chạm nhẹ nhàng ", 1),
	SMITE(Enchantment.DAMAGE_UNDEAD, "Trừng phạt ", 7),
	SWEEPING_EDGE(Enchantment.SWEEPING_EDGE, "Quét kiếm ", 6),
	THORNS(Enchantment.THORNS, "Phản sát thương ", 4),
	UNBREAKING(Enchantment.DURABILITY, "Chậm hỏng ", 10);
	
	private Enchantment root;
	private String lore;
	private int max_level;
	
	Enchanter(Enchantment root, String lore, int max_level){
		this.root = root;
		this.max_level = max_level;
		this.lore = lore;
	}
	
	public String getLore() {
		return lore;
	}
	
	public Enchantment getRootEnchantment() {
		return root;
	}
	
	public int getMaxLevel() {
		return max_level;
	}
	
}
