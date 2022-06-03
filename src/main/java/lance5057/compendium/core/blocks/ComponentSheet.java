package lance5057.compendium.core.blocks;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ComponentSheet extends Block {
	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	protected static final VoxelShape TOP_SHAPE = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public ComponentSheet(Block.Properties properties) {
		super(properties);
	}

	public boolean isTransparent(BlockState state) {
		return state.getValue(TYPE) != SlabType.DOUBLE;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		SlabType slabtype = state.getValue(TYPE);
		switch (slabtype) {
		case DOUBLE:
			return Shapes.block();
		case TOP:
			return TOP_SHAPE;
		default:
			return BOTTOM_SHAPE;
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = context.getLevel().getBlockState(blockpos);
//		if (blockstate.getBlock() == this) {
//			return blockstate.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, Boolean.valueOf(false));
//		} else {
			FluidState ifluidstate = context.getLevel().getFluidState(blockpos);
			BlockState blockstate1 = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED,
					Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
			Direction direction = context.getClickedFace();
			return direction != Direction.DOWN
					&& (direction == Direction.UP || !(context.getClickedPos().getY() - (double) blockpos.getY() > 0.5D))
							? blockstate1
							: blockstate1.setValue(TYPE, SlabType.TOP);
//		}
	}

	public boolean isReplaceable(BlockState state, BlockPlaceContext useContext) {
//		ItemStack itemstack = useContext.getItem();
//		SlabType slabtype = state.getValue(TYPE);
//		if (slabtype != SlabType.DOUBLE && itemstack.getItem() == this.asItem()) {
//			if (useContext.replacingClickedOnBlock()) {
//				boolean flag = useContext.getHitVec().y - (double) useContext.getPos().getY() > 0.5D;
//				Direction direction = useContext.getFace();
//				if (slabtype == SlabType.BOTTOM) {
//					return direction == Direction.UP || flag && direction.getAxis().isHorizontal();
//				} else {
//					return direction == Direction.DOWN || !flag && direction.getAxis().isHorizontal();
//				}
//			} else {
//				return true;
//			}
//		} else {
			return false;
//		}
	}

	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		return state.getValue(TYPE) != SlabType.DOUBLE ? this.receiveFluid(worldIn, pos, state, fluidStateIn)
				: false;
	}

	public boolean canContainFluid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return state.getValue(TYPE) != SlabType.DOUBLE ? this.canContainFluid(worldIn, pos, state, fluidIn)
				: false;
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor
	 * state, returning a new state. For example, fences make their connections to
	 * the passed in state if possible, and wet concrete powder immediately returns
	 * its solidified counterpart. Note that this method should ideally consider
	 * only the specific face passed in.
	 */
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean allowsMovement(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		switch (type) {
		case LAND:
			return false;
		case WATER:
			return worldIn.getFluidState(pos).isSourceOfType(Fluids.WATER);
		case AIR:
			return false;
		default:
			return false;
		}
	}
}