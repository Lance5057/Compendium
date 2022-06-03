package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ComponentBarDoor extends DoorBlock {

	protected static final VoxelShape DEFAULT_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape ROTATED_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	protected static final VoxelShape OPEN_DEFAULT_AABB = Block.box(0.0D, 14.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape OPEN_ROTATED_AABB = Block.box(6.0D, 14.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	protected static final VoxelShape OPEN_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public ComponentBarDoor(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false)).setValue(HINGE, DoorHingeSide.LEFT)
				.setValue(POWERED, Boolean.valueOf(false)).setValue(HALF, DoubleBlockHalf.LOWER));

	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(FACING);
		boolean flag = state.getValue(OPEN);
		//boolean flag1 = state.getValue(HINGE) == DoorHingeSide.RIGHT;
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
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
