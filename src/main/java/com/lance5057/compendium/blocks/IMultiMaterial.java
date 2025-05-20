package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IMultiMaterial {
	public List<String> getMaterials();

	public void setMaterials(List<String> materials);

	public abstract int getMaterialsCount();

	public void setMaterial(int index, String s);

	public void setMaterial(String[] s);

	default List<String> readNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("materials")) {
			CompoundTag mats = nbt.getCompound("materials");

			int count = mats.getInt("count");
			List<String> materials = new ArrayList<String>();

			for (int i = 0; i < count; i++) {
				String s = mats.getString("material_" + i);
				materials.add(s);
			}

			return materials;
		}

		return null;
	}

	default CompoundTag writeNBT(List<String> materials, CompoundTag tag, HolderLookup.Provider registries) {

		CompoundTag mats = new CompoundTag();
		mats.putInt("count", materials.size());
		for (int i = 0; i < materials.size(); i++)
			mats.putString("material_" + i, materials.get(i).toString());
		tag.put("materials", mats);

		return tag;
	}
}
