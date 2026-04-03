package com.lance5057.compendium.index.material.base.wood.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;

public class ExistsLocationsWood {
	ResourceLocation plankLocation;

	ResourceLocation logLocation;
	ResourceLocation strippedLogLocation;
	ResourceLocation woodLocation;
	ResourceLocation strippedWoodLocation;

	public ExistsLocationsWood(ResourceLocation plankLoc, ResourceLocation logLoc, ResourceLocation strippedLogLoc,
			ResourceLocation woodLoc, ResourceLocation strippedWoodLoc) {
		this.plankLocation = plankLoc;
		this.logLocation = logLoc;
		this.strippedLogLocation = strippedLogLoc;
		this.woodLocation = woodLoc;
		this.strippedWoodLocation = strippedWoodLoc;
	}

	public ResourceLocation getPlankLocation() {
		return plankLocation;
	}

	public ResourceLocation getLogLocation() {
		return logLocation;
	}

	public ResourceLocation getStrippedLogLocation() {
		return strippedLogLocation;
	}

	public ResourceLocation getWoodLocation() {
		return woodLocation;
	}

	public ResourceLocation getStrippedWoodLocation() {
		return strippedWoodLocation;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsWood>, JsonDeserializer<ExistsLocationsWood> {

		@Override
		public ExistsLocationsWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ResourceLocation plank = null;
			ResourceLocation log = null;
			ResourceLocation strippedLog = null;
			ResourceLocation wood = null;
			ResourceLocation strippedWood = null;

			if (j.get("plankLocation") != null)
				plank = ResourceLocation.parse(j.get("plankLocation").getAsString());
			if (j.get("logLocation") != null)
				log = ResourceLocation.parse(j.get("logLocation").getAsString());
			if (j.get("strippedLogLocation") != null)
				strippedLog = ResourceLocation.parse(j.get("strippedLogLocation").getAsString());
			if (j.get("woodLocation") != null)
				wood = ResourceLocation.parse(j.get("woodLocation").getAsString());
			if (j.get("strippedWoodLocation") != null)
				strippedWood = ResourceLocation.parse(j.get("strippedWoodLocation").getAsString());

			return new ExistsLocationsWood(plank, log, strippedLog, wood, strippedWood);
		}

		@Override
		public JsonElement serialize(ExistsLocationsWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.plankLocation != null)
				j.addProperty("plankLocation", src.plankLocation.toString());
			if (src.logLocation != null)
				j.addProperty("logLocation", src.logLocation.toString());
			if (src.strippedLogLocation != null)
				j.addProperty("strippedLogLocation", src.strippedLogLocation.toString());
			if (src.woodLocation != null)
				j.addProperty("woodLocation", src.woodLocation.toString());
			if (src.strippedWoodLocation != null)
				j.addProperty("strippedWoodLocation", src.strippedWoodLocation.toString());

			return j;
		}

	}
}
