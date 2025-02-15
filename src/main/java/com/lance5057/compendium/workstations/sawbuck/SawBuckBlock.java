package com.lance5057.compendium.workstations.sawbuck;

import com.lance5057.compendium.workstations._bases.blocks.StationGuiless;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SawBuckBlock extends StationGuiless {

	public SawBuckBlock() {
		super(Block.Properties.ofFullCopy(Blocks.STONE).strength(3, 4).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new SawBuckBlockEntity(p_153215_, p_153216_);
	}
}
