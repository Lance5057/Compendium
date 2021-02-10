package lance5057.compendium.core.recipes;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class InWorldHandedToolRecipe implements IRecipe<HandedToolWrapper> {
    private final ResourceLocation id;
    private final Ingredient offhand;
    private final ItemStack block;
    private final ItemStack output;

    public InWorldHandedToolRecipe(ResourceLocation id, ItemStack block, Ingredient offhand, ItemStack output) {
	this.id = id;
	this.offhand = offhand;
	this.block = block;
	this.output = output;
    }

    @Override
    public boolean matches(HandedToolWrapper inv, World worldIn) {
	if(inv.block.equals(block, true))
	    if(this.offhand.test(inv.offhand))
		return true;
	return false;
    }

    @Override
    public ItemStack getCraftingResult(HandedToolWrapper inv) {
	return this.getRecipeOutput();
    }

    @Override
    public boolean canFit(int width, int height) {
	return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
	return output.copy();
    }

    @Override
    public ResourceLocation getId() {
	return id;
    }
    
    public Ingredient getIngredient()
    {
	return this.offhand;
    }
    
    public ItemStack getBlock()
    {
	return this.block;
    }
}
