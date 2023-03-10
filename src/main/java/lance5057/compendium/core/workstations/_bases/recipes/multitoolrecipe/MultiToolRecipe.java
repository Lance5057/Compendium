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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.IShapedRecipe;

public abstract class MultiToolRecipe implements IShapedRecipe<WorkstationRecipeWrapper> {

	private final RecipeType<?> type;
	protected final NonNullList<AnimatedRecipeItemUse> recipeTools;
	private final ResourceLocation id;
	private final String group;

	public MultiToolRecipe(ResourceLocation idIn, String groupIn, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			RecipeType<?> type) {
		this.recipeTools = recipeToolsIn;
		this.type = type;
		this.id = idIn;
		this.group = groupIn;
	}

	public NonNullList<AnimatedRecipeItemUse> getToolList() {
		return this.getRecipeTools();
	}

	public int getToolListLength() {
		return recipeTools.size();
	}

	public NonNullList<AnimatedRecipeItemUse> getRecipeTools() {
		return recipeTools;
	}

	@Override
	public RecipeType<?> getType() {
		return type;
	}

	public ResourceLocation getId() {
		return this.id;
	}

	public String getGroup() {
		return this.group;
	}

	@Override
	public ItemStack assemble(WorkstationRecipeWrapper p_44001_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		// TODO Auto-generated method stub
		return false;
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

}
