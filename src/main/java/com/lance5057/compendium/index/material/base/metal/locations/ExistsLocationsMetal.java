package com.lance5057.compendium.index.material.base.metal.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;

public class ExistsLocationsMetal {
	public final ResourceLocation blockLocation;
	public final ResourceLocation ingotLocation;
	public final ResourceLocation nuggetLocation;

	public ExistsLocationsMetal(ResourceLocation blockLocation, ResourceLocation ingotLocation,
			ResourceLocation nuggetLocation) {
		this.blockLocation = blockLocation;
		this.ingotLocation = ingotLocation;
		this.nuggetLocation = nuggetLocation;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsMetal>, JsonDeserializer<ExistsLocationsMetal> {

		@Override
		public ExistsLocationsMetal deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			ResourceLocation block = null;
			ResourceLocation ingot = null;
			ResourceLocation nugget = null;

			if (j.get("blockLocation") != null)
				block = ResourceLocation.parse(j.get("blockLocation").getAsString());
			if (j.get("ingotLocation") != null)
				ingot = ResourceLocation.parse(j.get("ingotLocation").getAsString());
			if (j.get("nuggetLocation") != null)
				nugget = ResourceLocation.parse(j.get("nuggetLocation").getAsString());

			return new ExistsLocationsMetal(block, ingot, nugget);
		}

		@Override
		public JsonElement serialize(ExistsLocationsMetal src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());
			if (src.ingotLocation != null)
				j.addProperty("ingotLocation", src.ingotLocation.toString());
			if (src.nuggetLocation != null)
				j.addProperty("nuggetLocation", src.nuggetLocation.toString());
			return j;
		}

	}
}
