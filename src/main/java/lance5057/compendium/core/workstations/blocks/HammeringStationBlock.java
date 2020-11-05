package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.items.HammerItem;
import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;

public class HammeringStationBlock extends Block {

	public HammeringStationBlock() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(0).hardnessAndResistance(3, 4)
				.harvestTool(ToolType.PICKAXE));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new HammeringStationTE();
	}

	@Nonnull
	@Override
	public ActionResultType onBlockActivated(@Nonnull BlockState blockState, World world, @Nonnull BlockPos blockPos,
			@Nonnull PlayerEntity playerEntity, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
		//if (world instanceof ServerWorld) {
			TileEntity entity = world.getTileEntity(blockPos);
			if (entity instanceof HammeringStationTE) {

				HammeringStationTE te = ((HammeringStationTE) entity);
				if (!playerEntity.isCrouching()) {
					// Get item in both hands, ignore sent hand
					ItemStack heldmain = playerEntity.getHeldItem(Hand.MAIN_HAND);
					ItemStack heldoff = playerEntity.getHeldItem(Hand.OFF_HAND);

					// Try inserting main hand item
					if (!(heldmain.getItem() instanceof HammerItem)) {
						te.insertItem(heldmain);
					}
					// Try inserting off hand item
					if (!(heldoff.getItem() instanceof HammerItem)) {
						te.insertItem(heldoff);
					}

					// Hit it!
					// Try main hand, only try off hand if that fails
					if (heldmain.getItem() instanceof HammerItem) {
						te.hammer(heldmain);
						// heldmain.attemptDamageItem(1, RANDOM, null);
					} else if (heldoff.getItem() instanceof HammerItem) {
						te.hammer(heldoff);
						// heldoff.attemptDamageItem(1, RANDOM, null);
					}
				} else // If crouching, take item off table
				{
					te.extractItem(playerEntity);
					return ActionResultType.SUCCESS;
				}
			}
		//}
		return super.onBlockActivated(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
	}

	@Override
	public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState,
			boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof HammeringStationTE) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
						.ifPresent(itemHandler -> IntStream.range(0, itemHandler.getSlots())
								.forEach(i -> Block.spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i))));

				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}
}
