package com.lance5057.compendium.blocks;

import com.lance5057.compendium.blocks.entities.WindowBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicDecorativeBlock extends Block implements EntityBlock {

	public BasicDecorativeBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WindowBlockEntity(pos, state);
	}

}
