package com.lance5057.compendium.index.material.base.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ExistsLocationsWood {
	String plankLocation;
	String logLocation;
	String strippedLogLocation;
	String woodLocation;
	String strippedWoodLocation;

	public ExistsLocationsWood(String plankLoc, String logLoc, String strippedLogLoc, String woodLoc,
			String strippedWoodLoc) {
		this.plankLocation = plankLoc;
		this.logLocation = logLoc;
		this.strippedLogLocation = strippedLogLoc;
		this.woodLocation = woodLoc;
		this.strippedWoodLocation = strippedWoodLoc;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsWood>, JsonDeserializer<ExistsLocationsWood> {

		@Override
		public ExistsLocationsWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String plank = null;
			String log = null;
			String strippedLog = null;
			String wood = null;
			String strippedWood = null;

			if (j.get("plankLocation") != null)
				plank = j.get("plankLocation").getAsString();
			if (j.get("logLocation") != null)
				log = j.get("logLocation").getAsString();
			if (j.get("strippedLogLocation") != null)
				strippedLog = j.get("strippedLogLocation").getAsString();
			if (j.get("woodLocation") != null)
				wood = j.get("woodLocation").getAsString();
			if (j.get("strippedWoodLocation") != null)
				strippedWood = j.get("strippedWoodLocation").getAsString();

			return new ExistsLocationsWood(plank, log, strippedLog, wood, strippedWood);
		}

		@Override
		public JsonElement serialize(ExistsLocationsWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.plankLocation != null)
				j.addProperty("plankLocation", src.plankLocation);
			if (src.logLocation != null)
				j.addProperty("logLocation", src.logLocation);
			if (src.strippedLogLocation != null)
				j.addProperty("strippedLogLocation", src.strippedLogLocation);
			if (src.woodLocation != null)
				j.addProperty("woodLocation", src.woodLocation);
			if (src.strippedWoodLocation != null)
				j.addProperty("strippedWoodLocation", src.strippedWoodLocation);

			return j;
		}

	}
}
