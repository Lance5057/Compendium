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
	public final String plankLocation;
	public final String logLocation;
	public final String strippedLogLocation;
	public final String logTopLocation;
	public final String strippedLogTopLocation;

	public SpecialTextureLocationsWood(String plankLoc, String logLoc, String strippedLogLoc, String logTopLocation,
			String strippedLogTopLocation) {
		this.plankLocation = plankLoc;
		this.logLocation = logLoc;
		this.strippedLogLocation = strippedLogLoc;
		this.logTopLocation = logTopLocation;
		this.strippedLogTopLocation = strippedLogTopLocation;
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
			String logTop = null;
			String strippedLogTop = null;
			if (j.get("plankLocation") != null)
				plank = j.get("plankLocation").getAsString();
			if (j.get("logLocation") != null)
				log = j.get("logLocation").getAsString();
			if (j.get("strippedLogLocation") != null)
				strippedLog = j.get("strippedLogLocation").getAsString();
			if (j.get("logTopLocation") != null)
				logTop = j.get("logTopLocation").getAsString();
			if (j.get("strippedLogTopLocation") != null)
				strippedLogTop = j.get("strippedLogTopLocation").getAsString();

			return new SpecialTextureLocationsWood(plank, log, strippedLog, logTop, strippedLogTop);
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
			if (src.logLocation != null)
				j.addProperty("logTopLocation", src.logTopLocation);
			if (src.strippedLogLocation != null)
				j.addProperty("strippedLogTopLocation", src.strippedLogTopLocation);

			return j;
		}

	}
}
