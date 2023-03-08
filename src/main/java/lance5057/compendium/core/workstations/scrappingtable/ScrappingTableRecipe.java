package lance5057.compendium.core.workstations.scrappingtable;
//package lance5057.compendium.core.workstations.recipes;
//
//import lance5057.compendium.core.recipes.RecipeItemUse;
//import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
//import net.minecraft.core.NonNullList;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.RecipeType;
//
//public class ScrappingTableRecipe extends MultiToolRecipe {
//
//    final ResourceLocation loottable;
//
//    public ScrappingTableRecipe(ResourceLocation id, String groupIn, ResourceLocation loottable, Ingredient ingredient,
//	    NonNullList<RecipeItemUse> recipeToolsIn) {
//	super(id, groupIn, 1, 1, NonNullList.withSize(1, ingredient), recipeToolsIn, null,
//		WorkstationRecipes.SCRAPPING_TABLE_RECIPE);
//	this.loottable = loottable;
//    }
////    (ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn,
////	    NonNullList<Ingredient> recipeItemsIn, int toolLength, NonNullList<RecipeItemUse> recipeToolsIn,
////	    ItemStack recipeOutputIn, RecipeType<?> type)
//
//    @Override
//    public RecipeType<?> getType() {
//	return WorkstationRecipes.SCRAPPING_TABLE_RECIPE;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//	return WorkstationRecipes.SCRAPPING_TABLE_SERIALIZER.get();
//    }
//
//    public ResourceLocation getLoottable() {
//	return loottable;
//    }
//
//    @Override
//    protected boolean checkMatch(WorkstationRecipeWrapper craftingInventory, int w, int h, boolean p_77573_4_) {
//	Ingredient ingredient = this.getRecipeItems().get(0);
//
//	if (ingredient.test(craftingInventory.getItem(0)))
//	    return true;
//
//	return false;
//    }
//
//}
