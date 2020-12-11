package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CraftingAnvilRecipe implements IRecipe<IInventory>{

	private final IRecipeType<?> type;
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack output;
    private final int strikes;
    
    public CraftingAnvilRecipe(IRecipeType<?> type, ResourceLocation id, int strike, ItemStack result, Ingredient ingredient) {
        this.type = type;
        this.id = id;
        this.ingredient = ingredient;
        this.output = result;
        this.strikes = strike;
    }
    
	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return null;
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeType<?> getType() {
		return type;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return WorkstationRecipes.HAMMERING_STATION_SERIALIZER.get();
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}
	
	public int getStrikes() {
		return this.strikes;
	}

	public boolean matches(ItemStack stackInSlot) {
		return getIngredient().test(stackInSlot);
	}

}
