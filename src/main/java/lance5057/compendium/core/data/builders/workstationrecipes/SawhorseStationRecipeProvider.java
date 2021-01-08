package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class SawhorseStationRecipeProvider extends RecipeProvider {
	public SawhorseStationRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
		// From Tag
		this.createRecipe("plank_to_sticks_oak", new ResourceLocation(Reference.MOD_ID, "recipe/sawhorse"), Ingredient.fromStacks(new ItemStack(Items.OAK_PLANKS)), consumer, 2);
	}

	private void createRecipe(String name, ResourceLocation output, Ingredient input, Consumer<IFinishedRecipe> consumer) {
		consumer.accept(new FinishedRecipe(new ResourceLocation(Reference.MOD_ID, "sawhorsestation/" + name), output, input));
	}

	private void createRecipe(String name, ResourceLocation output, Ingredient input, Consumer<IFinishedRecipe> consumer, int strike) {
		consumer.accept(new FinishedRecipe(new ResourceLocation(Reference.MOD_ID, "sawhorsestation/" + name), output, input, strike));
	}

	// Copied it since inner class was private
	private static class FinishedRecipe implements IFinishedRecipe {
		private final ResourceLocation id;
		private final ResourceLocation output;
		private final Ingredient input;
		private final int strikes;

		private FinishedRecipe(ResourceLocation id, ResourceLocation output, Ingredient input, int strikes) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = strikes;
		}

		private FinishedRecipe(ResourceLocation id, ResourceLocation output, Ingredient input) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = 4;
		}

		@Override
		public void serialize(JsonObject json) {
			json.addProperty("strikes", strikes);
			json.add("input", input.serialize());

			json.addProperty("output", output.toString());
		}

//		public static JsonObject serializeItemStack(ItemStack output) {
//			JsonObject resultObject = new JsonObject();
//			resultObject.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());
//			if (output.getCount() > 1) {
//				resultObject.addProperty("count", output.getCount());
//			}
//
//			if (output.hasTag() && output.getTag() != null) {
//				resultObject.addProperty("nbt", output.getTag().toString());
//			}
//			return resultObject;
//		}

		@Override
		@Nonnull
		public ResourceLocation getID() {
			return id;
		}

		@Override
		@Nonnull
		public IRecipeSerializer<?> getSerializer() {
			return WorkstationRecipes.SAWHORSE_STATION_SERIALIZER.get();
		}

		@Nullable
		@Override
		public JsonObject getAdvancementJson() {
			return null;
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementID() {
			return null;
		}
	}

	@Override
	@Nonnull
	public String getName() {
		return "Sawhorse Station Recipes";
	}

}