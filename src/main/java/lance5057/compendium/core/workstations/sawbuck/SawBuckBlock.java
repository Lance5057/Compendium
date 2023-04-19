package lance5057.compendium.core.workstations.sawbuck;

import java.util.List;

import lance5057.compendium.core.workstations._bases.blocks.StationGuiless;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class SawBuckBlock extends StationGuiless {

	public SawBuckBlock() {
		super(Block.Properties.of(Material.WOOD).strength(3, 4).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		// TODO Auto-generated method stub
		return new SawBuckTE(p_153215_, p_153216_);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
			InteractionHand InteractionHandIn, BlockHitResult hit) {
		if (worldIn.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof SawBuckTE te) {

				if (player.isCrouching()) {
					te.extractItem(player);
					return InteractionResult.SUCCESS;
				} else {

					ItemStack itemstack = player.getItemInHand(InteractionHandIn);

					if (te.isSlotEmpty(0)) {

						List<SawBuckRecipe> optional = te.matchRecipe(itemstack);

						if (!optional.isEmpty()) {
							te.insertItem(player.getItemInHand(InteractionHandIn));
							te.setRecipe(optional);
							return InteractionResult.SUCCESS;
						}

					} else
						te.hammer(player, player.getItemInHand(InteractionHandIn));
				}
			}
			return InteractionResult.SUCCESS;
		}
	}
}
