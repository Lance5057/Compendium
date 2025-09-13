package com.lance5057.compendium.integration.jei.categories;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRecipe;

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

public class SawBuckRecipeCategory implements IRecipeCategory<SawBuckRecipe> {

	public static final RecipeType<SawBuckRecipe> TYPE = RecipeType.create(Compendium.MOD_ID, "sawbuck",
			SawBuckRecipe.class);
	private final IDrawable background;
	private final Component localizedName;
	private final IDrawable icon;

	public SawBuckRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "textures/gui/jei.png"), 0, 0, 162, 90);
		localizedName = Component.translatable("compendium.jei.sawbuck");
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(CompendiumItems.HAMMERING_STATION.get()));
	}

	@Override
	public RecipeType<SawBuckRecipe> getRecipeType() {
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
	public void setRecipe(IRecipeLayoutBuilder builder, SawBuckRecipe recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 50, 37).addIngredients(recipe.getItemIn());

		builder.addSlot(RecipeIngredientRole.OUTPUT, 1, 73).addIngredients(Ingredient.of(recipe.getItemOut()));

		int count = 0;
		for (AnimatedRecipeItemUse aru : recipe.getTools()) {
			builder.addSlot(RecipeIngredientRole.CATALYST, 1 + (count * 18), 1).addIngredients(aru.tool());
			count++;
		}
	}

}