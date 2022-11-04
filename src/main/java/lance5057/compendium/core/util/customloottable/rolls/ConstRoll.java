package lance5057.compendium.core.util.customloottable.rolls;

import java.util.Random;

import com.google.gson.JsonObject;

import lance5057.compendium.core.util.customloottable.IngredientEntry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class ConstRoll implements IRoll {

	int num;
	
	public ConstRoll(int n)
	{
		num = n;
	}
	
	@Override
	public int roll(Random random) {
		return random.nextInt(num);
	}

	public static ConstRoll read(JsonObject j) {
		int c = j.get("Number").getAsInt();

		return new ConstRoll(c);
	}

	public static ConstRoll read(FriendlyByteBuf buffer) {
		int c = buffer.readVarInt();

		return new ConstRoll(c);
	}

	public static void write(ConstRoll r, FriendlyByteBuf buffer) {
		buffer.writeVarInt(r.num);
	}

	public static JsonObject addProperty(ConstRoll r) {
		JsonObject o = new JsonObject();

		o.addProperty("Number", r.num);

		return o;
	}
}
