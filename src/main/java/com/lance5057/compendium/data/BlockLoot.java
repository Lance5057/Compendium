package com.lance5057.compendium.data;

import java.util.Collections;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

public class BlockLoot extends BlockLootSubProvider {
	protected BlockLoot(HolderLookup.Provider provider) {
		super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		CompendiumIndex.index.forEach(i -> {
			i.blockLoot(this);
		});

		this.dropSelf(CompendiumBlocks.HAMMERING_STATION.get());
		this.dropSelf(CompendiumBlocks.COSMETIC_TOOLBOX.get());
	}
}
