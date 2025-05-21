package com.lance5057.compendium.client.models.style;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.multimaterial.MaterialSwapModelBuilder;
import com.lance5057.compendium.client.models.multimaterial.model.IndexModelBuilder;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class StyleModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T baseModel;
	List<T> indexModels;
	String type;

	protected StyleModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("style"), parent, existingFileHelper, false);
		indexModels = new ArrayList<T>();
	}

	public StyleModelBuilder<T> base(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		baseModel = model;
		return this;
	}

	public StyleModelBuilder<T> add(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		indexModels.add(model);
		return this;
	}

	public static <T extends ModelBuilder<T>> StyleModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new StyleModelBuilder<>(parent, helper);
	}

	@Override
	public JsonObject toJson(JsonObject json) {

		json.add("base", baseModel.toJson());

		if (indexModels != null) {
			for (int i = 0; i < indexModels.size(); i++)
				indexModels.get(i).toJson();
		}

//		json.addProperty("count", models.size());
		json.addProperty("loader", loaderId.toString());

		return json;
	}
}
