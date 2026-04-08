package com.lance5057.compendium.index.material.base.glass.locations;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class ExistsLocationsGlass {
	ResourceLocation blockLocation;
	
	public ResourceLocation getBlockLocation() {
		return blockLocation;
	}

	public ExistsLocationsGlass(ResourceLocation block) {
		this.blockLocation = block;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsGlass>, JsonDeserializer<ExistsLocationsGlass> {

		@Override
		public ExistsLocationsGlass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ResourceLocation block = null;

			if (j.get("blockLocation") != null)
				block = net.minecraft.resources.ResourceLocation.parse(j.get("blockLocation").toString());

			return new ExistsLocationsGlass(block);
		}

		@Override
		public JsonElement serialize(ExistsLocationsGlass src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());

			return j;
		}

	}
}
