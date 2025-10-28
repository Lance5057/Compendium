package com.lance5057.compendium.index.util;

import java.util.function.Supplier;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class CompendiumItemHandler {
	public String name;
	Generate generate = Generate.IGNORE;

	public DeferredItem<Item> ITEM;

	public TagKey<Item> itemTag;

	public CompendiumItemHandler(String n) {
		name = n;
	}

	public boolean shouldGenerate() {
		return generate == Generate.GENERATE;
	}

	public Generate getGeneration() {
		return generate;
	}

	public void setGenerate(Generate b) {
		generate = b;
	}

	public void setup(_MaterialBase base, String tagNamespace, String tagName, ResourceLocation existsItem) {
		setup(base, () -> new Item(new Item.Properties()), tagNamespace, tagName, existsItem);
	}

	public void setup(_MaterialBase base, Supplier<? extends Item> item, String tagNamespace, String tagName,
			ResourceLocation existsItem) {
		if (generate == Generate.GENERATE) {
			ITEM = setupItem(base, item);
		} else if (generate == Generate.EXISTS) {
			ITEM = DeferredItem.createItem(existsItem);
		}

		itemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath(tagNamespace, tagName));
	}

	public DeferredItem<Item> setupItem(_MaterialBase base, Supplier<? extends Item> item) {
		return CompendiumIndex.ITEMS.register(base.name + "_" + name + "_item", item);
	}

	public void tab(_MaterialBase base, Output output) {
		if (generate == Generate.GENERATE)
			output.accept(ITEM);
	}

	public String location(_MaterialBase base) {
		return base.itemFolder();
	}

	public boolean isIgnored() {
		return this.generate == Generate.IGNORE;
	}

	public void itemTag(ItemTagsProvider itp) {
		itp.tag(itemTag).add(ITEM.asItem());
	}

}
