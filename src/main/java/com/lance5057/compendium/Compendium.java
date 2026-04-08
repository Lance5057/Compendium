package com.lance5057.compendium;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.workstations.WorkstationRecipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForgeConfig;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(Compendium.MOD_ID)
public class Compendium {
	public final static String MOD_ID = "compendium";
	public static final String VERSION = "2.0.2a";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public Compendium(IEventBus bus, ModContainer modContainer) {

		modContainer.registerConfig(ModConfig.Type.COMMON, CompendiumConfig.spec);

		bus.addListener(CompendiumNetworkHandler::setupPackets);

		IndexInitialResourceLoader.init();
		CompendiumIndex.setup(bus);

		CompendiumComponents.COMPONENTS.register(bus);

		CompendiumBlocks.BLOCKS.register(bus);
		CompendiumItems.ITEMS.register(bus);

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				mb.ITEMS.register(bus);
				mb.BLOCKS.register(bus);
			}
		});

		CompendiumBlockEntities.BLOCK_ENTITIES.register(bus);
		CompendiumEntities.ENTITIES.register(bus);

		CompendiumTabs.TABS.register(bus);
		bus.addListener(this::setupClient);
		CompendiumMenus.register(bus);
		WorkstationRecipes.register(bus);
	}

	public static List<DeferredItem<? extends Item>> styleItemRenderers = new ArrayList<DeferredItem<? extends Item>>();

	public void setupClient(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			CompendiumIndex.index.forEach(i -> {
				i.setupClient(event);
			});

			CompendiumClient.setBERenderers();
			NeoForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true);
		});
	}

	public static ResourceLocation modLoc(String string) {
		return ResourceLocation.fromNamespaceAndPath("compendium", string.toLowerCase());
	}
}