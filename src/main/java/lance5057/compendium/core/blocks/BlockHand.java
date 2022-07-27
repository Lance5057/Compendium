package lance5057.compendium.core.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.tileentities.HandTileEntity;
import lance5057.compendium.core.tileentities.ItemDisplayTileEntity;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockHand extends Block implements EntityBlock, SimpleWaterloggedBlock {
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIGHT = BlockStateProperties.LIT;

	public BlockHand() {
		super(Block.Properties.of(Material.METAL).strength(3, 4).noOcclusion().lightLevel((state) -> {
			if (state.getValue(LIGHT).booleanValue())
				return 8;
			else
				return 0;
		}));
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, Integer.valueOf(0))
				.setValue(LIGHT, false).setValue(WATERLOGGED, false));
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
		return new HandTileEntity(pPos, pState);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, WATERLOGGED, LIGHT);
	}
	
	@Override
	public BlockState rotate(BlockState p_56326_, Rotation p_56327_) {
		return p_56326_.setValue(ROTATION, Integer.valueOf(p_56327_.rotate(p_56326_.getValue(ROTATION), 16)));
	}

	@Override
	public BlockState mirror(BlockState p_56323_, Mirror p_56324_) {
		return p_56323_.setValue(ROTATION, Integer.valueOf(p_56324_.mirror(p_56323_.getValue(ROTATION), 16)));
	}

	@Nonnull
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
			InteractionHand InteractionHandIn, BlockHitResult hit) {

		BlockEntity entity = worldIn.getBlockEntity(pos);
		if (entity instanceof HandTileEntity) {

			HandTileEntity te = ((HandTileEntity) entity);

			if (player.getItemInHand(InteractionHandIn).isEmpty()) {
				te.extractItem(player);
			} else
				te.insertItem(player.getItemInHand(InteractionHandIn));

			return InteractionResult.SUCCESS;
		}

		return super.use(state, worldIn, pos, player, InteractionHandIn, hit);

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
	public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
			boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof CraftingAnvilTE) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
						itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots()).forEach(
								i -> Block.popResource(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));

				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());

		BlockState blockstate = this.defaultBlockState()
				.setValue(ROTATION, Integer.valueOf(Mth.floor((double)(context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15))
				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
		return blockstate;
	}

}
