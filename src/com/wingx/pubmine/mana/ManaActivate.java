package com.wingx.pubmine.mana;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ManaActivate implements Listener {

	@EventHandler
	public void MANA_BAR_ACTIVATE_CALL_EVENT(PlayerJoinEvent e) {
		ManaBar.getManaBar(e.getPlayer());
	}
	
}
