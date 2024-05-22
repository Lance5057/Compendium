package com.lance5057.compendium.index.material.extentions;

import com.google.gson.JsonObject;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class _MaterialExtension {
	public abstract void setup(_MaterialBase base);

	public abstract void tab(_MaterialBase base, Output output);

	public abstract void blockModel(_MaterialBase base, BlockStateProvider bsp);

	public abstract void itemModel(_MaterialBase base, ItemModelProvider tmp);

	public abstract void engLoc(_MaterialBase base, LanguageProvider lp);

	public abstract void recipes(_MaterialBase base, RecipeOutput consumer);

	public abstract void blockLoot(_MaterialBase base, BlockLootSubProvider blp);
	
	public abstract JsonObject serialize(JsonObject j);
	
	public abstract void deserialize(JsonObject j);
}
