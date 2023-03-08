package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.bases.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipeSingle;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class SawBuckRecipe extends MultiToolRecipeSingle {

	private final ResourceLocation loot;
	private final ItemStack output;

	public SawBuckRecipe(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn, ItemStack output,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {

		super(idIn, groupIn, recipeItemsIn, recipeToolsIn, WorkstationRecipes.SAWBUCK_RECIPE.get());

		this.loot = loottable;
		this.output = output;
	}

	public ResourceLocation getLootTable() {
		return loot;
	}
	
	public ItemStack getOutput() {
		return output;
	}
}
