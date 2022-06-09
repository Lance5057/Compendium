package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraftforge.network.NetworkHooks;

public class CraftingAnvilBlock extends Block implements EntityBlock ,SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CraftingAnvilBlock() {
		super(Block.Properties.of(Material.STONE).strength(3, 4).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
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

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
			InteractionHand InteractionHandIn, BlockHitResult hit) {
		if (worldIn.isClientSide)
			return InteractionResult.SUCCESS; // on client side, don't do anything

		BlockEntity entity = worldIn.getBlockEntity(pos);
		if (entity instanceof CraftingAnvilTE) {

			CraftingAnvilTE te = ((CraftingAnvilTE) entity);
			if (!player.isCrouching()) {
				boolean success = false;
				// Get item in both InteractionHands
				ItemStack heldmain = player.getItemInHand(InteractionHand.MAIN_HAND);
				// ItemStack heldoff = player.getHeldItem(InteractionHand.OFF_InteractionHand);

				if (heldmain != ItemStack.EMPTY)
					te.hammer(player, heldmain);
//		if (heldoff != ItemStack.EMPTY)
//		    te.hammer(player, heldoff);
			}
//			INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
//			if (this.hasTileEntity(defaultBlockState())) {
			else {
				if (!(player instanceof ServerPlayer))
					return InteractionResult.FAIL; 
				ServerPlayer serverPlayer = (ServerPlayer) player;
				NetworkHooks.openGui(serverPlayer, te, pos);
			}
//			}
		}

		return InteractionResult.PASS;
	}

//	@Nonnull
//	@Override
//	public InteractionResultHolder onBlockActivated(@Nonnull BlockState blockState, Level world, @Nonnull BlockPos blockPos, @Nonnull Player Player, @Nonnull InteractionHand InteractionHand, @Nonnull BlockHitResult BlockHitResult) {
//		if (InteractionHand == InteractionHand.MAIN_InteractionHand) {
//			TileEntity entity = world.getTileEntity(blockPos);
//			if (entity instanceof CraftingAnvilTE) {
//
//				CraftingAnvilTE te = ((CraftingAnvilTE) entity);
//				if (!Player.isCrouching()) {
//					boolean success = false;
//					// Get item in both InteractionHands
//					ItemStack heldmain = Player.getHeldItem(InteractionHand.MAIN_InteractionHand);
//					ItemStack heldoff = Player.getHeldItem(InteractionHand.OFF_InteractionHand);
//
//					// Hit it!
//					// Try main InteractionHand, only try off InteractionHand if that fails
//					if (heldmain.getItem() instanceof HammerItem) {
//						te.hammer(Player, heldmain);
//						success = true;
//					} else if (heldoff.getItem() instanceof HammerItem) {
//						te.hammer(Player, heldoff);
//						success = true;
//					}
//
//					if (success)
//						return InteractionResultHolder.SUCCESS;
//				} else // If crouching, take item off table
//				{
//					te.extractItem(Player);
//					return InteractionResultHolder.SUCCESS;
//				}
//			}
//		}
//		return super.onBlockActivated(blockState, world, blockPos, Player, InteractionHand, BlockHitResult);
//
//	}

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

	/**
	 * Called throughout the code as a replacement for
	 * ITileEntityProvider.createNewTileEntity Return the same thing you would from
	 * that function. This will fall back to
	 * ITileEntityProvider.createNewTileEntity(Level) if this block is a
	 * ITileEntityProvider
	 *
	 * @param state The state of the current block
	 * @param Level The Level to create the TE in
	 * @return A instance of a class extending TileEntity
	 */
	@Override
	@Nullable
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new CraftingAnvilTE(pPos, pState);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());

		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
		return blockstate;
	}
}
