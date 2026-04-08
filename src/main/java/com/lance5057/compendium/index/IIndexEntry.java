package com.lance5057.compendium.index;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.common.data.LanguageProvider;

public interface IIndexEntry {

	public String getName();

	public void setup();

	public void tab(Output output);

//	public void blockStateModel(BlockStateProvider bsp);

//	public void itemModel(ItemModelProvider tmp);

	public void engLoc(LanguageProvider lp);

	public void recipes(RecipeOutput consumer);

	public void blockLoot(BlockLootSubProvider blp);

	public abstract void otherLoot(LootTableSubProvider lsp);

//	public abstract void setupItemTags(ItemTagsProvider itp);

//	public abstract void setupBlockTags(BlockTagsProvider itp);

//	public void setupClient(FMLClientSetupEvent event);

//	public boolean isIndexItem(ItemStack stack);

//	public Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack);

//	public ItemStack breakDownItem(Ingredient ingredient); // ie ingot to nuggets

//	public ItemStack buildUpItem(Ingredient ingredient); // ie ingot to storage block

}
