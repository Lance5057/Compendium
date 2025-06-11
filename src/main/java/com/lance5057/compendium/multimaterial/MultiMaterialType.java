package com.lance5057.compendium.multimaterial;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.styleblock.StyleType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class MultiMaterialType {
	
	public static final Codec<StyleType> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.STRING.fieldOf("name").forGetter(StyleType::getName),
					Codec.list(Codec.STRING).fieldOf("types").forGetter(StyleType::getStyles),
					Codec.INT.fieldOf("current").forGetter(StyleType::getCurrentStyleIndex))
			.apply(p_337946_, StyleType::new));

	public static final StreamCodec<ByteBuf, StyleType> STREAM_CODEC = new StreamCodec<ByteBuf, StyleType>() {
		public StyleType decode(ByteBuf p_320431_) {
			String n = ByteBufCodecs.STRING_UTF8.decode(p_320431_);

			int count = ByteBufCodecs.INT.decode(p_320431_);

			List<String> s = new ArrayList<String>();
			for (int i = 0; i < count; i++)
				s.add(ByteBufCodecs.STRING_UTF8.decode(p_320431_));

			int current = ByteBufCodecs.INT.decode(p_320431_);

			return new StyleType(n, s, current);
		}

		public void encode(ByteBuf p_320258_, StyleType p_320532_) {
			ByteBufCodecs.STRING_UTF8.encode(p_320258_, p_320532_.name);

			ByteBufCodecs.INT.encode(p_320258_, p_320532_.numStyles());
			for (int i = 0; i < p_320532_.numStyles(); i++) {
				ByteBufCodecs.STRING_UTF8.encode(p_320258_, p_320532_.getStyles().get(i));
			}

			ByteBufCodecs.INT.encode(p_320258_, p_320532_.current);
		}
	};
	
	MATERIAL_TYPES type;
	String material;

	public MATERIAL_TYPES getType() {
		return type;
	}

	public String getMaterial() {
		return material;
	}

	public MultiMaterialType(MATERIAL_TYPES t, String m) {
		this.type = t;
		this.material = m;
	}
}
