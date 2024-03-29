package lance5057.compendium.core.data.builders.workstationrecipes.builders;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.data.builders.workstationrecipes.CraftingAnvilRecipeProvider;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class AnvilShapedRecipeBuilder implements RecipeBuilder {
	// private static final Logger LOGGER = LogManager.getLogger();
	private final Item result;
	private final int count;
	private final Item schematic;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final List<AnimatedRecipeItemUse> tools = NonNullList.create();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
	private String group;

	public AnvilShapedRecipeBuilder(Item resultIn, int countIn, Item schematicIn) {
		this.result = resultIn;
		this.count = countIn;
		if (schematicIn != null)
			this.schematic = schematicIn;
		else
			schematic = null;
	}

	public static AnvilShapedRecipeBuilder shapedRecipe(Item resultIn) {
		return shapedRecipe(resultIn, 1, null);
	}

	public static AnvilShapedRecipeBuilder shapedRecipe(Item resultIn, Item schematicIn) {
		return shapedRecipe(resultIn, 1, schematicIn);
	}

	public static AnvilShapedRecipeBuilder shapedRecipe(Item resultIn, int countIn) {
		return new AnvilShapedRecipeBuilder(resultIn, countIn, null);
	}

	public static AnvilShapedRecipeBuilder shapedRecipe(Item resultIn, int countIn, Item schematicIn) {
		return new AnvilShapedRecipeBuilder(resultIn, countIn, schematicIn);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public AnvilShapedRecipeBuilder key(Character symbol, TagKey<Item> tagIn) {
		return this.key(symbol, Ingredient.of(tagIn));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public AnvilShapedRecipeBuilder key(Character symbol, ItemLike itemIn) {
		return this.key(symbol, Ingredient.of(itemIn));
	}

	public AnvilShapedRecipeBuilder tool(Ingredient tool, int count, int uses, boolean damage,
			BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, count, damage, model));
		return this;
	}

	public AnvilShapedRecipeBuilder tool(Ingredient tool, int uses, boolean damage, BlacklistedModel... model) {
		this.tools.add(new AnimatedRecipeItemUse(uses, tool, 1, damage, model));
		return this;
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public AnvilShapedRecipeBuilder key(Character symbol, Ingredient ingredientIn) {
		if (this.key.containsKey(symbol)) {
			throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
		} else if (symbol == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(symbol, ingredientIn);
			return this;
		}
	}

	/**
	 * Adds a new entry to the patterns for this recipe.
	 */
	public AnvilShapedRecipeBuilder pattern(String patternIn) {
		if (!this.pattern.isEmpty() && patternIn.length() != this.pattern.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.pattern.add(patternIn);
			return this;
		}
	}

	/**
	 * Adds a criterion needed to unlock the recipe.
	 */
	public AnvilShapedRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
		this.advancementBuilder.addCriterion(name, criterionIn);
		return this;
	}

	public AnvilShapedRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}

	private void validate(ResourceLocation id) {
		if (this.pattern.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
		} else {
			Set<Character> set = Sets.newHashSet(this.key.keySet());
			set.remove(' ');

			for (String s : this.pattern) {
				for (int i = 0; i < s.length(); ++i) {
					char c0 = s.charAt(i);
					if (!this.key.containsKey(c0) && c0 != ' ') {
						throw new IllegalStateException(
								"Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
					}

					set.remove(c0);
				}
			}

			if (!set.isEmpty()) {
				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
//			} else if (this.pattern.size() == 1 && this.pattern.get(0).length() == 1) {
//				throw new IllegalStateException("Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
			} else if (this.advancementBuilder.getCriteria().isEmpty()) {
				throw new IllegalStateException("No way of obtaining recipe " + id);
			}
		}
	}

	@Override
	public RecipeBuilder unlockedBy(String p_176496_, CriterionTriggerInstance p_176497_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeBuilder group(String p_176495_) {
		this.group = p_176495_;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	@Override
	public void save(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		this.advancementBuilder.parent(new ResourceLocation("recipes/root"))
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
		consumerIn.accept(new CraftingAnvilRecipeProvider.AnvilResult(id, this.result, this.count, this.schematic,
				this.group == null ? "" : this.group, this.pattern, this.key, this.tools, this.advancementBuilder,
				new ResourceLocation(id.getNamespace(),
						"recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
	}
}
