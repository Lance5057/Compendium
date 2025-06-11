package com.lance5057.compendium.client.models.multimaterial;

import java.util.List;

import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class MultiMaterialModelData {
	public final static ModelProperty<List<MultiMaterialType>> STATE = new ModelProperty<>();

	public static ModelData.Builder builder(List<MultiMaterialType> s) {
		return ModelData.builder().with(STATE, s);
	}
}
