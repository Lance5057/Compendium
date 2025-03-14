package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.client.models.MultiMaterialModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class MultiMaterialBlockEntity extends BlockEntity {

	List<String> materials;

	public MultiMaterialBlockEntity(BlockPos pos, BlockState blockState) {
		this(pos, blockState, List.of());
	}

	public MultiMaterialBlockEntity(BlockPos pos, BlockState blockState, List<String> list) {
		super(CompendiumBlockEntities.MULTIMATERIAL.get(), pos, blockState);
		this.materials = list;
	}

	@Override
	public ModelData getModelData() {
		return MultiMaterialModelData.builder(materials.toArray(new String[0])).build();
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
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag, registries);
	}

	void readNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("materials")) {
			CompoundTag mats = nbt.getCompound("materials");

			int count = mats.getInt("count");
			this.materials = new ArrayList<String>();

			for (int i = 0; i < count; i++)
				materials.add(nbt.getString("material_" + i));
		}
	}

	CompoundTag writeNBT(CompoundTag tag, HolderLookup.Provider registries) {

		CompoundTag mats = new CompoundTag();
		mats.putInt("count", materials.size());
		for (int i = 0; i < materials.size(); i++)
			mats.putString("material_" + i, materials.get(i).toString());
		tag.put("materials", mats);

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
			this.materials = m.types();
		}
	}

}
