//package com.lance5057.compendium.workstations._bases.blockentities;
//
//import java.util.List;
//
//import javax.annotation.Nonnull;
//
//import com.lance5057.compendium.workstations._bases.components.WorkstationComponent;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.Connection;
//import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//
//public class WorkstationBasicBlockEntity extends BlockEntity {
//
//	List<WorkstationComponent> components;
//	
//	public WorkstationBasicBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
//		super(pType, pPos, pBlockState);
//	}
//
//	@Override
//	public CompoundTag getUpdateTag() {
//		CompoundTag nbt = super.getUpdateTag();
//
//		writeNBT(nbt);
//
//		return nbt;
//	}
//
//	@Override
//	public void handleUpdateTag(CompoundTag tag) {
//		readNBT(tag);
//	}
//
//	@Override
//	public ClientboundBlockEntityDataPacket getUpdatePacket() {
//		CompoundTag tag = new CompoundTag();
//
//		writeNBT(tag);
//
//		return ClientboundBlockEntityDataPacket.create(this);
//	}
//
//	@Override
//	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//		CompoundTag tag = pkt.getTag();
//		// InteractionHandle your Data
//		readNBT(tag);
//	}
//
//	void readNBT(CompoundTag nbt) {
//		components.forEach(c -> c.readNBT(nbt));
//	}
//
//	CompoundTag writeNBT(CompoundTag tag) {
//		components.forEach(c -> c.writeNBT(tag));
//		return tag;
//	}
//
//	@Override
//	public void load(@Nonnull CompoundTag nbt) {
//		super.load(nbt);
//		readNBT(nbt);
//	}
//
//	@Override
//	public void saveAdditional(@Nonnull CompoundTag nbt) {
//		super.saveAdditional(nbt);
//		writeNBT(nbt);
//	}
//	
//}
