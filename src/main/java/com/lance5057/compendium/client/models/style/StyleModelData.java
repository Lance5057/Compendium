package com.lance5057.compendium.client.models.style;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class StyleModelData {
	public final static ModelProperty<String[]> STYLE = new ModelProperty<>();

	public static ModelData.Builder builder(String[] s) {
		return ModelData.builder().with(STYLE, s);
	}
}
