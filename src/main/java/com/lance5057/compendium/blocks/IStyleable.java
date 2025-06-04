package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface IStyleable {
	public List<StyleType> getStyles();

	public void setStyles(List<StyleType> style);

	public default void readStyleNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("types")) {
			List<StyleType> style = new ArrayList<StyleType>();
			
			CompoundTag tag = nbt.getCompound("types");
			
			int count = nbt.getInt("count");

			for (int i = 0; i < count; i++) {
				StyleType s = new StyleType("", "");
				s.readNBT(tag.getCompound("style" + i), registries);
				style.add(s);
			}
			
			setStyles(style);
		}
	}

	public default void writeStyleNBT(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		List<StyleType> styles = getStyles();
		for (int i = 0; i < styles.size(); i++) {
			tag.put("style" + i, styles.get(i).writeNBT(nbt, registries));
		}
		
		nbt.put("types", tag);
		
		nbt.putInt("count", styles.size());

		
	}
}
