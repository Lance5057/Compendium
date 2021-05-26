package lance5057.compendium.core.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class BlockVerticalPlacement extends Block {
    public static final EnumProperty<Half> TYPE = BlockStateProperties.HALF;

    public BlockVerticalPlacement(AbstractBlock.Properties properties) {
	super(properties);
	this.setDefaultState(this.getDefaultState().with(TYPE, Half.BOTTOM));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
	BlockPos blockpos = context.getPos();

	BlockState blockstate1 = this.getDefaultState().with(TYPE, Half.BOTTOM);

	Direction direction = context.getFace();
	return direction != Direction.DOWN
		&& (direction == Direction.UP || !(context.getHitVec().y - (double) blockpos.getY() > 0.5D))
			? blockstate1
			: blockstate1.with(TYPE, Half.TOP);

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(TYPE);
    }
}
