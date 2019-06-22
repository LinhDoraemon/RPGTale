package com.wingx.pubmine.element.dust;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DustGUIEvents implements Listener {

	@EventHandler
	public void DUST_MAIN_MENU_CALL_EVENT(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();

		if (i == null) {
			return;
		}

		if (!(e.getView().getTitle().equalsIgnoreCase("§0§lBụi nâng cấp"))) {
			return;
		}
		
		if(i.hasItemMeta() == false || i.getItemMeta().hasDisplayName() == false) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(true);
		
		if(i.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lÉp bột nâng cấp")) {
			p.closeInventory();
			p.openInventory(DustGUI.getApplyDustGUI(p));
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
			return;
		}
		
		if(i.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lGỡ bột nâng cấp")) {
			p.closeInventory();
			p.openInventory(DustGUI.getDisapplyDustGUI(p));
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
			return;
		}
		
	}
	
	@EventHandler
	public void DUST_DISAPPLY_DUST_CALL_EVENT(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();

		if (i == null) {
			return;
		}

		if (!(e.getView().getTitle().equalsIgnoreCase("§0§lGỡ bột nâng cấp"))) {
			return;
		}
		
		if(i.hasItemMeta() == false || i.getItemMeta().hasDisplayName() == false) {
			e.setCancelled(true);
			return;
		}

		if (i.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lBẮT ĐẦU GỠ")) {
			ItemStack item = e.getClickedInventory().getItem(10);
			ItemStack dust = e.getClickedInventory().getItem(11);

			if (item == null) {
				e.setCancelled(true);
				p.sendMessage("§cVui lòng bỏ vật phẩm cần gỡ bột nâng cấp vào !");
				return;
			}

			if (dust == null || ElementDust.isElementDust(dust) == false) {
				e.setCancelled(true);
				p.sendMessage("§cVui lòng bỏ bột nâng cấp CÙNG LOẠI với bột nâng cấp cần gỡ trong vật phẩm vào !");
				return;
			}

			e.setCancelled(true);
			
			ItemStack done = item.clone();

			e.getClickedInventory().setItem(15, done);
			ElementDust.removeDust(p, done, dust);

			e.getClickedInventory().setItem(10, null);
			
			e.getClickedInventory().setItem(16, dust);
			e.getClickedInventory().setItem(11, null);

			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			p.sendMessage("§aBạn đã gỡ thành công " + dust.getItemMeta().getDisplayName() + " §akhỏi vật phẩm.");
			return;
		}

	}
	
	@EventHandler
	public void DUST_APPLY_DUST_CALL_EVENT(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();

		if (i == null) {
			return;
		}

		if (!(e.getView().getTitle().equalsIgnoreCase("§0§lÉp bột nâng cấp"))) {
			return;
		}
		
		if(i.hasItemMeta() == false || i.getItemMeta().hasDisplayName() == false) {
			e.setCancelled(true);
			return;
		}

		if (i.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lBẮT ĐẦU ÉP")) {
			ItemStack item = e.getClickedInventory().getItem(10);
			ItemStack dust = e.getClickedInventory().getItem(11);

			if (item == null) {
				e.setCancelled(true);
				p.sendMessage("§cVui lòng bỏ vật phẩm cần ép bột nâng cấp vào !");
				return;
			}

			if (dust == null || ElementDust.isElementDust(dust) == false) {
				e.setCancelled(true);
				p.sendMessage("§cVui lòng bỏ bột nâng cấp vào !");
				return;
			}

			if (ElementDust.hasDustSlot(item) == false) {
				e.setCancelled(true);
				p.sendMessage("§cVật phẩm không có ô để ép bột nâng cấp !");
				return;
			}

			e.setCancelled(true);
			
			ItemStack done = item.clone();

			e.getClickedInventory().setItem(15, done);
			ElementDust.applyDust(p, done, dust);
			
			e.getClickedInventory().setItem(10, null);
			e.getClickedInventory().setItem(11, null);

			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			p.sendMessage("§aBạn đã ép thành công " + dust.getItemMeta().getDisplayName() + " §avào vật phẩm.");
			return;
		}

	}

}
