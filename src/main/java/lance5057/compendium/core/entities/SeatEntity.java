package lance5057.compendium.core.entities;

import java.util.List;

import lance5057.compendium.CompendiumEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SeatEntity extends Entity {
    private BlockPos source;

    public SeatEntity(EntityType<? extends Entity> ent, World world) {
	super(ent, world);
	this.noClip = true;
    }

    private SeatEntity(World world, BlockPos source, double yOffset) {
	this(CompendiumEntities.SEAT_ENTITY.get(), world);
	this.source = source;
	this.setPosition(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
    }

    @Override
    protected void registerData() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
	// TODO Auto-generated method stub

    }

    @Override
    public double getMountedYOffset() {
	return -0.15;
    }

    public BlockPos getSource() {
	return source;
    }

    @Override
    protected boolean canBeRidden(Entity entity) {
	return true;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
	return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static ActionResultType create(World world, BlockPos pos, double yOffset, PlayerEntity player) {
	if (!world.isRemote) {
	    List<SeatEntity> seats = world.getEntitiesWithinAABB(SeatEntity.class, new AxisAlignedBB(pos.getX(),
		    pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
	    if (seats.isEmpty()) {
		SeatEntity seat = new SeatEntity(world, pos, yOffset);
		world.addEntity(seat);
		player.startRiding(seat, false);
	    }
	}
	return ActionResultType.SUCCESS;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (source == null) {
            source = this.getPosition();
        }
        if (!this.world.isRemote) {
            if (this.getPassengers().isEmpty() || this.world.isAirBlock(source)) {
                this.remove();		
            }
        }
    }
}
