package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockSideConnected extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");
	public static final BooleanProperty LEFT = BooleanProperty.create("left");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape SHAPE_SOUTH = Block.box(2, -4, 12, 14, 1, 14);
	protected static final VoxelShape SHAPE_NORTH = Block.box(2, -4, 2, 14, 1, 4);
	protected static final VoxelShape SHAPE_WEST = Block.box(2, -4, 2, 4, 1, 14);
	protected static final VoxelShape SHAPE_EAST = Block.box(12, -4, 2, 14, 1, 14);

	public BlockSideConnected(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(RIGHT, Boolean.valueOf(false))
				.setValue(LEFT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		VoxelShape vs = SHAPE_SOUTH;

		if (state.getValue(FACING) == Direction.NORTH)
			vs = SHAPE_NORTH;
		if (state.getValue(FACING) == Direction.SOUTH)
			vs = SHAPE_SOUTH;
		if (state.getValue(FACING) == Direction.WEST)
			vs = SHAPE_WEST;
		if (state.getValue(FACING) == Direction.EAST)
			vs = SHAPE_EAST;

		return vs;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LEFT, RIGHT, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return !state.getValue(WATERLOGGED);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState,
			LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return updateModel(stateIn, worldIn, currentPos);
	}

	public boolean canSurvive(BlockState state, LevelAccessor worldIn, BlockPos pos, int rot) {

		Direction d = worldIn.getBlockState(pos).getValue(FACING);
		int i = d.get2DDataValue();
		BlockPos p = pos.relative(Direction.from2DDataValue(i + rot));
		return worldIn.getBlockState(p).getBlock() == this;
	}

	protected BlockState updateModel(BlockState state, LevelAccessor worldIn, BlockPos pos) {

		BlockState newState = state;
		if (canSurvive(state, worldIn, pos, -1))
			newState = newState.setValue(RIGHT, true);
		if (canSurvive(state, worldIn, pos, 1))
			newState = newState.setValue(LEFT, true);

		return newState;
	} 

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		updateModel(state, worldIn, pos);
	}
}
