package com.lance5057.compendium.client.models.style;

import java.util.List;

import com.lance5057.compendium.styleblock.StyleType;

import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class StyleModelData {
	public final static ModelProperty<List<StyleType>> STYLES = new ModelProperty<>();

//	public static ModelData.Builder builder(StyleType... s) {
//		return ModelData.builder().with(STYLES, List.of(s));
//	}

	public static ModelData.Builder builder(List<StyleType> s) {
		return ModelData.builder().with(STYLES, s);
	}
}
