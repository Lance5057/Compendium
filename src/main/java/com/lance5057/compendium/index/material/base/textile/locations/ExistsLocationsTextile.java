package com.lance5057.compendium.index.material.base.textile.locations;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;

public class ExistsLocationsTextile {
	ResourceLocation blockLocation;
	ResourceLocation stringLocation;
	ResourceLocation carpetLocation;

	public ResourceLocation getBlockLocation() {
		return blockLocation;
	}

	public ResourceLocation getStringLocation() {
		return stringLocation;
	}

	public ResourceLocation getCarpetLocation() {
		return carpetLocation;
	}

	public ExistsLocationsTextile(ResourceLocation block, ResourceLocation string, ResourceLocation carpet) {
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

			ResourceLocation block = null;
			ResourceLocation string = null;
			ResourceLocation carpet = null;

			if (j.get("blockLocation") != null)
				block = net.minecraft.resources.ResourceLocation.parse(j.get("blockLocation").toString());
			if (j.get("stringLocation") != null)
				string = net.minecraft.resources.ResourceLocation.parse(j.get("stringLocation").toString());
			if (j.get("carpetLocation") != null)
				carpet = net.minecraft.resources.ResourceLocation.parse(j.get("carpetLocation").toString());

			return new ExistsLocationsTextile(block, string, carpet);
		}

		@Override
		public JsonElement serialize(ExistsLocationsTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());
			if (src.stringLocation != null)
				j.addProperty("stringLocation", src.stringLocation.toString());
			if (src.carpetLocation != null)
				j.addProperty("carpetLocation", src.carpetLocation.toString());

			return j;
		}

	}
}
