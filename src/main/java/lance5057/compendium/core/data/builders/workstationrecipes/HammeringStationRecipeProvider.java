package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class HammeringStationRecipeProvider extends RecipeProvider {
	public HammeringStationRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	// Copied it since inner class was private
	public static class HammeringStationFinishedRecipe implements FinishedRecipe {
		private final ResourceLocation id;
		private final ResourceLocation outputTable;
		private final Ingredient input;
		private final ItemStack output;
		private final List<AnimatedRecipeItemUse> tools;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;

		public HammeringStationFinishedRecipe(ResourceLocation id, ResourceLocation outputTable, Ingredient input,
				ItemStack output, List<AnimatedRecipeItemUse> tools, Advancement.Builder advancementBuilderIn,
				ResourceLocation advancementIdIn) {
			this.id = id;
			this.outputTable = outputTable;
			this.input = input;
			this.output = output;
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

			JsonObject objectResult = new JsonObject();
			objectResult.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());
			if (output.getCount() > 1) {
				objectResult.addProperty("count", output.getCount());
			}
			json.add("output", objectResult);

			json.addProperty("outputTable", outputTable.toString());
		}

		@Override
		@Nonnull
		public ResourceLocation getId() {
			return id;
		}

		@Override
		@Nonnull
		public RecipeSerializer<?> getType() {
			return WorkstationRecipes.HAMMERINGSTATION_SERIALIZER.get();
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