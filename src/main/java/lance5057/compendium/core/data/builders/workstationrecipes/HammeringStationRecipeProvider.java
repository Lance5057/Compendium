//package lance5057.compendium.core.data.builders.workstationrecipes;
//
//import java.util.function.Consumer;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.appendixes.metallurgy.data.builders.MetalRecipes;
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.data.recipes.RecipeProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraftforge.common.Tags.Items;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public class HammeringStationRecipeProvider extends RecipeProvider {
//	public HammeringStationRecipeProvider(DataGenerator generatorIn) {
//		super(generatorIn);
//	}
//
//	@Override
//	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
//		// From Tag
//		HammeringStationRecipeProvider.createRecipe("stone_to_cobble", new ItemStack(Blocks.COBBLESTONE), Ingredient.of(Items.STONE), consumer, 2);
//		HammeringStationRecipeProvider.createRecipe("cobble_to_gravel", new ItemStack(Blocks.GRAVEL), Ingredient.of(Blocks.COBBLESTONE), consumer, 2);
//		HammeringStationRecipeProvider.createRecipe("gravel_to_sand", new ItemStack(Blocks.SAND), Ingredient.of(Items.GRAVEL), consumer, 2);
//		HammeringStationRecipeProvider.createRecipe("deadcoral_to_soulsand", new ItemStack(Blocks.SOUL_SAND), Ingredient.of(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK), consumer);
//
//		MetalRecipes.hammeringBuild(this, consumer);
//	}
//
//	public static void createRecipe(String name, ItemStack output, Ingredient input, Consumer<FinishedRecipe> consumer) {
//		consumer.accept(new HammeringStationFinishedRecipe(new ResourceLocation(Reference.MOD_ID, "hammeringstation/" + name), output, input));
//	}
//
//	public static void createRecipe(String name, ItemStack output, Ingredient input, Consumer<FinishedRecipe> consumer, int strike) {
//		consumer.accept(new HammeringStationFinishedRecipe(new ResourceLocation(Reference.MOD_ID, "hammeringstation/" + name), output, input, strike));
//	}
//
//	// Copied it since inner class was private
//	private static class HammeringStationFinishedRecipe implements FinishedRecipe {
//		private final ResourceLocation id;
//		private final ItemStack output;
//		private final Ingredient input;
//		private final int strikes;
//
//		private HammeringStationFinishedRecipe(ResourceLocation id, ItemStack output, Ingredient input, int strikes) {
//			this.id = id;
//			this.output = output;
//			this.input = input;
//			this.strikes = strikes;
//		}
//
//		private HammeringStationFinishedRecipe(ResourceLocation id, ItemStack output, Ingredient input) {
//			this.id = id;
//			this.output = output;
//			this.input = input;
//			this.strikes = 4;
//		}
//
//		@Override
//		public void serializeRecipeData(JsonObject json) {
//			json.addProperty("strikes", strikes);
//			json.add("input", input.toJson());
//
//			JsonObject resultObject = serializeItemStack(output);
//			json.add("result", resultObject);
//		}
//
//		public static JsonObject serializeItemStack(ItemStack output) {
//			JsonObject resultObject = new JsonObject();
//			resultObject.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());
//			if (output.getCount() > 1) {
//				resultObject.addProperty("count", output.getCount());
//			}
//
//			if (output.hasTag() && output.getTag() != null) {
//				resultObject.addProperty("nbt", output.getTag().toString());
//			}
//			return resultObject;
//		}
//
//		@Override
//		@Nonnull
//		public ResourceLocation getId() {
//			return id;
//		}
//
//		@Override
//		@Nonnull
//		public RecipeSerializer<?> getType() {
//			return WorkstationRecipes.HAMMERING_STATION_SERIALIZER.get();
//		}
//
//		@Nullable
//		@Override
//		public JsonObject serializeAdvancement() {
//			return null;
//		}
//
//		@Nullable
//		@Override
//		public ResourceLocation getAdvancementId() {
//			return null;
//		}
//	}
//
//	@Override
//	@Nonnull
//	public String getName() {
//		return "Hammering Station Recipes";
//	}
//
//}