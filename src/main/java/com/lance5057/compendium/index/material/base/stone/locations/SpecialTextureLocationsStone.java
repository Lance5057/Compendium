package com.lance5057.compendium.index.material.base.stone.locations;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class SpecialTextureLocationsStone {
	public final ResourceLocation stoneLocation;
	public final ResourceLocation cobblestoneLocation;
	public final ResourceLocation smoothstoneLocation;

	public SpecialTextureLocationsStone(ResourceLocation stoneLocation, ResourceLocation smoothstoneLocation,
			ResourceLocation cobblestoneLocation) {
		this.stoneLocation = stoneLocation;
		this.cobblestoneLocation = cobblestoneLocation;
		this.smoothstoneLocation = smoothstoneLocation;
	}

	public static class Serializer
			implements JsonSerializer<SpecialTextureLocationsStone>, JsonDeserializer<SpecialTextureLocationsStone> {

		@Override
		public SpecialTextureLocationsStone deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			ResourceLocation stoneLocation = null;
			ResourceLocation cobblestoneLocation = null;
			ResourceLocation smoothstoneLocation = null;

			if (j.get("stoneLocation") != null)
				stoneLocation = ResourceLocation.parse(j.get("stoneLocation").getAsString());
			if (j.get("cobblestoneLocation") != null)
				cobblestoneLocation = ResourceLocation.parse(j.get("cobblestoneLocation").getAsString());
			if (j.get("smoothstoneLocation") != null)
				smoothstoneLocation = ResourceLocation.parse(j.get("smoothstoneLocation").getAsString());

			return new SpecialTextureLocationsStone(stoneLocation, smoothstoneLocation, cobblestoneLocation);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsStone src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.stoneLocation != null)
				j.addProperty("stoneLocation", src.stoneLocation.toString());
			if (src.cobblestoneLocation != null)
				j.addProperty("cobblestoneLocation", src.cobblestoneLocation.toString());
			if (src.stoneLocation != null)
				j.addProperty("smoothstoneLocation", src.smoothstoneLocation.toString());

			return j;
		}

	}
}
