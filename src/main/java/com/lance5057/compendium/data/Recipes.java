package com.lance5057.compendium.data;

import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.Compendium;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class Recipes extends RecipeProvider implements IConditionBuilder {
	public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	public static ResourceLocation modLoc(String loc) {
		return ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, loc);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {
		hammering(consumer);
	}

	private void hammering(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}
}
