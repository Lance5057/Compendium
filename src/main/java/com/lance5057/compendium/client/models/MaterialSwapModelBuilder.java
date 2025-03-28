package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MaterialSwapModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T baseModel;
	List<IndexModelBuilder> models;
	MATERIAL_TYPES type;

	protected MaterialSwapModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("material_swap"), parent, existingFileHelper, false);
		models = new ArrayList<IndexModelBuilder>();
	}

	public MaterialSwapModelBuilder<T> base(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		baseModel = model;
		return this;
	}

	public MaterialSwapModelBuilder<T> add(IndexModelBuilder model) {
		Preconditions.checkNotNull(model, "model must not be null");
		models.add(model);
		return this;
	}

	public static <T extends ModelBuilder<T>> MaterialSwapModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new MaterialSwapModelBuilder<>(parent, helper);
	}

	@Override
	public JsonObject toJson(JsonObject json) {

		json.add("base", baseModel.toJson());

		if (models != null) {
			for (int i = 0; i < models.size(); i++)
				models.get(i).toJson(json, i);
		}

		json.addProperty("count", models.size());
		json.addProperty("loader", loaderId.toString());

		return json;
	}

}
