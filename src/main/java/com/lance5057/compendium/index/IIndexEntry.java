package com.lance5057.compendium.index;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public interface IIndexEntry {
	public void setup();
	
	public void tab(Output output);

	public void blockModel(BlockStateProvider bsp);

	public void itemModel(ItemModelProvider tmp);

	public void engLoc(LanguageProvider lp);

	public void recipes(RecipeOutput consumer);

	public void blockLoot(BlockLootSubProvider blp);

	public void setupClient(FMLClientSetupEvent event);

}
