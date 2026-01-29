package com.lance5057.compendium.index.material.base.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;

public class SpecialTextureLocationsWood {
	public final ResourceLocation plankLocation;
	public final ResourceLocation logLocation;
	public final ResourceLocation strippedLogLocation;
	public final ResourceLocation logTopLocation;
	public final ResourceLocation strippedLogTopLocation;

	public SpecialTextureLocationsWood(ResourceLocation plankLoc, ResourceLocation logLoc,
			ResourceLocation strippedLogLoc, ResourceLocation logTopLocation, ResourceLocation strippedLogTopLocation) {
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
			ResourceLocation plank = null;
			ResourceLocation log = null;
			ResourceLocation strippedLog = null;
			ResourceLocation logTop = null;
			ResourceLocation strippedLogTop = null;

			if (j.get("plankLocation") != null)
				plank = ResourceLocation.parse(j.get("plankLocation").getAsString());
			if (j.get("logLocation") != null)
				log = ResourceLocation.parse(j.get("logLocation").getAsString());
			if (j.get("strippedLogLocation") != null)
				strippedLog = ResourceLocation.parse(j.get("strippedLogLocation").getAsString());
			if (j.get("logTopLocation") != null)
				logTop = ResourceLocation.parse(j.get("logTopLocation").getAsString());
			if (j.get("strippedLogTopLocation") != null)
				strippedLogTop = ResourceLocation.parse(j.get("strippedLogTopLocation").getAsString());

			return new SpecialTextureLocationsWood(plank, log, strippedLog, logTop, strippedLogTop);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsWood src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.plankLocation != null)
				j.addProperty("plankLocation", src.plankLocation.toString());
			if (src.logLocation != null)
				j.addProperty("logLocation", src.logLocation.toString());
			if (src.strippedLogLocation != null)
				j.addProperty("strippedLogLocation", src.strippedLogLocation.toString());
			if (src.logTopLocation != null)
				j.addProperty("logTopLocation", src.logTopLocation.toString());
			if (src.strippedLogTopLocation != null)
				j.addProperty("strippedLogTopLocation", src.strippedLogTopLocation.toString());

			return j;
		}

	}
}
