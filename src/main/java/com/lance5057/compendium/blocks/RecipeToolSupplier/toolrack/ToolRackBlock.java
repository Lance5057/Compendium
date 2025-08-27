package com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.lance5057.compendium.blocks.RecipeToolSupplier.RecipeToolSupplierBlock;
import com.lance5057.compendium.util.ShapeUtil;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ToolRackBlock extends RecipeToolSupplierBlock implements EntityBlock, SimpleWaterloggedBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected VoxelShape SHAPE_TOP_NORTH = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 8.0D, 16.0D);
	protected VoxelShape SHAPE_BOTTOM_NORTH = Block.box(0.0D, 8.0D, 0.0D, 3.0D, 16.0D, 16.0D);

	protected VoxelShape SHAPE_TOP_EAST = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 3.0D);
	protected VoxelShape SHAPE_BOTTOM_EAST = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 3.0D);

	protected VoxelShape SHAPE_TOP_SOUTH = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	protected VoxelShape SHAPE_BOTTOM_SOUTH = Block.box(13.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	protected VoxelShape SHAPE_TOP_WEST = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 8.0D, 16.0D);
	protected VoxelShape SHAPE_BOTTOM_WEST = Block.box(0.0D, 8.0D, 13.0D, 16.0D, 16.0D, 16.0D);

	public ToolRackBlock() {
		super(Block.Properties.ofFullCopy(Blocks.STONE).strength(3, 4).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		Direction d = pState.getValue(FACING);

		if (d == Direction.NORTH)
			return Shapes.joinUnoptimized(SHAPE_TOP_NORTH, SHAPE_BOTTOM_NORTH, BooleanOp.OR);
		if (d == Direction.EAST)
			return Shapes.joinUnoptimized(SHAPE_TOP_EAST, SHAPE_BOTTOM_EAST, BooleanOp.OR);
		if (d == Direction.SOUTH)
			return Shapes.joinUnoptimized(SHAPE_TOP_SOUTH, SHAPE_BOTTOM_SOUTH, BooleanOp.OR);
		if (d == Direction.WEST)
			return Shapes.joinUnoptimized(SHAPE_TOP_WEST, SHAPE_BOTTOM_WEST, BooleanOp.OR);

		return SHAPE_TOP_NORTH;
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
		return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, false);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof ToolRackBlockEntity be) {
			if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
				int i = topOrBottomClick(hitResult, state);
				if (i != -1) {

					player.setItemInHand(InteractionHand.MAIN_HAND, be.getItems().extractItem(i, 1, false));
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.CONSUME;
	}

	@Nonnull
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState pState, Level pLevel, BlockPos pPos,
			Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		BlockEntity blockentity = pLevel.getBlockEntity(pPos);
		if (blockentity instanceof ToolRackBlockEntity be) {
			if (be.canAccept(stack)) {
				int i = topOrBottomClick(pHit, pState);
				if (i != -1) {
					pPlayer.setItemInHand(pHand, be.getItems().insertItem(i, stack, false));
					return ItemInteractionResult.SUCCESS;
				}
			}
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	int topOrBottomClick(BlockHitResult hit, BlockState state) {
		Direction d = state.getValue(FACING);

		double x = hit.getLocation().x - hit.getBlockPos().getX();
		double y = hit.getLocation().y - hit.getBlockPos().getY();
		double z = hit.getLocation().z - hit.getBlockPos().getZ();

		Vec3 v = new Vec3(x, y, z);

		VoxelShape s;
		switch (d) {
		case NORTH:
			s = ShapeUtil.detectCollision(v, SHAPE_TOP_NORTH, SHAPE_BOTTOM_NORTH);
			if (s != null) {
				if (s == SHAPE_TOP_NORTH)
					return 0;
				else
					return 1;
			}
			break;
		case SOUTH:
			s = ShapeUtil.detectCollision(v, SHAPE_TOP_SOUTH, SHAPE_BOTTOM_SOUTH);
			if (s != null) {
				if (s == SHAPE_TOP_SOUTH)
					return 0;
				else
					return 1;
			}
			break;
		case EAST:
			s = ShapeUtil.detectCollision(v, SHAPE_TOP_EAST, SHAPE_BOTTOM_EAST);
			if (s != null) {
				if (s == SHAPE_TOP_EAST)
					return 0;
				else
					return 1;
			}
			break;
		case WEST:
			s = ShapeUtil.detectCollision(v, SHAPE_TOP_WEST, SHAPE_BOTTOM_WEST);
			if (s != null) {
				if (s == SHAPE_TOP_WEST)
					return 0;
				else
					return 1;
			}
			break;
		}
		return -1;
	}
}
