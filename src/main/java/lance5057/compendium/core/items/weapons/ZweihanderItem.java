package lance5057.compendium.core.items.weapons;

import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ZweihanderItem extends SwordItem {

    public ZweihanderItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
	super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
	stack.damageItem(1, attacker, (entity) -> {
	    entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	});

	if (attacker instanceof PlayerEntity) {
	    PlayerEntity player = (PlayerEntity) attacker;
	    if (player.getHeldItemOffhand().getItem() instanceof ShieldItem) {
		player.getCooldownTracker().setCooldown(this, (int) 100);
		player.resetActiveHand();
		player.world.setEntityState(player, (byte) 30);
	    }
	}
	return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

	if (!playerIn.getCooldownTracker().hasCooldown(this)) {
	    if (playerIn.getHeldItem(Hand.OFF_HAND).isEmpty())
		if (!playerIn.isPotionActive(Effects.WEAKNESS)) {
		    int i = 0;
		    i = i + EnchantmentHelper.getKnockbackModifier(playerIn);

		    AxisAlignedBB aabb = new AxisAlignedBB(-3, 0, -3, 3, 3, 3);

		    aabb = aabb.offset(playerIn.getPositionVec());
		    
		    List<Entity> ents = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, aabb);

		    for (int x = 0; x < 100; x++) {
			float angle = ((x + 1) / 100f) * 360;
			double rotX = Math.cos(Math.toRadians(angle)) * 2;
			double rotY = -Math.sin(Math.toRadians(angle)) * 2;

			worldIn.addParticle(ParticleTypes.WHITE_ASH, playerIn.getPosX() + rotX, playerIn.getPosY() + 1,
				playerIn.getPosZ() + rotY, rotX * 0.1f, 0, rotY * 0.1f);
		    }

		    for (Entity e : ents) {
			if (e instanceof LivingEntity) {
			    ((LivingEntity) e).applyKnockback((float) 1.0F, -(e.getPosX() - playerIn.getPosX()),
				    -(e.getPosZ() - playerIn.getPosZ()));

			    ((LivingEntity) e).attackEntityFrom(DamageSource.GENERIC, 1);
			    this.damageItem(playerIn.getHeldItem(handIn), 1, ((LivingEntity) e), null);
			}

			worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(),
				SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, playerIn.getSoundCategory(), 1.0F, 1.0F);

			worldIn.addParticle(ParticleTypes.SWEEP_ATTACK, e.getPosX(), e.getPosY() + 1, e.getPosZ(), 0, 0,
				0);
		    }

		    playerIn.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100));
		    return ActionResult.resultPass(playerIn.getHeldItem(handIn));
		}
	}
	return ActionResult.resultFail(playerIn.getHeldItem(handIn));
    }

}
