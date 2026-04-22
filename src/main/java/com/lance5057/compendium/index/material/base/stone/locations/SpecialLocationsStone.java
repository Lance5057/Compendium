package com.lance5057.compendium.index.material.base.stone.locations;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SpecialLocationsStone {

	public SpecialTextureLocationsStone textures;

	public SpecialLocationsStone(SpecialTextureLocationsStone textures) {

		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsStone>, JsonDeserializer<SpecialLocationsStone> {

		@Override
		public SpecialLocationsStone deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			SpecialTextureLocationsStone t = null;

			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsStone.class);

			return new SpecialLocationsStone(t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsStone src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
