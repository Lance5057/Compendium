package com.lance5057.compendium.workstations.scrappingtable.scrapping_rules;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.workstations.scrappingtable.ScrappingUtils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class CraftingTableScrappingRule implements IScrappingRule {

	@Override
	public List<ItemStack> scrap(RecipeHolder<?> recipe, ItemStack itemIn) {
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		if (recipe.value() instanceof ShapedRecipe sr) {
			for (Ingredient i : sr.pattern.ingredients()) {
				ItemStack s = ScrappingUtils.breakDownItem(i);
				if (!s.isEmpty())
					stacks.add(s);
				else {
					if (i.getItems() != null && i.getItems().length > 0)
						stacks.add(i.getItems()[0]);
				}
			}
		} else if (recipe.value() instanceof ShapelessRecipe sr) {
			for (Ingredient i : sr.getIngredients()) {
				ItemStack s = ScrappingUtils.breakDownItem(i);
				if (!s.isEmpty())
					stacks.add(s);
				else {
					if (i.getItems() != null && i.getItems().length > 0)
						stacks.add(i.getItems()[0]);
				}
			}
		}
		return stacks;
	}

	@Override
	public boolean matches(RecipeHolder<?> recipe) {
		return recipe.value() instanceof ShapedRecipe || recipe.value() instanceof ShapelessRecipe;
	}

}
