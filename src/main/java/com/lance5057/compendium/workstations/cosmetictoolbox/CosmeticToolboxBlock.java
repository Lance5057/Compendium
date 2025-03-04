package com.lance5057.compendium.workstations.cosmetictoolbox;

import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CosmeticToolboxBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	protected VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);

	public CosmeticToolboxBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState pState) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		MenuProvider containerProvider = new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.translatable("screen.cosmetic_toolbox.name");
			}

			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
				return new CosmeticToolboxPlacedMenu(windowId, playerInventory,
						ContainerLevelAccess.create(level, pos));
			}
		};
		player.openMenu(containerProvider, buf -> buf.writeBlockPos(pos));
		return InteractionResult.SUCCESS;
	}
}
