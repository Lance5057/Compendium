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

	public ExistsLocationsWood existsItem;
	public ExistsLocationsWood existsBlock;
	public SpecialTextureLocationsWood textures;

	public SpecialLocationsWood(ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock,
			SpecialTextureLocationsWood textures) {
		this.existsItem = existsItem;
		this.existsBlock = existsBlock;
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsWood>, JsonDeserializer<SpecialLocationsWood> {

		@Override
		public SpecialLocationsWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExistsLocationsWood ei = null;
			ExistsLocationsWood eb = null;
			SpecialTextureLocationsWood t = null;

			if (j.get("existsItem") != null)
				ei = context.deserialize(j.get("existsItem"), ExistsLocationsWood.class);
			if (j.get("existsBlock") != null)
				eb = context.deserialize(j.get("existsBlock"), ExistsLocationsWood.class);
			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsWood.class);

			return new SpecialLocationsWood(ei, eb, t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsWood src, Type typeOfSrc, JsonSerializationContext context) {
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
