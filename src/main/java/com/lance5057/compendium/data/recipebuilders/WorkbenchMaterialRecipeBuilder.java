package com.lance5057.compendium.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.util.SlotToMaterial;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.RecipeMobEffect;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;
import com.lance5057.compendium.workstations.workbench.WorkbenchMaterialRecipe;

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

public class WorkbenchMaterialRecipeBuilder implements RecipeBuilder {
	protected final Item result;
	protected final int count;
	protected final ItemStack resultStack; // Neo: add stack result support
	protected final List<String> rows = Lists.newArrayList();
	protected final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	protected final NonNullList<AnimatedRecipeItemUse> tools = NonNullList.create();
	@Nullable
	protected String group;

	private final NonNullList<SlotToMaterial> slotToMats = NonNullList.create();

	public WorkbenchMaterialRecipeBuilder(ItemLike result, int count) {
		this(new ItemStack(result, count));
	}

	public WorkbenchMaterialRecipeBuilder(ItemStack result) {
		this.result = result.getItem();
		this.count = result.getCount();
		this.resultStack = result;
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static WorkbenchMaterialRecipeBuilder shaped(ItemLike result) {
		return shaped(result, 1);
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static WorkbenchMaterialRecipeBuilder shaped(ItemLike result, int count) {
		return new WorkbenchMaterialRecipeBuilder(result, count);
	}

	public static WorkbenchMaterialRecipeBuilder shaped(ItemStack result) {
		return new WorkbenchMaterialRecipeBuilder(result);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchMaterialRecipeBuilder define(Character symbol, TagKey<Item> tag) {
		return this.define(symbol, Ingredient.of(tag));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchMaterialRecipeBuilder define(Character symbol, ItemLike item) {
		return this.define(symbol, Ingredient.of(item));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public WorkbenchMaterialRecipeBuilder define(Character symbol, Ingredient ingredient) {
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
	public WorkbenchMaterialRecipeBuilder pattern(String pattern) {
		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pattern);
			return this;
		}
	}

	public WorkbenchMaterialRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public WorkbenchMaterialRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	public WorkbenchMaterialRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage,
			ResourceKey<LootTable> table, List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public WorkbenchMaterialRecipeBuilder tool(Ingredient tool, int uses, boolean damage, ResourceKey<LootTable> table,
			List<RecipeMobEffect> effect, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, 1, damage, table.location(), effect, List.of(model)));
		return this;
	}

	public WorkbenchMaterialRecipeBuilder slotToMat(SlotToMaterial sm) {
		this.slotToMats.add(sm);
		return this;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		ResourceLocation recipeId = id.withPrefix("workbench/");
		MultiToolRecipeShapedPattern shapedrecipepattern = MultiToolRecipeShapedPattern.of(this.key, this.rows);
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		WorkbenchMaterialRecipe shapedrecipe = new WorkbenchMaterialRecipe(shapedrecipepattern, slotToMats, tools,
				this.resultStack);
		recipeOutput.accept(recipeId, shapedrecipe, advancement$builder.build(id.withPrefix("recipes/workbench/")));
	}
}
