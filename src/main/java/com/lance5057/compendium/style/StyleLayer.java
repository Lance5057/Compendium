package com.lance5057.compendium.style;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class StyleLayer {
	String name;
	List<String> types;

	public static final Codec<StyleLayer> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.STRING.fieldOf("name").forGetter(StyleLayer::getName),
					Codec.list(Codec.STRING).fieldOf("types").forGetter(StyleLayer::getTypes))
			.apply(p_337946_, StyleLayer::new));

	public StyleLayer(String name, List<String> types) {
		super();
		this.name = name;
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
