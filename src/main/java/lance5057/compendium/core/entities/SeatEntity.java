//package lance5057.compendium.core.entities;
//
//import java.util.List;
//
//import lance5057.compendium.CompendiumEntities;
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.AABB;
//import net.minecraftforge.network.NetworkHooks;
//
//public class SeatEntity extends Entity {
//	private BlockPos source;
//
//	public SeatEntity(EntityType<? extends Entity> ent, Level world) {
//		super(ent, world);
//		this.noPhysics = true;
//	}
//
//	private SeatEntity(Level world, BlockPos source, double yOffset) {
//		this(CompendiumEntities.SEAT_ENTITY.get(), world);
//		this.source = source;
//		this.setPos(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
//	}
//
//	@Override
//	public double getPassengersRidingOffset() {
//		return -0.15;
//	}
//
//	public BlockPos getSource() {
//		return source;
//	}
//
//	@Override
//	protected boolean canRide(Entity entity) {
//		return true;
//	}
//
//	public static InteractionResult create(Level world, BlockPos pos, double yOffset, Player player) {
//		if (!world.isClientSide()) {
//			List<SeatEntity> seats = world.getEntitiesOfClass(SeatEntity.class, new AABB(pos.getX(),
//					pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0), i -> true);
//			if (seats.isEmpty()) {
//				SeatEntity seat = new SeatEntity(world, pos, yOffset);
//				world.addFreshEntity(seat);
//				player.startRiding(seat, false);
//			}
//		}
//		return InteractionResult.SUCCESS;
//	}
//
//	@Override
//	public void tick() {
//		super.tick();
//		if (source == null) {
//			source = this.blockPosition();
//		}
//		if (!this.level.isClientSide()) {
//			if (this.getPassengers().isEmpty() || this.level.isEmptyBlock(source)) {
//				this.remove(RemovalReason.DISCARDED);
//			}
//		}
//	}
//
//	@Override
//	protected void defineSynchedData() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void readAdditionalSaveData(CompoundTag p_20052_) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void addAdditionalSaveData(CompoundTag p_20139_) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Packet<?> getAddEntityPacket() {
//		return NetworkHooks.getEntitySpawningPacket(this);
//	}
//}
