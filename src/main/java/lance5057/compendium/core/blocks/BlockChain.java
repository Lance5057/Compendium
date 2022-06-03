package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockChain extends BlockAllAxis {

	protected static final VoxelShape SHAPE_BASE = Block.box(7, 7, 7, 9, 9, 9);

	// protected static final VoxelShape SHAPE_UP = Block.box(7D, 8D, 7D, 9D, 16D,
	// 9D);
	protected static final VoxelShape SHAPE_UPDOWN = Block.box(7D, 0D, 7D, 9D, 16D, 9D);

	protected static final VoxelShape SHAPE_NORTH = Block.box(7, 7, 0, 9, 9, 8);
	protected static final VoxelShape SHAPE_SOUTH = Block.box(7, 7, 8, 9, 9, 16);

	protected static final VoxelShape SHAPE_EAST = Block.box(8, 7, 7, 16, 9, 9);
	protected static final VoxelShape SHAPE_WEST = Block.box(0, 7, 7, 8, 9, 9);

	public BlockChain(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		VoxelShape vs = SHAPE_BASE;

		if (state.getValue(DOWN) || state.getValue(UP))
			vs = Shapes.or(vs, SHAPE_UPDOWN);
		if (state.getValue(NORTH))
			vs = Shapes.or(vs, SHAPE_NORTH);
		if (state.getValue(SOUTH))
			vs = Shapes.or(vs, SHAPE_SOUTH);
		if (state.getValue(EAST))
			vs = Shapes.or(vs, SHAPE_EAST);
		if (state.getValue(WEST))
			vs = Shapes.or(vs, SHAPE_WEST);

		if (!state.getValue(UP) && !state.getValue(DOWN) && !state.getValue(NORTH) && !state.getValue(SOUTH)
				&& !state.getValue(EAST) && !state.getValue(WEST)) {
			vs = Shapes.or(vs, SHAPE_UPDOWN);
		}

		return vs;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return !state.getValue(WATERLOGGED);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return updateModel(this.defaultBlockState(), worldIn, currentPos);
	}

	public boolean canSurvive(BlockState state, LevelAccessor worldIn, BlockPos pos, Direction direction) {
		return worldIn.getBlockState(pos.relative(direction)) != Blocks.AIR.defaultBlockState();
		// return Block.hasEnoughSolidSide(worldIn, pos.relative(direction),
		// direction.getOpposite());
	}

	protected BlockState updateModel(BlockState state, LevelAccessor worldIn, BlockPos pos) {

		BlockState newState = state;

		if (canSurvive(state, worldIn, pos, Direction.UP))
			newState = newState.setValue(UP, true);
		if (canSurvive(state, worldIn, pos, Direction.DOWN))
			newState = newState.setValue(DOWN, true);
		if (canSurvive(state, worldIn, pos, Direction.NORTH))
			newState = newState.setValue(NORTH, true);
		if (canSurvive(state, worldIn, pos, Direction.SOUTH))
			newState = newState.setValue(SOUTH, true);
		if (canSurvive(state, worldIn, pos, Direction.EAST))
			newState = newState.setValue(EAST, true);
		if (canSurvive(state, worldIn, pos, Direction.WEST))
			newState = newState.setValue(WEST, true);

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
