package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;

import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class MultiToolRecipe implements Recipe<MultiToolRecipeWrapper> {
	NonNullList<AnimatedRecipeItemUse> tools;

	public MultiToolRecipe() {
	}

	public NonNullList<AnimatedRecipeItemUse> getTools() {
		return tools;
	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeType<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}

}