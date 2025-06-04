package com.lance5057.compendium.client.models.multimaterial;

import java.util.List;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class MultiMaterialModelData {
	public final static ModelProperty<List<String>> STATE = new ModelProperty<>();

	public static ModelData.Builder builder(List<String> s) {
		return ModelData.builder().with(STATE, s);
	}
}
