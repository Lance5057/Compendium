package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.SawBuckRecipeLoottables;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SawBuckRecipeProvider extends RecipeProvider {
	public SawBuckRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
		// From Tag
		this.createRecipe("plank_to_sticks", SawBuckRecipeLoottables.allplanks, Ingredient.of(ItemTags.PLANKS),
				consumer, 2);

		this.createRecipe("log_to_plank", new ResourceLocation(Reference.MOD_ID, "recipese/sawhorse/oak_log"),
				Ingredient.of(Items.OAK_LOG), consumer, 2);
	}

	private void createRecipe(String name, ResourceLocation output, Ingredient input,
			Consumer<FinishedRecipe> consumer) {
		consumer.accept(new SawBuckFinishedRecipe(
				new ResourceLocation(Reference.MOD_ID, "sawhorsestation/" + name), output, input));
	}

	private void createRecipe(String name, ResourceLocation output, Ingredient input, Consumer<FinishedRecipe> consumer,
			int strike) {
		consumer.accept(new SawBuckFinishedRecipe(
				new ResourceLocation(Reference.MOD_ID, "sawhorsestation/" + name), output, input, strike));
	}

	// Copied it since inner class was private
	private static class SawBuckFinishedRecipe implements FinishedRecipe {
		private final ResourceLocation id;
		private final ResourceLocation output;
		private final Ingredient input;
		private final int strikes;

		private SawBuckFinishedRecipe(ResourceLocation id, ResourceLocation output, Ingredient input,
				int strikes) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = strikes;
		}

		private SawBuckFinishedRecipe(ResourceLocation id, ResourceLocation output, Ingredient input) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = 4;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.addProperty("strikes", strikes);
			json.add("input", input.toJson());

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
		public ResourceLocation getId() {
			return id;
		}

		@Override
		@Nonnull
		public RecipeSerializer<?> getType() {
			return WorkstationRecipes.SAWBUCK_SERIALIZER.get();
		}

		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return null;
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementId() {
			return null;
		}
	}

	@Override
	@Nonnull
	public String getName() {
		return "Sawhorse Station Recipes";
	}

}