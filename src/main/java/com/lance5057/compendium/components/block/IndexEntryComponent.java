package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class IndexEntryComponent {
	protected String name;
	protected MATERIAL_TYPES type;

	public MATERIAL_TYPES getType() {
		return type;
	}

	public String getTypeAsString() {
		return type.toString();
	}

	public String getName() {
		return name;
	}

	public IndexEntryComponent(String type, String name) {
		this.type = MATERIAL_TYPES.valueOf(type);
		this.name = name;
	}

	public IndexEntryComponent(MATERIAL_TYPES type, String name) {
		this.type = type;
		this.name = name;
	}

	public static final Codec<IndexEntryComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.STRING.fieldOf("type").forGetter(IndexEntryComponent::getTypeAsString),
					Codec.STRING.fieldOf("name").forGetter(IndexEntryComponent::getName))
			.apply(p_337946_, IndexEntryComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, IndexEntryComponent> STREAM_CODEC = StreamCodec
			.of(IndexEntryComponent::write, IndexEntryComponent::read);

	private static IndexEntryComponent read(RegistryFriendlyByteBuf buffer) {
		String n = buffer.readUtf();
		String t = buffer.readUtf();

		return new IndexEntryComponent(t, n);
	}

	private static void write(RegistryFriendlyByteBuf buffer, IndexEntryComponent bm) {
		buffer.writeUtf(bm.getName());
		buffer.writeUtf(bm.getTypeAsString());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.type, this.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else {
			if (obj instanceof IndexEntryComponent mm)
				if (this.getType().equals(mm.getType()))
					if (this.getName().equals(mm.getName()))
						return true;
			return false;
		}
	}
}
