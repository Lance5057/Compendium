package lance5057.compendium.core.workstations.recipes.serializers;

import java.util.Map;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class CraftingAnvilRecipeSerializer
	extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<CraftingAnvilRecipe> {
    private static final ResourceLocation NAME = new ResourceLocation(Reference.MOD_ID, "crafting_anvil_shaped");

    public CraftingAnvilRecipe read(ResourceLocation recipeId, JsonObject json) {
	String s = JSONUtils.getString(json, "group", "");
	Map<String, Ingredient> map = CraftingAnvilRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
	String[] astring = CraftingAnvilRecipe
		.shrink(CraftingAnvilRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
	int i = astring[0].length();
	int j = astring.length;
	NonNullList<Ingredient> nonnulllist = CraftingAnvilRecipe.deserializeIngredients(astring, map, i, j);
	ItemStack itemstackSchem = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "schematic"));
	ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));

	NonNullList<RecipeItemUse> nonnulllistTools = CraftingAnvilRecipe
		.deserializeTool(JSONUtils.getJsonObject(json, "tools"));

	return new CraftingAnvilRecipe(recipeId, s, i, j, nonnulllist, nonnulllistTools, itemstackSchem, itemstack);
    }

    public CraftingAnvilRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
	int width = buffer.readVarInt();
	int height = buffer.readVarInt();
	String group = buffer.readString(32767);

	NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

	for (int k = 0; k < (width * height); ++k) {
	    ingredients.set(k, Ingredient.read(buffer));
	}

	int h = buffer.readVarInt();

	NonNullList<RecipeItemUse> tools = NonNullList.withSize(h, RecipeItemUse.EMPTY);

	for (int k = 0; k < tools.size(); ++k) {
	    tools.set(k, RecipeItemUse.read(buffer));
	}

	ItemStack schematic = buffer.readItemStack();
	ItemStack output = buffer.readItemStack();

	return new CraftingAnvilRecipe(recipeId, group, width, height, ingredients, tools, schematic, output);
    }

    public void write(PacketBuffer buffer, CraftingAnvilRecipe recipe) {
	buffer.writeVarInt(recipe.getRecipeWidth());
	buffer.writeVarInt(recipe.getRecipeHeight());
	buffer.writeString(recipe.getGroup());

	for (Ingredient ingredient : recipe.getRecipeItems()) {
	    ingredient.write(buffer);
	}

	buffer.writeVarInt(recipe.getToolListLength());

	for (RecipeItemUse riu : recipe.getRecipeTools())
	    RecipeItemUse.write(riu, buffer);

	buffer.writeItemStack(recipe.getSchematic());
	buffer.writeItemStack(recipe.getRecipeOutput());
    }
}