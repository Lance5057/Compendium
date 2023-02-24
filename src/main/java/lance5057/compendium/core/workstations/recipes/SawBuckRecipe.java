package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipeSingle;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SawBuckRecipe extends MultiToolRecipeSingle {

	private final ResourceLocation loot;

	public SawBuckRecipe(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {

		super(idIn, groupIn, recipeItemsIn, recipeToolsIn, WorkstationRecipes.SAWBUCK_RECIPE.get());

		this.loot = loottable;
	}

	public ResourceLocation getLootTable() {
		return loot;
	}
}
