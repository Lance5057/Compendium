package lance5057.compendium.core.util.customloottable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class IngredientEntry implements ILoot {
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

		o.add("Item", r.item.toJson());
		o.addProperty("Count", r.count);

		return o;
	}

	@Override
	public List<ItemStack> invoke(Level level, Player player, ItemStack usedItem) {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(item, count));
		return null;
	}
}
