package com.lance5057.compendium.client.models;

import com.lance5057.compendium.Compendium;

import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MaterialSwapModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {

	protected MaterialSwapModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(Compendium.modLoc("material_swap"), parent, existingFileHelper, false);
	}

	public static <T extends ModelBuilder<T>> MaterialSwapModelBuilder<T> begin(T parent, ExistingFileHelper helper) {
		return new MaterialSwapModelBuilder<>(parent, helper);
	}

}
