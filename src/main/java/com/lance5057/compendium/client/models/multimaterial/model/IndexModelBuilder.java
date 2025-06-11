package com.lance5057.compendium.client.models.multimaterial.model;

import com.google.gson.JsonObject;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.resources.ResourceLocation;

public class IndexModelBuilder {
	MATERIAL_TYPES type;
	ResourceLocation model;
	String material;

	public IndexModelBuilder(MATERIAL_TYPES t, ResourceLocation b, String m) {
		this.type = t;
		this.model = b;
		this.material = m;
	}

	public JsonObject toJson(JsonObject jo, int i) {
		JsonObject j = new JsonObject();

		j.addProperty("type", type.toString());
		j.addProperty("model", model.toString());
		j.addProperty("material", material);

		jo.add("model" + i, j);

		return jo;
	}
}
