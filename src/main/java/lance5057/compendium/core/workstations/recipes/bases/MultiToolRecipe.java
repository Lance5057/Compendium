package lance5057.compendium.core.workstations.recipes.bases;

import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
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
	public ItemStack getResultItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
