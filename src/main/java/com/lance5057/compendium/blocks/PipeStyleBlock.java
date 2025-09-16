package com.lance5057.compendium.blocks;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class PipeStyleBlock extends PipeBlock implements EntityBlock {
	public static final MapCodec<PipeStyleBlock> CODEC = simpleCodec(PipeStyleBlock::new);

	public PipeStyleBlock(Properties properties) {
		super(0.25f, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false)));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends PipeBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();

		return checkNeighbors(level, pos);
	}

	private BlockState checkNeighbors(LevelAccessor level, BlockPos pos) {
		BlockState down = level.getBlockState(pos.below());
		BlockState up = level.getBlockState(pos.above());
		BlockState north = level.getBlockState(pos.north());
		BlockState east = level.getBlockState(pos.east());
		BlockState south = level.getBlockState(pos.south());
		BlockState west = level.getBlockState(pos.west());

		return this.defaultBlockState().trySetValue(DOWN, down.isSolidRender(level, pos))
				.trySetValue(UP, up.isSolidRender(level, pos)).trySetValue(NORTH, north.isSolidRender(level, pos))
				.trySetValue(EAST, east.isSolidRender(level, pos)).trySetValue(SOUTH, south.isSolidRender(level, pos))
				.trySetValue(WEST, west.isSolidRender(level, pos));
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
			BlockPos currentPos, BlockPos facingPos) {
		return checkNeighbors(level, currentPos);
	}

}
