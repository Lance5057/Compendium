package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockSideConnected extends HorizontalBlock implements IWaterLoggable {
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(2, -4, 12, 14, 1, 14);
    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(2, -4, 2, 14, 1, 4);
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(2, -4, 2, 4, 1, 14);
    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(12, -4, 2, 14, 1, 14);

    public BlockSideConnected(Properties properties) {
	super(properties);
	this.setDefaultState(this.getDefaultState().with(RIGHT, Boolean.valueOf(false))
		.with(LEFT, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	VoxelShape vs = SHAPE_SOUTH;

	if (state.get(HORIZONTAL_FACING) == Direction.NORTH)
	    vs = SHAPE_NORTH;
	if (state.get(HORIZONTAL_FACING) == Direction.SOUTH)
	    vs = SHAPE_SOUTH;
	if (state.get(HORIZONTAL_FACING) == Direction.WEST)
	    vs = SHAPE_WEST;
	if (state.get(HORIZONTAL_FACING) == Direction.EAST)
	    vs = SHAPE_EAST;

	return vs;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(HORIZONTAL_FACING, LEFT, RIGHT, WATERLOGGED);
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

    public boolean isValidPosition(BlockState state, IWorld worldIn, BlockPos pos, int rot) {

	Direction d = worldIn.getBlockState(pos).get(HORIZONTAL_FACING);
	int i = d.getHorizontalIndex();
	BlockPos p = pos.offset(Direction.byHorizontalIndex(i + rot));
	return worldIn.getBlockState(p).getBlock() == this;
    }

    protected BlockState updateModel(BlockState state, IWorld worldIn, BlockPos pos) {

	BlockState newState = state;
	if (isValidPosition(state, worldIn, pos, -1))
	    newState = newState.with(RIGHT, true);
	if (isValidPosition(state, worldIn, pos, 1))
	    newState = newState.with(LEFT, true);

	return newState;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
	return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
	    boolean isMoving) {
	updateModel(state, worldIn, pos);
    }
}
