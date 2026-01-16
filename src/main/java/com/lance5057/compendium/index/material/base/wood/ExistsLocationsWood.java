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

			String plank = j.get("plankLocation").getAsString();
			String log = j.get("logLocation").getAsString();
			String strippedLog = j.get("strippedLogLocation").getAsString();
			String wood = j.get("woodLocation").getAsString();
			String strippedWood = j.get("strippedWoodLocation").getAsString();

			return new ExistsLocationsWood(plank, log, strippedLog, wood, strippedWood);
		}

		@Override
		public JsonElement serialize(ExistsLocationsWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("plankLocation", src.plankLocation);
			j.addProperty("logLocation", src.logLocation);
			j.addProperty("strippedLogLocation", src.strippedLogLocation);
			j.addProperty("woodLocation", src.woodLocation);
			j.addProperty("strippedWoodLocation", src.strippedWoodLocation);

			return j;
		}

	}
}
