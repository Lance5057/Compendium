package lance5057.compendium.core.data.builders.workstationrecipes.builders;

import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.data.builders.workstationrecipes.HammeringStationRecipeProvider;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class HammeringStationRecipeBuilder implements RecipeBuilder {
	private final ResourceLocation outputTable;
	private final ItemStack output;
	private final Ingredient input;
	private final List<AnimatedRecipeItemUse> tools = NonNullList.create();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
	private String group;

	public static HammeringStationRecipeBuilder recipe(Ingredient input, ResourceLocation resultIn, ItemStack output) {
		return new HammeringStationRecipeBuilder(input, resultIn, output);
	}

	public HammeringStationRecipeBuilder(Ingredient input, ResourceLocation resultIn, ItemStack output) {
		this.outputTable = resultIn;
		this.input = input;
		this.output = output;
	}

	public HammeringStationRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, model));
		return this;
	}

	public HammeringStationRecipeBuilder tool(Ingredient tool, int uses, boolean damage, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, 1, damage, model));
		return this;
	}

	public HammeringStationRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
		this.advancementBuilder.addCriterion(name, criterionIn);
		return this;
	}

	@Override
	public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterionIn) {
		this.advancementBuilder.addCriterion(name, criterionIn);
		return this;
	}

	@Override
	public RecipeBuilder group(String p_176495_) {
		this.group = p_176495_;
		return this;
	}

	@Override
	public Item getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	private void validate(ResourceLocation id) {
		if (this.advancementBuilder.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	@Override
	public void save(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		this.advancementBuilder.parent(new ResourceLocation("recipes/root"))
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
		consumerIn.accept(new HammeringStationRecipeProvider.HammeringStationFinishedRecipe(id, this.outputTable, this.input, this.output,
				this.tools, this.advancementBuilder,
				new ResourceLocation(id.getNamespace(), "recipes/hammering/" + id.getPath())));
	}

}
