package com.lance5057.compendium.workstations.workbench;

import com.lance5057.compendium.recipes.interfaces.item.io.multiple.IRecipeShapedItemIn;
import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemOut;
import com.lance5057.compendium.recipes.interfaces.loottable.io.IRecipeLootTableOut;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShaped;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class WorkbenchBaseRecipe extends MultiToolRecipeShaped
		implements IRecipeShapedItemIn, IRecipeSingleItemOut, IRecipeLootTableOut {

	public WorkbenchBaseRecipe(MultiToolRecipeShapedPattern input, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ItemStack recipeOutputIn) {
		super(input, recipeToolsIn, recipeOutputIn);

	}

//	public WorkbenchBaseRecipe(MultiToolRecipeShapedPattern input, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
//			ItemStack recipeOutputIn) {
//		super(input, recipeToolsIn, recipeOutputIn);
//
//	}
//
//	@Override
//	public abstract ItemStack assemble(MultiToolRecipeWrapper input, Provider registries);

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width <= 5 && height <= 5;
	}

	@Override
	public ResourceLocation getLootTableOut() {
		return null;
	}

	@Override
	public ItemStack getItemOut() {
		return this.recipeOutput;
	}

	@Override
	public void setShapedIn(MultiToolRecipeShapedPattern p) {
		this.pattern = p;
	}

	@Override
	public MultiToolRecipeShapedPattern getShapedIn() {
		return this.pattern;
	}

}