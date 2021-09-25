package lance5057.compendium.compat.jei;

import java.util.Arrays;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.compat.jei.catagories.CraftingAnvilCatagory;
import lance5057.compendium.compat.jei.catagories.SawhorseStationCatagory;
import lance5057.compendium.compat.jei.catagories.ScrappingTableCatagory;
import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class CompendiumJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
	return new ResourceLocation("compendium:jei");
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registration) {
	registration.addRecipeCategories(new CraftingAnvilCatagory(registration.getJeiHelpers().getGuiHelper()));
	
	registration.addRecipeCategories(new SawhorseStationCatagory(registration.getJeiHelpers().getGuiHelper()));
	registration.addRecipeCategories(new ScrappingTableCatagory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
	registration.addRecipes(Arrays.asList(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
		.filter(recipe -> recipe instanceof MultiToolRecipe)
		.map(recipe -> (MultiToolRecipe) recipe).toArray()), CraftingAnvilCatagory.UID);
	
	registration.addRecipes(Arrays.asList(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
		.filter(recipe -> recipe instanceof SawhorseStationRecipe)
		.map(recipe -> (SawhorseStationRecipe) recipe).toArray()), SawhorseStationCatagory.UID);
	
	registration.addRecipes(Arrays.asList(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
		.filter(recipe -> recipe instanceof ScrappingTableRecipe)
		.map(recipe -> (ScrappingTableRecipe) recipe).toArray()), ScrappingTableCatagory.UID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
	registration.addRecipeCatalyst(new ItemStack(CompendiumBlocks.CRAFTING_ANVIL.get()), CraftingAnvilCatagory.UID);
	
	registration.addRecipeCatalyst(new ItemStack(CompendiumBlocks.SAWHORSE_STATION.get()), SawhorseStationCatagory.UID);
	
	registration.addRecipeCatalyst(new ItemStack(CompendiumBlocks.SCRAPPING_TABLE.get()), ScrappingTableCatagory.UID);
    }
}
