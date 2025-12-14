package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public record StyleBlockComponent(List<Integer> styles) {
//	public static final StyleBlockComponent EMPTY = new StyleBlockComponent(new ArrayList<StyleType>());
	public static final Codec<StyleBlockComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.list(Codec.INT).fieldOf("current").forGetter(StyleBlockComponent::styles))
			.apply(p_337946_, StyleBlockComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, StyleBlockComponent> STREAM_CODEC = StreamCodec
			.of(StyleBlockComponent::write, StyleBlockComponent::read);

	public void addToTooltip(ItemStack stack, TooltipContext context, Consumer<Component> tooltipAdder,
			TooltipFlag tooltipFlag) {

		if (tooltipFlag.hasControlDown()) {
			IStyleBlock b = ((IStyleBlock) ((BlockItem) stack.getItem()).getBlock());
			List<String> s = b.getStyles(styles);
			tooltipAdder.accept(Component.translatable("compendium.tooltip.style"));
			for (int i = 0; i < styles.size(); i++)
				tooltipAdder.accept(Component.literal(" - ")
						.append(Component.translatable("style." + b.getBaseStyleName(i) + "." + s.get(i))));
		} else {
			tooltipAdder.accept(Component.translatable("compendium.tooltip.style.see_more").withColor(0xFFAAAAAA));
		}
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
