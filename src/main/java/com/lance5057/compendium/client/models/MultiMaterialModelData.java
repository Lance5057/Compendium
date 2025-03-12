package com.lance5057.compendium.client.models;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class MultiMaterialModelData {
	public final static ModelProperty<String[]> STATE = new ModelProperty<>();

	public static ModelData.Builder builder(String[] s) {
		return ModelData.builder().with(STATE, s);
	}
}
