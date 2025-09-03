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

public record StyleRemover(List<StyleLayer> styles) implements DataMapValueRemover<Block, List<StyleLayer>> {
	public static final Codec<StyleRemover> CODEC = Codec.list(StyleLayer.CODEC).xmap(StyleRemover::new,
			StyleRemover::styles);

	@Override
	public Optional<List<StyleLayer>> remove(List<StyleLayer> value, Registry<Block> registry,
			Either<TagKey<Block>, ResourceKey<Block>> source, Block object) {
		List<StyleLayer> newList = new ArrayList<StyleLayer>(value);

		for (StyleLayer s : styles)
			newList.remove(s);a wdfasdf bad lance, this doesnt even remotely work

		return Optional.of(newList);
	}


}
