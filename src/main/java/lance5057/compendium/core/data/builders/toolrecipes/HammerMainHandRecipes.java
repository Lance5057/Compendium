//package lance5057.compendium.core.data.builders.toolrecipes;
//
//import java.util.function.Consumer;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.google.gson.JsonObject;
//
//import lance5057.compendium.CompendiumRecipes;
//import lance5057.compendium.Reference;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.data.recipes.RecipeProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public class HammerMainHandRecipes extends RecipeProvider {
//
//	public HammerMainHandRecipes(DataGenerator generatorIn) {
//		super(generatorIn);
//	}
//
//	@Override
//	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
////	for (MaterialHelper mh : CompendiumMaterials.materials) {
////	    if (mh.getExtraComponents() != null) {
////		MaterialExtraComponents ec = mh.getExtraComponents();
////
////		this.createRecipe("empty_to_" + mh.name + "_shingles", new ItemStack(ec.SHINGLES.get()),
////			new ItemStack(CompendiumBlocks.SHINGLES.get()),
////			Ingredient.of(ItemTags.makeWrapperTag("forge:plates/" + mh.name)), consumer);
////		this.createRecipe("empty_to_" + mh.name + "_shingles_alt", new ItemStack(ec.SHINGLES_ALT.get()),
////			new ItemStack(CompendiumBlocks.SHINGLES_ALT.get()),
////			Ingredient.of(ItemTags.makeWrapperTag("forge:plates/" + mh.name)), consumer);
////	    }
////	}
//	}
//
//	public static void createRecipe(String name, ItemStack output, Ingredient block, Ingredient input,
//			Consumer<FinishedRecipe> consumer) {
//		consumer.accept(new HammerFinishedRecipe(new ResourceLocation(Reference.MOD_ID, "tool/hammer/" + name), output,
//				input, block));
//	}
//
//	// Copied it since inner class was private
//	private static class HammerFinishedRecipe implements FinishedRecipe {
//		private final ResourceLocation id;
//		private final ItemStack result;
//		private final Ingredient block;
//		private final Ingredient input;
//
//		private HammerFinishedRecipe(ResourceLocation id, ItemStack result, Ingredient input, Ingredient block) {
//			this.id = id;
//			this.result = result;
//			this.input = input;
//			this.block = block;
//		}
//
//		@Override
//		public void serializeRecipeData(JsonObject json) {
//			json.add("input", input.toJson());
//
//			// JsonObject blockObject = serializeItemStack(block);
//			json.add("block", block.toJson());
//
//			JsonObject resultObject = serializeItemStack(result);
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
//			return CompendiumRecipes.HAMMERING_TOOL_SERIALIZER.get();
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
//		return "Hammer Main InteractionHand Recipes";
//	}
//}
