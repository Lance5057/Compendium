package lance5057.compendium.core.workstations.recipes.serializers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class HammeringStationRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<HammeringStationRecipe> {
	@Nonnull
	@Override
	public HammeringStationRecipe read(@Nonnull ResourceLocation recipeId, JsonObject json) {
		final int strikes = json.get("strikes").getAsInt();
		final Ingredient input = Ingredient.deserialize(json.get("input"));
		final ItemStack result = ShapedRecipe.deserializeItem(json.getAsJsonObject("result"));
		return new HammeringStationRecipe(recipeId, strikes, result, input);
	}

	@Nullable
	@Override
	public HammeringStationRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
		int strikes = buffer.readInt();
		ItemStack result = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);

		return new HammeringStationRecipe(recipeId, strikes, result, input);
	}

	@Override
	public void write(PacketBuffer buffer, HammeringStationRecipe recipe) {
		buffer.writeInt(recipe.getStrikes());
		buffer.writeItemStack(recipe.getRecipeOutput());
		recipe.getIngredient().write(buffer);

	}
}
