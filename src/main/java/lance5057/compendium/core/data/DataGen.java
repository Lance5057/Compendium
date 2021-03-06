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
import lance5057.compendium.core.data.builders.toolrecipes.HammerMainHandRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.CraftingAnvilRecipeProvider;
import lance5057.compendium.core.data.builders.workstationrecipes.HammeringStationRecipeProvider;
import lance5057.compendium.core.data.builders.workstationrecipes.SawhorseStationRecipeProvider;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.WorkstationLoottableProvider;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
	Compendium.logger.info("Base Mod Data Generator Started!");
	DataGenerator generator = event.getGenerator();

	generator.addProvider(new TCItemTags(generator, new BlockTagsProvider(event.getGenerator())));
	generator.addProvider(new TCBlockTags(generator));
	generator.addProvider(new TCItemModels(generator, event.getExistingFileHelper()));
	generator.addProvider(new TCRecipes(generator));
	generator.addProvider(new TCBlockModels(generator, Reference.MOD_ID, event.getExistingFileHelper()));
	generator.addProvider(new TCEnglishLoc(generator));

	generator.addProvider(new TCLootTables(generator));
	generator.addProvider(new WorkstationLoottableProvider(generator));

	generator.addProvider(new HammeringStationRecipeProvider(generator));
	generator.addProvider(new CraftingAnvilRecipeProvider(generator));
	generator.addProvider(new SawhorseStationRecipeProvider(generator));

	generator.addProvider(new HammerMainHandRecipes(generator));
	
	//MetalDataGen.gatherData(event);
    }
}
