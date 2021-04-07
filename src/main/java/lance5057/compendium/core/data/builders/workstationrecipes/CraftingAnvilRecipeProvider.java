package lance5057.compendium.core.data.builders.workstationrecipes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

public class CraftingAnvilRecipeProvider extends RecipeProvider {
    public CraftingAnvilRecipeProvider(DataGenerator generatorIn) {
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

//    private void gem(MaterialHelper mh, Consumer<IFinishedRecipe> consumer) {
//	BasicGemMaterial cm = mh.getGem();
//
//	// Vanilla Tools
//	if (mh.getVanillaTools() != null) {
//	    MaterialVanillaTools vt = mh.getVanillaTools();
//
//	    AnvilShapedRecipeBuilder.shapedRecipe(Items.IRON_SWORD, 8).key('#', Items.STICK).key('X', cm.GEM.get())
//		    .patternLine("X").patternLine("X").patternLine("#").addCriterion("has_gem", hasItem(cm.GEM.get()))
//		    .build(consumer, "anvil/" + mh.name + "_sword");
//	}
//    }
//
//    private void ingot(MaterialHelper mh, Consumer<IFinishedRecipe> consumer) {
//	BasicMetalMaterial mm = mh.getIngot();
//
//    }

    @Override
    @Nonnull
    public String getName() {
	return "Crafting Anvil Recipes";
    }

    public static class AnvilResult implements IFinishedRecipe {
	private final ResourceLocation id;
	private final Item result;
	private final int count;
	private final int strikes;
	private final String group;
	private final List<String> pattern;
	private final Map<Character, Ingredient> key;
	private final Advancement.Builder advancementBuilder;
	private final ResourceLocation advancementId;

	public AnvilResult(ResourceLocation idIn, Item resultIn, int strikesIn, int countIn, String groupIn,
		List<String> patternIn, Map<Character, Ingredient> keyIn, Advancement.Builder advancementBuilderIn,
		ResourceLocation advancementIdIn) {
	    this.id = idIn;
	    this.result = resultIn;
	    this.count = countIn;
	    this.strikes = strikesIn;
	    this.group = groupIn;
	    this.pattern = patternIn;
	    this.key = keyIn;
	    this.advancementBuilder = advancementBuilderIn;
	    this.advancementId = advancementIdIn;
	}

	public void serialize(JsonObject json) {
	    if (!this.group.isEmpty()) {
		json.addProperty("group", this.group);
	    }

	    JsonArray jsonarray = new JsonArray();

	    for (String s : this.pattern) {
		jsonarray.add(s);
	    }

	    json.add("pattern", jsonarray);
	    JsonObject jsonobject = new JsonObject();

	    for (Entry<Character, Ingredient> entry : this.key.entrySet()) {
		jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().serialize());
	    }

	    json.add("key", jsonobject);
	    JsonObject jsonobject1 = new JsonObject();
	    jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
	    if (this.count > 1) {
		jsonobject1.addProperty("count", this.count);
	    }

	    json.addProperty("strikes", strikes);
	    json.add("result", jsonobject1);
	}

	public IRecipeSerializer<?> getSerializer() {
	    return WorkstationRecipes.CRAFTING_ANVIL_SERIALIZER.get();
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