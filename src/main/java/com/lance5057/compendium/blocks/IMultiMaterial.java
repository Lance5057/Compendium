package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IMultiMaterial {
	public List<MultiMaterialType> getMaterials();

	public void setMaterials(List<MultiMaterialType> materials);

	public abstract int getMaterialsCount();

	public void setMaterial(int index, String s);

	public void setMaterial(MultiMaterialType[] s);

	default List<MultiMaterialType> readMaterialNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("materials")) {
			CompoundTag mats = nbt.getCompound("materials");

			int count = mats.getInt("count");
			List<MultiMaterialType> materials = new ArrayList<MultiMaterialType>();

			for (int i = 0; i < count; i++) {
				CompoundTag m = mats.getCompound("material" + i);
				MultiMaterialType s = MultiMaterialType.readNBT(m, registries);
				materials.add(s);
			}

			return materials;
		}

		return null;
	}

	default void writeMaterialNBT(List<MultiMaterialType> materials, CompoundTag tag,
			HolderLookup.Provider registries) {

		CompoundTag mats = new CompoundTag();
		mats.putInt("count", materials.size());
		for (int i = 0; i < materials.size(); i++) {
			CompoundTag m = new CompoundTag();
			MultiMaterialType.writeNBT(materials.get(i), m, registries);
			mats.put("material" + i, m);
		}

		tag.put("materials", mats);
	}
}
