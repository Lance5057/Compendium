package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BlockChain extends BlockAllAxis {

    protected static final VoxelShape SHAPE_BASE = Block.makeCuboidShape(7, 7, 7, 9, 9, 9);

    //protected static final VoxelShape SHAPE_UP = Block.makeCuboidShape(7D, 8D, 7D, 9D, 16D, 9D);
    protected static final VoxelShape SHAPE_UPDOWN = Block.makeCuboidShape(7D, 0D, 7D, 9D, 16D, 9D);

    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(7, 7, 0, 9, 9, 8);
    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(7, 7, 8, 9, 9, 16);

    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(8, 7, 7, 16, 9, 9);
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(0, 7, 7, 8, 9, 9);

    public BlockChain(Properties properties) {
	super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	VoxelShape vs = SHAPE_BASE;

	if (state.get(DOWN) || state.get(UP))
	    vs = VoxelShapes.or(vs, SHAPE_UPDOWN);
	if (state.get(NORTH))
	    vs = VoxelShapes.or(vs, SHAPE_NORTH);
	if (state.get(SOUTH))
	    vs = VoxelShapes.or(vs, SHAPE_SOUTH);
	if (state.get(EAST))
	    vs = VoxelShapes.or(vs, SHAPE_EAST);
	if (state.get(WEST))
	    vs = VoxelShapes.or(vs, SHAPE_WEST);

	if (!state.get(UP) && !state.get(DOWN) && !state.get(NORTH) && !state.get(SOUTH) && !state.get(EAST)
		&& !state.get(WEST)) {
	    vs = VoxelShapes.or(vs, SHAPE_UPDOWN);
	}

	return vs;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
	return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	return !state.get(WATERLOGGED);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
	    BlockPos currentPos, BlockPos facingPos) {
	if (stateIn.get(WATERLOGGED)) {
	    worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	}

	return updateModel(this.getDefaultState(), worldIn, currentPos);
    }

    public boolean isValidPosition(BlockState state, IWorld worldIn, BlockPos pos, Direction direction) {
	return worldIn.getBlockState(pos.offset(direction)) != Blocks.AIR.getDefaultState();
	// return Block.hasEnoughSolidSide(worldIn, pos.offset(direction),
	// direction.getOpposite());
    }

    protected BlockState updateModel(BlockState state, IWorld worldIn, BlockPos pos) {

	BlockState newState = state;

	if (isValidPosition(state, worldIn, pos, Direction.UP))
	    newState = newState.with(UP, true);
	if (isValidPosition(state, worldIn, pos, Direction.DOWN))
	    newState = newState.with(DOWN, true);
	if (isValidPosition(state, worldIn, pos, Direction.NORTH))
	    newState = newState.with(NORTH, true);
	if (isValidPosition(state, worldIn, pos, Direction.SOUTH))
	    newState = newState.with(SOUTH, true);
	if (isValidPosition(state, worldIn, pos, Direction.EAST))
	    newState = newState.with(EAST, true);
	if (isValidPosition(state, worldIn, pos, Direction.WEST))
	    newState = newState.with(WEST, true);

	return newState;
    }

    @Override
    public boolean isSlimeBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        return true;
    }
}
