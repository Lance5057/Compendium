package com.lance5057.compendium.index.material.base.textile.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpecialLocationsTextile {

	public ExistsLocationsTextile existsItem;
	public ExistsLocationsTextile existsBlock;
	public SpecialTextureLocationsTextile textures;

	public SpecialLocationsTextile(ExistsLocationsTextile existsItem, ExistsLocationsTextile existsBlock,
			SpecialTextureLocationsTextile textures) {
		this.existsItem = existsItem;
		this.existsBlock = existsBlock;
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsTextile>, JsonDeserializer<SpecialLocationsTextile> {

		@Override
		public SpecialLocationsTextile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExistsLocationsTextile ei = null;
			ExistsLocationsTextile eb = null;
			SpecialTextureLocationsTextile t = null;

			if (j.get("existsItem") != null)
				ei = context.deserialize(j.get("existsItem"), ExistsLocationsTextile.class);
			if (j.get("existsBlock") != null)
				eb = context.deserialize(j.get("existsBlock"), ExistsLocationsTextile.class);
			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsTextile.class);

			return new SpecialLocationsTextile(ei, eb, t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.existsItem != null)
				j.add("existsItem", context.serialize(src.existsItem));
			if (src.existsBlock != null)
				j.add("existsBlock", context.serialize(src.existsBlock));
			if (src.textures != null)
				j.add("textures", context.serialize(src.textures));

			return j;
		}

	}
}
