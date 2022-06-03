//package lance5057.compendium.core.workstations.recipes.serializers;
//
//import java.util.Map.Entry;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.core.recipes.RecipeItemUse;
//import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//
//public class ScrappingTableRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
//		implements RecipeSerializer<ScrappingTableRecipe> {
//
//	@Nonnull
//	@Override
//	public ScrappingTableRecipe fromJson(@Nonnull ResourceLocation recipeId, JsonObject json) {
//		final Ingredient input = Ingredient.fromJson(json.get("input"));
//
//		String q = json.get("output").getAsString();
//		String s = GsonHelper.getAsString(json, "group", "");
//		final ResourceLocation loottable = new ResourceLocation(q);
//
//		NonNullList<RecipeItemUse> nonnulllistTools = deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));
//
//		// ( ResourceLocation id, ResourceLocation loottable, Ingredient ingredient,
//		// NonNullList<RecipeItemUse> recipeToolsIn)
//		return new ScrappingTableRecipe(recipeId, s, loottable, input, nonnulllistTools);
//	}
//
//	@Nullable
//	@Override
//	public ScrappingTableRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
//		// input
//		Ingredient input = Ingredient.fromNetwork(buffer);
//		// group
//		String s = buffer.readUtf(32767);
//		// loottable
//		String q = buffer.readUtf(); 
//		final ResourceLocation loottable = new ResourceLocation(q);
//		// tool list length
//		int h = buffer.readVarInt();
//		NonNullList<RecipeItemUse> nonnulllistTool = NonNullList.withSize(h, RecipeItemUse.EMPTY);
//		// tool list
//		for (int k = 0; k < nonnulllistTool.size(); ++k) {
//			nonnulllistTool.set(k, RecipeItemUse.read(buffer));
//		}
//
//		return new ScrappingTableRecipe(recipeId, s, loottable, input, nonnulllistTool);
//	}
//
//	@Override
//	public void toNetwork(FriendlyByteBuf buffer, ScrappingTableRecipe recipe) {
//		// input
//		recipe.getIngredients().get(0).toNetwork(buffer);
//		// group
//		buffer.writeUtf(recipe.getGroup());
//		// loottable
//		buffer.writeResourceLocation(recipe.getLoottable());
//		// tool list length
//		buffer.writeVarInt(recipe.getToolListLength());
//		// tool list
//		for (RecipeItemUse riu : recipe.getRecipeTools())
//			RecipeItemUse.write(riu, buffer);
//
//	}
//
//	private static NonNullList<RecipeItemUse> deserializeTool(JsonObject json) {
//		NonNullList<RecipeItemUse> map = NonNullList.create();
//
//		for (Entry<String, JsonElement> entry : json.entrySet()) {
////	    if (entry.getKey().length() != 1) {
////		throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey()
////			+ "' is an invalid symbol (must be 1 character only).");
////	    }
////
////	    if (" ".equals(entry.getKey())) {
////		throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
////	    }
//
//			RecipeItemUse r = RecipeItemUse.read(entry.getValue().getAsJsonObject());
//
//			map.add(r);
//		}
//
//		// map.put(" ", RecipeItemUse.EMPTY);
//		return map;
//	}
//}
