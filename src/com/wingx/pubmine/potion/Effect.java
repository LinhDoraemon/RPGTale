package com.wingx.pubmine.potion;

import java.util.HashMap;

public enum Effect {

	// ADD MORE EXP INCOME OF PLAYER
	BOOST_EXP("§aTăng kinh nghiệm"),
	// CAN NOT DIE IN AN AMOUNT OF TIME
	IMMORTAL("§eBất diệt"),
	// REVIVE WHEN PLAYER DIE
	GUARDIAN_ANGEL("§aHộ mệnh"),
	// CANN NOT MOVE
	STUN("§cChoáng");

	public static HashMap<String, Effect> EFFECT_DATA = new HashMap();
	private String lore;

	private Effect(String lore) {
		this.lore = lore;
	}

	public String getLore() {
		return lore;
	}

	public static void init() {
		for (Effect e : Effect.values()) {
			EFFECT_DATA.put(e.getLore(), e);
		}
	}
}
