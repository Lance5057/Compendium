package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ScrappingTableRecipeProvider extends RecipeProvider {
    public ScrappingTableRecipeProvider(DataGenerator generatorIn) {
	super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    if (mh.getIngot() != null)
//		ingot(mh, consumer);
//	    else if (mh.getGem() != null)
//		gem(mh, consumer);
//	}

    }

    @Override
    @Nonnull
    public String getName() {
	return "Scrapping Table Recipes";
    }

    public static class FinishedResult implements IFinishedRecipe {
	private final ResourceLocation id;
	private final Ingredient input;
	private final ResourceLocation result;
	//private final int count;
	private final String group;
	private final List<RecipeItemUse> tools;
	private final Advancement.Builder advancementBuilder;
	private final ResourceLocation advancementId;

	public FinishedResult(ResourceLocation idIn, Ingredient inputIn, ResourceLocation resultIn,
		String groupIn, List<RecipeItemUse> toolsIn, Advancement.Builder advancementBuilderIn,
		ResourceLocation advancementIdIn) {
	    this.id = idIn;
	    this.input = inputIn;
	    this.result = resultIn;
	    //this.count = countIn;
	    this.group = groupIn;
	    this.advancementBuilder = advancementBuilderIn;
	    this.advancementId = advancementIdIn;
	    this.tools = toolsIn;
	}

	public void serialize(JsonObject json) {
	    if (!this.group.isEmpty()) {
		json.addProperty("group", this.group);
	    }

	    json.add("input", input.serialize());

	    JsonObject jsonobjectTools = new JsonObject();

	    for (int i = 0; i < this.tools.size(); i++) {// entry : this.tools) {
		jsonobjectTools.add("Step_" + i, RecipeItemUse.addProperty(tools.get(i)));
	    }
	    json.add("tools", jsonobjectTools);
	    
	    json.addProperty("output", result.toString());
	}

	public IRecipeSerializer<?> getSerializer() {
	    return WorkstationRecipes.SCRAPPING_TABLE_SERIALIZER.get();
	}

	/**
	 * Gets the ID for the recipe.
	 */
	public ResourceLocation getID() {
	    return this.id;
	}

	/**
	 * Gets the JSON for the advancement that unlocks this recipe. Null if there is
	 * no advancement.
	 */
	@Nullable
	public JsonObject getAdvancementJson() {
	    return this.advancementBuilder.serialize();
	}

	/**
	 * Gets the ID for the advancement associated with this recipe. Should not be
	 * null if {@link #getAdvancementJson} is non-null.
	 */
	@Nullable
	public ResourceLocation getAdvancementID() {
	    return this.advancementId;
	}
    }
}