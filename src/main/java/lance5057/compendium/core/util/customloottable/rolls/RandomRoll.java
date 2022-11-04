package lance5057.compendium.core.util.customloottable.rolls;

import java.util.Random;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;

public class RandomRoll implements IRoll {

	int upper;
	int lower;

	public RandomRoll(int u, int l)
	{
		upper = u;
		lower = l;
	}

	@Override
	public int roll(Random random) {
		return random.nextInt(upper, lower);
	}

	public static RandomRoll read(JsonObject j) {
		int u = j.get("Upper Limit").getAsInt();
		int l = j.get("Lower Limit").getAsInt();
		return new RandomRoll(u,l);
	}

	public static RandomRoll read(FriendlyByteBuf buffer) {
		int u = buffer.readVarInt();
		int l = buffer.readVarInt();
		return new RandomRoll(u,l);
	}

	public static void write(RandomRoll r, FriendlyByteBuf buffer) {
		buffer.writeVarInt(r.upper);
		buffer.writeVarInt(r.lower);
	}

	public static JsonObject addProperty(RandomRoll r) {
		JsonObject o = new JsonObject();

		o.addProperty("Upper Limit", r.upper);
		o.addProperty("Lower Limit", r.lower);

		return o;
	}

}
