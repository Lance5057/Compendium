//package lance5057.compendium.core.items.projectiles;
//
//import lance5057.compendium.core.entities.GrenadeEntity;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResultHolder;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
//public class GrenadeItem extends Item {
//
//	public GrenadeItem(Item.Properties builder) {
//		super(builder);
//	}
//
//	public InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn,
//			InteractionHand InteractionHandIn) {
//		ItemStack itemstack = playerIn.getItemInHand(InteractionHandIn);
//		worldIn.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW,
//				SoundSource.PLAYERS, 0.5F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));
//		if (!worldIn.isClientSide()) {
//			GrenadeEntity eggentity = new GrenadeEntity(worldIn, playerIn);
//			eggentity.setItem(itemstack);
//			eggentity.setPower(16);
//			eggentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
//			worldIn.addFreshEntity(eggentity);
//		}
//
//		playerIn.awardStat(Stats.ITEM_USED.get(this));
//		if (!playerIn.getAbilities().instabuild) {
//			itemstack.shrink(1);
//		}
//
//		return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
//	}
//}
