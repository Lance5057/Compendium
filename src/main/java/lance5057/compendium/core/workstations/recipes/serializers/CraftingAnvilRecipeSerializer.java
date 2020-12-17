//package lance5057.compendium.core.workstations.recipes.serializers;
//
//import java.util.Map;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSyntaxException;
//
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.IRecipeSerializer;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraft.item.crafting.ShapedRecipe;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.JSONUtils;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//
//public class CraftingAnvilRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
//		implements IRecipeSerializer<CraftingAnvilRecipe> {
//	@Nonnull
//	@Override
//	public CraftingAnvilRecipe read(@Nonnull ResourceLocation recipeId, JsonObject json) {
//		Map<String, Ingredient> map = ShapedRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
//		String[] pattern = ShapedRecipe.shrink(patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
//		final int strikes = json.get("strikes").getAsInt();
//		final Ingredient input = Ingredient.deserialize(json.get("input"));
//		final ItemStack result = ShapedRecipe.deserializeItem(json.getAsJsonObject("result"));
//		return new CraftingAnvilRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE, recipeId, strikes,
//				result, input);
//	}
//
//	@Nullable
//	@Override
//	public CraftingAnvilRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
//		int strikes = buffer.readInt();
//		ItemStack result = buffer.readItemStack();
//		Ingredient input = Ingredient.read(buffer);
//
//		return new CraftingAnvilRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE, recipeId, strikes,
//				result, input);
//	}
//
//	private static String[] patternFromJson(JsonArray jsonArr) {
//		String[] astring = new String[jsonArr.size()];
//		for (int i = 0; i < astring.length; ++i) {
//			String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
//
//			if (i > 0 && astring[0].length() != s.length()) {
//				throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
//			}
//
//			astring[i] = s;
//		}
//
//		return astring;
//	}
//	
//	@Override
//	public void write(PacketBuffer buffer, CraftingAnvilRecipe recipe) {
//		buffer.writeInt(recipe.getStrikes());
//		buffer.writeItemStack(recipe.getRecipeOutput());
//		recipe.getIngredients().write(buffer);
//
//	}
//}
