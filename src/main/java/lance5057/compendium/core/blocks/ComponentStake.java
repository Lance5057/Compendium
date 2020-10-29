package lance5057.compendium.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ComponentStake extends EndRodBlock implements IWaterLoggable {
	
	public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public ComponentStake() {
		super(Block.Properties.create(Material.IRON)
				.hardnessAndResistance(5F, 10F)
				.sound(SoundType.METAL));
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
		builder.add(CONNECTED);
		builder.add(WATERLOGGED);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		// no
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	      Direction direction = context.getFace();
	      BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
	      FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      
	      if(blockstate.getBlock() == this)
	    	  return this.getDefaultState().with(FACING, direction).with(CONNECTED, true).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	      return this.getDefaultState().with(FACING, direction).with(CONNECTED, false).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	   }
}