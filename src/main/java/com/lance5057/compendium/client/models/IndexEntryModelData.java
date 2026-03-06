package com.lance5057.compendium.client.models;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class IndexEntryModelData {
	public final static ModelProperty<MATERIAL_TYPES> TYPE = new ModelProperty<>();
	public final static ModelProperty<String> NAME = new ModelProperty<>();

	public static ModelData.Builder builder(MATERIAL_TYPES t, String n) {
		return ModelData.builder().with(TYPE, t).with(NAME, n);
	}
}
