package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.client.HammeringStationRenderer;
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
public class TinkersCompendium {
	public static Logger logger = LogManager.getLogger();

	public static TCItems items;
	public static TCBlocks blocks;
	public static CompendiumMaterials mats;
	public static CompendiumWorldGen worldgen;

	public TinkersCompendium() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::modSetup);
		modEventBus.addListener(this::setRenderLayers);
		
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, CompendiumConfig.initialize());
        CompendiumConfig.loadConfig(CompendiumConfig.getInstance().getSpec(), FMLPaths.CONFIGDIR.get().resolve("compendium-common.toml"));

		blocks = new TCBlocks();
		items = new TCItems();
		mats = new CompendiumMaterials();
		worldgen = new CompendiumWorldGen();
		
		TCItems.register(modEventBus);
		TCBlocks.register(modEventBus);
		TCTileEntities.register(modEventBus);
		WorkstationRecipes.register(modEventBus);
		MinecraftForge.EVENT_BUS.register(worldgen);
	}

	private void modSetup(final FMLCommonSetupEvent event) {
		mats.setup(event);
	}

	public void setRenderLayers(FMLClientSetupEvent event) {
		TCClient.setRenderLayers();
		
		ClientRegistry.bindTileEntityRenderer(TCTileEntities.HAMMERING_STATION_TE.get(), HammeringStationRenderer::new);
	}
}
