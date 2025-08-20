package com.lance5057.compendium.blocks.fence;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FancyFenceBlock extends FenceBlock implements EntityBlock {

	public FancyFenceBlock(Properties p_53302_) {
		super(p_53302_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FancyFenceBlockEntity(pos, state);
	}
}
