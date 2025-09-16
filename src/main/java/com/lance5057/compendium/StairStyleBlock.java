package com.lance5057.compendium;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StairStyleBlock extends StairBlock implements EntityBlock{

	public StairStyleBlock(BlockState baseState, Properties properties) {
		super(baseState, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state);
	}

}
