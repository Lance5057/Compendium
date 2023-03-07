package lance5057.compendium.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class BlacklistedModel {
	public boolean isBlock;
	public ResourceLocation rc;
	public List<Integer> blacklist;
	public AnimationFloatTransform transform;

	public static BlacklistedModel empty = new BlacklistedModel(new ResourceLocation("", ""), new ArrayList<Integer>(),
			true, AnimationFloatTransform.ZERO);

//	public BlacklistedModel(BlockState block)
//	{
//		ModelLoaderRegistry.
//		this.rc = block.
//		isBlock = true;
//	}

	public BlacklistedModel(Item item, AnimationFloatTransform anim) {
		this.rc = ForgeRegistries.ITEMS.getKey(item);
		isBlock = false;
		this.transform = anim;
	}

	public BlacklistedModel(ResourceLocation rc, List<Integer> blacklist, boolean block, AnimationFloatTransform anim) {
		this.rc = rc;
		this.blacklist = blacklist;
		this.isBlock = block;
		this.transform = anim;
	}

	public static BlacklistedModel read(JsonObject j) {
		ResourceLocation rc = j.get("location") != null ? new ResourceLocation(j.get("location").getAsString())
				: new ResourceLocation("", "");

		List<Integer> b = new ArrayList<Integer>();
		if (j.get("blacklist") != null) {
			JsonArray ja = j.get("blacklist").getAsJsonArray();

			if (ja != null) {
				for (int i = 0; i < ja.size(); i++) {
					b.add(ja.get(i).getAsInt());
				}
			}
		}

		boolean block = j.get("IsBlock") != null ? j.get("IsBlock").getAsBoolean() : false;
		AnimationFloatTransform t = AnimationFloatTransform.read(j.getAsJsonObject("animation"));

		return new BlacklistedModel(rc, b, block, t);
	}

	public static BlacklistedModel read(FriendlyByteBuf buffer) {
		ResourceLocation rc = new ResourceLocation(buffer.readUtf());

		List<Integer> b = IntStream.of(buffer.readVarIntArray()).boxed().collect(Collectors.toList());

		boolean block = buffer.readBoolean();

		AnimationFloatTransform t = AnimationFloatTransform.read(buffer);

		return new BlacklistedModel(rc, b, block, t);
	}

	public static void write(BlacklistedModel bm, FriendlyByteBuf buffer) {
		buffer.writeUtf(bm.rc.toString());
		buffer.writeVarIntArray(bm.blacklist.stream().mapToInt(i -> i).toArray());
		buffer.writeBoolean(bm.isBlock);
		AnimationFloatTransform.write(bm.transform, buffer);
	}

	public static JsonObject addProperty(BlacklistedModel bm) {
		JsonObject jo = new JsonObject();

		if (bm.blacklist != null && !bm.blacklist.isEmpty()) {
			JsonArray ja = new JsonArray();
			for (int i = 0; i < bm.blacklist.size(); i++)
				ja.add(bm.blacklist.get(i));

			jo.add("blacklist", ja);
		}

		if (!bm.rc.equals(new ResourceLocation("", "")))
			jo.addProperty("location", bm.rc.toString());

		if (bm.isBlock)
			jo.addProperty("IsBlock", bm.isBlock);

		jo.add("animation", AnimationFloatTransform.addProperty(bm.transform));

		return jo;
	}
}
