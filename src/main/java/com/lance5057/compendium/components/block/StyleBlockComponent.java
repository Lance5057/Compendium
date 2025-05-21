package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.lance5057.compendium.styleblock.StyleType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public record StyleBlockComponent(List<StyleType> styles) implements TooltipProvider {
	public static final Codec<StyleBlockComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.list(StyleType.CODEC).fieldOf("types").forGetter(StyleBlockComponent::styles))
			.apply(p_337946_, StyleBlockComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, StyleBlockComponent> STREAM_CODEC = StreamCodec
			.of(StyleBlockComponent::write, StyleBlockComponent::read);

	@Override
	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		// TODO Auto-generated method stub

	}

	private static StyleBlockComponent read(RegistryFriendlyByteBuf buffer) {
		List<StyleType> s = new ArrayList<StyleType>();

		int c = buffer.readInt();
		for (int i = 0; i < c; i++) {
			s.add(StyleType.STREAM_CODEC.decode(buffer));
		}

		return new StyleBlockComponent(s);
	}

	private static void write(RegistryFriendlyByteBuf buffer, StyleBlockComponent bm) {
		buffer.writeInt(bm.styles.size());

		for (int i = 0; i < bm.styles.size(); i++) {
			StyleType.STREAM_CODEC.encode(buffer, bm.styles.get(i));
		}
	}

}
