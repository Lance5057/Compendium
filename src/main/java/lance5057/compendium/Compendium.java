package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class Compendium {
	public static Logger logger = LogManager.getLogger();

	public static CompendiumItems items;
	public static CompendiumBlocks blocks;

//	public static CompendiumMaterials materials;
//	public static CompendiumIndexes indexes;
//	public static CompendiumWorldGen worldgen;

//    public static AppendixMetallurgy metal;

	public Compendium() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::modSetup);
		modEventBus.addListener(this::setupClient);

//		materials = new CompendiumMaterials();

		// Config
//		ModLoadingContext modLoadingContext = ModLoadingContext.get();
//		modLoadingContext.registerConfig(ModConfig.Type.COMMON, CompendiumConfig.initialize());
//		CompendiumConfig.loadConfig(CompendiumConfig.getInstance().getSpec(),
//				FMLPaths.CONFIGDIR.get().resolve("compendium-common.toml"));
//
//		try {
//			Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve("CompendiumMaterials/"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// init configs and setup bases
//		for (String m : CompendiumConfig.getInstance().materials.materials.get()) {
//			String[] s = m.split(":");
//			ForgeConfigSpec spec = MaterialConfig.initialize(s[1], s[0]);
//
//			modLoadingContext.registerConfig(ModConfig.Type.COMMON, spec, "CompendiumMaterials/" + s[1] + ".toml");
//			MaterialConfig.loadConfig(spec, FMLPaths.CONFIGDIR.get().resolve("CompendiumMaterials/" + s[1] + ".toml"));
//			
		// CompendiumMaterials.materials.add(new
		// MaterialHelper("aluminium").addMetalBase());
//		}

//		blocks = new CompendiumBlocks();
//		items = new CompendiumItems();
//		indexes = new CompendiumIndexes();
////		worldgen = new CompendiumWorldGen();
//
//		for (MaterialHelper m : CompendiumMaterials.materials) {
//			m.setup();
//		}

		CompendiumItems.register(modEventBus);
		CompendiumBlocks.register(modEventBus);
//		CompendiumEntities.register(modEventBus);
		CompendiumTileEntities.register(modEventBus);
		CompendiumContainers.register(modEventBus);
		// CompendiumStructures.register(modEventBus);
//		CompendiumRecipes.register(modEventBus);
//		CompendiumFeatures.register(modEventBus);
//
		WorkstationRecipes.register(modEventBus);

//		MinecraftForge.EVENT_BUS.register(worldgen);
//		MinecraftForge.EVENT_BUS.addListener(CompendiumWorldGen::onBiomeLoadingEvent);

//		AppendixMetallurgy.setup();
//		AppendixCarpentry.setup();
//		AppendixConstruction.setup();
//		AppendixOreDressing.setup();
	}

	private void modSetup(final FMLCommonSetupEvent event) {
		// mats.setup(event);

//		event.enqueueWork(() -> {
//			CompendiumStructures.setupStructures();
//			CompendiumConfiguredStructures.registerConfiguredStructures();
//		});
//
//		event.enqueueWork(() -> {
//			CompendiumConfiguredFeatures.registerFeatures();
//		});
	}

	public void setupClient(FMLClientSetupEvent event) {
		CompendiumClient.setRenderLayers();
//		CompendiumClient.setTERenderers();
//		CompendiumClient.setEntityRenderers();
//
		CompendiumContainers.registerClient(event);
//
//		event.enqueueWork(() -> {
//			AppendixMetallurgy.client(event);
//		});
//		event.enqueueWork(() -> {
//			AppendixConstruction.client(event);
//		});
	}
}
