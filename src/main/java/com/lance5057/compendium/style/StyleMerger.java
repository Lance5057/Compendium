package com.lance5057.compendium.style;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapValueMerger;

public class StyleMerger implements DataMapValueMerger<Block, List<StyleData>> {

	@Override
	public List<StyleData> merge(Registry<Block> registry, Either<TagKey<Block>, ResourceKey<Block>> first,
			List<StyleData> firstValue, Either<TagKey<Block>, ResourceKey<Block>> second,
			List<StyleData> secondValue) {

		List<StyleData> merged = new ArrayList<StyleData>();
		for (StyleData f : firstValue) {
			for (StyleData s : secondValue) {
				if (f.name.equals(s)) {
					merged.add(combine(f, s));
				}
			}
		}

		return merged;
	}

	StyleData combine(StyleData first, StyleData second) {
		List<String> newStyles = new ArrayList<String>();
		newStyles.addAll(first.getTypes());
		newStyles.addAll(second.getTypes());

		return new StyleData(first.name, newStyles);
	}

}
