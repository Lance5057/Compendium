package lance5057.compendium.core.items.tools;

import lance5057.compendium.core.blocks.IPryable;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

public class PrybarItem extends Item {

    public PrybarItem(Item.Properties builder) {
	super(builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	ItemStack itemstack = playerIn.getHeldItem(handIn);

	if (!playerIn.abilities.isCreativeMode) {
	    return ActionResult.resultFail(itemstack);
	} else {
	    playerIn.setActiveHand(handIn);
	    return ActionResult.resultConsume(itemstack);
	}
    }

    @Override
    public int getUseDuration(ItemStack stack) {
	return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
	return UseAction.BOW;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
	if (entityLiving instanceof PlayerEntity) {
	    PlayerEntity player = (PlayerEntity) entityLiving;

	    int i = this.getUseDuration(stack) - timeLeft;
	    if (i < 30) {
		worldIn.playSound(player, player.getPosition(), SoundEvents.BLOCK_ANVIL_STEP,
			SoundCategory.BLOCKS, 1, 1);
		return;
	    }

	    Vector3d pos = new Vector3d(player.getPosX(), player.getPosY() + player.getEyeHeight(), player.getPosZ());

	    double reach = player.getAttributeManager().getAttributeValue(ForgeMod.REACH_DISTANCE.get());
	    Vector3d look = player.getLookVec().scale(reach).add(pos);

	    BlockRayTraceResult result = worldIn
		    .rayTraceBlocks(new RayTraceContext(pos, look, BlockMode.COLLIDER, FluidMode.NONE, player));

	    if (result != null) {
		Block b = worldIn.getBlockState(result.getPos()).getBlock();
		if (b instanceof IPryable) {
		    worldIn.playSound(player, result.getPos(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1,
			    1);
		    
		    ((IPryable)b).onBreak(worldIn, result.getPos(), entityLiving);

		    worldIn.destroyBlock(result.getPos(), false, player);

		}

	    }
	}

    }
}
