package lance5057.compendium.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloat;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class BlacklistedModel {
	public ResourceLocation rc;
	public List<Integer> blacklist;

	BlacklistedModel(ResourceLocation rc, List<Integer> blacklist) {
		this.rc = rc;
		this.blacklist = blacklist;
	}

	public static BlacklistedModel read(JsonObject j) {
		ResourceLocation rc = new ResourceLocation(j.get("Location").getAsString());
		JsonArray ja = j.get("blacklist").getAsJsonArray();

		List<Integer> b = new ArrayList<Integer>();
		if (ja != null) {
			for (int i = 0; i < ja.size(); i++) {
				b.add(ja.get(i).getAsInt());
			}
		}

		return new BlacklistedModel(rc, b);
	}

	public static AnimatedFloat read(FriendlyByteBuf buffer) {
		ResourceLocation rc = new ResourceLocation(buffer.readUtf());
		
		List<Integer> b = IntStream.of(buffer.readVarIntArray()).boxed().collect(Collectors.toList());

		return new BlacklistedModel(min, max, speed, loop, pingpong);
	}

	public static void write(AnimatedFloat af, FriendlyByteBuf buffer) {
		buffer.writeFloat(af.iMin);
		buffer.writeFloat(af.iMin);
		buffer.writeFloat(af.iMin);
		buffer.writeBoolean(af.loop);
	}

	public static JsonObject addProperty(AnimatedFloat af) {
		JsonObject jo = new JsonObject();

		jo.addProperty("min", af.iMin);
		jo.addProperty("max", af.iMax);
		jo.addProperty("speed", af.speed);
		jo.addProperty("loop", af.loop);

		return jo;
	}
}
