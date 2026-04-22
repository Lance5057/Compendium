package com.lance5057.compendium.index.material.base.gem.locations;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class SpecialTextureLocationsGem {
	public final ResourceLocation blockLocation;
	public final ResourceLocation gemLocation;
	public final ResourceLocation shardLocation;

	public SpecialTextureLocationsGem(ResourceLocation blockLoc, ResourceLocation shardLocation,
			ResourceLocation gemLocation) {
		this.blockLocation = blockLoc;
		this.gemLocation = gemLocation;
		this.shardLocation = shardLocation;
	}

	public static class Serializer
			implements JsonSerializer<SpecialTextureLocationsGem>, JsonDeserializer<SpecialTextureLocationsGem> {

		@Override
		public SpecialTextureLocationsGem deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();
			ResourceLocation block = null;
			ResourceLocation gem = null;
			ResourceLocation shard = null;

			if (j.get("blockLocation") != null)
				block = ResourceLocation.parse(j.get("blockLocation").getAsString());
			if (j.get("gemLocation") != null)
				gem = ResourceLocation.parse(j.get("gemLocation").getAsString());
			if (j.get("shardLocation") != null)
				shard = ResourceLocation.parse(j.get("shardLocation").getAsString());

			return new SpecialTextureLocationsGem(block, shard, gem);
		}

		@Override
		public JsonElement serialize(SpecialTextureLocationsGem src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			if (src.blockLocation != null)
				j.addProperty("blockLocation", src.blockLocation.toString());
			if (src.gemLocation != null)
				j.addProperty("gemLocation", src.gemLocation.toString());
			if (src.shardLocation != null)
				j.addProperty("shardLocation", src.shardLocation.toString());

			return j;
		}

	}
}
