package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;

import com.lance5057.compendium.util.recipes.WorkstationRecipeWrapper;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.IShapedRecipe;

public class MultiToolRecipeShapeless implements IShapedRecipe<WorkstationRecipeWrapper> {

	@Override
	public boolean matches(WorkstationRecipeWrapper p_44002_, Level p_44003_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack assemble(WorkstationRecipeWrapper p_44001_, RegistryAccess p_267165_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess p_267165_) {
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

	@Override
	public int getRecipeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecipeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
