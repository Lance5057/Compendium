package lance5057.compendium.core.items.weapons;

import java.util.List;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class ZweihanderItem extends SwordItem {

	public ZweihanderItem(Tier tier, int attackDamageIn, float attackSpeedIn,
			Item.Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, (entity) -> {
			entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		});

		if (attacker instanceof Player) {
			Player player = (Player) attacker;
			if (player.getMainHandItem().getItem() instanceof ShieldItem) {
				player.getCooldowns().addCooldown(this, (int) 100);
				player.stopUsingItem();
				player.level.broadcastEntityEvent(player, (byte) 30);
			}
		}
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn,
			InteractionHand InteractionHandIn) {

		if (!playerIn.getCooldowns().isOnCooldown(this)) {
			if (playerIn.getItemInHand(InteractionHand.OFF_HAND).isEmpty())
				if (!playerIn.hasEffect(MobEffects.WEAKNESS)) {
					int i = 0;
					i = i + EnchantmentHelper.getKnockbackBonus(playerIn);

					AABB aabb = new AABB(-3, 0, -3, 3, 3, 3);

					aabb = aabb.move(playerIn.position());

					List<Entity> ents = worldIn.getEntities(playerIn, aabb);

					for (int x = 0; x < 100; x++) {
						float angle = ((x + 1) / 100f) * 360;
						double rotX = Math.cos(Math.toRadians(angle)) * 2;
						double rotY = -Math.sin(Math.toRadians(angle)) * 2;

						worldIn.addParticle(ParticleTypes.WHITE_ASH, playerIn.getX() + rotX, playerIn.getY() + 1,
								playerIn.getZ() + rotY, rotX * 0.1f, 0, rotY * 0.1f);
					}

					for (Entity e : ents) {
						if (e instanceof LivingEntity) {
							((LivingEntity) e).knockback((float) 1.0F, -(e.getX() - playerIn.getX()),
									-(e.getZ() - playerIn.getZ()));

							((LivingEntity) e).hurt(DamageSource.GENERIC, 1);
							this.damageItem(playerIn.getItemInHand(InteractionHandIn), 1, ((LivingEntity) e), null);
						}

						worldIn.playSound(playerIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
								SoundEvents.PLAYER_ATTACK_KNOCKBACK, playerIn.getSoundSource(), 1.0F, 1.0F);

						worldIn.addParticle(ParticleTypes.SWEEP_ATTACK, e.getX(), e.getY() + 1, e.getZ(), 0, 0,
								0);
					}

					playerIn.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
					return InteractionResultHolder.pass(playerIn.getItemInHand(InteractionHandIn));
				}
		}
		return InteractionResultHolder.fail(playerIn.getItemInHand(InteractionHandIn));
	}

}
