package com.lance5057.compendium.styleblock;

import java.util.Arrays;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MultiStyle {
	private String name;
	public String getName() {
		return name;
	}

	private List<String> types;
	private int current = 0;

	public MultiStyle(String name, String... styles) {
		this.name = name;
		this.types = Arrays.asList(styles);
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
