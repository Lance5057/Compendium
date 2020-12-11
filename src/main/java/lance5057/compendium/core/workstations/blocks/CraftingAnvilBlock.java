package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;

public class CraftingAnvilBlock extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private static final ITextComponent containerName = new TranslationTextComponent("compendium.container.anvilcraft");

	public CraftingAnvilBlock() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE).notSolid());
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY());
	}

	@Nullable
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, inventory, player) -> {
	         return new WorkbenchContainer(id, inventory, IWorldPosCallable.of(worldIn, pos));
	      }, containerName);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
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
			if (tileentity instanceof HammeringStationTE) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> IntStream.range(0, itemHandler.getSlots()).forEach(i -> Block.spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i))));

				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new HammeringStationTE();
	}
}
