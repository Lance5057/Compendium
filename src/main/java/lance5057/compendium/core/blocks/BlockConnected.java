package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockConnected extends Block implements IWaterLoggable {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockConnected(Properties properties) {
	super(properties);
	this.setDefaultState(this.getDefaultState().with(NORTH, Boolean.valueOf(false))
		.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
		.with(WEST, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(NORTH, EAST, SOUTH, WEST, WATERLOGGED);
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

	return updateModel(stateIn, worldIn, currentPos);
    }

    public boolean isValidPosition(BlockState state, IWorld worldIn, BlockPos pos, Direction direction) {
	return worldIn.getBlockState(pos.offset(direction)).getBlock() == this;
	// return Block.hasEnoughSolidSide(worldIn, pos.offset(direction),
	// direction.getOpposite());
    }

    protected BlockState updateModel(BlockState state, IWorld worldIn, BlockPos pos) {

	BlockState newState = state;
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
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
	    boolean isMoving) {
	updateModel(state, worldIn, pos);
    }
}
