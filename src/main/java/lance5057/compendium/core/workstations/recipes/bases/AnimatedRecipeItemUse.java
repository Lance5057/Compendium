package lance5057.compendium.core.workstations.recipes.bases;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.recipes.RecipeItemUse;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class AnimatedRecipeItemUse extends RecipeItemUse {

	public final List<BlacklistedModel> model;

	public static AnimatedRecipeItemUse EMPTY = new AnimatedRecipeItemUse(RecipeItemUse.EMPTY, BlacklistedModel.empty);

	public AnimatedRecipeItemUse(int uses, Ingredient tool, int count, boolean damage, BlacklistedModel... model) {
		super(uses, tool, count, damage);

		this.model = List.of(model);
	}

	public AnimatedRecipeItemUse(RecipeItemUse riu, BlacklistedModel... model) {
		super(riu.uses, riu.tool, riu.count, riu.damageTool);

		this.model = List.of(model);
	}

	public static AnimatedRecipeItemUse read(JsonObject j) {
		RecipeItemUse riu = RecipeItemUse.read(j);

		List<BlacklistedModel> b = new ArrayList<BlacklistedModel>();
		if (j.get("models") != null) {
			JsonArray ja = j.get("models").getAsJsonArray();

			if (ja != null) {
				for (int i = 0; i < ja.size(); i++) {
					b.add(BlacklistedModel.read(ja.get(i).getAsJsonObject()));
				}
			}
		}

		// BlacklistedModel b = BlacklistedModel.read(j.getAsJsonObject("model"));

		return new AnimatedRecipeItemUse(riu, (BlacklistedModel[]) b.toArray());
	}

	public static AnimatedRecipeItemUse read(FriendlyByteBuf buffer) {
		RecipeItemUse riu = RecipeItemUse.read(buffer);

		int size = buffer.readInt();

		List<BlacklistedModel> b = new ArrayList<BlacklistedModel>();

		for (int i = 0; i < size; i++)
			b.add(BlacklistedModel.read(buffer));

		return new AnimatedRecipeItemUse(riu, (BlacklistedModel[]) b.toArray());
	}

	public static void write(AnimatedRecipeItemUse r, FriendlyByteBuf buffer) {
		RecipeItemUse.write(r, buffer);

		buffer.writeInt(r.model.size());

		for (int i = 0; i < r.model.size(); i++)
			BlacklistedModel.write(r.model.get(i), buffer);
	}

	public static JsonObject addProperty(AnimatedRecipeItemUse r) {
		JsonObject o = RecipeItemUse.addProperty(r);

		if (r.model != null && !r.model.isEmpty()) {
			JsonArray ja = new JsonArray();
			for (int i = 0; i < r.model.size(); i++)
				ja.add(BlacklistedModel.addProperty(r.model.get(i)));

			o.add("models", ja);
		}

		return o;
	}

	public Vec3i getToolList() {
		// TODO Auto-generated method stub
		return null;
	}
}
