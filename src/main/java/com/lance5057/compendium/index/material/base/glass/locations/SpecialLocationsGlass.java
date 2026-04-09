package com.lance5057.compendium.index.material.base.glass.locations;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SpecialLocationsGlass {

	public SpecialTextureLocationsGlass textures;

	public SpecialLocationsGlass(SpecialTextureLocationsGlass textures) {

		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsGlass>, JsonDeserializer<SpecialLocationsGlass> {

		@Override
		public SpecialLocationsGlass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			SpecialTextureLocationsGlass t = null;

			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsGlass.class);

			return new SpecialLocationsGlass(t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsGlass src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
