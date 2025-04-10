package com.lance5057.compendium.styleblock;

import java.util.List;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MultiStyle {
	private List<String> types;
	private int current = 0;

	public MultiStyle(String... styles) {
		this.types.addAll(Stream.of(styles).toList());
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
}
