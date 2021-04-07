package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.data.builders.MetalRecipes;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class HammeringStationRecipeProvider extends RecipeProvider {
	public HammeringStationRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
		// From Tag
		this.createRecipe("stone_to_cobble", new ItemStack(Blocks.COBBLESTONE), Ingredient.fromTag(Items.STONE), consumer, 2);
		this.createRecipe("cobble_to_gravel", new ItemStack(Blocks.GRAVEL), Ingredient.fromItems(Blocks.COBBLESTONE), consumer, 2);
		this.createRecipe("gravel_to_sand", new ItemStack(Blocks.SAND), Ingredient.fromTag(Items.GRAVEL), consumer, 2);
		this.createRecipe("deadcoral_to_soulsand", new ItemStack(Blocks.SOUL_SAND), Ingredient.fromItems(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK), consumer);

		MetalRecipes.hammeringBuild(this, consumer);
	}

	public void createRecipe(String name, ItemStack output, Ingredient input, Consumer<IFinishedRecipe> consumer) {
		consumer.accept(new FinishedRecipe(new ResourceLocation(Reference.MOD_ID, "hammeringstation/" + name), output, input));
	}

	public void createRecipe(String name, ItemStack output, Ingredient input, Consumer<IFinishedRecipe> consumer, int strike) {
		consumer.accept(new FinishedRecipe(new ResourceLocation(Reference.MOD_ID, "hammeringstation/" + name), output, input, strike));
	}

	// Copied it since inner class was private
	private static class FinishedRecipe implements IFinishedRecipe {
		private final ResourceLocation id;
		private final ItemStack output;
		private final Ingredient input;
		private final int strikes;

		private FinishedRecipe(ResourceLocation id, ItemStack output, Ingredient input, int strikes) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = strikes;
		}

		private FinishedRecipe(ResourceLocation id, ItemStack output, Ingredient input) {
			this.id = id;
			this.output = output;
			this.input = input;
			this.strikes = 4;
		}

		@Override
		public void serialize(JsonObject json) {
			json.addProperty("strikes", strikes);
			json.add("input", input.serialize());

			JsonObject resultObject = serializeItemStack(output);
			json.add("result", resultObject);
		}

		public static JsonObject serializeItemStack(ItemStack output) {
			JsonObject resultObject = new JsonObject();
			resultObject.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());
			if (output.getCount() > 1) {
				resultObject.addProperty("count", output.getCount());
			}

			if (output.hasTag() && output.getTag() != null) {
				resultObject.addProperty("nbt", output.getTag().toString());
			}
			return resultObject;
		}

		@Override
		@Nonnull
		public ResourceLocation getID() {
			return id;
		}

		@Override
		@Nonnull
		public IRecipeSerializer<?> getSerializer() {
			return WorkstationRecipes.HAMMERING_STATION_SERIALIZER.get();
		}

		@Nullable
		@Override
		public JsonObject getAdvancementJson() {
			return null;
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementID() {
			return null;
		}
	}

	@Override
	@Nonnull
	public String getName() {
		return "Hammering Station Recipes";
	}

}