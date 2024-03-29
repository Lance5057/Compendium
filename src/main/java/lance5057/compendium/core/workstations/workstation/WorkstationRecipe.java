package lance5057.compendium.core.workstations.workstation;

import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShaped;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class WorkstationRecipe extends MultiToolRecipeShaped {

	private final ItemStack schematic;

	public WorkstationRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn,
			NonNullList<Ingredient> recipeItemsIn, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ItemStack schematicIn, ItemStack recipeOutputIn) {
		super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeToolsIn, recipeOutputIn,
				WorkstationRecipes.WORKSTATION_RECIPE.get());

		this.schematic = schematicIn;
	}

	public ItemStack getSchematic() {
		return schematic;
	}

	protected boolean schematicMatch(WorkstationRecipeWrapper inv, Level worldIn) {
		return this.getSchematic().equals(inv.getItem(26), true);
	}

	@Override
	public boolean matches(WorkstationRecipeWrapper inv, Level worldIn) {
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
	protected boolean checkMatch(WorkstationRecipeWrapper craftingInventory, int width, int height,
			boolean p_77573_4_) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				int k = i - width;
				int l = j - height;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.getRecipeWidth() && l < this.getRecipeHeight()) {
					if (p_77573_4_) {
						ingredient = this.getRecipeItems()
								.get(this.getRecipeWidth() - k - 1 + l * this.getRecipeWidth());
					} else {
						ingredient = this.getRecipeItems().get(k + l * this.getRecipeWidth());
					}
				}

				if (!ingredient.test(craftingInventory.getItem(i + j * 5))) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public RecipeType<?> getType() {
		return WorkstationRecipes.WORKSTATION_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WorkstationRecipes.WORKSTATION_SERIALIZER.get();
	}
}