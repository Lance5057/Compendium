package com.lance5057.compendium.client.models.style;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.style.model.StyleModelBuilder;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class StyleBlockModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T baseModel;
	List<StyleModelBuilder> indexModels;
	String type;

	protected StyleBlockModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("style"), parent, existingFileHelper, false);
		indexModels = new ArrayList<StyleModelBuilder>();
	}

	public StyleBlockModelBuilder<T> base(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		baseModel = model;
		return this;
	}

	public StyleBlockModelBuilder<T> add(StyleModelBuilder model) {
		Preconditions.checkNotNull(model, "model must not be null");
		indexModels.add(model);
		return this;
	}

	public static <T extends ModelBuilder<T>> StyleBlockModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new StyleBlockModelBuilder<>(parent, helper);
	}

	@Override
	public JsonObject toJson(JsonObject json) {

//		json.add("base", baseModel.toJson());

		if (indexModels != null) {
			for (int i = 0; i < indexModels.size(); i++)
				indexModels.get(i).toJson(json, i);
		}

		json.addProperty("count", indexModels.size());
		json.addProperty("loader", loaderId.toString());

		return json;
	}
}
