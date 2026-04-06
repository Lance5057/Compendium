package com.lance5057.compendium.index.material.base;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class MaterialDust extends _MaterialBase {

	public MaterialDust(String name, String tagNamespace) {
		super(name, tagNamespace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tab(Output output) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engLoc(LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(BlockTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ingredient getBaseItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MATERIAL_TYPES getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}

	@Override
	public ItemStack breakDownItem(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack buildUpItem(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ItemStack.EMPTY;
	}

	@Override
	public void attachComponents(ModifyDefaultComponentsEvent event) {
		// TODO Auto-generated method stub

	}

}
