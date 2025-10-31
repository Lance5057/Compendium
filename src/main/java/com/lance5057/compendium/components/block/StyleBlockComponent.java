package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public record StyleBlockComponent(List<Integer> styles) implements TooltipProvider {
//	public static final StyleBlockComponent EMPTY = new StyleBlockComponent(new ArrayList<StyleType>());
	public static final Codec<StyleBlockComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.list(Codec.INT).fieldOf("current").forGetter(StyleBlockComponent::styles))
			.apply(p_337946_, StyleBlockComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, StyleBlockComponent> STREAM_CODEC = StreamCodec
			.of(StyleBlockComponent::write, StyleBlockComponent::read);

	@Override
	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		// TODO Auto-generated method stub

	}

	private static StyleBlockComponent read(RegistryFriendlyByteBuf buffer) {
		List<Integer> s = new ArrayList<Integer>();

		int c = buffer.readInt();
		for (int i = 0; i < c; i++) {
			s.add(buffer.readInt());
		}

		return new StyleBlockComponent(s);
	}

	private static void write(RegistryFriendlyByteBuf buffer, StyleBlockComponent bm) {
		buffer.writeInt(bm.styles.size());

		for (int i = 0; i < bm.styles.size(); i++) {
			buffer.writeInt(bm.styles.get(i));
		}
	}

	public StyleBlockComponent copy() {
		
		return new StyleBlockComponent(new ArrayList<Integer>(styles));
	}

}
