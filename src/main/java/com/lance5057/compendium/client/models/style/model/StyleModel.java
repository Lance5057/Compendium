package com.lance5057.compendium.client.models.style.model;

import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

public class StyleModel {
	public String style;

	public String getStyle() {
		return style;
	}

	public ResourceLocation getRC() {
		return modelRC;
	}

	public ResourceLocation modelRC;
	public UnbakedModel model;

	public StyleModel(String t, ResourceLocation b) {
		this.style = t;
		this.modelRC = b;
	}
}
