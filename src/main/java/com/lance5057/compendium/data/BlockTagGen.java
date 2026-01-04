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

		tag(BlockTags.FENCES).add(CompendiumBlocks.FANCY_FENCE.get());

		tag(BlockTags.BEDS).add(CompendiumBlocks.FANCY_BED.get());

		CompendiumIndex.index.forEach(i -> {
			i.setupBlockTags(this);
		});
	}

}
