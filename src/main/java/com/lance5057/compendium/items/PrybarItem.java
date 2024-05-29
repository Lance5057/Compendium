package com.lance5057.compendium.items;

import org.joml.Quaternionf;

import com.lance5057.compendium.CompendiumTags;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.NeoForgeMod;

public class PrybarItem extends TieredItem implements Vanishable {

	public PrybarItem(Tier pTier, Properties pProperties) {
		super(pTier, pProperties);
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 32;
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		ItemUtils.startUsingInstantly(pContext.getLevel(), pContext.getPlayer(), pContext.getHand());
		return InteractionResult.PASS;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity pEntityLiving) {
		if (pEntityLiving instanceof Player player) {

			Vec3 pos = new Vec3(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

			double reach = player.getAttributes().getBaseValue(NeoForgeMod.ENTITY_REACH.value());
			Vec3 look = player.getLookAngle().scale(reach).add(pos);

			BlockHitResult result = level
					.clip(new ClipContext(pos, look, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

			if (result != null) {
				BlockState b = level.getBlockState(result.getBlockPos());
				if (b.is(CompendiumTags.PRYABLE)) {
					level.playSound(player, result.getBlockPos(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1, 1);

					level.destroyBlock(result.getBlockPos(), true, player);

				}

			}
			player.getCooldowns().addCooldown(this, 20);
		}

		return stack;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player) {

		}

	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.NONE;
	}

	@Override
	public void initializeClient(
			java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {

		consumer.accept(new IClientItemExtensions() {

			private static final HumanoidModel.ArmPose EXAMPLE_POSE = HumanoidModel.ArmPose.create("EXAMPLE", false,
					(model, entity, arm) -> {
						float f = entity.getUseItemRemainingTicks();
						float f2 = Mth.abs(Mth.cos(f / 6.0F * (float) Math.PI) * 0.5F);
						if (arm == HumanoidArm.RIGHT) {

							model.rightArm.yRot = -45;
							model.rightArm.xRot = (float) (f2 - 45);
						} else {
							model.leftArm.yRot = 45;
							model.leftArm.xRot = (float) (f2 - 45);
						}
					});

//			@Override
//			public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand,
//					ItemStack itemStack) {
////				if (!itemStack.isEmpty()) {
////					if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
////						return EXAMPLE_POSE;
////					}
////				}
//				return HumanoidModel.ArmPose.ITEM;
//			}

//			@Override
//			public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm,
//					ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
//				float f = player.getUseItemRemainingTicks() - partialTick + 1.0F;
//				int i = arm == HumanoidArm.RIGHT ? 1 : -1;
//				float i2 = arm == HumanoidArm.RIGHT ? -0.5f : 0.5f;
//				float i3 = arm == HumanoidArm.RIGHT ? 1f : 1f;
//				poseStack.translate(i * 0.56F, -0.52F, -0.72F);
//				float f2 = Mth.abs(Mth.cos(f / 8.0F * (float) Math.PI) * 0.5F);
////				if (player.getUseItem() == itemInHand && player.isUsingItem()) {
////
////					poseStack.mulPose(new Quaternionf().rotateXYZ(0, 90, 0));
////					poseStack.translate(1f, 0, 1);
////
////				}
//				return true;
//			}
		});
	}
}
