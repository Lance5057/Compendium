package com.lance5057.compendium.styleblock;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IStyleBlock {
	public List<String> getStyles(List<Integer> current);
	public ResourceLocation getItemModelLocation();
	public void onStyleChanged(Level level, BlockPos pos, BlockState state);
	public String getBaseStyleName(int current);
}
