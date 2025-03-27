package com.lance5057.compendium.blocks;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.entities.ChairBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends HorizontalDirectionalBlock implements EntityBlock {

	protected static final VoxelShape BASE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

	protected static final VoxelShape BACKN = Block.box(2.0D, 7.0D, 2.0D, 14.0D, 16.0D, 4.0D);
	protected static final VoxelShape BACKE = Block.box(12.0D, 7.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape BACKS = Block.box(2.0D, 7.0D, 12.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape BACKW = Block.box(2.0D, 7.0D, 2.0D, 4.0D, 16.0D, 14.0D);

	public static final MapCodec<ChairBlock> CODEC = simpleCodec(ChairBlock::new);

	public ChairBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof ChairBlockEntity be) {
			return be.attemptSit(state, level, pos, player, hitResult);
		}
		return InteractionResult.CONSUME;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return CompendiumBlockEntities.CHAIR.get().create(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction d = state.getValue(HorizontalDirectionalBlock.FACING);

		switch (d) {
		case Direction.NORTH:
			return Shapes.joinUnoptimized(BASE, BACKN, BooleanOp.OR);
		case Direction.EAST:
			return Shapes.joinUnoptimized(BASE, BACKE, BooleanOp.OR);
		case Direction.SOUTH:
			return Shapes.joinUnoptimized(BASE, BACKS, BooleanOp.OR);
		case Direction.WEST:
			return Shapes.joinUnoptimized(BASE, BACKW, BooleanOp.OR);
		default:
			return BASE;
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getPlayer().isCrouching())
			return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

}
