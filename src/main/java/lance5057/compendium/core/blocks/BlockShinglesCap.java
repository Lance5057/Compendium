package lance5057.compendium.core.blocks;

import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FourWayBlock;
import net.minecraft.block.SixWayBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BlockShinglesCap extends FourWayBlock {

    public static final BooleanProperty UP = SixWayBlock.UP;
    private final VoxelShape renderShape;

    public BlockShinglesCap(AbstractBlock.Properties properties) {

	super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, properties);
	this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
		.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
		.with(WEST, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false))
		.with(UP, Boolean.valueOf(false)));
	this.renderShape = Block.makeCuboidShape(0, 0, 0, 16, 8, 16);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED, UP);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
	    BlockPos currentPos, BlockPos facingPos) {
	if (stateIn.get(WATERLOGGED)) {
	    worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	}

	if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
	    return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(this.canConnect(facingState,
		    facingState.isSolidSide(worldIn, facingPos, facing.getOpposite()), facing.getOpposite())));
	else if (facing == Direction.UP) {
	    return stateIn.with(UP, Boolean.valueOf(facingState.getBlock() != Blocks.AIR));
	}

	return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
	Block block = state.getBlock();
	boolean flag = this.isShingle(block);
	return flag;
    }
    
    private boolean isShingle(Block block) {
	return block.isIn(CompendiumTags.SHINGLESCAP);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return this.renderShape;
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
	return this.renderShape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
	    ISelectionContext context) {
	return this.renderShape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	return this.renderShape;
    }
}
