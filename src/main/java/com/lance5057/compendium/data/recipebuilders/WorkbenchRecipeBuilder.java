package com.lance5057.compendium.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.RecipeMobEffect;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;
import com.lance5057.compendium.workstations.workbench.WorkbenchRecipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootTable;

public class WorkbenchRecipeBuilder implements RecipeBuilder {
	private final Item result;
	private final int count;
	private final ItemStack resultStack; // Neo: add stack result support
	private final List<String> rows = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	private final NonNullList<AnimatedRecipeItemUse> tools = NonNullList.create();
	@Nullable
	private String group;

	public WorkbenchRecipeBuilder(ItemLike result, int count) {
		this(new ItemStack(result, count));
	}

	public WorkbenchRecipeBuilder(ItemStack result) {
		this.result = result.getItem();
		this.count = result.getCount();
		this.resultStack = result;
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static WorkbenchRecipeBuilder shaped(ItemLike result) {
		return shaped(result, 1);
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static WorkbenchRecipeBuilder shaped(ItemLike result, int count) {
		return new WorkbenchRecipeBuilder(result, count);
	}

	public static WorkbenchRecipeBuilder shaped(ItemStack result) {
		return new WorkbenchRecipeBuilder(result);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchRecipeBuilder define(Character symbol, TagKey<Item> tag) {
		return this.define(symbol, Ingredient.of(tag));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchRecipeBuilder define(Character symbol, ItemLike item) {
		return this.define(symbol, Ingredient.of(item));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchRecipeBuilder define(Character symbol, Ingredient ingredient) {
		if (this.key.containsKey(symbol)) {
			throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
		} else if (symbol == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(symbol, ingredient);
			return this;
		}
	}

	/**
	 * Adds a new entry to the patterns for this recipe.
	 */
	public WorkbenchRecipeBuilder pattern(String pattern) {
		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pattern);
			return this;
		}
	}

	public WorkbenchRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public WorkbenchRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	public WorkbenchRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage,
			ResourceKey<LootTable> table, List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public WorkbenchRecipeBuilder tool(Ingredient tool, int uses, boolean damage, ResourceKey<LootTable> table,
			List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, 1, damage, table.location(), effect, List.of(model)));
		return this;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		MultiToolRecipeShapedPattern shapedrecipepattern = MultiToolRecipeShapedPattern.of(this.key, this.rows);
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		WorkbenchRecipe shapedrecipe = new WorkbenchRecipe(shapedrecipepattern, tools, this.resultStack);
		recipeOutput.accept(id, shapedrecipe, advancement$builder.build(id.withPrefix("recipes/workbench/")));
	}
}
