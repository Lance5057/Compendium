package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;

import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class MultiToolRecipeShaped extends MultiToolRecipe {

	public MultiToolRecipeShapedPattern pattern;
	private final ItemStack recipeOutput;

	public MultiToolRecipeShaped(MultiToolRecipeShapedPattern pattern, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ItemStack recipeOutputIn, RecipeType<?> type) {
		super(recipeToolsIn);
		this.pattern = pattern;
		this.recipeOutput = recipeOutputIn;

	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		// TODO Auto-generated method stub
		return pattern.matches(input);
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		return recipeOutput;
	}

}
