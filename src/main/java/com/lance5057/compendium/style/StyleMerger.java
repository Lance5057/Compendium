package com.lance5057.compendium.style;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapValueMerger;

public class StyleMerger implements DataMapValueMerger<Block, List<StyleLayer>> {

	@Override
	public List<StyleLayer> merge(Registry<Block> registry, Either<TagKey<Block>, ResourceKey<Block>> first,
			List<StyleLayer> firstValue, Either<TagKey<Block>, ResourceKey<Block>> second,
			List<StyleLayer> secondValue) {

		List<StyleLayer> merged = new ArrayList<StyleLayer>();
		for (StyleLayer f : firstValue) {
			for (StyleLayer s : secondValue) {
				if (f.name.equals(s)) {
					merged.add(combine(f, s));
				}
			}
		}

		return merged;
	}

	StyleLayer combine(StyleLayer first, StyleLayer second) {
		List<String> newStyles = new ArrayList<String>();
		newStyles.addAll(first.getTypes());
		newStyles.addAll(second.getTypes());

		return new StyleLayer(first.name, newStyles);
	}

}
