//package lance5057.compendium.core.data.builders.workstationrecipes;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.core.recipes.RecipeItemUse;
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.data.recipes.RecipeProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//
//public class ScrappingTableRecipeProvider extends RecipeProvider {
//	public ScrappingTableRecipeProvider(DataGenerator generatorIn) {
//		super(generatorIn);
//	}
//
//	@Override
//	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
//
////	for (MaterialHelper mh : CompendiumMaterials.materials) {
////	    if (mh.getIngot() != null)
////		ingot(mh, consumer);
////	    else if (mh.getGem() != null)
////		gem(mh, consumer);
////	}
//
//	}
//
//	@Override
//	@Nonnull
//	public String getName() {
//		return "Scrapping Table Recipes";
//	}
//
//	public static class ScrappingTableFinishedRecipe implements FinishedRecipe {
//		private final ResourceLocation id;
//		private final Ingredient input;
//		private final ResourceLocation result;
//		// private final int count;
//		private final String group;
//		private final List<RecipeItemUse> tools;
//		private final Advancement.Builder advancementBuilder;
//		private final ResourceLocation advancementId;
//
//		public ScrappingTableFinishedRecipe(ResourceLocation idIn, Ingredient inputIn, ResourceLocation resultIn,
//				String groupIn, List<RecipeItemUse> toolsIn, Advancement.Builder advancementBuilderIn,
//				ResourceLocation advancementIdIn) {
//			this.id = idIn;
//			this.input = inputIn;
//			this.result = resultIn;
//			// this.count = countIn;
//			this.group = groupIn;
//			this.advancementBuilder = advancementBuilderIn;
//			this.advancementId = advancementIdIn;
//			this.tools = toolsIn;
//		}
//
//		@Override
//		public void serializeRecipeData(JsonObject json) {
//			if (!this.group.isEmpty()) {
//				json.addProperty("group", this.group);
//			}
//
//			json.add("input", input.toJson());
//
//			JsonObject jsonobjectTools = new JsonObject();
//
//			for (int i = 0; i < this.tools.size(); i++) {// entry : this.tools) {
//				jsonobjectTools.add("Step_" + i, RecipeItemUse.addProperty(tools.get(i)));
//			}
//			json.add("tools", jsonobjectTools);
//
//			json.addProperty("output", result.toString());
//		}
//
//		@Override
//		public RecipeSerializer<?> getType() {
//			return WorkstationRecipes.SCRAPPING_TABLE_SERIALIZER.get();
//		}
//
//		@Override
//		public ResourceLocation getId() {
//			return this.id;
//		}
//
//		@Override
//		@Nullable
//		public JsonObject serializeAdvancement() {
//			return this.advancementBuilder.serializeToJson();
//		}
//
//		@Override
//		@Nullable
//		public ResourceLocation getAdvancementId() {
//			return this.advancementId;
//		}
//	}
//}