package com.lance5057.compendium.index.material.base.textile;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ExistsLocationsTextile {
	String blockLocation;
	String stringLocation;
	String carpetLocation;

	public ExistsLocationsTextile(String block, String string, String carpet) {
		this.blockLocation = block;
		this.stringLocation = string;
		this.carpetLocation = carpet;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsTextile>, JsonDeserializer<ExistsLocationsTextile> {

		@Override
		public ExistsLocationsTextile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String block = null;
			String string = null;
			String carpet = null;

			if (j.get("blockLocation") != null)
				block = j.get("blockLocation").getAsString();
			if (j.get("stringLocation") != null)
				string = j.get("stringLocation").getAsString();
			if (j.get("carpetLocation") != null)
				string = j.get("carpetLocation").getAsString();

			return new ExistsLocationsTextile(block, string, carpet);
		}

		@Override
		public JsonElement serialize(ExistsLocationsTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation);
			if (src.stringLocation != null)
				j.addProperty("stringLocation", src.stringLocation);
			if (src.carpetLocation != null)
				j.addProperty("carpetLocation", src.carpetLocation);

			return j;
		}

	}
}
