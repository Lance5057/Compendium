package com.lance5057.compendium.index.material.base.glass.locations;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class SpecialTextureLocationsGlass {
	public final ResourceLocation blockLocation;

	public SpecialTextureLocationsGlass(ResourceLocation blockLoc) {
		this.blockLocation = blockLoc;
	}

	public static class Serializer implements JsonSerializer<SpecialTextureLocationsGlass>,
			JsonDeserializer<SpecialTextureLocationsGlass> {

		@Override
		public SpecialTextureLocationsGlass deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			ResourceLocation block = null;
			if (j.get("blockLocation") != null)
				block = ResourceLocation.parse(j.get("blockLocation").getAsString());

			return new SpecialTextureLocationsGlass(block);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsGlass src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());

			return j;
		}

	}
}
