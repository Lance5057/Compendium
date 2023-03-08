package lance5057.compendium.core.workstations.recipes.serializers;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.recipes.SawBuckRecipe;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class SawBuckRecipeSerializer implements RecipeSerializer<SawBuckRecipe> {
	private static final ResourceLocation NAME = new ResourceLocation(Reference.MOD_ID, "crafting_anvil_shaped");

	public SawBuckRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		String s = GsonHelper.getAsString(json, "group", "");
		Ingredient itemIn = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));

		NonNullList<AnimatedRecipeItemUse> nonnulllistTools = SawBuckRecipe
				.deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));

		ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
		ResourceLocation output = new ResourceLocation(json.get("outputTable").getAsString());

		return new SawBuckRecipe(recipeId, s, itemIn, itemstack, nonnulllistTools, output);
	}

	public SawBuckRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		String group = buffer.readUtf(32767);

		Ingredient ing = Ingredient.fromNetwork(buffer);

		int h = buffer.readVarInt();

		NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(h, AnimatedRecipeItemUse.EMPTY);

		for (int k = 0; k < tools.size(); ++k) {
			tools.set(k, AnimatedRecipeItemUse.read(buffer));
		}

		String q = buffer.readUtf();
		
		ItemStack output = buffer.readItem();
		final ResourceLocation outputTable = new ResourceLocation(q);

		return new SawBuckRecipe(recipeId, group, ing, output, tools, outputTable);
	}

	public void toNetwork(FriendlyByteBuf buffer, SawBuckRecipe recipe) {
		buffer.writeUtf(recipe.getGroup());

		recipe.getRecipeItem().toNetwork(buffer);
		buffer.writeVarInt(recipe.getToolListLength());

		for (AnimatedRecipeItemUse riu : recipe.getRecipeTools())
			AnimatedRecipeItemUse.write(riu, buffer);

		buffer.writeItem(recipe.getOutput());
		buffer.writeResourceLocation(recipe.getLootTable());
	}
}