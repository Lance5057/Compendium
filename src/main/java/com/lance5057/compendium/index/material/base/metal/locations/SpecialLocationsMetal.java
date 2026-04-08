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

	public ExistsLocationsMetal existsItem;
	public ExistsLocationsMetal existsBlock;
	public SpecialTextureLocationsMetal textures;

	public SpecialLocationsMetal(ExistsLocationsMetal existsItem, ExistsLocationsMetal existsBlock,
			SpecialTextureLocationsMetal textures) {
		this.existsItem = existsItem;
		this.existsBlock = existsBlock;
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsMetal>, JsonDeserializer<SpecialLocationsMetal> {

		@Override
		public SpecialLocationsMetal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExistsLocationsMetal ei = null;
			ExistsLocationsMetal eb = null;
			SpecialTextureLocationsMetal t = null;

			if (j.get("existsItem") != null)
				ei = context.deserialize(j.get("existsItem"), ExistsLocationsMetal.class);
			if (j.get("existsBlock") != null)
				eb = context.deserialize(j.get("existsBlock"), ExistsLocationsMetal.class);
			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsMetal.class);

			return new SpecialLocationsMetal(ei, eb, t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsMetal src, Type typeOfSrc, JsonSerializationContext context) {
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
