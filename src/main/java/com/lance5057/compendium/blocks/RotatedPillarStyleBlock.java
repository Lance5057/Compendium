package com.lance5057.compendium.blocks;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RotatedPillarStyleBlock extends RotatedPillarBlock implements EntityBlock {
	public final StyleData[] styles;

	public RotatedPillarStyleBlock(Properties properties, StyleData... styles) {
		super(properties);
		this.styles = styles;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, styles.length, styles);
	}

}
