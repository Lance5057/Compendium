package lance5057.compendium.core.blocks;

import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockShinglesCap extends CrossCollisionBlock {

	public static final BooleanProperty UP = PipeBlock.UP;
	private final VoxelShape renderShape;

	public BlockShinglesCap(Properties properties) {

		super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, properties);
		this.registerDefaultState(
				this.defaultBlockState().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false))
						.setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)));
		this.renderShape = Block.box(0, 0, 0, 16, 8, 16);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED, UP);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
			return stateIn.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(this.canConnect(facingState,
					facingState.isFaceSturdy(worldIn, facingPos, facing.getOpposite()), facing.getOpposite())));
		else if (facing == Direction.UP) {
			return stateIn.setValue(UP, Boolean.valueOf(facingState.getBlock() != Blocks.AIR));
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
		Block block = state.getBlock();
		boolean flag = this.isShingle(state);
		return flag;
	}

	private boolean isShingle(BlockState block) {
		return block.is(CompendiumTags.SHINGLESCAP);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return this.renderShape;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return this.renderShape;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos,
			CollisionContext context) {
		return this.renderShape;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return this.renderShape;
	}
}
