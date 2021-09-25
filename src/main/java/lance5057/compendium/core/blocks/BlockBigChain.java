package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockBigChain extends RotatedPillarBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape Y_AABB = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    public BlockBigChain(Properties properties) {
	super(properties);
	this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.Y).with(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	switch (state.get(AXIS)) {
	case X:
	default:
	    return X_AABB;
	case Z:
	    return Z_AABB;
	case Y:
	    return Y_AABB;
	}
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
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
	super.fillStateContainer(builder);
	builder.add(WATERLOGGED);
    }

    @Override
    public boolean isTransparent(BlockState state) {
	return true;
    }

    @Override
    public boolean isLadder(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos,
	    net.minecraft.entity.LivingEntity entity) {
	return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
	FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

	return this.getDefaultState().with(AXIS, context.getFace().getAxis()).with(WATERLOGGED,
		Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
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
