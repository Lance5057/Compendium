package lance5057.compendium.compat.jei.catagories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ScrappingTableCatagory implements IRecipeCategory<ScrappingTableRecipe> {

    public static ResourceLocation UID = new ResourceLocation("compendium:scrapping_table");

    public static final int width = 176;
    public static final int height = 193;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;

    public ScrappingTableCatagory(IGuiHelper guiHelper) {
	ResourceLocation location = new ResourceLocation("compendium:textures/gui/workstations_gui.png");
	background = guiHelper.createDrawable(location, 0, 0, 162, 32); //TODO
	icon = guiHelper.createDrawableIngredient(new ItemStack(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()));
	localizedName = I18n.format("compendium.jei.catagory.scrapping_table");
    }

    @Override
    public ResourceLocation getUid() {
	return UID;
    }

    @Override
    public Class<? extends ScrappingTableRecipe> getRecipeClass() {
	return ScrappingTableRecipe.class;
    }

    @Override
    public String getTitle() {
	return localizedName;
    }

    @Override
    public IDrawable getBackground() {
	return background;
    }

    @Override
    public IDrawable getIcon() {
	return icon;
    }

    @Override
    public void setIngredients(ScrappingTableRecipe recipe, IIngredients ingredients) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(recipe.getIngredients().get(0).getMatchingStacks()));
	ingredients.setOutputs(VanillaTypes.ITEM, items);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ScrappingTableRecipe recipe, IIngredients ingredients) {
	IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

	guiItemStacks.init(0, true, 162/2-8, 8);
	
	guiItemStacks.set(ingredients);
    }

}
