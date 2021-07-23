package lance5057.compendium.core.entities;

import lance5057.compendium.CompendiumEntities;
import lance5057.compendium.CompendiumItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class GrenadeEntity extends ProjectileItemEntity {
    public GrenadeEntity(EntityType<? extends ProjectileItemEntity> p_i50154_1_, World p_i50154_2_) {
	super(p_i50154_1_, p_i50154_2_);
    }

    public GrenadeEntity(World worldIn, LivingEntity throwerIn) {
	super(CompendiumEntities.GRENADE_ENTITY.get(), throwerIn, worldIn);
    }

    public GrenadeEntity(World worldIn, double x, double y, double z) {
	super(CompendiumEntities.GRENADE_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
	return CompendiumItems.MINER_GRENADE.get();
    }

    int power = 4;

    public void setPower(int power) {
	this.power = power;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
	if (id == 3) {
	    double d0 = 0.08D;

	    for (int i = 0; i < 8; ++i) {
		this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getPosX(),
			this.getPosY(), this.getPosZ(), ((double) this.rand.nextFloat() - 0.5D) * 0.08D,
			((double) this.rand.nextFloat() - 0.5D) * 0.08D,
			((double) this.rand.nextFloat() - 0.5D) * 0.08D);
	    }
	}

    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
	super.onEntityHit(p_213868_1_);
	explode(p_213868_1_);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
	super.onImpact(result);
	explode(result);
    }

    private void explode(RayTraceResult result) {
	this.world.createExplosion(this, result.getHitVec().x, result.getHitVec().y, result.getHitVec().z, 8,
		Mode.BREAK);
	this.world.setEntityState(this, (byte) 3);
	this.remove();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }
}
