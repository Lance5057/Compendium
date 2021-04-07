package lance5057.compendium.compat.jei;

import java.util.Arrays;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.compat.jei.catagories.CraftingAnvilCatagory;
import lance5057.compendium.compat.jei.catagories.SawhorseStationCatagory;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
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
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
	registration.addRecipes(Arrays.asList(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
		.filter(recipe -> recipe instanceof CraftingAnvilRecipe)
		.map(recipe -> (CraftingAnvilRecipe) recipe).toArray()), CraftingAnvilCatagory.UID);
	
	registration.addRecipes(Arrays.asList(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
		.filter(recipe -> recipe instanceof SawhorseStationRecipe)
		.map(recipe -> (SawhorseStationRecipe) recipe).toArray()), SawhorseStationCatagory.UID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
	registration.addRecipeCatalyst(new ItemStack(CompendiumBlocks.CRAFTING_ANVIL.get()), CraftingAnvilCatagory.UID);
	
	registration.addRecipeCatalyst(new ItemStack(CompendiumBlocks.SAWHORSE_STATION.get()), SawhorseStationCatagory.UID);
    }
}
