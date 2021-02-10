package lance5057.compendium.core.recipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.CompendiumRecipes;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class HammerHandedToolRecipe extends InWorldHandedToolRecipe {

    public HammerHandedToolRecipe(ResourceLocation id, ItemStack block, Ingredient offhand, ItemStack output) {
	super(id, block, offhand, output);
    }

    @Override
    public IRecipeType<?> getType() {
	return CompendiumRecipes.HAMMERING_TOOL_RECIPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
	return CompendiumRecipes.HAMMERING_TOOL_SERIALIZER.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	    implements IRecipeSerializer<HammerHandedToolRecipe> {
	@Nonnull
	@Override
	public HammerHandedToolRecipe read(@Nonnull ResourceLocation recipeId, JsonObject json) {
	    final Ingredient input = Ingredient.deserialize(json.get("input"));
	    final ItemStack block = ShapedRecipe.deserializeItem(json.getAsJsonObject("block"));
	    final ItemStack result = ShapedRecipe.deserializeItem(json.getAsJsonObject("result"));
	    
	    return new HammerHandedToolRecipe(recipeId, block, input, result);
	}

	@Nullable
	@Override
	public HammerHandedToolRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
	    ItemStack result = buffer.readItemStack();
	    ItemStack block = buffer.readItemStack();
	    Ingredient input = Ingredient.read(buffer);

	    return new HammerHandedToolRecipe(recipeId, block, input, result);
	}

	@Override
	public void write(PacketBuffer buffer, HammerHandedToolRecipe recipe) {

	    buffer.writeItemStack(recipe.getRecipeOutput());
	    buffer.writeItemStack(recipe.getBlock());
	    recipe.getIngredient().write(buffer);

	}
    }
}
