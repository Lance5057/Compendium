//package lance5057.compendium.core.entities;
//
//import lance5057.compendium.CompendiumEntities;
//import lance5057.compendium.CompendiumItems;
//import net.minecraft.core.particles.ItemParticleOption;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.Explosion.BlockInteraction;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.EntityHitResult;
//import net.minecraft.world.phys.HitResult;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.network.NetworkHooks;
//
//public class GrenadeEntity extends ThrowableItemProjectile {
//	public GrenadeEntity(EntityType<? extends ThrowableItemProjectile> p_i50154_1_, Level p_i50154_2_) {
//		super(p_i50154_1_, p_i50154_2_);
//	}
//
//	public GrenadeEntity(Level worldIn, LivingEntity throwerIn) {
//		super(CompendiumEntities.GRENADE_ENTITY.get(), throwerIn, worldIn);
//	}
//
//	public GrenadeEntity(Level worldIn, double x, double y, double z) {
//		super(CompendiumEntities.GRENADE_ENTITY.get(), x, y, z, worldIn);
//	}
//
//	@Override
//	protected Item getDefaultItem() {
//		return CompendiumItems.MINER_GRENADE.get();
//	}
//
//	int power = 4;
//
//	public void setPower(int power) {
//		this.power = power;
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	public void InteractionHandleStatusUpdate(byte id) {
//		if (id == 3) {
//			double d0 = 0.08D;
//
//			for (int i = 0; i < 8; ++i) {
//				this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(),
//						this.getY(), this.getZ(), ((double) this.random.nextFloat() - 0.5D) * 0.08D,
//						((double) this.random.nextFloat() - 0.5D) * 0.08D,
//						((double) this.random.nextFloat() - 0.5D) * 0.08D);
//			}
//		}
//
//	}
//
//	/**
//	 * Called when the arrow hits an entity
//	 */
//	protected void onHitEntity(EntityHitResult p_213868_1_) {
//		super.onHitEntity(p_213868_1_);
//		explode(p_213868_1_);
//	}
//
//	/**
//	 * Called when this EntityFireball hits a block or entity.
//	 */
//	protected void onHit(HitResult result) {
//		super.onHit(result);
//		explode(result);
//	}
//
//	private void explode(HitResult result) {
//		this.level.explode(this, result.getLocation().x, result.getLocation().y, result.getLocation().z, 8,
//				BlockInteraction.BREAK);
//		this.level.broadcastEntityEvent(this, (byte) 3);
//		this.remove(RemovalReason.KILLED);
//	}
//
//	@Override
//	public Packet<?> getAddEntityPacket() {
//		return NetworkHooks.getEntitySpawningPacket(this);
//	}
//}
