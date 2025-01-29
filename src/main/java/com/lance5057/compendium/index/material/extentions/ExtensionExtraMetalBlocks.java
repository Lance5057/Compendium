package com.lance5057.compendium.index.material.extentions;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionExtraMetalBlocks extends _MaterialExtension {

	public boolean loadTile;
	public boolean loadHalfTile;
	public boolean loadQuarterTile;

	public DeferredItem<BlockItem> TILE_ITEM;
	public DeferredBlock<Block> TILE;
	public DeferredItem<BlockItem> HALF_TILE_ITEM;
	public DeferredBlock<Block> HALF_TILE;
	public DeferredItem<BlockItem> QUARTER_TILE_ITEM;
	public DeferredBlock<Block> QUARTER_TILE;

	private TagKey<Item> blockItemTag;
	private TagKey<Block> blockTag;

	public ExtensionExtraMetalBlocks() {
		this.loadHalfTile = true;
		this.loadQuarterTile = true;
		this.loadTile = true;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadTile) {
			TILE = CompendiumIndex.BLOCKS.register(base.name + "_block",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
			TILE_ITEM = CompendiumIndex.ITEMS.register(base.name + "_block_item",
					() -> new BlockItem(TILE.get(), new Item.Properties()));
		}
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

}
