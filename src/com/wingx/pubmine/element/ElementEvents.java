package com.wingx.pubmine.element;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wingx.pubmine.element.dust.DustItem.PowderParameter;
import com.wingx.pubmine.element.dust.ElementDust;

public class ElementEvents implements Listener {

	@EventHandler
	public void ELEMENT_DAMAGE_ADD_CALL_EVENT(EntityDamageByEntityEvent e) {

		if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player)) {
			return;
		}

		Player dmger = (Player) e.getDamager();
		Player en = (Player) e.getEntity();

		ItemStack item = dmger.getInventory().getItemInMainHand();
		ItemStack[] en_armor = en.getInventory().getArmorContents();

		if (ElementType.hasElementsInItem(item) == false) {
			return;
		}

		double dmg = e.getDamage();

		for (ElementType ele : ElementType.getElementsInItem(item)) {

			for (ItemStack i : en_armor) {
				if (ElementType.hasElementsInItem(i) == false) {
					return;
				}

				for (ElementType ele_ar : ElementType.getElementsInItem(i)) {
					if (ele.getOpposite().contains(ele_ar)) {

						ItemMeta mt = item.getItemMeta();
						List<String> lores = mt.getLore();

						for (String s : lores) {

							if (s.length() < 8) {
								continue;
							}

							if (s.contains(ele_ar.getIcon().substring(4, 5))) {

								String seri = s.split(" ")[5];
								int min = Integer.parseInt(seri.split("-")[0]);
								int max = Integer.parseInt(seri.split("-")[1]);

								double d = PowderParameter.valueOf(ele_ar.toString())
										.getParameter(ElementDust.getDustLevel(item))[0];
								double ee = PowderParameter.valueOf(ele_ar.toString())
										.getParameter(ElementDust.getDustLevel(item))[1];

								double new_min_value = min + d;
								double new_max_value = max + ee;

								dmg = dmg
										+ ((new Random().nextInt((int) (new_max_value - new_min_value)) + new_min_value)
												/ 10);
							}

						}

					}

					if (ele.getLikely().contains(ele_ar)) {

						ItemMeta mt = item.getItemMeta();
						List<String> lores = mt.getLore();

						for (String s : lores) {

							if (s.length() < 8) {
								continue;
							}

							if (s.contains(ele_ar.getIcon().substring(4, 5))) {

								String seri = s.split(" ")[5];
								int min = Integer.parseInt(seri.split("-")[0]);
								int max = Integer.parseInt(seri.split("-")[1]);

								double d = PowderParameter.valueOf(ele_ar.toString())
										.getParameter(ElementDust.getDustLevel(item))[0];
								double ee = PowderParameter.valueOf(ele_ar.toString())
										.getParameter(ElementDust.getDustLevel(item))[1];

								double new_min_value = min + d;
								double new_max_value = max + ee;

								dmg = dmg
										- ((new Random().nextInt((int) (new_max_value - new_min_value)) + new_min_value)
												/ 10);
							}

						}

					}

				}
			}
		}

		e.setDamage(dmg);
	}

}
