package com.lance5057.compendium.workstations.hammeringstation;

import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemIn;
import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemOut;
import com.lance5057.compendium.recipes.interfaces.loottable.io.IRecipeLootTableOut;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipe;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class HammeringStationRecipe extends MultiToolRecipe
		implements IRecipeSingleItemIn, IRecipeSingleItemOut, IRecipeLootTableOut {

	private final Ingredient input;
	private final ResourceLocation loot;
	private final ItemStack output;

	public HammeringStationRecipe(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn, ItemStack output,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {
		this.input = recipeItemsIn;
		this.loot = loottable;
		this.output = output;
	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		return this.input.test(input.getItem(0));
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		return this.output;
	}

	@Override
	public RecipeType<?> getType() {
		return WorkstationRecipes.HAMMERINGSTATION_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	@Override
	public ResourceLocation getLootTableOut() {
		return this.loot;
	}

	@Override
	public ItemStack getItemOut() {
		return this.output;
	}

	@Override
	public Ingredient getItemIn() {
		return this.input;
	}

	public static class Serializer implements RecipeSerializer<HammeringStationRecipe> {

		@Override
		public MapCodec<HammeringStationRecipe> codec() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, HammeringStationRecipe> streamCodec() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
