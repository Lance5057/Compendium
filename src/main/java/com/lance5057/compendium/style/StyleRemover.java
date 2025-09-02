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

public record StyleRemover(List<String> styles) implements DataMapValueRemover<Block, List<String>> {
	public static final Codec<StyleRemover> CODEC = Codec.list(Codec.STRING).xmap(StyleRemover::new,
			StyleRemover::styles);

	@Override
	public Optional<List<String>> remove(List<String> value, Registry<Block> registry,
			Either<TagKey<Block>, ResourceKey<Block>> source, Block object) {

		List<String> newList = new ArrayList<String>(value);

		for (String s : styles)
			newList.remove(s);

		return Optional.of(newList);
	}

}
