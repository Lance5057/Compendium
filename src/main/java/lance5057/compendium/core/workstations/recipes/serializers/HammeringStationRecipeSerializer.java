//package lance5057.compendium.core.workstations.recipes.serializers;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.ShapedRecipe;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//
//public class HammeringStationRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
//		implements RecipeSerializer<HammeringStationRecipe> {
//	@Nonnull
//	@Override
//	public HammeringStationRecipe fromJson(@Nonnull ResourceLocation recipeId, JsonObject json) {
//		final int strikes = json.get("strikes").getAsInt();
//		final Ingredient input = Ingredient.fromJson(json.get("input"));
//		final ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
//		return new HammeringStationRecipe(recipeId, strikes, result, input);
//	}
//
//	@Nullable
//	@Override
//	public HammeringStationRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
//		int strikes = buffer.readInt();
//
//		Ingredient input = Ingredient.fromNetwork(buffer);
//		ItemStack result = buffer.readItem();
//
//		return new HammeringStationRecipe(recipeId, strikes, result, input);
//	}
//
//	@Override
//	public void toNetwork(FriendlyByteBuf buffer, HammeringStationRecipe recipe) {
//		buffer.writeInt(recipe.getStrikes());
//
//		recipe.getIngredient().toNetwork(buffer);
//		buffer.writeItem(recipe.getResultItem());
//
//	}
//}
