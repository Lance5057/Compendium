package com.lance5057.compendium.blocks;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StairStyleBlock extends StairBlock implements EntityBlock {
	public final StyleData[] styles;

	public StairStyleBlock(BlockState base, Properties properties, StyleData... styles) {
		super(base, properties);
		this.styles = styles;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, styles.length, styles);
	}

}