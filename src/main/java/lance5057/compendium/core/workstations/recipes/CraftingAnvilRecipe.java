package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CraftingAnvilRecipe extends MultiToolRecipe {

    private final ItemStack schematic;

    public CraftingAnvilRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn,
	    NonNullList<Ingredient> recipeItemsIn, NonNullList<RecipeItemUse> recipeToolsIn,
	    ItemStack schematicIn, ItemStack recipeOutputIn) {
	super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeToolsIn, recipeOutputIn,
		WorkstationRecipes.CRAFTING_ANVIL_RECIPE);

	this.schematic = schematicIn;
    }

    public ItemStack getSchematic() {
	return schematic;
    }

    protected boolean schematicMatch(WorkstationRecipeWrapper inv, World worldIn) {
	return this.getSchematic().equals(inv.getStackInSlot(26), true);
    }

    @Override
    public boolean matches(WorkstationRecipeWrapper inv, World worldIn) {
	for (int i = 0; i <= 5 - this.getRecipeWidth(); ++i) {
	    for (int j = 0; j <= 5 - this.getHeight(); ++j) {
		if (this.checkMatch(inv, i, j, true)) {
		    return this.schematicMatch(inv, worldIn);
		}

		if (this.checkMatch(inv, i, j, false)) {
		    return this.schematicMatch(inv, worldIn);
		}
	    }
	}

	return false;
    }
    
    @Override
    protected boolean checkMatch(WorkstationRecipeWrapper craftingInventory, int width, int height, boolean p_77573_4_) {
	for (int i = 0; i < 5; ++i) {
	    for (int j = 0; j < 5; ++j) {
		int k = i - width;
		int l = j - height;
		Ingredient ingredient = Ingredient.EMPTY;
		if (k >= 0 && l >= 0 && k < this.getRecipeWidth() && l < this.getRecipeHeight()) {
		    if (p_77573_4_) {
			ingredient = this.getRecipeItems().get(this.getRecipeWidth() - k - 1 + l * this.getRecipeWidth());
		    } else {
			ingredient = this.getRecipeItems().get(k + l * this.getRecipeWidth());
		    }
		}

		if (!ingredient.test(craftingInventory.getStackInSlot(i + j * 5))) {
		    return false;
		}
	    }
	}

	return true;
    }
}
