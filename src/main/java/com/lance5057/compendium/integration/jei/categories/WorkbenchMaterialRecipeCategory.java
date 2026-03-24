package com.lance5057.compendium.integration.jei.categories;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.lance5057.compendium.workstations.workbench.WorkbenchRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.items.ItemStackHandler;

public class WorkbenchMaterialRecipeCategory implements IRecipeCategory<WorkbenchRecipe> {

	public static final RecipeType<WorkbenchRecipe> TYPE = RecipeType.create(Compendium.MOD_ID, "workbench_material",
			WorkbenchRecipe.class);
	private final IDrawable background;
	private final Component localizedName;
	private final IDrawable icon;

	public WorkbenchMaterialRecipeCategory(IGuiHelper guiHelper) {
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
		for (Ingredient i : recipe.pattern.ingredients()) {
			builder.addSlot(RecipeIngredientRole.INPUT, 91 + ((count % 3) * 18), 1 + (int) (count / 3) * 18)
					.addIngredients(i);
			count++;
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 73).addItemStack(recipe.getItemOut());

		count = 0;
		for (AnimatedRecipeItemUse aru : recipe.getTools()) {
			builder.addSlot(RecipeIngredientRole.CATALYST, 1 + ((count % 4) * 18), 1 + (int) (count / 4) * 18)
					.addIngredients(aru.tool());
			count++;
		}
	}

	@Override
	public void onDisplayedIngredientsUpdate(WorkbenchRecipe recipe, List<IRecipeSlotDrawable> recipeSlots,
			IFocusGroup focuses) {
		NonNullList<ItemStack> out = NonNullList.create();

		for (int i = 0; i < recipe.pattern.ingredients().size() - 1; i++)
			if (!recipeSlots.get(i).isEmpty())
				out.add(recipeSlots.get(i).getDisplayedItemStack().get());
			else
				out.add(ItemStack.EMPTY);

		out.add(recipe.getItemOut());

		recipeSlots.get(recipe.pattern.ingredients().size()).createDisplayOverrides()
				.addItemStack(recipe.assemble(MultiToolRecipeWrapper.of(new ItemStackHandler(out)), null));
	}

}
