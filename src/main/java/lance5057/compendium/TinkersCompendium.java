package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class TinkersCompendium {
	public static Logger logger = LogManager.getLogger();

	public static TCItems items;
	public static TCBlocks blocks;
	public static CompendiumMaterials mats;

	public TinkersCompendium() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::modSetup);
		modEventBus.addListener(this::setRenderLayers);

		blocks = new TCBlocks();
		items = new TCItems();
		mats = new CompendiumMaterials();
		
		TCItems.register(modEventBus);
		TCBlocks.register(modEventBus);
		TCTileEntities.register(modEventBus);
		WorkstationRecipes.register(modEventBus);
	}

	private void modSetup(final FMLCommonSetupEvent event) {
		mats.setup(event);
	}

	public void setRenderLayers(FMLClientSetupEvent event) {
		TCClient.setRenderLayers();
	}
}
