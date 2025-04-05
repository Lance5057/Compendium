package com.lance5057.compendium.blocks.RecipeToolSupplier.drawer;

import javax.annotation.Nullable;

import com.lance5057.compendium.blocks.RecipeToolSupplier.RecipeToolSupplierBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ComponentDrawerBlock extends RecipeToolSupplierBlock implements EntityBlock, SimpleWaterloggedBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected VoxelShape SHAPE_NORTH = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
	protected VoxelShape SHAPE_EAST = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	protected VoxelShape SHAPE_SOUTH = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected VoxelShape SHAPE_WEST = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);

	public ComponentDrawerBlock() {
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
			return SHAPE_NORTH;
		if (d == Direction.EAST)
			return SHAPE_EAST;
		if (d == Direction.SOUTH)
			return SHAPE_SOUTH;
		if (d == Direction.WEST)
			return SHAPE_WEST;

		return SHAPE_NORTH;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ComponentDrawerBlockEntity(pos, state);
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
		if (blockentity instanceof ComponentDrawerBlockEntity be) {
			MenuProvider containerProvider = new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.translatable("screen.workbench.name");
				}

				@Override
				public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
					return new ComponentDrawerMenu(windowId, playerInventory, be);
				}
			};
			player.openMenu(containerProvider, buf -> buf.writeBlockPos(pos));
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.CONSUME;
	}
}
