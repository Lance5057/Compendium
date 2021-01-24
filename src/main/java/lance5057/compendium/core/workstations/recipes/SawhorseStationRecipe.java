package lance5057.compendium.core.workstations.recipes;

import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SawhorseStationRecipe implements IRecipe<IInventory>{

	private final IRecipeType<?> type;
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ResourceLocation loottable;
    private final int strikes;
    
    public SawhorseStationRecipe(IRecipeType<?> type, ResourceLocation id, int strike, ResourceLocation loottable, Ingredient ingredient) {
        this.type = type;
        this.id = id;
        this.ingredient = ingredient;
        this.loottable = loottable;
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
		return ItemStack.EMPTY;
	}
	
	public ResourceLocation getOutput() {
		return loottable;
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
		return WorkstationRecipes.SAWHORSE_STATION_SERIALIZER.get();
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
