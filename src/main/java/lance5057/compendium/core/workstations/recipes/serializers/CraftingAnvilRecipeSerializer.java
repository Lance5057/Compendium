package lance5057.compendium.core.workstations.recipes.serializers;

import java.util.Map;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;

public class CraftingAnvilRecipeSerializer implements RecipeSerializer<CraftingAnvilRecipe> {
	private static final ResourceLocation NAME = new ResourceLocation(Reference.MOD_ID, "crafting_anvil_shaped");

	public CraftingAnvilRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		String s = GsonHelper.getAsString(json, "group", "");
		Map<String, Ingredient> map = CraftingAnvilRecipe.deserializeKey(GsonHelper.getAsJsonObject(json, "key"));
		String[] astring = CraftingAnvilRecipe
				.shrink(CraftingAnvilRecipe.patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = CraftingAnvilRecipe.deserializeIngredients(astring, map, i, j);
		ItemStack itemstackSchem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "schematic"), true,
				false);
		ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

		NonNullList<AnimatedRecipeItemUse> nonnulllistTools = CraftingAnvilRecipe
				.deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));

		return new CraftingAnvilRecipe(recipeId, s, i, j, nonnulllist, nonnulllistTools, itemstackSchem, itemstack);
	}

	public CraftingAnvilRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
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

		return new CraftingAnvilRecipe(recipeId, group, width, height, ingredients, tools, schematic, output);
	}

	public void toNetwork(FriendlyByteBuf buffer, CraftingAnvilRecipe recipe) {
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