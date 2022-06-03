//package lance5057.compendium.core.workstations.recipes.serializers;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//
//public class SawhorseStationRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
//		implements RecipeSerializer<SawhorseStationRecipe> {
//	@Nonnull
//	@Override
//	public SawhorseStationRecipe fromJson(@Nonnull ResourceLocation recipeId, JsonObject json) {
//		final int strikes = json.get("strikes").getAsInt();
//		final Ingredient input = Ingredient.fromJson(json.get("input"));
//
//		String s = json.get("output").getAsString();
//		final ResourceLocation result = new ResourceLocation(s);
//
//		return new SawhorseStationRecipe(WorkstationRecipes.SAWHORSE_STATION_RECIPE, recipeId, strikes, result, input);
//	}
//
//	@Nullable
//	@Override
//	public SawhorseStationRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
//		int strikes = buffer.readInt();
//		Ingredient input = Ingredient.fromNetwork(buffer);
//
//		String s = buffer.readUtf();
//		final ResourceLocation result = new ResourceLocation(s);
//
//		return new SawhorseStationRecipe(WorkstationRecipes.SAWHORSE_STATION_RECIPE, recipeId, strikes, result, input);
//	}
//
//	@Override
//	public void toNetwork(FriendlyByteBuf buffer, SawhorseStationRecipe recipe) {
//		buffer.writeInt(recipe.getStrikes());
//		recipe.getIngredient().toNetwork(buffer);
//		buffer.writeUtf(recipe.getOutput().toString());
//
//	}
//}
