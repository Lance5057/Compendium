package lance5057.compendium.compat.jei.catagories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.core.util.recipes.RecipeUtil;
import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class SawhorseStationCatagory implements IRecipeCategory<SawhorseStationRecipe> {

    public static ResourceLocation UID = new ResourceLocation("compendium:saw_horse");

    public static final int width = 176;
    public static final int height = 193;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;

    public SawhorseStationCatagory(IGuiHelper guiHelper) {
	ResourceLocation location = new ResourceLocation("compendium:textures/gui/workstations_gui.png");
	background = guiHelper.createDrawable(location, 4, 4, 162, 93);
	icon = guiHelper.createDrawableIngredient(new ItemStack(CompendiumItems.SAWHORSE_STATION_ITEMBLOCK.get()));
	localizedName = I18n.format("compendium.jei.catagory.saw_horse");
    }

    @Override
    public ResourceLocation getUid() {
	return UID;
    }

    @Override
    public Class<? extends SawhorseStationRecipe> getRecipeClass() {
	return SawhorseStationRecipe.class;
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
    public void setIngredients(SawhorseStationRecipe recipe, IIngredients ingredients) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	World world = Minecraft.getInstance().world;
	if (!world.isRemote) {
	    LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(recipe.getOutput());
	    
	    //ForgeHooks.loadLootTable(LootTableManager.GSON_INSTANCE, new ResourceLocation(Reference.MOD_ID, "dummy"), (JsonObject) json, true, null));

//	    items.addAll(RecipeUtil.getStacksFromLootPool(loottable.getPool("main")));
//	    items.addAll(RecipeUtil.getStacksFromLootPool(loottable.getPool("extra")));
	}
	ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(recipe.getIngredient().getMatchingStacks()));
	ingredients.setOutputs(VanillaTypes.ITEM, items);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SawhorseStationRecipe recipe, IIngredients ingredients) {
	IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

	guiItemStacks.init(0, true, 138, 65);

	guiItemStacks.init(1, false, 138, 65);

	guiItemStacks.set(ingredients);
    }

}
