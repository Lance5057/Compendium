package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.world.CompendiumConfiguredStructures;
import lance5057.compendium.core.world.CompendiumStructures;
import lance5057.compendium.indexes.CompendiumIndexes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
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
    public static CompendiumIndexes indexes;
    public static CompendiumWorldGen worldgen;

    public static AppendixMetallurgy metal;

    public Compendium() {
	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	modEventBus.addListener(this::modSetup);
	modEventBus.addListener(this::setupClient);

	ModLoadingContext modLoadingContext = ModLoadingContext.get();
	modLoadingContext.registerConfig(ModConfig.Type.COMMON, CompendiumConfig.initialize());
	CompendiumConfig.loadConfig(CompendiumConfig.getInstance().getSpec(),
		FMLPaths.CONFIGDIR.get().resolve("compendium-common.toml"));

	blocks = new CompendiumBlocks();
	items = new CompendiumItems();
	indexes = new CompendiumIndexes();
	worldgen = new CompendiumWorldGen();

	CompendiumItems.register(modEventBus);
	CompendiumBlocks.register(modEventBus);
	CompendiumEntities.register(modEventBus);
	CompendiumTileEntities.register(modEventBus);
	CompendiumContainers.register(modEventBus);
	// CompendiumStructures.register(modEventBus);
	CompendiumRecipes.register(modEventBus);

	WorkstationRecipes.register(modEventBus);

	MinecraftForge.EVENT_BUS.register(worldgen);
	
	AppendixMetallurgy.setup();
	AppendixCarpentry.setup();
	AppendixConstruction.setup();
	AppendixOreDressing.setup();
    }

    private void modSetup(final FMLCommonSetupEvent event) {
	// mats.setup(event);
	

	event.enqueueWork(() -> {
	    CompendiumStructures.setupStructures();
	    CompendiumConfiguredStructures.registerConfiguredStructures();
	});

    }

    public void setupClient(FMLClientSetupEvent event) {
	CompendiumClient.setRenderLayers();
	CompendiumClient.setTERenderers();
	CompendiumClient.setEntityRenderers();

	CompendiumContainers.registerClient(event);

	event.enqueueWork(() -> {
	    AppendixMetallurgy.client(event);
	});
	event.enqueueWork(() -> {
	    AppendixConstruction.client(event);
	});
    }
}
