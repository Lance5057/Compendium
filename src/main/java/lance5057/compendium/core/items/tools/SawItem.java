package lance5057.compendium.core.items.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import lance5057.compendium.core.items.HandedAbilityTool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SawItem extends HandedAbilityTool {

	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet(Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON);

	public SawItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON_BLOCKS, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ActionResultType mainHandAbility(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		BlockState block = blockstate.getToolModifiedState(world, blockpos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.AXE);
		if (block != null) {
			PlayerEntity playerentity = context.getPlayer();
			world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isRemote) {
				world.setBlockState(blockpos, block, 11);
				if (playerentity != null) {
					context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {
						p_220040_1_.sendBreakAnimation(context.getHand());
					});
				}
			}

			return ActionResultType.func_233537_a_(world.isRemote);
		} else {
			return ActionResultType.PASS;
		}
	}

	@Override
	protected ActionResultType offHandAbility(ItemUseContext context) {
		// TODO Chop down the tree!
		return ActionResultType.PASS;
	}

}
