package com.lance5057.compendium.data;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.data.loottables.RecipeLootTables;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class LootTables extends LootTableProvider {
	public LootTables(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
		super(pOutput, Collections.emptySet(),
				List.of(new SubProviderEntry(RecipeLootTables::new, LootContextParamSets.EMPTY),
						new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)),
				registries);
	}
}