package com.lance5057.compendium.client.models.style.model;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;

public class StyleModelBuilder {
	String style;
	ResourceLocation model;
//	String material;

	public StyleModelBuilder(String t, ResourceLocation b) {
		this.style = t;
		this.model = b;
	}

	public JsonObject toJson(JsonObject jo, int i) {
		JsonObject j = new JsonObject();

		j.addProperty("style", style.toString());
		j.addProperty("model", model.toString());

		jo.add("model" + i, j);

		return jo;
	}
}