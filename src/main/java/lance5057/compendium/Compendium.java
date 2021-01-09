package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.client.CraftingAnvilRenderer;
import lance5057.compendium.core.workstations.client.HammeringStationRenderer;
import lance5057.compendium.core.workstations.client.SawhorseStationRenderer;
import lance5057.compendium.core.world.CompendiumConfiguredStructures;
import lance5057.compendium.core.world.CompendiumStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Reference.MOD_ID)
public class Compendium {
	public static Logger logger = LogManager.getLogger();

	public static CompendiumItems items;
	public static CompendiumBlocks blocks;
	public static CompendiumMaterials mats;
	public static CompendiumWorldGen worldgen;

	public Compendium() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::modSetup);
		modEventBus.addListener(this::setupClient);

		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		modLoadingContext.registerConfig(ModConfig.Type.COMMON, CompendiumConfig.initialize());
		CompendiumConfig.loadConfig(CompendiumConfig.getInstance().getSpec(), FMLPaths.CONFIGDIR.get().resolve("compendium-common.toml"));

		blocks = new CompendiumBlocks();
		items = new CompendiumItems();
		mats = new CompendiumMaterials();
		worldgen = new CompendiumWorldGen();

		CompendiumItems.register(modEventBus);
		CompendiumBlocks.register(modEventBus);
		CompendiumTileEntities.register(modEventBus);
		CompendiumContainers.register(modEventBus);
		CompendiumStructures.register(modEventBus);

		WorkstationRecipes.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(worldgen);
	}

	private void modSetup(final FMLCommonSetupEvent event) {
		mats.setup(event);

		event.enqueueWork(() -> {
			CompendiumStructures.setupStructures();
			CompendiumConfiguredStructures.registerConfiguredStructures();
		});
	}

	public void setupClient(FMLClientSetupEvent event) {
		CompendiumClient.setRenderLayers();

		ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.HAMMERING_STATION_TE.get(), HammeringStationRenderer::new);
		ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.CRAFTING_ANVIL_TE.get(), CraftingAnvilRenderer::new);
		ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SAWHORSE_STATION_TE.get(), SawhorseStationRenderer::new);

		CompendiumContainers.registerClient(event);
	}
}
