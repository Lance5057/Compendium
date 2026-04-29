package com.lance5057.compendium.index.material.base.wood.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpecialLocationsWood {

	public SpecialTextureLocationsWood textures;

	public SpecialLocationsWood(SpecialTextureLocationsWood textures) {
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsWood>, JsonDeserializer<SpecialLocationsWood> {

		@Override
		public SpecialLocationsWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			SpecialTextureLocationsWood t = null;

			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsWood.class);

			return new SpecialLocationsWood(t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
