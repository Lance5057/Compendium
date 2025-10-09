package com.lance5057.compendium.workstations.scrappingtable.scrapping_rules;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class CraftingTableScrappingRule implements IScrappingRule {

	@Override
	public List<ItemStack> scrap(ItemStack stack) {
		// Take apart
		return null;
	}

	@Override
	public boolean matches(RecipeHolder<?> recipe) {
		return recipe.value() instanceof ShapedRecipe || recipe.value() instanceof ShapelessRecipe;
	}

}
