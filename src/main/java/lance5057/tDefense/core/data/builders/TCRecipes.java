package lance5057.tDefense.core.data.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;

public class TCRecipes extends RecipeProvider {

	public static List<Pair<ShapelessRecipeBuilder, String>> shapeless = new ArrayList<Pair<ShapelessRecipeBuilder, String>>();
	public static List<Pair<ShapedRecipeBuilder, String>> shaped = new ArrayList<Pair<ShapedRecipeBuilder, String>>();
	public static List<Pair<CookingRecipeBuilder, String>> furnace = new ArrayList<Pair<CookingRecipeBuilder, String>>();

	public TCRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
		for (Pair<ShapelessRecipeBuilder, String> recipe : shapeless) {
			recipe.getKey().addCriterion("", this.enteredBlock(Blocks.AIR)).build(consumer, recipe.getValue());
		}
		for (Pair<ShapedRecipeBuilder, String> recipe : shaped) {
			recipe.getKey().addCriterion("", this.enteredBlock(Blocks.AIR)).build(consumer, recipe.getValue());
		}
		for (Pair<CookingRecipeBuilder, String> recipe : furnace) {
			recipe.getKey().addCriterion("", this.enteredBlock(Blocks.AIR)).build(consumer, recipe.getValue());
		}
	}

}
