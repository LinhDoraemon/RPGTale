package com.wingx.pubmine.element.dust;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DustGUI {

	public static HashMap<UUID, Inventory> APPLY_DUST_INV = new HashMap<>();
	public static HashMap<UUID, Inventory> DISAPPLY_DUST_INV = new HashMap<>();

	public static Inventory MAIN_MENU = Bukkit.createInventory(null, 9, "§0§lBụi nâng cấp");

	private static int[] INPUT_SLOTS = { 0, 1, 2, 3, 9, 12, 18, 19, 20, 21 };
	private static int[] OUTPUT_SLOTS = { 5, 6, 7, 8, 14, 17, 23, 24, 25, 26 };

	public static Inventory getMainGUI(Player p) {

		ItemStack APPLY = new ItemStack(Material.ANVIL);
		ItemMeta mt = APPLY.getItemMeta();
		mt.setDisplayName("§a§lÉp bột nâng cấp");
		mt.setLore(Arrays.asList("", "§7Ép các loại bột nâng cấp để tăng cường",
				"§7chỉ số cho các nguyên tố trong vật phẩm."));
		APPLY.setItemMeta(mt);

		ItemStack DISAPPLY = new ItemStack(Material.DAMAGED_ANVIL);
		ItemMeta meta = DISAPPLY.getItemMeta();
		meta.setDisplayName("§c§lGỡ bột nâng cấp");
		meta.setLore(Arrays.asList("§7Sử dụng một loại bột nâng cấp để gỡ nâng cấp",
				"§7tương ứng trong vật phẩm và nhận lại chính", "§7loại bột đó."));
		DISAPPLY.setItemMeta(meta);
		
		MAIN_MENU.setItem(2, APPLY);
		MAIN_MENU.setItem(6, DISAPPLY);

		for(int so = 0; so < MAIN_MENU.getSize(); so++) {
			if(MAIN_MENU.getItem(so) == null) {
				MAIN_MENU.setItem(so, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
			}
		}
		
		return MAIN_MENU;
	}

	public static Inventory getApplyDustGUI(Player p) {
		Inventory i = APPLY_DUST_INV.get(p.getUniqueId());

		if (i == null) {
			i = Bukkit.createInventory(null, 27, "§0§lÉp bột nâng cấp");

			for (int INPUT : INPUT_SLOTS) {
				i.setItem(INPUT, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
			}

			for (int OUTPUT : OUTPUT_SLOTS) {
				i.setItem(OUTPUT, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
			}

			ItemStack WORK = new ItemStack(Material.LIME_WOOL);
			ItemMeta mt = WORK.getItemMeta();
			mt.setDisplayName("§a§lBẮT ĐẦU ÉP");
			mt.setLore(Arrays.asList("§7Bỏ đầy đủ vật phẩm và bột nâng cấp vào",
					"§7các ô trống bên trái rồi click để nhận", "§7thành quả ở các ô bên phải."));
			WORK.setItemMeta(mt);
			i.setItem(13, WORK);

			ItemStack HELP = new ItemStack(Material.OAK_SIGN);
			ItemMeta meta = HELP.getItemMeta();
			meta.setDisplayName("§c§lHướng dẫn");
			meta.setLore(Arrays.asList("", "§a♥ §e♥  §8->  §d♥ §b♥", "", "§a♥ §7: Ô chứa vật phẩm cần ép bột nâng cấp",
					"§e♥ §7: Ô chứa bột nâng cấp", "§d♥, §b♥ §7: Các ô chứa thành quả", "", "§c§lCHÚ Ý :",
					"§6- Một vật phẩm không thể ép 2 bột nâng", "§6cấp cùng loại"));
			HELP.setItemMeta(meta);
			i.setItem(22, HELP);

			i.setItem(4, new ItemStack(Material.IRON_BARS));

			APPLY_DUST_INV.put(p.getUniqueId(), i);
		}

		return i;
	}

	public static Inventory getDisapplyDustGUI(Player p) {
		Inventory i = DISAPPLY_DUST_INV.get(p.getUniqueId());

		if (i == null) {
			i = Bukkit.createInventory(null, 27, "§0§lGỡ bột nâng cấp");

			for (int INPUT : INPUT_SLOTS) {
				i.setItem(INPUT, new ItemStack(Material.RED_STAINED_GLASS_PANE));
			}

			for (int OUTPUT : OUTPUT_SLOTS) {
				i.setItem(OUTPUT, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
			}

			ItemStack WORK = new ItemStack(Material.LIME_WOOL);
			ItemMeta mt = WORK.getItemMeta();
			mt.setDisplayName("§a§lBẮT ĐẦU GỠ");
			mt.setLore(Arrays.asList("§7Bỏ vật phẩm cần gỡ bột nâng cấp vào",
					"§7các ô trống bên trái rồi click để nhận", "§7thành quả ở các ô bên phải."));
			WORK.setItemMeta(mt);
			i.setItem(13, WORK);

			ItemStack HELP = new ItemStack(Material.OAK_SIGN);
			ItemMeta meta = HELP.getItemMeta();
			meta.setDisplayName("§c§lHướng dẫn");
			meta.setLore(Arrays.asList("", "§a♥ §e♥  §8->  §d♥ §b♥", "", "§a♥ §7: Ô chứa vật phẩm cần gỡ bột nâng cấp",
					"§e♥ §7: Ô chứa bột nâng cấp CÙNG LOẠI với bột", "§7nâng cấp cần gỡ trong vật phẩm",
					"§d♥ §7: Ô chứa vật phẩm sau khi gỡ", "§b♥ §7: Ô chứa bột nâng cấp sau khi gỡ từ vật phẩm", "",
					"§c§lCHÚ Ý :", "§6- Sau khi gỡ, ô chứa bột nâng cấp đã gỡ sẽ",
					"§6trở lại thành một ô trống để ép bột khác."));
			HELP.setItemMeta(meta);
			i.setItem(22, HELP);

			i.setItem(4, new ItemStack(Material.IRON_BARS));

			DISAPPLY_DUST_INV.put(p.getUniqueId(), i);
		}

		return i;
	}

}
