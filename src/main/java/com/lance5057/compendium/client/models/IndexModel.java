package com.lance5057.compendium.client.models;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.resources.ResourceLocation;

public class IndexModel {
	public MATERIAL_TYPES type;

	public MATERIAL_TYPES getType() {
		return type;
	}

	public ResourceLocation getModel() {
		return model;
	}

	public ResourceLocation model;

	public IndexModel(MATERIAL_TYPES t, ResourceLocation b) {
		this.type = t;
		this.model = b;
	}
}
