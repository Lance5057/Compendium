package com.lance5057.compendium.styleblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class StyleType {
	public static final Codec<StyleType> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.STRING.fieldOf("name").forGetter(StyleType::getName),
					Codec.list(Codec.STRING).fieldOf("types").forGetter(StyleType::getStyles))
			.apply(p_337946_, StyleType::new));

	public static final StreamCodec<ByteBuf, StyleType> STREAM_CODEC = new StreamCodec<ByteBuf, StyleType>() {
		public StyleType decode(ByteBuf p_320431_) {
			String n = ByteBufCodecs.STRING_UTF8.decode(p_320431_);

			int count = ByteBufCodecs.INT.decode(p_320431_);

			List<String> s = new ArrayList<String>();
			for (int i = 0; i < count; i++)
				s.add(ByteBufCodecs.STRING_UTF8.decode(p_320431_));

			return new StyleType(n, s);
		}

		public void encode(ByteBuf p_320258_, StyleType p_320532_) {
			ByteBufCodecs.STRING_UTF8.encode(p_320258_, p_320532_.name);

			ByteBufCodecs.INT.encode(p_320258_, p_320532_.numStyles());
			for (int i = 0; i < p_320532_.numStyles(); i++) {
				ByteBufCodecs.STRING_UTF8.encode(p_320258_, p_320532_.getStyles().get(i));
			}
		}
	};

	private String name;

	public String getName() {
		return name;
	}

	private List<String> types;
	private int current = 0;

	public StyleType(String name, String... styles) {
		this.name = name;
		this.types = Arrays.asList(styles);
	}

	public StyleType(String name, List<String> styles) {
		this.name = name;
		this.types = styles;
	}

	public int numStyles() {
		return types.size();
	}

	public String getCurrentStyle() {
		return types.get(current);
	}

	public void setNextStyle(Level level, BlockPos pos, BlockState state) {
		if (current + 1 >= types.size())
			current = 0;
		else
			current++;
	}

	public void setPrevStyle(Level level, BlockPos pos, BlockState state) {
		if (current <= 0)
			current = types.size() - 1;
		else
			current--;
	}

	public void setStyle(int style) {
		if (style >= 0 && style < types.size())
			this.current = style;
	}

	public List<String> getStyles() {
		return this.types;
	}

	public boolean isPatreonStyle(int style) {
		return false;
	}

	public CompoundTag writeNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();

		tag.putInt("current", current);

		return tag;
	}

	public void readNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		this.current = nbt.getInt("current");

	}
}
