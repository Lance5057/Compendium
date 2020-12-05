package lance5057.compendium.core.blocks;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ComponentBarDoor extends DoorBlock {

	protected static final VoxelShape DEFAULT_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape ROTATED_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	protected static final VoxelShape OPEN_DEFAULT_AABB = Block.makeCuboidShape(0.0D, 14.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape OPEN_ROTATED_AABB = Block.makeCuboidShape(6.0D, 14.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	protected static final VoxelShape OPEN_BOTTOM = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public ComponentBarDoor(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT)
				.with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER));

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(FACING);
		boolean flag = state.get(OPEN);
		//boolean flag1 = state.get(HINGE) == DoorHingeSide.RIGHT;
		if (state.get(HALF) == DoubleBlockHalf.UPPER) {
			switch (direction) {
			case EAST:
			case WEST:
			default:
				return flag ? OPEN_ROTATED_AABB : ROTATED_AABB;
			case SOUTH:
			case NORTH:
				return flag ? OPEN_DEFAULT_AABB : DEFAULT_AABB;
			}
		} else {
			switch (direction) {
			case EAST:
			case WEST:
			default:
				return flag ? OPEN_BOTTOM : ROTATED_AABB;
			case SOUTH:
			case NORTH:
				return flag ? OPEN_BOTTOM : DEFAULT_AABB;
			}
		}
	}
}
