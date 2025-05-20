package com.lance5057.compendium.blocks;

import java.util.List;

import com.lance5057.compendium.styleblock.MultiStyle;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IStyleable {
	public List<MultiStyle> getStyles();

	public void getStyles(List<MultiStyle> style);

	public default void readNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("types")) {
			CompoundTag tag = nbt.getCompound("types");

			for (int i = 0; i < getStyles().size(); i++) {
				getStyles().get(i).readNBT(tag.getCompound("style" + i), registries);
			}
		}
	}

	public default void writeNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		List<MultiStyle> styles = getStyles();
		for (int i = 0; i < styles.size(); i++) {
			tag.put("style" + i, styles.get(i).writeNBT(nbt, registries));
		}

		nbt.put("types", tag);
	}
}
