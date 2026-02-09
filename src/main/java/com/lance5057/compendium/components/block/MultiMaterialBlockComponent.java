package com.lance5057.compendium.components.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;

public class MultiMaterialBlockComponent {
	List<MultiMaterialType> types;

	public List<MultiMaterialType> getTypes() {
		return types;
	}

	public void setTypes(List<MultiMaterialType> types) {
		this.types = types;
	}

	public static final Codec<MultiMaterialBlockComponent> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.list(MultiMaterialType.CODEC).fieldOf("types")
					.forGetter(MultiMaterialBlockComponent::getTypes))
			.apply(p_337946_, MultiMaterialBlockComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, MultiMaterialBlockComponent> STREAM_CODEC = StreamCodec
			.of(MultiMaterialBlockComponent::write, MultiMaterialBlockComponent::read);

	public MultiMaterialBlockComponent(List<MultiMaterialType> types) {
		this.types = types;
	}

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

	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		if (tooltipFlag.hasShiftDown()) {
			tooltipAdder.accept(Component.translatable("compendium.tooltip.material"));
			types.forEach(i -> tooltipAdder.accept(Component.literal(" - ")
					.append(Component.translatable("compendium.tooltip.material." + i.getCurrentMaterial()))));
		} else {
			tooltipAdder.accept(Component.translatable("compendium.tooltip.material.see_more").withColor(0xFFAAAAAA));
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(this.types);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else {
			if (obj instanceof MultiMaterialBlockComponent mm)
				if (this.getTypes().equals(mm.getTypes()))
					return true;
			return false;
		}
	}
}
