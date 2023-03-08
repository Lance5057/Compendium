package lance5057.compendium.core.workstations.hammeringstation;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.SingleInSingleOutPlusLoottable;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class HammeringStationRecipe extends SingleInSingleOutPlusLoottable {

	public HammeringStationRecipe(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn, ItemStack output,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {
		super(idIn, groupIn, recipeItemsIn, output, recipeToolsIn, loottable,
				WorkstationRecipes.HAMMERINGSTATION_RECIPE.get());
	}

}
