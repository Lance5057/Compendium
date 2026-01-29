package com.lance5057.compendium.data;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockTagGen extends BlockTagsProvider {

	public BlockTagGen(PackOutput output, CompletableFuture<Provider> lookupProvider, String modId,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, modId, existingFileHelper);
	}

	@Override
	protected void addTags(Provider pProvider) {
		tag(CompendiumTags.PRYABLE).add(Blocks.IRON_BLOCK);

		tag(CompendiumTags.TABLE).add(CompendiumBlocks.TABLE.get(), CompendiumBlocks.CLOTHED_TABLE.get());

		tag(BlockTags.WOODEN_FENCES).add(CompendiumBlocks.FANCY_FENCE.get());

		tag(BlockTags.BEDS).add(CompendiumBlocks.FANCY_BED.get());

		tag(BlockTags.MINEABLE_WITH_AXE).add(CompendiumBlocks.CHAIR.get(), CompendiumBlocks.CLOTHED_TABLE.get(),
				CompendiumBlocks.COMPONENT_DRAWER.get(), CompendiumBlocks.FANCY_BED.get(),
				CompendiumBlocks.FANCY_FENCE.get(), CompendiumBlocks.SAW_BUCK.get(),
				CompendiumBlocks.SHINGLES_CAP_SLANTED.get(), CompendiumBlocks.SHINGLES_SLANTED.get(),
				CompendiumBlocks.TABLE.get(), CompendiumBlocks.WORKBENCH.get());

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CompendiumBlocks.COSMETIC_TOOLBOX.get(),
				CompendiumBlocks.HAMMERING_STATION.get(), CompendiumBlocks.SCRAPPING_TABLE.get(),
				CompendiumBlocks.WINDOW.get());

		CompendiumIndex.index.forEach(i -> {
			i.setupBlockTags(this);
		});
	}

}
