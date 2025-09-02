package com.lance5057.compendium.style;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record StyleData(List<String> styles) {
	public static final Codec<StyleData> CODEC = RecordCodecBuilder
			.create(instance -> instance.group(Codec.list(Codec.STRING).fieldOf("amount").forGetter(StyleData::styles))
					.apply(instance, StyleData::new));
}
