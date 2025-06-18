package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MultiStyleMaterialBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T baseModel;
	List<MultiStyleMaterialUnbakedModel.Layer> types = new ArrayList<MultiStyleMaterialUnbakedModel.Layer>();
	protected MultiStyleMaterialBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("multi_style_material"), parent, existingFileHelper, false);
	}

	public static <T extends ModelBuilder<T>> MultiStyleMaterialBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new MultiStyleMaterialBuilder<>(parent, helper);
	}
	
	public MultiStyleMaterialBuilder<T> base(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		baseModel = model;
		return this;
	}
	
	public MultiStyleMaterialBuilder<T> addLayer(MultiStyleMaterialUnbakedModel.Layer l) {
		types.add(l);
		return this;
	}
	
	@Override
	public T end() {
		Preconditions.checkState(!types.isEmpty(), "At least one layer must be added!");
		return parent;
	}
	
	@Override
	public JsonObject toJson(JsonObject json) {

		json.add("base", baseModel.toJson());

		json.addProperty("loader", loaderId.toString());

		json.addProperty("layer_count", types.size());
		for (int i = 0; i < this.types.size(); i++) {
			this.types.get(i).toJson(json, i);
		}

		return json;
	}

}
