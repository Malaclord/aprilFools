package com.malaclord.aprilfools;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class AprilFools extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventListener(), this);

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
