package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IStyleable {
	public List<StyleData> getStyles();

	public int getCurrent(int index);

	public List<Integer> getCurrentAll();

	public List<String> getCurrentAllString();

	public default String validateCurrent(List<String> l, int i2) {
		if (l.size() > i2)
			return l.get(i2);

		return l.get(0);
	}

	public void setCurrent(int index, int c);

	public int getStyleCount();

	public default List<Integer> readStyleNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		List<Integer> l = new ArrayList<Integer>();
		if (nbt.contains("types")) {

			CompoundTag tag = nbt.getCompound("types");

			int count = tag.getInt("count");

			for (int i = 0; i < count; i++) {
				l.add(tag.getInt("style" + i));
			}

		}

		return l;
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
