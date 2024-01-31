package com.lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Compendium.MOD_ID)
public class Compendium {
	public final static String MOD_ID = "compendium";
	public static final String VERSION = "2.0";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public Compendium(IEventBus bus, Dist dist) {

		CompendiumItems.ITEMS.register(bus);
		
		CompendiumTabs.TABS.register(bus);
	}
}