package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;

import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class MultiToolRecipeShapeless extends MultiToolRecipe {

	public MultiToolRecipeShapeless() {
		super();
	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		// TODO Auto-generated method stub
		return null;
	}

}
