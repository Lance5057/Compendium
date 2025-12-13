package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IMultiMaterial;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public abstract class MultiMaterialBlockEntity extends BlockEntity implements IMultiMaterial {

	protected List<MultiMaterialType> materials;

	public List<MultiMaterialType> getMaterials() {
		return materials;
	}

	public abstract int getMaterialsCount();

	public void setMaterial(int index, String s) {
		if (materials.size() > index)
			materials.get(index).setCurrentMaterial(s);
		this.setChanged();
	}

	public void setMaterial(MultiMaterialType[] s) {
		materials = Stream.of(s).toList();
		this.setChanged();
		getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public void setMaterials(List<MultiMaterialType> materials) {
		this.materials = materials;
	}

	public MultiMaterialBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		this(type, pos, blockState, new ArrayList<MultiMaterialType>());
	}

	public MultiMaterialBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState,
			List<MultiMaterialType> list) {
		super(type, pos, blockState);
		this.materials = list;
	}
	
	

	@Override
	public ModelData getModelData() {
		return MultiMaterialModelData.builder(materials).build();
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag nbt = super.getUpdateTag(registries);

		writeNBT(nbt, registries);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
		readNBT(tag, registries);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
		super.onDataPacket(net, pkt, registries);
		if (getLevel() != null) {
			CompoundTag tag = pkt.getTag();
			readNBT(tag, registries);
			BlockState state = getLevel().getBlockState(getBlockPos());
			requestModelDataUpdate();
			getLevel().sendBlockUpdated(getBlockPos(), state, state, 3);
		}
	}

	void readNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		this.materials = this.readMaterialNBT(nbt, registries);

		readNBTExtra(nbt, registries);
	}

	CompoundTag writeNBT(CompoundTag tag, HolderLookup.Provider registries) {
		this.writeMaterialNBT(materials, tag, registries);
		
		writeNBTExtra(tag, registries);

		return tag;
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		readNBT(nbt, registries);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		writeNBT(nbt, registries);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);
		builder.set(CompendiumComponents.MULTI_MATERIAL.get(), new MultiMaterialBlockComponent(this.materials));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		MultiMaterialBlockComponent m = input.getOrDefault(CompendiumComponents.MULTI_MATERIAL.get(), null);
		if (m != null) {
			this.materials = new ArrayList<MultiMaterialType>();
			m.types().forEach(i -> materials.add(i.copy()));
		}
	}

	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {

	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {

	}
}
