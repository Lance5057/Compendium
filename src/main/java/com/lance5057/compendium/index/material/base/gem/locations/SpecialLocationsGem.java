package com.lance5057.compendium.index.material.base.gem.locations;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SpecialLocationsGem {

	public SpecialTextureLocationsGem textures;

	public SpecialLocationsGem(SpecialTextureLocationsGem textures) {

		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsGem>, JsonDeserializer<SpecialLocationsGem> {

		@Override
		public SpecialLocationsGem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			SpecialTextureLocationsGem t = null;

			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsGem.class);

			return new SpecialLocationsGem(t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsGem src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
