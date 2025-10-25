package com.lance5057.compendium.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.RecipeMobEffect;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRecipe;

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
import net.minecraft.world.phys.Vec3;

public class SawBuckRecipeBuilder implements RecipeBuilder {
	private final Ingredient input;
	private final ItemStack result;
	private final Vec3 offset;
	private final NonNullList<AnimatedRecipeItemUse> tools = NonNullList.create();

	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public SawBuckRecipeBuilder(Ingredient in, ItemStack out, Vec3 offset) {
		this.input = in;
		this.result = out;
		this.offset = offset;
	}

	public static SawBuckRecipeBuilder saw(Ingredient input, ItemStack result, Vec3 offset) {
		return new SawBuckRecipeBuilder(input, result, offset);
	}

	public SawBuckRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
		this.criteria.put(criterionName, criterionTrigger);
		return this;
	}

	public SawBuckRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
		return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
	}

	public SawBuckRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
		this.criteria.put("has_any_ingredient",
				InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build()));
		return this;
	}

	public SawBuckRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage, ResourceKey<LootTable> table,
			List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public SawBuckRecipeBuilder tool(Ingredient tool, int uses, boolean damage, ResourceKey<LootTable> table,
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
		ResourceLocation recipeId = id.withPrefix("sawing/");
		Advancement.Builder advancementBuilder = output.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancementBuilder::addCriterion);

		SawBuckRecipe recipe = new SawBuckRecipe(input, result, tools, recipeId, offset);
		output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/sawing/")));
	}

}