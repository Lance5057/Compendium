package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.SawBuckRecipeLoottables;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import net.minecraft.advancements.Advancement;
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
//		this.createRecipe("plank_to_sticks", SawBuckRecipeLoottables.allplanks, Ingredient.of(ItemTags.PLANKS),
//				consumer, 2);
//
//		this.createRecipe("log_to_plank", new ResourceLocation(Reference.MOD_ID, "recipese/sawhorse/oak_log"),
//				Ingredient.of(Items.OAK_LOG), consumer, 2);
	}

	// Copied it since inner class was private
	public static class SawBuckFinishedRecipe implements FinishedRecipe {
		private final ResourceLocation id;
		private final ResourceLocation output;
		private final Ingredient input;
		private final List<AnimatedRecipeItemUse> tools;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;

		public SawBuckFinishedRecipe(ResourceLocation id, ResourceLocation output, Ingredient input,
				List<AnimatedRecipeItemUse> tools, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.tools = tools;
			this.advancementBuilder = advancementBuilderIn;
			this.advancementId = advancementIdIn;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			JsonObject jsonobjectTools = new JsonObject();

			for (int i = 0; i < this.tools.size(); i++) {// entry : this.tools) {
				jsonobjectTools.add("Step_" + i, AnimatedRecipeItemUse.addProperty(tools.get(i)));
			}
			json.add("tools", jsonobjectTools);
			json.add("input", input.toJson());

			json.addProperty("output", output.toString());
		}

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