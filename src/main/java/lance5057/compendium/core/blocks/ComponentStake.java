package lance5057.compendium.core.blocks;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ComponentStake extends EndRodBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public ComponentStake() {
		super(Block.Properties.of(Material.METAL).strength(5F, 10F).sound(SoundType.METAL));
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return !state.getValue(WATERLOGGED);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTED);
		builder.add(WATERLOGGED);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		// no
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());

		if (blockstate.getBlock() == this)
			return this.defaultBlockState().setValue(FACING, direction).setValue(CONNECTED, true).setValue(WATERLOGGED,
					Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
		return this.defaultBlockState().setValue(FACING, direction).setValue(CONNECTED, false).setValue(WATERLOGGED,
				Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
	}
}