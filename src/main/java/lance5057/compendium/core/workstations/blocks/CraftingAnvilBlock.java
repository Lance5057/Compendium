package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.items.HammerItem;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

public class CraftingAnvilBlock extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public CraftingAnvilBlock() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE).notSolid());
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote)
			return ActionResultType.SUCCESS; // on client side, don't do anything

		TileEntity entity = worldIn.getTileEntity(pos);
		if (entity instanceof CraftingAnvilTE) {

			CraftingAnvilTE te = ((CraftingAnvilTE) entity);
			if (!player.isCrouching()) {
				boolean success = false;
				// Get item in both hands
				ItemStack heldmain = player.getHeldItem(Hand.MAIN_HAND);
				ItemStack heldoff = player.getHeldItem(Hand.OFF_HAND);

				// Hit it!
				// Try main hand, only try off hand if that fails
				if (heldmain.getItem() instanceof HammerItem) {
					te.hammer(player, heldmain);
					success = true;
				} else if (heldoff.getItem() instanceof HammerItem) {
					te.hammer(player, heldoff);
					success = true;
				}

				if (success)
					return ActionResultType.SUCCESS;
			}
//			INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
//			if (this.hasTileEntity(getDefaultState())) {
				if (!(player instanceof ServerPlayerEntity))
					return ActionResultType.FAIL;
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
				NetworkHooks.openGui(serverPlayerEntity, te, (packetBuffer) -> {
					packetBuffer.writeInt(te.progress);
				});
//			}
		}

		return ActionResultType.SUCCESS;
	}

//	@Nonnull
//	@Override
//	public ActionResultType onBlockActivated(@Nonnull BlockState blockState, World world, @Nonnull BlockPos blockPos, @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
//		if (hand == Hand.MAIN_HAND) {
//			TileEntity entity = world.getTileEntity(blockPos);
//			if (entity instanceof CraftingAnvilTE) {
//
//				CraftingAnvilTE te = ((CraftingAnvilTE) entity);
//				if (!playerEntity.isCrouching()) {
//					boolean success = false;
//					// Get item in both hands
//					ItemStack heldmain = playerEntity.getHeldItem(Hand.MAIN_HAND);
//					ItemStack heldoff = playerEntity.getHeldItem(Hand.OFF_HAND);
//
//					// Hit it!
//					// Try main hand, only try off hand if that fails
//					if (heldmain.getItem() instanceof HammerItem) {
//						te.hammer(playerEntity, heldmain);
//						success = true;
//					} else if (heldoff.getItem() instanceof HammerItem) {
//						te.hammer(playerEntity, heldoff);
//						success = true;
//					}
//
//					if (success)
//						return ActionResultType.SUCCESS;
//				} else // If crouching, take item off table
//				{
//					te.extractItem(playerEntity);
//					return ActionResultType.SUCCESS;
//				}
//			}
//		}
//		return super.onBlockActivated(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
//
//	}

	@Override
	public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof CraftingAnvilTE) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> IntStream.range(0, itemHandler.getSlots()).forEach(i -> Block.spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i))));

				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	/**
	 * Called throughout the code as a replacement for
	 * ITileEntityProvider.createNewTileEntity Return the same thing you would from
	 * that function. This will fall back to
	 * ITileEntityProvider.createNewTileEntity(World) if this block is a
	 * ITileEntityProvider
	 *
	 * @param state The state of the current block
	 * @param world The world to create the TE in
	 * @return A instance of a class extending TileEntity
	 */
	@Override
	@Nullable
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CraftingAnvilTE();
	}
}
