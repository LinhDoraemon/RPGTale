package com.wingx.pubmine.generator;

import org.bukkit.Material;

public enum ItemType {

	SWORD("§3§lKiếm"),
	LONG_SWORD("§3§lKiếm dài", Material.IRON_SWORD),
	GREAT_SWORD("§3§lĐoản kiếm", Material.DIAMOND_SWORD),
	BOW("§3§lCung tiễn", Material.BOW),
	AXE("§3§lRìu", Material.IRON_AXE),
	DAGGER("§3§lDao găm", Material.STONE_SWORD),
	ARMOR("§3§lGiáp"),
	KATANA("§3§lKatana"),
	GREAT_AXE("§3§lRìu lớn", Material.DIAMOND_AXE),
	SHIELD("§3§lKhiên", Material.SHIELD),
	GREAT_SHIELD("§3§lKhiên lớn", Material.SHIELD),
	SPEAR("§3§lGiáo", Material.STICK),
	TRIDENT("§3§lĐinh ba", Material.TRIDENT),
	CROSSBOW("§3§lNỏ tiễn", Material.CROSSBOW);
	
	private String des;
	private Material attr;

	ItemType(String des, Material mat){
		this.des = des;
		this.attr = mat;
	}
	
	ItemType(String des){
		this.des = des;
	}
	
	public void setMaterial(Material mat) {
		this.attr = mat;
	}
	
	public String getDescription() {
		return des;
	}
	
	public Material getMaterial() {
		return attr;
	}
}
