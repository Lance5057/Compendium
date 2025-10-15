package com.lance5057.compendium.workstations.scrappingtable;

import com.lance5057.compendium.workstations._bases.blocks.StationGuiless;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class ScrappingTableBlock extends StationGuiless {

	public ScrappingTableBlock() {
		super(Block.Properties.ofFullCopy(Blocks.STONE).strength(3, 4).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new ScrappingTableBlockEntity(p_153215_, p_153216_);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());

		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
		return blockstate;
	}
}
