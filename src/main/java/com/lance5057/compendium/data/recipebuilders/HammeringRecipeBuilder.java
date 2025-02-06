package com.lance5057.compendium.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.RecipeMobEffect;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRecipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootTable;

public class HammeringRecipeBuilder implements RecipeBuilder {
	private final Ingredient input;
	private final ItemStack result;
	private final NonNullList<AnimatedRecipeItemUse> tools = NonNullList.create();

	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public HammeringRecipeBuilder(Ingredient in, ItemStack out) {
		this.input = in;
		this.result = out;
	}

	public static HammeringRecipeBuilder hammer(Ingredient input, ItemStack result) {
		return new HammeringRecipeBuilder(input, result);
	}

	public HammeringRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
		this.criteria.put(criterionName, criterionTrigger);
		return this;
	}

	public HammeringRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
		return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
	}

	public HammeringRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
		this.criteria.put("has_any_ingredient",
				InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build()));
		return this;
	}

	public HammeringRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage,
			ResourceKey<LootTable> table, List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public HammeringRecipeBuilder tool(Ingredient tool, int uses, boolean damage, ResourceKey<LootTable> table,
			List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, 1, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public void build(RecipeOutput consumer) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(result.getItem());
		build(consumer, Compendium.MOD_ID + ":" + location.getPath());
	}

	public void build(RecipeOutput consumerIn, String save) {
		save(consumerIn, ResourceLocation.parse(save));
	}

	@Override
	public RecipeBuilder group(String p_176495_) {
		return this;
	}

	@Override
	public Item getResult() {
		return this.result.getItem();
	}

	public ItemStack getResultStack() {
		return this.result;
	}

	@Override
	public void save(RecipeOutput output, ResourceLocation id) {
		ResourceLocation recipeId = id.withPrefix("hammering/");
		Advancement.Builder advancementBuilder = output.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancementBuilder::addCriterion);

		HammeringStationRecipe recipe = new HammeringStationRecipe(input, result, tools, recipeId); 
		output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/hammering/")));
	}

}
