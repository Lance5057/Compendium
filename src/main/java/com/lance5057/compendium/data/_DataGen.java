package com.lance5057.compendium.data;

import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = Compendium.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class _DataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		ExistingFileHelper helperEmpty = new ExistingFileHelper(Set.of(Paths.get("", "")), Set.of(""), false, null,
				null);
		
//		IndexInitialResourceLoader.doData();

		generator.addProvider(event.includeClient(), new EngLoc(output));

		generator.addProvider(true, new BlockStateModels(output, helperEmpty));
		generator.addProvider(true, new ItemModels(output, helperEmpty));
		generator.addProvider(true, new IndexBlockModelProvider(output, helperEmpty));

		BlockTagGen btg = new BlockTagGen(output, lookupProvider, Compendium.MOD_ID, helper);
		generator.addProvider(true, btg);
		generator.addProvider(true, new ItemTagGen(output, lookupProvider, btg.contentsGetter(), helper));
		generator.addProvider(true, new Recipes(output, lookupProvider));

		generator.addProvider(true, new LootTables(output, lookupProvider));
	}
}
