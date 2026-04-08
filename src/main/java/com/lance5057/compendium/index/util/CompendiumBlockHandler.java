package com.lance5057.compendium.index.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class CompendiumBlockHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134750634581821030L;
	public String name;
	transient Generate generate = Generate.IGNORE;

	public transient DeferredItem<BlockItem> BLOCK_ITEM;
	public transient DeferredBlock<Block> BLOCK;

	public transient List<TagKey<Item>> itemTag = new ArrayList<TagKey<Item>>();
	public transient List<TagKey<Block>> blockTag = new ArrayList<TagKey<Block>>();

	protected ResourceLocation existsLocationBlock;
	protected ResourceLocation existsLocationItem;

	public ResourceLocation getExistsLocationBlock() {
		return existsLocationBlock;
	}

	public ResourceLocation getExistsLocationItem() {
		return existsLocationItem;
	}

	public CompendiumBlockHandler() {
	}

	public CompendiumBlockHandler(String n) {
		name = n;
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

	public void setGenerate(Generate b) {
		generate = b;
	}

	public boolean isIgnored() {
		return generate == Generate.IGNORE;
	}

	public boolean isNotIgnored() {
		return generate != Generate.IGNORE;
	}

	public void setup(_MaterialBase base, ResourceLocation existsItem, ResourceLocation existsBlock) {
		setup(base, () -> new Block(Block.Properties.of()), () -> new BlockItem(BLOCK.get(), new Item.Properties()));
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block) {
		setup(base, block, () -> new BlockItem(BLOCK.get(), new Item.Properties()));
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block, Supplier<? extends BlockItem> item) {
		if (generate == Generate.GENERATE) {
			BLOCK = setupBlock(base, block);
			BLOCK_ITEM = setupBlockItem(base, item);
		}
//		} else if (generate == Generate.EXISTS) {
//			BLOCK = DeferredBlock.createBlock(existsBlock);
//			BLOCK_ITEM = DeferredItem.createItem(existsItem);
//		}
	}

	public DeferredBlock<Block> setupBlock(_MaterialBase base, Supplier<? extends Block> block) {
		return CompendiumBlocks.BLOCKS.register(name, block);
	}

	public DeferredItem<BlockItem> setupBlockItem(_MaterialBase base, Supplier<? extends BlockItem> item) {
		return CompendiumItems.ITEMS.register(name + "_item", item);
	}

	public void setupItemTag(ResourceLocation rc) {
		this.itemTag.add(ItemTags.create(rc));
	}

	public void setupItemTag(TagKey<Item> tag) {
		this.itemTag.add(tag);
	}

	public void setupBlockTag(ResourceLocation rc) {
		this.blockTag.add(BlockTags.create(rc));
	}

	public void setupBlockTag(TagKey<Block> tag) {
		this.blockTag.add(tag);
	}

	public void tab(_MaterialBase base, Output output) {
		if (generate == Generate.GENERATE)
			output.accept(BLOCK_ITEM);
	}

	public String location(_MaterialBase base) {
		return base.blockFolder();
	}

	public void itemTag(ItemTagsProvider itp) {
		for (TagKey<Item> tag : itemTag)
			itp.tag(tag).add(BLOCK_ITEM.asItem());
	}

	public void blockTag(BlockTagsProvider btp) {
		for (TagKey<Block> tag : blockTag)
			btp.tag(tag).add(BLOCK.get());
	}

	public boolean is(ItemStack item) {
		if (BLOCK_ITEM != null && BLOCK_ITEM.isBound() && item.is(BLOCK_ITEM))
			return true;
		return false;
	}

	public void setAsValidStyleBlock() {
		if (this.shouldGenerate())
			CompendiumBlockEntities.validStyleBlocks.add(this.BLOCK);
	}

	public void setAsValidStyleItem() {
		if (this.shouldGenerate())
			Compendium.styleItemRenderers.add(this.BLOCK_ITEM);
	}

	public void blockLoot(_MaterialBase _MaterialBase, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	public JsonElement serialize(_MaterialBase src) {
		JsonObject j = new JsonObject();
		j.addProperty("existsLocationBlock", this.getExistsLocationBlock().toString());
		j.addProperty("existsLocationItem", this.getExistsLocationBlock().toString());
		return j;
	}

	public void deserialize(JsonObject json) {
		if (json.has("existsLocationBlock"))
			this.existsLocationBlock = ResourceLocation.parse(json.get("existsLocationBlock").getAsString());
		if (json.has("existsLocationItem"))
			this.existsLocationItem = ResourceLocation.parse(json.get("existsLocationItem").getAsString());
	}
}
