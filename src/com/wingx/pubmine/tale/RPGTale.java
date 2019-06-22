package com.wingx.pubmine.tale;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wingx.pubmine.data.PlayerRegisterDataEvent;
import com.wingx.pubmine.element.ElementEvents;
import com.wingx.pubmine.element.ElementType;
import com.wingx.pubmine.element.dust.DustGUI;
import com.wingx.pubmine.element.dust.DustGUIEvents;
import com.wingx.pubmine.element.dust.DustItem;
import com.wingx.pubmine.element.dust.ElementDust;
import com.wingx.pubmine.generator.ItemGenerator;
import com.wingx.pubmine.generator.ItemType
import com.wingx.pubmine.mana.ManaActivate;
import com.wingx.pubmine.mana.ManaRegeneration;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.potion.PotionEvents;

public class RPGTale extends org.bukkit.plugin.java.JavaPlugin {
	public RPGTale() {
	}

	public void onEnable() {
		Effect.init();
		Bukkit.getPluginManager().registerEvents(new PotionEvents(), this);
		Bukkit.getPluginManager().registerEvents(new com.wingx.pubmine.skill.SkillsEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerRegisterDataEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ElementEvents(), this);
		Bukkit.getPluginManager().registerEvents(new DustGUIEvents(), this);
		Bukkit.getPluginManager().registerEvents(new ManaActivate(), this);

		new ManaRegeneration().runTaskTimer(this, 40, 40);
	}

	//DEV COMMANDS - not for release ver
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("setname")) {
			if (args[0] == null) {
				p.sendMessage("§cSử dụng : /setname <tên mới>");
				return true;
			}
			ItemStack i = p.getInventory().getItemInMainHand();
			ItemMeta mt = i.getItemMeta();
			String name = "";
			for (String s : args) {
				name = name.concat(" " + s);
			}
			mt.setDisplayName(name.replaceFirst(" ", "").replace('&', '§'));
			i.setItemMeta(mt);
			p.updateInventory();
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("addlore")) {
			if (args[0] == null) {
				p.sendMessage("§cSử dụng : /addlore <dòng mới>");
				return true;
			}
			ItemStack i = p.getInventory().getItemInMainHand();
			ItemMeta mt = i.getItemMeta();
			String name = "";
			for (String s : args) {
				name = name.concat(" " + s);
			}
			if (mt.hasLore() == false) {
				mt.setLore(Arrays.asList(name.replace('&', '§')));
			} else {
				List<String> lores = mt.getLore();
				lores.add(name.replaceFirst(" ", "").replace('&', '§'));
				mt.setLore(lores);
			}
			i.setItemMeta(mt);
			p.updateInventory();
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("removelore")) {
			if (args[0] == null) {
				p.sendMessage("§cSử dụng : /removelore <dòng số>");
				return true;
			}
			ItemStack i = p.getInventory().getItemInMainHand();
			ItemMeta mt = i.getItemMeta();
			int d = Integer.parseInt(args[0]);
			if (mt.hasLore() == false) {
				p.sendMessage("§cVật phẩm này không có lores !");
				return true;
			} else {
				List<String> lores = mt.getLore();
				if (lores.remove(lores.get(d))) {
					mt.setLore(lores);
				} else {
					p.sendMessage("§cVật phẩm này không có dòng thứ " + d);
				}
			}
			i.setItemMeta(mt);
			p.updateInventory();
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("rpgtale")) {
			if (p.isOp() || p.hasPermission("*")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("element")) {
						if (args[1] == null || args[2] == null || args[3] == null || args[4] == null) {
							p.sendMessage(
									"§cSử dụng : /rpgtale element <Tên nguyên tố> <Thông số nguyên tố min> <Thông số nguyên tố max> <true/false : true == Defense>");
							p.sendMessage("§cCác nguyên tố hiện có : FIRE, EARTH, WATER, WIND, THUNDER");
							return true;
						} else {
							ElementType type = ElementType.valueOf(args[1].toUpperCase());
							type.addElementLore(p, p.getInventory().getItemInMainHand(), Integer.parseInt(args[2]),
									Integer.parseInt(args[3]), Boolean.valueOf(args[4]));
							return true;
						}
					}
				} else {
					p.sendMessage(
							"§e/rpgtale element <Tên nguyên tố> <Thông số nguyên tố min> <Thông số nguyên tố max> <true/false : true == Defense>");
					p.sendMessage("§e/setname <Tên mới>");
					p.sendMessage("§e/addlore <Dòng mới>");
					p.sendMessage("§e/removelore <Dòng số>");
					return true;
				}
			} else {
				p.sendMessage("§cBạn không có quyền sử dụng lệnh này !");
				return true;
			}
		}

		if (cmd.getName().equalsIgnoreCase("choang")) {

			p.getInventory()
					.addItem(new ItemGenerator(ItemType.valueOf(args[0].toUpperCase()), Material.valueOf(args[1]))
							.getRandomItem());

		}

		if (cmd.getName().equalsIgnoreCase("nangcap")) {
			p.openInventory(DustGUI.getMainGUI(p));
			ElementDust.addDustSlot(p, p.getInventory().getItemInMainHand());

			p.getInventory().addItem(DustItem.EARTH_POWDER(2));
			p.getInventory().addItem(DustItem.FIRE_POWDER(2));
			p.getInventory().addItem(DustItem.WIND_POWDER(2));
			p.getInventory().addItem(DustItem.WATER_POWDER(2));
			p.getInventory().addItem(DustItem.THUNDER_POWDER(2));

			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			return true;
		}

		return true;
	}
}
