package com.lance5057.compendium.index.material.base.textile;

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
	ResourceLocation ResourceLocationLocation;
	ResourceLocation carpetLocation;

	public ExistsLocationsTextile(ResourceLocation block, ResourceLocation ResourceLocation, ResourceLocation carpet) {
		this.blockLocation = block;
		this.ResourceLocationLocation = ResourceLocation;
		this.carpetLocation = carpet;
	}

	public static class Serializer
			implements JsonSerializer<ExistsLocationsTextile>, JsonDeserializer<ExistsLocationsTextile> {

		@Override
		public ExistsLocationsTextile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ResourceLocation block = null;
			ResourceLocation ResourceLocation = null;
			ResourceLocation carpet = null;

			if (j.get("blockLocation") != null)
				block = net.minecraft.resources.ResourceLocation.parse(j.get("blockLocation").toString());
			if (j.get("ResourceLocationLocation") != null)
				ResourceLocation = net.minecraft.resources.ResourceLocation
						.parse(j.get("ResourceLocationLocation").toString());
			if (j.get("carpetLocation") != null)
				ResourceLocation = net.minecraft.resources.ResourceLocation.parse(j.get("carpetLocation").toString());

			return new ExistsLocationsTextile(block, ResourceLocation, carpet);
		}

		@Override
		public JsonElement serialize(ExistsLocationsTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());
			if (src.ResourceLocationLocation != null)
				j.addProperty("ResourceLocationLocation", src.ResourceLocationLocation.toString());
			if (src.carpetLocation != null)
				j.addProperty("carpetLocation", src.carpetLocation.toString());

			return j;
		}

	}
}
