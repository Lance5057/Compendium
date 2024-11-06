package com.lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(Compendium.MOD_ID)
public class Compendium {
	public final static String MOD_ID = "compendium";
	public static final String VERSION = "2.0";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public Compendium(IEventBus bus, Dist dist) {
		IndexInitialResourceLoader.init();
		CompendiumIndex.setup(bus);

		CompendiumItems.ITEMS.register(bus);

		CompendiumTabs.TABS.register(bus);
		bus.addListener(this::setupClient);
		CompendiumMenus.register(bus);
	}

	public void setupClient(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			CompendiumIndex.index.forEach(i -> {
				i.setupClient(event);
			});
		});
	}
}