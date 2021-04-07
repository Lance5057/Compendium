package lance5057.compendium.core.data.builders.workstationrecipes;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class AnvilShapedRecipeBuilder {
	private static final Logger LOGGER = LogManager.getLogger();
	private final Item result;
	private final int count;
	private final int strikes;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	private String group;

	public AnvilShapedRecipeBuilder(IItemProvider resultIn, int strikes, int countIn) {
		this.result = resultIn.asItem();
		this.count = countIn;
		this.strikes = strikes;
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static AnvilShapedRecipeBuilder shapedRecipe(IItemProvider resultIn, int strikes) {
		return shapedRecipe(resultIn, strikes, 1);
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static AnvilShapedRecipeBuilder shapedRecipe(IItemProvider resultIn, int strikes, int countIn) {
		return new AnvilShapedRecipeBuilder(resultIn, strikes, countIn);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public AnvilShapedRecipeBuilder key(Character symbol, ITag<Item> tagIn) {
		return this.key(symbol, Ingredient.fromTag(tagIn));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public AnvilShapedRecipeBuilder key(Character symbol, IItemProvider itemIn) {
		return this.key(symbol, Ingredient.fromItems(itemIn));
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
	public AnvilShapedRecipeBuilder patternLine(String patternIn) {
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
	public AnvilShapedRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
		this.advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}

	public AnvilShapedRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}

	/**
	 * Builds this recipe into an {@link IFinishedRecipe}.
	 */
	public void build(Consumer<IFinishedRecipe> consumerIn) {
		this.build(consumerIn, Registry.ITEM.getKey(this.result));
	}

	/**
	 * Builds this recipe into an {@link IFinishedRecipe}. Use
	 * {@link #build(Consumer)} if save is the same as the ID for the result.
	 */
	public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
		if ((new ResourceLocation(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, new ResourceLocation(Reference.MOD_ID, save));
		}
	}

	/**
	 * Builds this recipe into an {@link IFinishedRecipe}.
	 */
	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumerIn.accept(new CraftingAnvilRecipeProvider.AnvilResult(id, this.result, this.strikes, this.count, this.group == null ? "" : this.group, this.pattern, this.key, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + id.getPath())));
	}

	/**
	 * Makes sure that this recipe is valid and obtainable.
	 */
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
						throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
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
}
