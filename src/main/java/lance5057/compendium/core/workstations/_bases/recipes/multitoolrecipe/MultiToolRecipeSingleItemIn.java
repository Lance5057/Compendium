package lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class MultiToolRecipeSingleItemIn extends MultiToolRecipe {

	private final Ingredient recipeItem;

	public MultiToolRecipeSingleItemIn(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, RecipeType<?> type) {
		super(idIn, groupIn, recipeToolsIn, type);

		this.recipeItem = recipeItemsIn;

	}

	/**
	 * Get the result of this recipe, usually for display purposes (e.g. recipe
	 * book). If your recipe has more than one possible result (e.g. it's dynamic
	 * and depends on its inputs), then return an empty stack.
	 */
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(WorkstationRecipeWrapper inv, Level worldIn) {
		return this.recipeItem.test(inv.getItem(0));
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(WorkstationRecipeWrapper inv) {
		return this.getRecipeOutput().copy();
	}

	public static NonNullList<AnimatedRecipeItemUse> deserializeTool(JsonObject json) {
		NonNullList<AnimatedRecipeItemUse> map = NonNullList.create();

		for (Entry<String, JsonElement> entry : json.entrySet()) {

			AnimatedRecipeItemUse r = AnimatedRecipeItemUse.read(entry.getValue().getAsJsonObject());

			map.add(r);
		}

		// map.put(" ", AnimatedRecipeItemUse.EMPTY);
		return map;
	}

	public static ItemStack deserializeItem(JsonObject object) {
		String s = GsonHelper.convertToString(object, "item");
		Item item = Registry.ITEM.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
			return new JsonSyntaxException("Unknown item '" + s + "'");
		});
		if (object.has("data")) {
			throw new JsonParseException("Disallowed data tag found");
		} else {
			int i = GsonHelper.getAsInt(object, "count", 1);
			return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(object, true);
		}
	}

	public Ingredient getRecipeItem() {
		return recipeItem;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	@Override
	public ItemStack assemble(WorkstationRecipeWrapper p_77572_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getResultItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRecipeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecipeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
