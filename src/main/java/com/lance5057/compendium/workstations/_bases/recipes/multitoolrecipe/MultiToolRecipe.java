package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;

import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Recipe;

public abstract class MultiToolRecipe implements Recipe<MultiToolRecipeWrapper> {
	String group;
	NonNullList<AnimatedRecipeItemUse> tools;

	public MultiToolRecipe(String group) {
		this.group = group;
	}

	public NonNullList<AnimatedRecipeItemUse> getTools() {
		return tools;
	}
	
	@Override
	public String getGroup()
	{
		return this.group;
	}

}