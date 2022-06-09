package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CraftingAnvilRecipeProvider extends RecipeProvider {
	public CraftingAnvilRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    if (mh.getIngot() != null)
//		ingot(mh, consumer);
//	    else if (mh.getGem() != null)
//		gem(mh, consumer);
//	}

	}

	@Override
	@Nonnull
	public String getName() {
		return "Crafting Anvil Recipes";
	}

	public static class AnvilResult implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final Item schematic;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		private final List<RecipeItemUse> tools;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;

		public AnvilResult(ResourceLocation idIn, Item resultIn, int countIn, Item schematicIn, String groupIn,
				List<String> patternIn, Map<Character, Ingredient> keyIn, List<RecipeItemUse> toolsIn,
				Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
			this.id = idIn;
			this.result = resultIn;
			this.count = countIn;
			this.group = groupIn;
			this.pattern = patternIn;
			this.key = keyIn;
			this.advancementBuilder = advancementBuilderIn;
			this.advancementId = advancementIdIn;
			this.tools = toolsIn;
			this.schematic = schematicIn;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			JsonArray jsonarray = new JsonArray();

			for (String s : this.pattern) {
				jsonarray.add(s);
			}

			json.add("pattern", jsonarray);
			JsonObject jsonobject = new JsonObject();

			for (Entry<Character, Ingredient> entry : this.key.entrySet()) {
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			json.add("key", jsonobject);

			JsonObject jsonobjectTools = new JsonObject();

			for (int i = 0; i < this.tools.size(); i++) {// entry : this.tools) {
				jsonobjectTools.add("Step_" + i, RecipeItemUse.addProperty(tools.get(i)));
			}
			json.add("tools", jsonobjectTools);

			JsonObject schematic = new JsonObject();
			schematic.addProperty("item", Registry.ITEM.getKey(this.schematic).toString());
			json.add("schematic", schematic);

			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}

			json.add("result", jsonobject1);
		}

		public RecipeSerializer<?> getType() {
			return WorkstationRecipes.CRAFTING_ANVIL_SERIALIZER.get();
		}

		/**
		 * Gets the ID for the recipe.
		 */
		public ResourceLocation getId() {
			return this.id;
		}

		/**
		 * Gets the JSON for the advancement that unlocks this recipe. Null if there is
		 * no advancement.
		 */
		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return this.advancementBuilder.serializeToJson();
		}

		/**
		 * Gets the ID for the advancement associated with this recipe. Should not be
		 * null if {@link #getAdvancementJson} is non-null.
		 */
		@Nullable
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}