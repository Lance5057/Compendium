package com.lance5057.compendium.client.models.multistylematerial.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;

public class MultiStyleMaterialUnbakedModel {
	Map<String, ResourceLocation> multistyles = new HashMap<String, ResourceLocation>();
	Map<String, UnbakedModel> models = new HashMap<String, UnbakedModel>();

	public MultiStyleMaterialUnbakedModel(_MaterialBase mb, String model, List<String> styles) {
		styles.forEach(s -> multistyles.put(s, ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
				"block/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/" + model)));
	}

	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {

	}

	public MultiStyleMaterialBakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Map<String, BakedModel> bakedModels = new HashMap<String, BakedModel>();
		models.forEach((k, v) -> {
			BakedModel baked = v.bake(baker, spriteGetter, modelState);

			bakedModels.put(k, baked);
		});
		return new MultiStyleMaterialBakedModel(bakedModels);
	}
}
