package com.lance5057.compendium.style;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapValueRemover;

public record StyleRemover(List<StyleData> styles) implements DataMapValueRemover<Block, List<StyleData>> {
	public static final Codec<StyleRemover> CODEC = Codec.list(StyleData.CODEC).xmap(StyleRemover::new,
			StyleRemover::styles);

	@Override
	public Optional<List<StyleData>> remove(List<StyleData> value, Registry<Block> registry,
			Either<TagKey<Block>, ResourceKey<Block>> source, Block object) {
		List<StyleData> newList = new ArrayList<StyleData>();

		for (StyleData f : styles) {
			for (StyleData s : newList) {
				if (f.name.equals(s.name)) {
					newList.add(removeEntries(f, s));
				}
			}
		}

		return Optional.of(newList);
	}

	StyleData removeEntries(StyleData styles, StyleData toRemove) {
		List<String> newStyles = new ArrayList<String>();
		newStyles.addAll(styles.getTypes());
		newStyles.removeAll(toRemove.getTypes());

		return new StyleData(styles.name, newStyles);
	}
}
