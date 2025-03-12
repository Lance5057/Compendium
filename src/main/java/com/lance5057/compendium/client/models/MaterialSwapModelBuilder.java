package com.lance5057.compendium.client.models;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MaterialSwapModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	T base;
	MATERIAL_TYPES type;

	protected MaterialSwapModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("material_swap"), parent, existingFileHelper, false);
//		this.type = t;
	}

	public MaterialSwapModelBuilder<T> base(T modelBuilder) {
		Preconditions.checkNotNull(modelBuilder, "modelBuilder must not be null");
		base = modelBuilder;
		return this;
	}

	public static <T extends ModelBuilder<T>> MaterialSwapModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new MaterialSwapModelBuilder<>(parent, helper);
	}

	public MaterialSwapModelBuilder<T> setType(MATERIAL_TYPES t) {
		type = t;
		return this;
	}

	@Override
	public JsonObject toJson(JsonObject json) {

		if (base != null) {
			json.add("base", base.toJson());
		}

//		JsonObject j = new JsonObject();
//		JsonObject base = super.toJson(json);
		json.addProperty("loader", loaderId.toString());
//		j.add("base", base);

		json.addProperty("material_type", type.toString());

		return json;
	}

}
