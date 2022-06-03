package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class BlockConnected extends Block implements SimpleWaterloggedBlock {

	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BlockConnected(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, WATERLOGGED);
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

	public boolean canSurvive(BlockState state, LevelAccessor worldIn, BlockPos pos, Direction direction) {
		return worldIn.getBlockState(pos.relative(direction)).getBlock() == this;
		// return Block.hasEnoughSolidSide(worldIn, pos.relative(direction),
		// direction.getOpposite());
	}

	protected BlockState updateModel(BlockState state, LevelAccessor worldIn, BlockPos pos) {

		BlockState newState = state;
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
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		updateModel(state, worldIn, pos);
	}
}
