package lance5057.compendium.core.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockSnuffable extends RedstoneLampBlock {

    Item item;

    public BlockSnuffable(Item i, AbstractBlock.Properties properties) {
	super(properties);
	this.setDefaultState(this.getDefaultState().with(LIT, Boolean.valueOf(false)));
	item = i;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
	    Hand handIn, BlockRayTraceResult hit) {
	if (!worldIn.isBlockPowered(pos)) {
	    if (!state.get(LIT)) {
		if (player.getHeldItem(handIn).getItem() == item || item == null) {
		    worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(true)), 3);
		    player.getHeldItem(handIn).damageItem(1, player, null);
		}
	    } else {
		worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(false)), 3);
	    }
	    return ActionResultType.SUCCESS;
	}
	return ActionResultType.PASS;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
	    boolean isMoving) {
    }
}
