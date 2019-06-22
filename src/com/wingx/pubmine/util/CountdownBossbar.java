package com.wingx.pubmine.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class CountdownBossbar {
	private BukkitTask task;

	public CountdownBossbar() {
	}

	public void showBossbar(String title, BarColor color, BarStyle style, int durations, Player... players) {
		BossBar bar = Bukkit.createBossBar(title, color, style, new BarFlag[] { BarFlag.PLAY_BOSS_MUSIC });
		for (Player p : players) {
			bar.addPlayer(p);
		}
		task = new Counting(durations, bar).runTaskTimer(Bukkit.getPluginManager().getPlugin("RPGTale"), 20L, 20L);
	}

	public class Counting extends BukkitRunnable {
		private int duration;
		private BossBar bar;
		private int phantru;

		public Counting(int duration, BossBar bar) {
			this.duration = duration;
			phantru = (100 / duration);
			this.bar = bar;
		}

		public void run() {
			duration -= 1;
			Double d = new Double(bar.getProgress() * 100.0D);
			int n = d.intValue() - phantru;
			if (n < 0) {
				bar.setProgress(0);
			} else {
				bar.setProgress(n / 100.0D);
			}
			if (duration == 0) {
				cancel();
				bar.removeAll();
				bar.setVisible(false);
				bar.setProgress(1.0D);
			}
		}
	}
}
