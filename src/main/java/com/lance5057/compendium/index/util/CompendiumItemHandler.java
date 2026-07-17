package com.lance5057.compendium.index.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;

public class CompendiumItemHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8460994906633097803L;
	public String name;
	transient Generate generate = Generate.IGNORE;

	public transient DeferredItem<Item> ITEM;

	public transient List<TagKey<Item>> itemTag = new ArrayList<TagKey<Item>>();

	protected ResourceLocation existsLocation;

	public ResourceLocation getExistsLocation() {
		return existsLocation;
	}

	public CompendiumItemHandler() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean shouldGenerate() {
		return generate == Generate.GENERATE;
	}

	public Generate getGeneration() {
		return generate;
	}

	public void setGenerate() {
		generate = Generate.GENERATE;
	}

	public void setGenerate(Generate b) {
		generate = b;
	}

	public boolean isNotIgnored() {
		return generate != Generate.IGNORE;
	}

	public void setup(_MaterialBase base) {
		setup(base, () -> new Item(new Item.Properties()));
	}

	public void setup(_MaterialBase base, Supplier<? extends Item> item) {
		if (generate == Generate.GENERATE) {
			ITEM = setupItem(base, item);

		} else if (generate == Generate.EXISTS) {
			ITEM = DeferredItem.createItem(existsLocation);
		}
	}

	public DeferredItem<Item> setupItem(_MaterialBase base, Supplier<? extends Item> item) {
		return CompendiumItems.ITEMS.register(name + "_item", item);
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

	public void setIgnore() {
		this.generate = Generate.IGNORE;
	}

	public void itemTag(ItemTagsProvider itp) {
		if (!this.isIgnored())
			for (TagKey<Item> tag : itemTag)
				itp.tag(tag).addOptional(ITEM.getId());
	}

	public void setupItemTag(ResourceLocation rc) {
		if (!this.isIgnored())
			this.itemTag.add(ItemTags.create(rc));
	}

	public void setupItemTag(TagKey<Item> tag) {
		if (!this.isIgnored())
			this.itemTag.add(tag);
	}

	public boolean is(ItemStack item) {
		if (ITEM != null && ITEM.isBound() && item.is(ITEM))
			return true;
		for (TagKey<Item> key : this.itemTag)
			if (item.is(key))
				return true;
		return false;
	}

	public JsonElement serialize() {
		JsonObject j = new JsonObject();

		if (this.getExistsLocation() != null)
			j.addProperty("existsLocation", this.getExistsLocation().toString());

		j.addProperty("generate", this.generate.toString());
		return j;
	}

	public void deserialize(JsonObject json) {
		if (json.has("existsLocation")) {
			String s = json.get("existsLocation").getAsString();

			if (!s.isEmpty())
				this.existsLocation = ResourceLocation.parse(s);
		}

		String g = json.get("generate").getAsString();
		this.generate = Generate.valueOf(g.toUpperCase());
	}

	public void setExists(ResourceLocation existsLocationItem) {
		this.generate = Generate.EXISTS;

		this.existsLocation = existsLocationItem;
	}

}
