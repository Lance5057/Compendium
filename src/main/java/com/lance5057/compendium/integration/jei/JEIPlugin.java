package com.lance5057.compendium.integration.jei;

import org.jetbrains.annotations.NotNull;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.integration.jei.categories.HammeringStationRecipeCategory;
import com.lance5057.compendium.integration.jei.categories.SawBuckRecipeCategory;
import com.lance5057.compendium.workstations.WorkstationRecipes;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
	private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "main");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new HammeringStationRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new SawBuckRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registry) {
		registry.addRecipes(HammeringStationRecipeCategory.TYPE,
				Minecraft.getInstance().level.getRecipeManager()
						.getAllRecipesFor(WorkstationRecipes.HAMMERINGSTATION_RECIPE.get()).stream()
						.map(RecipeHolder::value).toList());
		registry.addRecipes(SawBuckRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(WorkstationRecipes.SAWBUCK_RECIPE.get()).stream().map(RecipeHolder::value).toList());

	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addRecipeCatalyst(new ItemStack(CompendiumItems.HAMMERING_STATION.get()),
				HammeringStationRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(CompendiumItems.SAW_BUCK.get()), SawBuckRecipeCategory.TYPE);
	}

}
