//package lance5057.compendium.core.data.builders.workstationrecipes.builders;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import lance5057.compendium.core.data.builders.workstationrecipes.ScrappingTableRecipeProvider;
//import lance5057.compendium.core.recipes.RecipeItemUse;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.advancements.AdvancementRewards;
//import net.minecraft.advancements.CriterionTriggerInstance;
//import net.minecraft.advancements.RequirementsStrategy;
//import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
//import net.minecraft.core.NonNullList;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Ingredient;
//
//public class ScrappingTableRecipeBuilder {
//	private static final Logger LOGGER = LogManager.getLogger();
//	private final Ingredient input;
//	private final ResourceLocation result;
//	private final List<RecipeItemUse> tools = NonNullList.create();
//	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
//	private String group;
//
//	public ScrappingTableRecipeBuilder(Ingredient input, ResourceLocation resultIn) {
//		this.input = input;
//		this.result = resultIn;
//	}
//
//	public static ScrappingTableRecipeBuilder start(Ingredient input, ResourceLocation resultIn) {
//		return new ScrappingTableRecipeBuilder(input, resultIn);
//	}
//
//	public ScrappingTableRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage) {
//		this.tools.add(new RecipeItemUse(uses, tool, count, damage));
//		return this;
//	}
//
//	public ScrappingTableRecipeBuilder tool(Ingredient tool, int uses, boolean damage) {
//		this.tools.add(new RecipeItemUse(uses, tool, 1, damage));
//		return this;
//	}
//
//	/**
//	 * Adds a criterion needed to unlock the recipe.
//	 */
//	public ScrappingTableRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
//		this.advancementBuilder.addCriterion(name, criterionIn);
//		return this;
//	}
//
//	public ScrappingTableRecipeBuilder setGroup(String groupIn) {
//		this.group = groupIn;
//		return this;
//	}
//
//	/**
//	 * Builds this recipe into an {@link FinishedRecipe}.
//	 */
//	public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
//		this.validate(id);
//		this.advancementBuilder.parent(new ResourceLocation("recipes/root"))
//				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
//				.rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
//		consumerIn.accept(new ScrappingTableRecipeProvider.ScrappingTableFinishedRecipe(id, this.input, this.result,
//				this.group == null ? "" : this.group, this.tools, this.advancementBuilder,
//				new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath())));
//
////	ResourceLocation idIn, Ingredient inputIn, ResourceLocation resultIn, int countIn,
////	String groupIn, List<RecipeItemUse> toolsIn, Advancement.Builder advancementBuilderIn,
////	ResourceLocation advancementIdIn)
//	}
//
//	/**
//	 * Makes sure that this recipe is valid and obtainable.
//	 */
//	private void validate(ResourceLocation id) {
//		if (this.tools.isEmpty()) {
//			throw new IllegalStateException("No toolset is defined for shaped recipe " + id + "!");
//		}
//	}
//}
