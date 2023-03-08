package lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe;

import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;

public class SingleInSingleOutPlusLoottable extends MultiToolRecipeSingleItemIn {

	private final ResourceLocation loot;
	private final ItemStack output;

	public SingleInSingleOutPlusLoottable(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn,
			ItemStack output, NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable,
			RecipeType<?> typeIn) {

		super(idIn, groupIn, recipeItemsIn, recipeToolsIn, typeIn);

		this.loot = loottable;
		this.output = output;
	}

	public ResourceLocation getLootTable() {
		return loot;
	}

	public ItemStack getOutput() {
		return output;
	}

}
