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

public class SpecialTextureLocationsTextile {
	public final ResourceLocation blockLocation;
	public final ResourceLocation stringLocation;
	public final ResourceLocation carpetLocation;

	public SpecialTextureLocationsTextile(ResourceLocation blockLoc, ResourceLocation stringLoc, ResourceLocation carpetLoc) {
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
			ResourceLocation block = null;
			ResourceLocation string = null;
			ResourceLocation carpet = null;
			if (j.get("blockLocation") != null)
				block = ResourceLocation.parse(j.get("blockLocation").getAsString());
			if (j.get("stringLocation") != null)
				string = ResourceLocation.parse(j.get("stringLocation").getAsString());
			if (j.get("carpetLocation") != null)
				carpet = ResourceLocation.parse(j.get("carpetLocation").getAsString());

			return new SpecialTextureLocationsTextile(block, string, carpet);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsTextile src, Type typeOfSrc,
				JsonSerializationContext context) {
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
