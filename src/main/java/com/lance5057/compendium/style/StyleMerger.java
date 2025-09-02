package com.lance5057.compendium.style;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapValueMerger;

public class StyleMerger implements DataMapValueMerger<Block, StyleData> {

	@Override
	public StyleData merge(Registry<Block> registry, Either<TagKey<Block>, ResourceKey<Block>> first,
			StyleData firstValue, Either<TagKey<Block>, ResourceKey<Block>> second, StyleData secondValue) {
		// TODO Auto-generated method stub
		return null;
	}


}
