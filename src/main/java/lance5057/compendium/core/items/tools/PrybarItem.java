package lance5057.compendium.core.items.tools;

import lance5057.compendium.core.blocks.IPryable;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class PrybarItem extends Item {

	public PrybarItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand InteractionHandIn) {
		ItemStack itemstack = playerIn.getItemInHand(InteractionHandIn);

		if (!playerIn.getAbilities().instabuild) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			playerIn.getItemInHand(InteractionHandIn);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player player = (Player) entityLiving;

			int i = this.getUseDuration(stack) - timeLeft;
			if (i < 30) {
				worldIn.playSound(player, player.blockPosition(), SoundEvents.ANVIL_STEP, SoundSource.BLOCKS, 1f,
						1f);
				return;
			}

			Vec3 pos = new Vec3(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

			double reach = player.getAttributes().getBaseValue(ForgeMod.REACH_DISTANCE.get());
			Vec3 look = player.getLookAngle().scale(reach).add(pos);

			BlockHitResult result = worldIn
					.clip(new ClipContext(pos, look, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

			if (result != null) {
				Block b = worldIn.getBlockState(result.getBlockPos()).getBlock();
				if (b instanceof IPryable) {
					worldIn.playSound(player, result.getBlockPos(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1,
							1);

					((IPryable) b).onBreak(worldIn, result.getBlockPos(), entityLiving);

					worldIn.destroyBlock(result.getBlockPos(), false, player);

				}

			}
		}

	}
}
