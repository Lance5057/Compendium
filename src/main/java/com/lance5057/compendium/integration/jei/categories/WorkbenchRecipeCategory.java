package com.lance5057.compendium.integration.jei.categories;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRecipe;
import com.lance5057.compendium.workstations.workbench.WorkbenchRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class WorkbenchRecipeCategory implements IRecipeCategory<WorkbenchRecipe> {

	public static final RecipeType<WorkbenchRecipe> TYPE = RecipeType.create(Compendium.MOD_ID, "workbench",
			WorkbenchRecipe.class);
	private final IDrawable background;
	private final Component localizedName;
	private final IDrawable icon;

	public WorkbenchRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "textures/gui/jei.png"), 0, 130, 162, 126);
		localizedName = Component.translatable("compendium.jei.workbench");
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(CompendiumItems.WORKBENCH.get()));
	}

	@Override
	public RecipeType<WorkbenchRecipe> getRecipeType() {
		return TYPE;
	}

	@Override
	public Component getTitle() {
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
	public void setRecipe(IRecipeLayoutBuilder builder, WorkbenchRecipe recipe, IFocusGroup focuses) {

		int count = 0;
		for (Ingredient i : recipe.pattern.ingredients())
		{
			builder.addSlot(RecipeIngredientRole.INPUT, 91 + ((count % 3) * 18), 1 + (int)(count/3)*18).addIngredients(i);
			count++;
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 73).addIngredients(Ingredient.of(recipe.getItemOut()));

		count = 0;
		for (AnimatedRecipeItemUse aru : recipe.getTools()) {
			builder.addSlot(RecipeIngredientRole.CATALYST, 1 + ((count % 3) * 18), 1+ (int)(count/3)*18).addIngredients(aru.tool());
			count++;
		}
	}

}
