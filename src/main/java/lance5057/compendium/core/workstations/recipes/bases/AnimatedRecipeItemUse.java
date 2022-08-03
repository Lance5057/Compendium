package lance5057.compendium.core.workstations.recipes.bases;

import com.google.gson.JsonObject;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class AnimatedRecipeItemUse extends RecipeItemUse {

    public final AnimationFloatTransform transform;
    public final BlacklistedModel model;

    public static AnimatedRecipeItemUse EMPTY = new AnimatedRecipeItemUse(RecipeItemUse.EMPTY,
	    AnimationFloatTransform.ZERO, BlacklistedModel.empty);

    public AnimatedRecipeItemUse(int uses, Ingredient tool, int count, boolean damage,
	    AnimationFloatTransform transform, BlacklistedModel model) {
	super(uses, tool, count, damage);

	this.transform = transform;
	this.model = model;
    }

    public AnimatedRecipeItemUse(RecipeItemUse riu, AnimationFloatTransform transform, BlacklistedModel model) {
	super(riu.uses, riu.tool, riu.count, riu.damageTool);

	this.transform = transform;
	this.model = model;
    }

    public static AnimatedRecipeItemUse read(JsonObject j) {
	RecipeItemUse riu = RecipeItemUse.read(j);

	AnimationFloatTransform t = AnimationFloatTransform.read(j.getAsJsonObject("animation"));
	BlacklistedModel b = BlacklistedModel.read(j.getAsJsonObject("model"));

	return new AnimatedRecipeItemUse(riu, t, b);
    }

    public static AnimatedRecipeItemUse read(FriendlyByteBuf buffer) {
	RecipeItemUse riu = RecipeItemUse.read(buffer);

	AnimationFloatTransform t = AnimationFloatTransform.read(buffer);
	BlacklistedModel b = BlacklistedModel.read(buffer);

	return new AnimatedRecipeItemUse(riu, t, b);
    }

    public static void write(AnimatedRecipeItemUse r, FriendlyByteBuf buffer) {
	RecipeItemUse.write(r, buffer);

	AnimationFloatTransform.write(r.transform, buffer);
	BlacklistedModel.write(r.model, buffer);
    }

    public static JsonObject addProperty(AnimatedRecipeItemUse r) {
	JsonObject o = RecipeItemUse.addProperty(r);

	o.add("animation", AnimationFloatTransform.addProperty(r.transform));
	o.add("model", BlacklistedModel.addProperty(r.model));

	return o;
    }

    public Vec3i getToolList() {
	// TODO Auto-generated method stub
	return null;
    }
}
