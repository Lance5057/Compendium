package com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack;

import javax.annotation.Nullable;

import com.lance5057.compendium.blocks.RecipeToolSupplier.RecipeToolSupplierBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ToolRackBlock extends RecipeToolSupplierBlock implements EntityBlock, SimpleWaterloggedBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public ToolRackBlock() {
		super(Block.Properties.ofFullCopy(Blocks.STONE).strength(3, 4).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ToolRackBlockEntity(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
		Direction direction = p_49479_.getHorizontalDirection().getClockWise();
		BlockPos blockpos = p_49479_.getClickedPos();
		BlockPos blockpos1 = blockpos.relative(direction);
		Level level = p_49479_.getLevel();
		return level.getBlockState(blockpos1).canBeReplaced(p_49479_)
				&& level.getWorldBorder().isWithinBounds(blockpos1)
						? this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, false)
						: null;
	}

}
