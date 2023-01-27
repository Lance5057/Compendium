package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipeShaped;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;

public class SawBuckRecipe extends MultiToolRecipeShaped {

	public SawBuckRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn,
			NonNullList<Ingredient> recipeItemsIn, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ItemStack recipeOutputIn, RecipeType<?> type) {
		super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeToolsIn, recipeOutputIn, type);
		// TODO Auto-generated constructor stub
	}

}
