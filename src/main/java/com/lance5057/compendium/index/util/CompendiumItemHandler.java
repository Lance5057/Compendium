package com.lance5057.compendium.index.util;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class CompendiumItemHandler {
	public String name;
	boolean isEnabled = false;

	public DeferredItem<Item> ITEM;

	public CompendiumItemHandler(String n) {
		name = n;
	}

	public boolean enabled() {
		return isEnabled;
	}

	public void setEnabled(boolean b) {
		isEnabled = b;
	}

	public void setup(_MaterialBase base) {
		ITEM = setBlockItem(base);
	}

	public DeferredItem<Item> setBlockItem(_MaterialBase base) {
		return CompendiumIndex.ITEMS.register(base.name + "_" + name + "_item", () -> new Item(new Item.Properties()));
	}

	public void tab(_MaterialBase base, Output output) {
		if (this.enabled())
			output.accept(ITEM);
	}

}
