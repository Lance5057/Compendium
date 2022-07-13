package lance5057.compendium.core.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.tileentities.ItemDisplayTileEntity;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemDisplayBlock extends Block implements EntityBlock ,SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIGHT = BlockStateProperties.LIT;

	public ItemDisplayBlock() {
		super(Block.Properties.of(Material.METAL).strength(3, 4).noOcclusion().lightLevel((state) -> {
			if (state.getValue(LIGHT).booleanValue())
				return 8;
			else
				return 0;
		}));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIGHT, false)
				.setValue(WATERLOGGED, false));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return !state.getValue(WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ItemDisplayTileEntity(CompendiumTileEntities..get(), pPos, pState);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, LIGHT);
	}

	@Nonnull
	@Override
	public InteractionResultHolder onBlockActivated(@Nonnull BlockState blockState, Level world,
			@Nonnull BlockPos blockPos, @Nonnull Player Player, @Nonnull InteractionHand InteractionHand,
			@Nonnull BlockHitResult BlockHitResult) {

		TileEntity entity = world.getTileEntity(blockPos);
		if (entity instanceof ItemDisplayTileEntity) {

			ItemDisplayTileEntity te = ((ItemDisplayTileEntity) entity);

			if (Player.getHeldItem(InteractionHand).isEmpty()) {
				te.extractItem(Player);
			} else
				te.insertItem(Player.getHeldItem(InteractionHand));

			return InteractionResultHolder.SUCCESS;
		}

		return super.onBlockActivated(blockState, world, blockPos, Player, InteractionHand, BlockHitResult);

	}

//    @Override
//    public void onBlockClicked(BlockState state, Level worldIn, BlockPos pos, Player player) {
//	TileEntity entity = worldIn.getTileEntity(pos);
//	if (entity instanceof ItemDisplayTileEntity) {
//
//	    ItemDisplayTileEntity te = ((ItemDisplayTileEntity) entity);
//
//	    te.extractItem(player);
//	}
//    }

	@Override
	public void onReplaced(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
			boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof ItemDisplayTileEntity) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY).ifPresent(
						itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots()).forEach(
								i -> Block.spawnAsEntity(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));

				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getPos());

		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
		return blockstate;
	}

}
