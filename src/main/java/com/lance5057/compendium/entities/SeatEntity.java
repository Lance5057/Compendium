package com.lance5057.compendium.entities;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {

	public SeatEntity(EntityType<Entity> ent, Level level) {
		super(CompendiumEntities.SEAT.get(), level);
		this.noPhysics = true;
	}

	public SeatEntity(Level level, BlockPos pos, Direction dir, float yOffset) {
		super(CompendiumEntities.SEAT.get(), level);
		this.noPhysics = true;
		this.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
		this.setRot(dir.getOpposite().toYRot(), 0);
	}

	@Override
	protected void defineSynchedData(Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
	}

	@Override
	protected boolean canRide(Entity entity) {
		return true;
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide()) {
			if (this.getPassengers().isEmpty()
					|| !this.level().getBlockState(this.blockPosition()).is(CompendiumBlocks.CHAIR.get())) {
				this.remove(RemovalReason.DISCARDED);
			}
		}
	}

	@Override
	public Vec3 getPassengerRidingPosition(Entity entity) {
		return this.position().add(new Vec3(0.0, 0.5, 0.0));
	}

	@Override
	public void onPassengerTurned(Entity entity) {
		this.clampYaw(entity);
	}

	private void clampYaw(Entity passenger) {
		passenger.setYBodyRot(this.getYRot());
		float wrappedYaw = Mth.wrapDegrees(passenger.getYRot() - this.getYRot());
		float clampedYaw = Mth.clamp(wrappedYaw, -90.0F, 90.0F);
		passenger.yRotO += clampedYaw - wrappedYaw;
		passenger.setYRot(passenger.getYRot() + clampedYaw - wrappedYaw);
		passenger.setYHeadRot(passenger.getYRot());
	}

	@Override
	public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
		Direction original = this.getDirection();
		Direction[] offsets = { original, original.getClockWise(), original.getCounterClockWise(),
				original.getOpposite() };
		for (Direction dir : offsets) {
			Vec3 safeVec = DismountHelper.findSafeDismountLocation(entity.getType(), this.level(),
					this.blockPosition().relative(dir), false);
			if (safeVec != null) {
				return safeVec.add(0, 0.25, 0);
			}
		}
		return super.getDismountLocationForPassenger(entity);
	}
}
