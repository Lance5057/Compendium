package com.lance5057.compendium.index.material.base.textile;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpecialTextureLocationsTextile {
	public final String blockLocation;
	public final String stringLocation;
	public final String carpetLocation;

	public SpecialTextureLocationsTextile(String blockLoc, String stringLoc, String carpetLoc) {
		this.blockLocation = blockLoc;
		this.stringLocation = stringLoc;
		this.carpetLocation = carpetLoc;
	}

	public static class Serializer implements JsonSerializer<SpecialTextureLocationsTextile>,
			JsonDeserializer<SpecialTextureLocationsTextile> {

		@Override
		public SpecialTextureLocationsTextile deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			String plank = null;
			String log = null;
			String strippedLog = null;
			if (j.get("blockLocation") != null)
				plank = j.get("blockLocation").getAsString();
			if (j.get("stringLocation") != null)
				log = j.get("stringLocation").getAsString();
			if (j.get("carpetLocation") != null)
				strippedLog = j.get("carpetLocation").getAsString();

			return new SpecialTextureLocationsTextile(plank, log, strippedLog);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsTextile src, Type typeOfSrc,
				JsonSerializationContext context) {
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
