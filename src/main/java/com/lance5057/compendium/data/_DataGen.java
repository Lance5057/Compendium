package com.lance5057.compendium.data;

import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.Compendium;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Compendium.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class _DataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new EngLoc(output));
		
		generator.addProvider(true, new ItemModels(output, helper));
		generator.addProvider(true, new BlockModels(output, helper));
	}
}
