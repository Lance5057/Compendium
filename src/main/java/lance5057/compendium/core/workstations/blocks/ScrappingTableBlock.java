package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.items.tools.HammerItem;
import lance5057.compendium.core.workstations.tileentities.ScrappingTableTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;

public class ScrappingTableBlock extends Block implements IWaterLoggable {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public ScrappingTableBlock() {
	super(Block.Properties.create(Material.ROCK).harvestLevel(0).hardnessAndResistance(3, 4)
		.harvestTool(ToolType.PICKAXE).notSolid());
    }
    
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(FACING, WATERLOGGED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
	return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	return !state.get(WATERLOGGED);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
	return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	return new ScrappingTableTE();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	return SHAPE;
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState blockState, World world, @Nonnull BlockPos blockPos,
	    @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
	if (hand == Hand.MAIN_HAND) {
	    TileEntity entity = world.getTileEntity(blockPos);
	    if (entity instanceof ScrappingTableTE) {

		ScrappingTableTE te = ((ScrappingTableTE) entity);
		if (!playerEntity.isCrouching()) {
		    boolean success = false;
		    // Get item in both hands, ignore sent hand
		    ItemStack heldmain = playerEntity.getHeldItem(Hand.MAIN_HAND);
		    ItemStack heldoff = playerEntity.getHeldItem(Hand.OFF_HAND);

		    // Try inserting main hand item
		    if (!(heldmain.getItem() instanceof HammerItem)) {
			te.insertItem(heldmain);
			success = true;
		    }
		    // Try inserting off hand item
		    if (!(heldoff.getItem() instanceof HammerItem)) {
			te.insertItem(heldoff);
			success = true;
		    }

		    // Hit it!
		    // Try main hand, only try off hand if that fails
		    if (heldmain.getItem() instanceof HammerItem) {
			te.hammer(playerEntity, heldmain);
			success = true;
		    } else if (heldoff.getItem() instanceof HammerItem) {
			te.hammer(playerEntity, heldoff);
			success = true;
		    }

		    if (success)
			return ActionResultType.SUCCESS;
		} else // If crouching, take item off table
		{
		    te.extractItem(playerEntity);
		    return ActionResultType.SUCCESS;
		}
	    }
	}
	return super.onBlockActivated(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);

    }

    @Override
    public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState,
	    boolean isMoving) {
	if (state.getBlock() != newState.getBlock()) {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	    if (tileentity instanceof ScrappingTableTE) {
		tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			.ifPresent(itemHandler -> IntStream.range(0, itemHandler.getSlots())
				.forEach(i -> Block.spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i))));

		worldIn.updateComparatorOutputLevel(pos, this);
	    }

	    super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
	FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

	BlockState blockstate = this.getDefaultState()
		.with(FACING, context.getPlacementHorizontalFacing().getOpposite())
		.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	return blockstate;
    }
}
