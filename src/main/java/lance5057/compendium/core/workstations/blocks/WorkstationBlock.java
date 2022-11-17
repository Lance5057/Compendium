package lance5057.compendium.core.workstations.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WorkstationBlock extends Block implements EntityBlock ,SimpleWaterloggedBlock {

	public WorkstationBlock(Properties p_49795_) {
		super(p_49795_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		// TODO Auto-generated method stub
		return null;
	}

}
