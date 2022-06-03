package lance5057.compendium.core.blocks;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;

public class BlockVerticalPlacement extends Block {
    public static final EnumProperty<Half> TYPE = BlockStateProperties.HALF;

    public BlockVerticalPlacement(Properties properties) {
	super(properties);
	this.registerDefaultState(this.defaultBlockState().setValue(TYPE, Half.BOTTOM));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
	BlockPos blockpos = context.getClickedPos();

	BlockState blockstate1 = this.defaultBlockState().setValue(TYPE, Half.BOTTOM);

	Direction direction = context.getClickedFace();
	return direction != Direction.DOWN
		&& (direction == Direction.UP || !(context.getClickLocation().y - (double) blockpos.getY() > 0.5D))
			? blockstate1
			: blockstate1.setValue(TYPE, Half.TOP);

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
	builder.add(TYPE);
    }
}
