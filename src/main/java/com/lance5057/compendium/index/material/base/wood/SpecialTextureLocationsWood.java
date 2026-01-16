package com.lance5057.compendium.index.material.base.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SpecialTextureLocationsWood {
	String plankLocation;
	String logLocation;
	String strippedLogLocation;

	public SpecialTextureLocationsWood(String plankLoc, String logLoc, String strippedLogLoc) {
		this.plankLocation = plankLoc;
		this.logLocation = logLoc;
		this.strippedLogLocation = strippedLogLoc;
	}

	public static class Serializer
			implements JsonSerializer<SpecialTextureLocationsWood>, JsonDeserializer<SpecialTextureLocationsWood> {

		@Override
		public SpecialTextureLocationsWood deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			String plank = null;
			String log = null;
			String strippedLog = null;
			if (j.get("plankLocation") != null)
				plank = j.get("plankLocation").getAsString();
			if (j.get("logLocation") != null)
				log = j.get("logLocation").getAsString();
			if (j.get("strippedLogLocation") != null)
				strippedLog = j.get("strippedLogLocation").getAsString();

			return new SpecialTextureLocationsWood(plank, log, strippedLog);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsWood src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.plankLocation != null)
				j.addProperty("plankLocation", src.plankLocation);
			if (src.logLocation != null)
				j.addProperty("logLocation", src.logLocation);
			if (src.strippedLogLocation != null)
				j.addProperty("strippedLogLocation", src.strippedLogLocation);

			return j;
		}

	}
}
