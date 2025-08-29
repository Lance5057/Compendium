package com.lance5057.compendium.blocks.shingles.slanted;

import com.lance5057.compendium.CompendiumBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShinglesSlantedBlock extends StairBlock implements EntityBlock {

	public ShinglesSlantedBlock(BlockState baseState, Properties properties) {
		super(baseState, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
		return CompendiumBlockEntities.SLANTED_SHINGLES.get().create(pos, state);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
}
