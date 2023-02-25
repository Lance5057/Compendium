package lance5057.compendium.core.workstations.recipes.serializers;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.recipes.SawBuckRecipe;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SawBuckRecipeSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>>
		implements RecipeSerializer<SawBuckRecipe> {
	private static final ResourceLocation NAME = new ResourceLocation(Reference.MOD_ID, "crafting_anvil_shaped");

	public SawBuckRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		String s = GsonHelper.getAsString(json, "group", "");
		Ingredient itemIn = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));

		NonNullList<AnimatedRecipeItemUse> nonnulllistTools = SawBuckRecipe
				.deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));

		ResourceLocation output = new ResourceLocation(json.get("output").getAsString());

		return new SawBuckRecipe(recipeId, s, itemIn, nonnulllistTools, output);
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
		final ResourceLocation output = new ResourceLocation(q);

		return new SawBuckRecipe(recipeId, group, ing, tools, output);
	}

	public void toNetwork(FriendlyByteBuf buffer, SawBuckRecipe recipe) {
		buffer.writeUtf(recipe.getGroup());

		recipe.getRecipeItem().toNetwork(buffer);
		buffer.writeVarInt(recipe.getToolListLength());

		for (AnimatedRecipeItemUse riu : recipe.getRecipeTools())
			AnimatedRecipeItemUse.write(riu, buffer);

		// buffer.writeItem(recipe.getSchematic());
		buffer.writeResourceLocation(recipe.getLootTable());
	}
}