package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public record MultiMaterialBlockComponent(List<MultiMaterialType> types) implements TooltipProvider {
	public static final Codec<MultiMaterialBlockComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.list(MultiMaterialType.CODEC).fieldOf("types").forGetter(MultiMaterialBlockComponent::types))
			.apply(p_337946_, MultiMaterialBlockComponent::new));

//	public static final StreamCodec<ByteBuf, MultiMaterialBlockComponent> UNIT_STREAM_CODEC = StreamCodec
//			.unit(new MultiMaterialBlockComponent(List.of()));

	public static final StreamCodec<RegistryFriendlyByteBuf, MultiMaterialBlockComponent> STREAM_CODEC = StreamCodec
			.of(MultiMaterialBlockComponent::write, MultiMaterialBlockComponent::read);

	private static MultiMaterialBlockComponent read(RegistryFriendlyByteBuf buffer) {
		List<MultiMaterialType> s = new ArrayList<MultiMaterialType>();

		int c = buffer.readInt();
		for (int i = 0; i < c; i++) {
			s.add(MultiMaterialType.STREAM_CODEC.decode(buffer));
		}

		return new MultiMaterialBlockComponent(s);
	}

	private static void write(RegistryFriendlyByteBuf buffer, MultiMaterialBlockComponent bm) {
		buffer.writeInt(bm.types.size());

		for (int i = 0; i < bm.types.size(); i++) {
			MultiMaterialType.STREAM_CODEC.encode(buffer, bm.types.get(i));
		}
	}

	@Override
	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		// TODO Auto-generated method stub

	}

}
