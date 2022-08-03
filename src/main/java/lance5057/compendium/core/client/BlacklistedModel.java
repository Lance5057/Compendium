package lance5057.compendium.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class BlacklistedModel {
	public ResourceLocation rc;
	public List<Integer> blacklist;
	
	public static BlacklistedModel empty = new BlacklistedModel(new ResourceLocation("",""), new ArrayList<Integer>());

	public BlacklistedModel(ResourceLocation rc, List<Integer> blacklist) {
		this.rc = rc;
		this.blacklist = blacklist;
	}

	public static BlacklistedModel read(JsonObject j) {
		ResourceLocation rc = new ResourceLocation(j.get("location").getAsString());
		JsonArray ja = j.get("blacklist").getAsJsonArray();

		List<Integer> b = new ArrayList<Integer>();
		if (ja != null) {
			for (int i = 0; i < ja.size(); i++) {
				b.add(ja.get(i).getAsInt());
			}
		}

		return new BlacklistedModel(rc, b);
	}

	public static BlacklistedModel read(FriendlyByteBuf buffer) {
		ResourceLocation rc = new ResourceLocation(buffer.readUtf());
		
		List<Integer> b = IntStream.of(buffer.readVarIntArray()).boxed().collect(Collectors.toList());

		return new BlacklistedModel(rc, b);
	}

	public static void write(BlacklistedModel bm, FriendlyByteBuf buffer) {
	    buffer.writeUtf(bm.rc.toString());
	    buffer.writeVarIntArray(bm.blacklist.stream().mapToInt(i->i).toArray());
	}

	public static JsonObject addProperty(BlacklistedModel bm) {
		JsonObject jo = new JsonObject();
		
		JsonArray ja = new JsonArray();
		for(int i = 0; i < bm.blacklist.size(); i++)
		    ja.add(bm.blacklist.get(i));

		jo.add("blacklist", ja);
		jo.addProperty("location", bm.rc.toString());

		return jo;
	}
}
