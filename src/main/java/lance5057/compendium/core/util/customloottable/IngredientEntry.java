package lance5057.compendium.core.util.customloottable;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientEntry {
	public Ingredient item;
	public int count;

	public IngredientEntry(Ingredient item, int count) {
		this.item = item;
		this.count = count;
	}

	public static IngredientEntry read(JsonObject j) {
		Ingredient i = Ingredient.fromJson(j.getAsJsonObject("Item"));
		int c = j.get("Count").getAsInt();

		return new IngredientEntry(i, c);
	}

	public static IngredientEntry read(FriendlyByteBuf buffer) {
		Ingredient i = Ingredient.fromNetwork(buffer);
		int c = buffer.readVarInt();

		return new IngredientEntry(i, c);
	}

	public static void write(IngredientEntry r, FriendlyByteBuf buffer) {
		r.item.toNetwork(buffer);
		buffer.writeVarInt(r.count);
	}

	public static JsonObject addProperty(IngredientEntry r) {
		JsonObject o = new JsonObject();

		o.add("Tool", r.item.toJson());
		o.addProperty("Count", r.count);

		return o;
	}
}
