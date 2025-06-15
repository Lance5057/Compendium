package com.lance5057.compendium.client.models.multistylematerial.models;

import java.util.Map;

import net.minecraft.client.resources.model.BakedModel;

public class MultiStyleMaterialBakedModel {
//	public final List<String> styles;
	public final Map<String, BakedModel> models;

	public MultiStyleMaterialBakedModel(Map<String, BakedModel> models) {
		this.models = models;

	}
}
