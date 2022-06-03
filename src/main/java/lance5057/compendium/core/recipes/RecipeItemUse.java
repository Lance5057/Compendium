package lance5057.compendium.core.recipes;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class RecipeItemUse {

	public final int uses;
	public final Ingredient tool;
	public final int count;
	public final boolean damageTool;

	public static RecipeItemUse EMPTY = new RecipeItemUse(0, Ingredient.EMPTY, 1, false);

	public RecipeItemUse(int uses, Ingredient tool, int count, boolean damage) {
		this.uses = uses;
		this.tool = tool;
		this.count = count;
		this.damageTool = damage;
	}

	public static RecipeItemUse read(JsonObject j) {
		int use = j.get("Uses").getAsInt();
		Ingredient i = Ingredient.fromJson(j.getAsJsonObject("Tool"));
		int c = j.get("Count").getAsInt();
		// ItemStack stack = ShapedRecipe.deserializeItem(j.getAsJsonObject("tool"));
		boolean b = j.get("Damage").getAsBoolean();

		return new RecipeItemUse(use, i, c, b);
	}

	public static RecipeItemUse read(FriendlyByteBuf buffer) {
		int u = buffer.readVarInt();
		// ItemStack stack = buffer.readItemStack();
		Ingredient i = Ingredient.fromNetwork(buffer);
		int c = buffer.readVarInt();
		boolean b = buffer.readBoolean();

		return new RecipeItemUse(u, i, c, b);
	}

	public static void write(RecipeItemUse r, FriendlyByteBuf buffer) {
		buffer.writeVarInt(r.uses);
		r.tool.toNetwork(buffer);
		buffer.writeVarInt(r.count);
		buffer.writeBoolean(r.damageTool);
	}

	public static JsonObject addProperty(RecipeItemUse r) {
		JsonObject o = new JsonObject();

		o.addProperty("Uses", r.uses);
		o.add("Tool", r.tool.toJson());
		o.addProperty("Count", r.count);
		o.addProperty("Damage", r.damageTool);

		return o;
	}
}
