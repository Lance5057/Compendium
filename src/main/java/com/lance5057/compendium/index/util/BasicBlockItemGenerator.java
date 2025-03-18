package com.lance5057.compendium.index.util;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class BasicBlockItemGenerator implements IQuickBuildItem {
	public String name;
	boolean isEnabled = false;

	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<Block> BLOCK;

	public BasicBlockItemGenerator(String n) {
		name = n;
	}

	@Override
	public boolean enabled() {
		return isEnabled;
	}

	@Override
	public void setEnabled(boolean b) {
		isEnabled = b;
	}

	@Override
	public void setup(_MaterialBase base) {
		BLOCK = CompendiumIndex.BLOCKS.register(base.name + "_" + name,
				() -> new Block(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
		BLOCK_ITEM = CompendiumIndex.ITEMS.register(base.name + "_" + name + "_item",
				() -> new BlockItem(BLOCK.get(), new Item.Properties()));
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.enabled())
			output.accept(BLOCK_ITEM);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.enabled())
			DataUtil.basicMaterialBlock(bsp, this.BLOCK.get(), base.name, "_" + name, "");
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.enabled())
			DataUtil.basicMaterialBlockItem(tmp, BLOCK_ITEM, base.name, "_" + name);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

}
