package com.lance5057.compendium.index.util;

import java.util.function.Supplier;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class CompendiumBlockHandler {
	public String name;
	boolean isEnabled = false;

	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<Block> BLOCK;

	public TagKey<Item> itemTag;
	public TagKey<Block> blockTag;

	public CompendiumBlockHandler(String n) {
		name = n;
	}

	public boolean enabled() {
		return isEnabled;
	}

	public void setEnabled(boolean b) {
		isEnabled = b;
	}

	public void setup(_MaterialBase base, String tagNamespace, String tagName) {
		setup(base, () -> new Block(Block.Properties.of()), () -> new BlockItem(BLOCK.get(), new Item.Properties()),
				tagNamespace, tagName);
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block, String tagNamespace, String tagName) {
		setup(base, block, () -> new BlockItem(BLOCK.get(), new Item.Properties()), tagNamespace, tagName);
	}

	public void setup(_MaterialBase base, Supplier<? extends Block> block, Supplier<? extends BlockItem> item,
			String tagNamespace, String tagName) {
		if (isEnabled) {
			BLOCK = setupBlock(base, block);
			BLOCK_ITEM = setupBlockItem(base, item);
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
		if (this.enabled())
			output.accept(BLOCK_ITEM);
	}

	public String location(_MaterialBase base) {
		return base.blockFolder();
	}

}
