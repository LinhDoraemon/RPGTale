package com.wingx.pubmine.potion;

import com.wingx.pubmine.util.TimeParser;
import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class CustomPotion extends ItemStack {
	private CustomPotionType type;
	private int duration;

	public CustomPotion(CustomPotionType type) {
		super(type.getMaterial(), 1);
		this.type = type;
	}

	public void setDisplayName(String displayname) {
		ItemMeta mt = getItemMeta();
		mt.setDisplayName(displayname);
		mt.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		setItemMeta(mt);
	}

	public String getDisplayName() {
		if (getItemMeta().hasDisplayName()) {
			return getItemMeta().getDisplayName();
		}
		return "";
	}

	public Color getBaseColor() {
		return ((PotionMeta) getItemMeta()).getColor();
	}

	public void setBaseColor(Color color) {
		PotionMeta mt = (PotionMeta) getItemMeta();
		mt.setColor(color);
		setItemMeta(mt);
	}

	public void setCustomEffect(Effect effect) {
		ItemMeta mt = getItemMeta();
		mt.setLore(Arrays
				.asList(new String[] { effect.getLore() + " (" + TimeParser.secondToMinutes(getDuration()) + ")" }));
		setItemMeta(mt);
	}

	public Effect getCustomEffect() {
		String lore = (String) getItemMeta().getLore().get(0);
		String values = lore.split("(")[0];
		return (Effect) Effect.EFFECT_DATA.get(values);
	}

	public void setDuration(int seconds) {
		duration = seconds;
	}

	public int getDuration() {
		return duration;
	}
}
