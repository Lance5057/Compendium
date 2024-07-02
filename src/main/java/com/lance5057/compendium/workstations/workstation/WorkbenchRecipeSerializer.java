package com.lance5057.compendium.workstations.workstation;

import java.util.Map;

import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.neoforged.neoforge.common.crafting.CraftingHelper;

public class WorkbenchRecipeSerializer implements RecipeSerializer<WorkbenchRecipe> {
	private static final ResourceLocation NAME = new ResourceLocation(Compendium.MOD_ID, "crafting_anvil_shaped");

	public WorkbenchRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		String s = GsonHelper.getAsString(json, "group", "");
		Map<String, Ingredient> map = WorkbenchRecipe.deserializeKey(GsonHelper.getAsJsonObject(json, "key"));
		String[] astring = WorkbenchRecipe
				.shrink(WorkbenchRecipe.patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = WorkbenchRecipe.deserializeIngredients(astring, map, i, j);
		ItemStack itemstackSchem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "schematic"), true,
				false);
		ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

		NonNullList<AnimatedRecipeItemUse> nonnulllistTools = WorkbenchRecipe
				.deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));

		return new WorkbenchRecipe(recipeId, s, i, j, nonnulllist, nonnulllistTools, itemstackSchem, itemstack);
	}

	public WorkbenchRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		int width = buffer.readVarInt();
		int height = buffer.readVarInt();
		String group = buffer.readUtf(32767);

		NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

		for (int k = 0; k < (width * height); ++k) {
			ingredients.set(k, Ingredient.fromNetwork(buffer));
		}

		int h = buffer.readVarInt();

		NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(h, AnimatedRecipeItemUse.EMPTY);

		for (int k = 0; k < tools.size(); ++k) {
			tools.set(k, AnimatedRecipeItemUse.read(buffer));
		}

		ItemStack schematic = buffer.readItem();
		ItemStack output = buffer.readItem();

		return new WorkbenchRecipe(recipeId, group, width, height, ingredients, tools, schematic, output);
	}

	public void toNetwork(FriendlyByteBuf buffer, WorkbenchRecipe recipe) {
		buffer.writeVarInt(recipe.getRecipeWidth());
		buffer.writeVarInt(recipe.getRecipeHeight());
		buffer.writeUtf(recipe.getGroup());

		for (Ingredient ingredient : recipe.getRecipeItems()) {
			ingredient.toNetwork(buffer);
		}

		buffer.writeVarInt(recipe.getToolListLength());

		for (AnimatedRecipeItemUse riu : recipe.getRecipeTools())
			AnimatedRecipeItemUse.write(riu, buffer);

		buffer.writeItem(recipe.getSchematic());
		buffer.writeItem(recipe.getRecipeOutput());
	}
}