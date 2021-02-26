package lance5057.compendium.core.workstations.recipes.serializers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SawhorseStationRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<SawhorseStationRecipe> {
    @Nonnull
    @Override
    public SawhorseStationRecipe read(@Nonnull ResourceLocation recipeId, JsonObject json) {
	final int strikes = json.get("strikes").getAsInt();
	final Ingredient input = Ingredient.deserialize(json.get("input"));
	
	String s = json.get("output").getAsString();
	final ResourceLocation result = new ResourceLocation(s);

	return new SawhorseStationRecipe(WorkstationRecipes.SAWHORSE_STATION_RECIPE, recipeId, strikes, result, input);
    }

    @Nullable
    @Override
    public SawhorseStationRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
	int strikes = buffer.readInt();
	Ingredient input = Ingredient.read(buffer);
	
	String s = buffer.readString();
	final ResourceLocation result = new ResourceLocation(s);

	return new SawhorseStationRecipe(WorkstationRecipes.SAWHORSE_STATION_RECIPE, recipeId, strikes, result, input);
    }

    @Override
    public void write(PacketBuffer buffer, SawhorseStationRecipe recipe) {
	buffer.writeInt(recipe.getStrikes());
	recipe.getIngredient().write(buffer);
	buffer.writeString(recipe.getOutput().toString());

    }
}
