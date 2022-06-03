package lance5057.compendium.core.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BlockAllAxis extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockAllAxis(Properties properties) {
	super(properties);
	this.registerDefaultState(this.defaultBlockState().setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false))
		.setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false))
		.setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false))
		.setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
	      builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	   }
}
