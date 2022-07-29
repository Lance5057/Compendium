package lance5057.compendium.core.workstations.recipes.bases;

import com.google.gson.JsonObject;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class AnimatedMultiToolRecipe extends RecipeItemUse {

	public final AnimationFloatTransform transform;
	public final ResourceLocation model;
	
	public static AnimatedMultiToolRecipe EMPTY = new AnimatedMultiToolRecipe(RecipeItemUse.EMPTY, AnimationFloatTransform.ZERO, new ResourceLocation(""));

	public AnimatedMultiToolRecipe(int uses, Ingredient tool, int count, boolean damage,
			AnimationFloatTransform transform, ResourceLocation model) {
		super(uses, tool, count, damage);

		this.transform = transform;
		this.model = model;
	}
	
	public AnimatedMultiToolRecipe(RecipeItemUse riu,
			AnimationFloatTransform transform, ResourceLocation model) {
		super(riu.uses, riu.tool, riu.count, riu.damageTool);

		this.transform = transform;
		this.model = model;
	}

	public static AnimatedMultiToolRecipe read(JsonObject j) {
		RecipeItemUse riu = RecipeItemUse.read(j);
		
		AnimationFloatTransform t = AnimationFloatTransform.read(j);
		ResourceLocation rc = new ResourceLocation(j.get("Location").getAsString());

		return new AnimatedMultiToolRecipe(riu, t, rc);
	}

	public static AnimatedMultiToolRecipe read(FriendlyByteBuf buffer) {
		RecipeItemUse riu = RecipeItemUse.read(buffer);
		
		AnimationFloatTransform t = AnimationFloatTransform.read(buffer);
		ResourceLocation rc = new ResourceLocation(buffer.readUtf());

		return new AnimatedMultiToolRecipe(riu, t, rc);
	}

	public static void write(AnimatedMultiToolRecipe r, FriendlyByteBuf buffer) {
		RecipeItemUse.write(r, buffer);
		
		AnimationFloatTransform.write(r.transform, buffer);
		buffer.writeUtf(r.model.toString());
	}

	public static JsonObject addProperty(AnimatedMultiToolRecipe r) {
		JsonObject o = new JsonObject();

		RecipeItemUse.addProperty(r);
		
		AnimationFloatTransform.addProperty(r.transform);
		o.addProperty("Location", r.model.toString());

		return o;
	}
}
