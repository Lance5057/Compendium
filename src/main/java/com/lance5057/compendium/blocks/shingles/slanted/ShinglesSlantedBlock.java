package com.lance5057.compendium.blocks.shingles.slanted;

import com.lance5057.compendium.blocks.entity.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;

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
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.SHINGLES, StyleData.SUPPORT,
				StyleData.GABLE);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
}
