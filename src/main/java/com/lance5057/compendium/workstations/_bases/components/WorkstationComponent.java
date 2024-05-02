package com.lance5057.compendium.workstations._bases.components;

import net.minecraft.nbt.CompoundTag;

public interface WorkstationComponent {
	public boolean isUsedInRecipe();

	CompoundTag writeNBT(CompoundTag tag);

	void readNBT(CompoundTag nbt);
}
