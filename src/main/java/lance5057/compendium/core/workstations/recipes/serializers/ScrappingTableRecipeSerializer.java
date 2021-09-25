package lance5057.compendium.core.workstations.recipes.serializers;

import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ScrappingTableRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<ScrappingTableRecipe> {
    @Nonnull
    @Override
    public ScrappingTableRecipe read(@Nonnull ResourceLocation recipeId, JsonObject json) {
	final Ingredient input = Ingredient.deserialize(json.get("input"));

	String q = json.get("output").getAsString();
	String s = JSONUtils.getString(json, "group", "");
	final ResourceLocation loottable = new ResourceLocation(q);

	NonNullList<RecipeItemUse> nonnulllistTools = deserializeTool(JSONUtils.getJsonObject(json, "tools"));

	// ( ResourceLocation id, ResourceLocation loottable, Ingredient ingredient,
	// NonNullList<RecipeItemUse> recipeToolsIn)
	return new ScrappingTableRecipe(recipeId, s, loottable, input, nonnulllistTools);
    }

    @Nullable
    @Override
    public ScrappingTableRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {

	Ingredient input = Ingredient.read(buffer);
	String s = buffer.readString(32767);

	String q = buffer.readString();
	final ResourceLocation loottable = new ResourceLocation(q);

	int h = buffer.readVarInt();

	NonNullList<RecipeItemUse> nonnulllistTool = NonNullList.withSize(h, RecipeItemUse.EMPTY);

	for (int k = 0; k < nonnulllistTool.size(); ++k) {
	    nonnulllistTool.set(k, RecipeItemUse.read(buffer));
	}

	return new ScrappingTableRecipe(recipeId, s, loottable, input, nonnulllistTool);
    }

    @Override
    public void write(PacketBuffer buffer, ScrappingTableRecipe recipe) {

	buffer.writeVarInt(recipe.getRecipeWidth());
	buffer.writeVarInt(recipe.getRecipeHeight());
	buffer.writeString(recipe.getGroup());

	for (Ingredient ingredient : recipe.getRecipeItems()) {
	    ingredient.write(buffer);
	}

	buffer.writeVarInt(recipe.getToolListLength());

	for (RecipeItemUse riu : recipe.getRecipeTools())
	    RecipeItemUse.write(riu, buffer);

	buffer.writeItemStack(recipe.getRecipeOutput());

    }

    private static NonNullList<RecipeItemUse> deserializeTool(JsonObject json) {
	NonNullList<RecipeItemUse> map = NonNullList.create();

	for (Entry<String, JsonElement> entry : json.entrySet()) {
//	    if (entry.getKey().length() != 1) {
//		throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey()
//			+ "' is an invalid symbol (must be 1 character only).");
//	    }
//
//	    if (" ".equals(entry.getKey())) {
//		throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
//	    }

	    RecipeItemUse r = RecipeItemUse.read(entry.getValue().getAsJsonObject());

	    map.add(r);
	}

	// map.put(" ", RecipeItemUse.EMPTY);
	return map;
    }
}
