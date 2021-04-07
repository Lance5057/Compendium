package lance5057.compendium.core.data.builders.toolrecipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import lance5057.compendium.CompendiumRecipes;
import lance5057.compendium.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class HammerMainHandRecipes extends RecipeProvider {

    public HammerMainHandRecipes(DataGenerator generatorIn) {
	super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents ec = mh.getExtraComponents();
//
//		this.createRecipe("empty_to_" + mh.name + "_shingles", new ItemStack(ec.SHINGLES.get()),
//			new ItemStack(CompendiumBlocks.SHINGLES.get()),
//			Ingredient.fromTag(ItemTags.makeWrapperTag("forge:plates/" + mh.name)), consumer);
//		this.createRecipe("empty_to_" + mh.name + "_shingles_alt", new ItemStack(ec.SHINGLES_ALT.get()),
//			new ItemStack(CompendiumBlocks.SHINGLES_ALT.get()),
//			Ingredient.fromTag(ItemTags.makeWrapperTag("forge:plates/" + mh.name)), consumer);
//	    }
//	}
    }

    private void createRecipe(String name, ItemStack output, ItemStack block, Ingredient input,
	    Consumer<IFinishedRecipe> consumer) {
	consumer.accept(new FinishedRecipe(new ResourceLocation(Reference.MOD_ID, "tool/hammer/" + name), output, input,
		block));
    }

    // Copied it since inner class was private
    private static class FinishedRecipe implements IFinishedRecipe {
	private final ResourceLocation id;
	private final ItemStack result;
	private final ItemStack block;
	private final Ingredient input;

	private FinishedRecipe(ResourceLocation id, ItemStack result, Ingredient input, ItemStack block) {
	    this.id = id;
	    this.result = result;
	    this.input = input;
	    this.block = block;
	}

	@Override
	public void serialize(JsonObject json) {
	    json.add("input", input.serialize());

	    JsonObject blockObject = serializeItemStack(block);
	    json.add("block", blockObject);

	    JsonObject resultObject = serializeItemStack(result);
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
	    return CompendiumRecipes.HAMMERING_TOOL_SERIALIZER.get();
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
	return "Hammer Main Hand Recipes";
    }
}
