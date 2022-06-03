//package lance5057.compendium.core.workstations.recipes;
//
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.Container;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.Recipe;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.RecipeType;
//import net.minecraft.world.level.Level;
//
//public class HammeringStationRecipe implements Recipe<Container>{
//
//    private final ResourceLocation id;
//    private final Ingredient ingredient;
//    private final ItemStack output;
//    private final int strikes;
//    
//    public HammeringStationRecipe(ResourceLocation id, int strike, ItemStack result, Ingredient ingredient) {
//        this.id = id;
//        this.ingredient = ingredient;
//        this.output = result;
//        this.strikes = strike;
//    }
//    
//	@Override
//	public boolean matches(Container inv, Level worldIn) {
//		return false;
//	}
//
//	@Override
//	public ItemStack assemble(Container inv) {
//		return null;
//	}
//
//	@Override
//	public boolean canCraftInDimensions(int width, int height) {
//		return false;
//	}
//
//	@Override
//	public ItemStack getResultItem() {
//		return output;
//	}
//
//	@Override
//	public ResourceLocation getId() {
//		return id;
//	}
//
//	@Override
//	public RecipeType<?> getType() {
//		return WorkstationRecipes.HAMMERING_STATION_RECIPE;
//	}
//
//	@Override
//	public RecipeSerializer<?> getSerializer() {
//		return WorkstationRecipes.HAMMERING_STATION_SERIALIZER.get();
//	}
//
//	public Ingredient getIngredient() {
//		return this.ingredient;
//	}
//	
//	public int getStrikes() {
//		return this.strikes;
//	}
//
//	public boolean matches(ItemStack stackInSlot) {
//		return getIngredient().test(stackInSlot);
//	}
//
//}
