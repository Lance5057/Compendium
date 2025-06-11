package com.lance5057.compendium.client.models.multimaterial;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MaterialSwapModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T baseModel;
//	List<IndexModelBuilder> indexModels;
	ResourceLocation invalid;
	MATERIAL_TYPES type;

	protected MaterialSwapModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("material_swap"), parent, existingFileHelper, false);
//		indexModels = new ArrayList<IndexModelBuilder>();
	}

	public MaterialSwapModelBuilder<T> base(T model) {
		Preconditions.checkNotNull(model, "model must not be null");
		baseModel = model;
		return this;
	}

	public MaterialSwapModelBuilder<T> addInvalidLocation(ResourceLocation rc) {
//		Preconditions.checkNotNull(model, "model must not be null");
		invalid = rc;
		return this;
	}

	public static <T extends ModelBuilder<T>> MaterialSwapModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new MaterialSwapModelBuilder<>(parent, helper);
	}

	@Override
	public JsonObject toJson(JsonObject json) {

		json.add("base", baseModel.toJson());

//		if (indexModels != null) {
//			for (int i = 0; i < indexModels.size(); i++)
//				indexModels.get(i).toJson(json, i);
//		}

//		json.addProperty("count", indexModels.size());
		json.addProperty("loader", loaderId.toString());

		return json;
	}

}
