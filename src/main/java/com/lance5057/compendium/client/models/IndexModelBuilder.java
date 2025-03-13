package com.lance5057.compendium.client.models;

import com.google.gson.JsonObject;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.neoforged.neoforge.client.model.generators.ModelBuilder;

public class IndexModelBuilder<T extends ModelBuilder<T>> {
	MATERIAL_TYPES type;
	T model;

	public IndexModelBuilder(MATERIAL_TYPES t, T b) {
		this.type = t;
		this.model = b;
	}

	public JsonObject toJson(JsonObject jo, int i) {
		JsonObject j = new JsonObject();

		j.addProperty("type", type.toString());
		j.add("model", model.toJson());

		jo.add("model_" + i, j);

		return jo;
	}
}
