package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ScrappingTableRecipe extends MultiToolRecipe {

    final ResourceLocation loottable;

    public ScrappingTableRecipe(ResourceLocation id, String groupIn, ResourceLocation loottable, Ingredient ingredient,
	    NonNullList<RecipeItemUse> recipeToolsIn) {
	super(id, groupIn, 1, 1, NonNullList.withSize(1, ingredient), recipeToolsIn, null,
		WorkstationRecipes.SCRAPPING_TABLE_RECIPE);
	this.loottable = loottable;
    }
//    (ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn,
//	    NonNullList<Ingredient> recipeItemsIn, int toolLength, NonNullList<RecipeItemUse> recipeToolsIn,
//	    ItemStack recipeOutputIn, IRecipeType<?> type)

    @Override
    public IRecipeType<?> getType() {
	return WorkstationRecipes.SCRAPPING_TABLE_RECIPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
	return WorkstationRecipes.SCRAPPING_TABLE_SERIALIZER.get();
    }

    public ResourceLocation getLoottable() {
	return loottable;
    }

    @Override
    protected boolean checkMatch(WorkstationRecipeWrapper craftingInventory, int w, int h, boolean p_77573_4_) {
	Ingredient ingredient = this.getRecipeItems().get(0);

	if (ingredient.test(craftingInventory.getStackInSlot(0)))
	    return true;

	return false;
    }

}
