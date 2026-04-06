package com.lance5057.compendium.index.material.base.glass.locations;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SpecialLocationsGlass {

	public ExistsLocationsGlass existsItem;
	public ExistsLocationsGlass existsBlock;
	public SpecialTextureLocationsGlass textures;

	public SpecialLocationsGlass(ExistsLocationsGlass existsItem, ExistsLocationsGlass existsBlock,
                                   SpecialTextureLocationsGlass textures) {
		this.existsItem = existsItem;
		this.existsBlock = existsBlock;
		this.textures = textures;
	}

	public static class Serializer
			implements JsonSerializer<SpecialLocationsGlass>, JsonDeserializer<SpecialLocationsGlass> {

		@Override
		public SpecialLocationsGlass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExistsLocationsGlass ei = null;
			ExistsLocationsGlass eb = null;
			SpecialTextureLocationsGlass t = null;

			if (j.get("existsItem") != null)
				ei = context.deserialize(j.get("existsItem"), ExistsLocationsGlass.class);
			if (j.get("existsBlock") != null)
				eb = context.deserialize(j.get("existsBlock"), ExistsLocationsGlass.class);
			if (j.get("textures") != null)
				t = context.deserialize(j.get("textures"), SpecialTextureLocationsGlass.class);

			return new SpecialLocationsGlass(ei, eb, t);
		}

		@Override
		public JsonElement serialize(SpecialLocationsGlass src, Type typeOfSrc, JsonSerializationContext context) {
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
