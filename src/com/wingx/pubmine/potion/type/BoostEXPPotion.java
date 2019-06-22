package com.wingx.pubmine.potion.type;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

import com.wingx.pubmine.potion.CustomPotion;
import com.wingx.pubmine.potion.CustomPotionType;
import com.wingx.pubmine.potion.Effect;
import com.wingx.pubmine.util.CountdownBossbar;
import com.wingx.pubmine.util.Nearby;

public class BoostEXPPotion extends CustomPotion {

	public static String[] NAMES = { "§6Thuốc tăng kinh nghiệm" };

	public static List<UUID> BOOST_EXP_LIST = new ArrayList<>();

	public BoostEXPPotion(String displayname) {
		super(CustomPotionType.SPLASH);

		setDisplayName(displayname);
		setBaseColor(Color.LIME);
		setDuration(25);
		setCustomEffect(Effect.BOOST_EXP);
	}

	public static void onApply(PotionSplashEvent e) {
		for (Entity en : Nearby.getEntitiesAroundPoint(e.getEntity().getLocation(), 1.0D)) {
			if ((en instanceof ThrownPotion)) {
				return;
			}

			if ((en instanceof Player)) {
				if (BOOST_EXP_LIST.contains(en.getUniqueId())) {
					en.sendMessage("§cBạn đang có sẵn hiệu ứng §2Tăng kinh nghiệm §crồi !");
					return;
				}
				new CountdownBossbar().showBossbar("§2Tăng kinh nghiệm §a[00:25]", BarColor.GREEN, BarStyle.SOLID, 25,
						new Player[] { (Player) en });

				BOOST_EXP_LIST.add(en.getUniqueId());
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("RPGTale"),
						new Runnable() {
							@Override
							public void run() {
								BOOST_EXP_LIST.remove(en.getUniqueId());
							}
						}, 25 * 20);
			}
		}
	}

	public static void onActivate(PlayerExpChangeEvent e) {
		Player p = e.getPlayer();
		int exp = e.getAmount();

		if (BOOST_EXP_LIST.contains(p.getUniqueId())) {
			e.setAmount(exp + exp);
			p.sendMessage("§aBởi hiệu ứng §2Tăng kinh nghiệm§a, bạn nhận được thêm §e§l" + exp + " §akinh nghiệm !");
			return;
		}
	}

}
