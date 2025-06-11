package com.lance5057.compendium.client.models.multimaterial.model;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

public class IndexModel {
	public MATERIAL_TYPES type;
	public String material;

	public MATERIAL_TYPES getType() {
		return type;
	}

	public ResourceLocation getRC() {
		return modelRC;
	}

	public ResourceLocation modelRC;
	public UnbakedModel model;

//	public void setModel(UnbakedModel model) {
//		this.model = model;
//	}

	public IndexModel(MATERIAL_TYPES t, String m, ResourceLocation b) {
		this.type = t;
		this.modelRC = b;
		this.material = m;
	}
}
