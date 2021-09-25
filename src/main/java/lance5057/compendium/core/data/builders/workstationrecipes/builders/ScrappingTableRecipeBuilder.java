package lance5057.compendium.core.data.builders.workstationrecipes.builders;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.workstationrecipes.ScrappingTableRecipeProvider;
import lance5057.compendium.core.recipes.RecipeItemUse;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ScrappingTableRecipeBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Ingredient input;
    private final ResourceLocation result;
    private final List<RecipeItemUse> tools = NonNullList.create();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
    private String group;

    public ScrappingTableRecipeBuilder(Ingredient input, ResourceLocation resultIn) {
	this.input = input;
	this.result = resultIn;
    }
    
    public static ScrappingTableRecipeBuilder start(Ingredient input, ResourceLocation resultIn) {
	return new ScrappingTableRecipeBuilder(input, resultIn);
    }

    public ScrappingTableRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage) {
	this.tools.add(new RecipeItemUse(uses, tool, count, damage));
	return this;
    }

    public ScrappingTableRecipeBuilder tool(Ingredient tool, int uses, boolean damage) {
	this.tools.add(new RecipeItemUse(uses, tool, 1, damage));
	return this;
    }

    /**
     * Adds a criterion needed to unlock the recipe.
     */
    public ScrappingTableRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
	this.advancementBuilder.withCriterion(name, criterionIn);
	return this;
    }

    public ScrappingTableRecipeBuilder setGroup(String groupIn) {
	this.group = groupIn;
	return this;
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
	this.validate(id);
	this.advancementBuilder.withParentId(new ResourceLocation("recipes/root"))
		.withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id))
		.withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
	consumerIn.accept(new ScrappingTableRecipeProvider.FinishedResult(id, this.input, this.result, 
		this.group == null ? "" : this.group,  this.tools, this.advancementBuilder,
		new ResourceLocation(id.getNamespace(),
			"recipes/" + id.getPath())));
	
//	ResourceLocation idIn, Ingredient inputIn, ResourceLocation resultIn, int countIn,
//	String groupIn, List<RecipeItemUse> toolsIn, Advancement.Builder advancementBuilderIn,
//	ResourceLocation advancementIdIn)
    }

    /**
     * Makes sure that this recipe is valid and obtainable.
     */
    private void validate(ResourceLocation id) {
	if (this.tools.isEmpty()) {
	    throw new IllegalStateException("No toolset is defined for shaped recipe " + id + "!");
	}
    }
}
