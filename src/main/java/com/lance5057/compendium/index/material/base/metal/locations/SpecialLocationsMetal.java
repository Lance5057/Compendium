package com.lance5057.compendium.index.material.base.metal.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpecialLocationsMetal {

	public SpecialTextureLocationsMetal textures;

	public SpecialLocationsMetal(SpecialTextureLocationsMetal textures) {
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsMetal>, JsonDeserializer<SpecialLocationsMetal> {

		@Override
		public SpecialLocationsMetal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			SpecialTextureLocationsMetal t = null;

			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsMetal.class);

			return new SpecialLocationsMetal(t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsMetal src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
