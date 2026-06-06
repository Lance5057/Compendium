package com.lance5057.compendium.workstations.sawbuck;

import com.lance5057.compendium.workstations._bases.blocks.StationGuiless;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SawBuckBlock extends StationGuiless {
	protected static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

	public SawBuckBlock() {
		super(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).strength(3, 4).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new SawBuckBlockEntity(p_153215_, p_153216_);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		BlockEntity be = level.getBlockEntity(pos);
		
		if(be != null && be instanceof SawBuckBlockEntity sb)
		{
			ItemStack stack = sb.getInventory().getStackInSlot(0);
		}
		
		return BASE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());

		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite().getCounterClockWise())
				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
		return blockstate;
	}
}
