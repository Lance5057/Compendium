package lance5057.compendium.core.blocks;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlockShingles extends StairBlock {

	public BlockShingles(Supplier<BlockState> state, Block.Properties properties) {
		super(state, properties);
	}

}