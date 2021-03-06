package lance5057.compendium.compat.jei.catagories;

import com.mojang.blaze3d.matrix.MatrixStack;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.util.recipes.RecipeUtil;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CraftingAnvilCatagory implements IRecipeCategory<CraftingAnvilRecipe> {

    public static ResourceLocation UID = new ResourceLocation("compendium:crafting_anvil");

    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    public static final int width = 176;
    public static final int height = 193;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;

    public CraftingAnvilCatagory(IGuiHelper guiHelper) {
	ResourceLocation location = new ResourceLocation("compendium:textures/gui/crafting_anvil.png");
	background = guiHelper.createDrawable(location, 4, 4, 162, 93);
	icon = guiHelper.createDrawableIngredient(new ItemStack(CompendiumItems.CRAFTING_ANVIL_ITEMBLOCK.get()));
	localizedName = I18n.format("compendium.jei.catagory.crafting_anvil");
    }

    @Override
    public ResourceLocation getUid() {
	return UID;
    }

    @Override
    public Class<? extends CraftingAnvilRecipe> getRecipeClass() {
	return CraftingAnvilRecipe.class;
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
    public void setIngredients(CraftingAnvilRecipe recipe, IIngredients ingredients) {
	ingredients.setInputLists(VanillaTypes.ITEM, RecipeUtil.getMatchingStacks(recipe.getIngredients()));
	ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CraftingAnvilRecipe recipe, IIngredients ingredients) {
	IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

	for (int i = 0; i < recipe.getRecipeHeight(); ++i) {
	    for (int j = 0; j < recipe.getRecipeWidth(); ++j) {
		int x = i * recipe.getRecipeWidth() + j;
		guiItemStacks.init(x, true, 8 + j * 18, 3 + i * 18);
	    }
	}

	guiItemStacks.init(25, false, 138, 65);

	guiItemStacks.set(ingredients);
    }

//    @Override
//    public void draw(CraftingAnvilRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
//	FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
//	fontRenderer.drawString(matrixStack, "" + recipe.getStrikes(), 118f, 80f, 0x0000000);
//    }

}
