package com.lance5057.compendium.index.util;

import java.io.Serializable;
import java.util.function.Supplier;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
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

	public transient TagKey<Item> itemTag;
	public transient TagKey<Block> blockTag;

	public CompendiumBlockHandler(String n) {
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

	public boolean isIgnored() {
		return generate == Generate.IGNORE;
	}

	public void setup(_MaterialBase base, String tagNamespace, String tagName, ResourceLocation existsItem,
			ResourceLocation existsBlock) {
		setup(base, () -> new Block(Block.Properties.of()), () -> new BlockItem(BLOCK.get(), new Item.Properties()),
				tagNamespace, tagName, existsItem, existsBlock);
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block, String tagNamespace, String tagName,
			ResourceLocation existsItem, ResourceLocation existsBlock) {
		setup(base, block, () -> new BlockItem(BLOCK.get(), new Item.Properties()), tagNamespace, tagName, existsItem,
				existsBlock);
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block, Supplier<? extends BlockItem> item,
			String tagNamespace, String tagName, ResourceLocation existsItem, ResourceLocation existsBlock) {
		if (generate == Generate.GENERATE) {
			BLOCK = setupBlock(base, block);
			BLOCK_ITEM = setupBlockItem(base, item);
		} else if (generate == Generate.EXISTS) {
			BLOCK = DeferredBlock.createBlock(existsBlock);
			BLOCK_ITEM = DeferredItem.createItem(existsItem);
		}

		itemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath(tagNamespace, tagName));
		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath(tagNamespace, tagName));
	}

	public DeferredBlock<Block> setupBlock(_MaterialBase base, Supplier<? extends Block> block) {
		return CompendiumIndex.BLOCKS.register(base.name + "_" + name, block);
	}

	public DeferredItem<BlockItem> setupBlockItem(_MaterialBase base, Supplier<? extends BlockItem> item) {
		return CompendiumIndex.ITEMS.register(base.name + "_" + name + "_item", item);
	}

	public void tab(_MaterialBase base, Output output) {
		if (generate == Generate.GENERATE)
			output.accept(BLOCK_ITEM);
	}

	public String location(_MaterialBase base) {
		return base.blockFolder();
	}

	public void itemTag(ItemTagsProvider itp) {
		itp.tag(itemTag).add(BLOCK_ITEM.asItem());
	}

	public void blockTag(BlockTagsProvider btp) {
		btp.tag(blockTag).add(BLOCK.get());
	}
}
