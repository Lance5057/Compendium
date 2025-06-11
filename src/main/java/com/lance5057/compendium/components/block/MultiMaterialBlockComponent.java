package com.lance5057.compendium.components.block;

import java.util.List;
import java.util.function.Consumer;

import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
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

	public static final StreamCodec<ByteBuf, MultiMaterialBlockComponent> STREAM_CODEC = StreamCodec.composite(
			MultiMaterialType.STREAM_CODEC.apply(ByteBufCodecs.list()), MultiMaterialBlockComponent::types,
			MultiMaterialBlockComponent::new);

	@Override
	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		// TODO Auto-generated method stub

	}

}
