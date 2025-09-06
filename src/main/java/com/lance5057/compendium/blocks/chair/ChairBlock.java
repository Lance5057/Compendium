package com.lance5057.compendium.blocks.chair;

import com.lance5057.compendium.blocks.entity.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.entities.SeatEntity;
import com.lance5057.compendium.style.StyleData;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends HorizontalDirectionalBlock implements EntityBlock {

	protected static final VoxelShape BASE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 9.0D, 13.0D);

	protected static final VoxelShape BACKN = Block.box(3.0D, 7.0D, 3.0D, 13.0D, 18.0D, 5.0D);
	protected static final VoxelShape BACKE = Block.box(11.0D, 7.0D, 3.0D, 13.0D, 18.0D, 13.0D);
	protected static final VoxelShape BACKS = Block.box(3.0D, 7.0D, 11.0D, 13.0D, 18.0D, 13.0D);
	protected static final VoxelShape BACKW = Block.box(3.0D, 7.0D, 3.0D, 5.0D, 18.0D, 13.0D);

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

	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {

		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		this.attemptSit(state, level, pos, player, hitResult);
		return InteractionResult.PASS;
	}

	public InteractionResult attemptSit(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {

		if (level
				.getEntities(null,
						new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1))
				.isEmpty()) {
			SeatEntity s = new SeatEntity(level, pos, state.getValue(HorizontalDirectionalBlock.FACING), 0.2f);
			level.addFreshEntity(s);

			player.startRiding(s);
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.CONSUME;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.CHAIR_BACK, StyleData.CHAIR_SEAT,
				StyleData.CHAIR_LEGS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction d = state.getValue(HorizontalDirectionalBlock.FACING);

		switch (d) {
		case NORTH:
			return Shapes.joinUnoptimized(BASE, BACKN, BooleanOp.OR);
		case EAST:
			return Shapes.joinUnoptimized(BASE, BACKE, BooleanOp.OR);
		case SOUTH:
			return Shapes.joinUnoptimized(BASE, BACKS, BooleanOp.OR);
		case WEST:
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
