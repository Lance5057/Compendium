package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

public class BlockAllAxis extends Block implements IWaterLoggable {

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockAllAxis(Properties properties) {
	super(properties);
	this.setDefaultState(this.getDefaultState().with(UP, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false))
		.with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false))
		.with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false))
		.with(WATERLOGGED, Boolean.valueOf(false)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	   }
}
