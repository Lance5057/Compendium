package com.lance5057.compendium.blocks;

import java.util.List;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IStyleable {
	public List<List<String>> getStyles();

//	public void setStyles(List<List<String>> style);

	public int getCurrent(int index);

	public void setCurrent(int index, int c);

	public int getStyleCount();

	public default void readStyleNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("types")) {

			CompoundTag tag = nbt.getCompound("types");

			int count = nbt.getInt("count");

			for (int i = 0; i < count; i++) {
				setCurrent(i, tag.getInt("style" + i));
			}
		}
	}

	public default void writeStyleNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		for (int i = 0; i < getStyleCount(); i++) {
			tag.putInt("style" + i, getCurrent(i));
		}

		tag.putInt("count", getStyleCount());

		nbt.put("types", tag);

	}
}
