package lance5057.compendium.core.data;

import lance5057.compendium.Compendium;
import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCLootTables;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.SawBuckRecipeLoottables;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		Compendium.logger.info("Base Mod Data Generator Started!");
		DataGenerator generator = event.getGenerator();

		generator.addProvider(true, new TCItemModels(generator, event.getExistingFileHelper()));
		generator.addProvider(true, new TCRecipes(generator));
		generator.addProvider(true, new TCBlockModels(generator, Reference.MOD_ID, event.getExistingFileHelper()));
		generator.addProvider(true, new TCEnglishLoc(generator));

		generator.addProvider(true, new TCLootTables(generator));

		TCBlockTags btg = new TCBlockTags(generator, event.getExistingFileHelper());
		generator.addProvider(true, btg);
		generator.addProvider(true, new TCItemTags(generator, btg, event.getExistingFileHelper()));
	}
}
