package com.lance5057.compendium.index.material.extentions;

import com.google.gson.JsonObject;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionVanillaTools extends _MaterialExtension {
	boolean loadSword;
	boolean loadAxe;
	boolean loadShovel;
	boolean loadHoe;
	boolean loadPickaxe;
	
	@Override
	public void setup(_MaterialBase base) {
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
	public JsonObject serialize(JsonObject j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserialize(JsonObject j) {
		// TODO Auto-generated method stub
		
	}

}
 