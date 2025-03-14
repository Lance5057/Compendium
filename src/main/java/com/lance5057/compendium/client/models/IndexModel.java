package com.lance5057.compendium.client.models;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.client.renderer.block.model.BlockModel;

public class IndexModel {
	public MATERIAL_TYPES type;

	public MATERIAL_TYPES getType() {
		return type;
	}

	public BlockModel getModel() {
		return model;
	}

	public BlockModel model;

	public IndexModel(MATERIAL_TYPES t, BlockModel b) {
		this.type = t;
		this.model = b;
	}
}
