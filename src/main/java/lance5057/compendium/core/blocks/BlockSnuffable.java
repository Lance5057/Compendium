package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlockSnuffable extends RedstoneLampBlock {

	Item item;

	public BlockSnuffable(Item i, Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
		item = i;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
			InteractionHand InteractionHandIn, BlockHitResult hit) {
		if (!worldIn.hasNeighborSignal(pos)) {
			if (!state.getValue(LIT)) {
				if (player.getItemInHand(InteractionHandIn).getItem() == item || item == null) {
					worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3);
					player.getItemInHand(InteractionHandIn).hurtAndBreak(1, player, null);
				}
			} else {
				worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
	}
}
