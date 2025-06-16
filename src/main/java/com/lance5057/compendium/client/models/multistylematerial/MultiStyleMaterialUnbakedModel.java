package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.multistylematerial.models.MultiStyleMaterialModel;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class MultiStyleMaterialUnbakedModel implements IUnbakedGeometry<MultiStyleMaterialUnbakedModel> {
	private final BlockModel baseModel;

	private List<MultiStyleMaterialUnbakedModel.Layer> layers = new ArrayList<MultiStyleMaterialUnbakedModel.Layer>();

	public MultiStyleMaterialUnbakedModel(BlockModel baseModel2, List<MultiStyleMaterialUnbakedModel.Layer> layers) {
		this.baseModel = baseModel2;
		this.layers = layers;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
		this.baseModel.resolveParents(modelGetter);

		this.layers.forEach(l -> l.resolveParents(modelGetter, context));
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {

		List<MultiStyleMaterialBakedModel.BakedLayer> bakedLayers = new ArrayList<MultiStyleMaterialBakedModel.BakedLayer>();

		for (int i = 0; i < layers.size(); i++) {
			bakedLayers.add(layers.get(i).bake(context, baker, spriteGetter, modelState, overrides));
		}

		return new MultiStyleMaterialBakedModel(baseModel.bake(baker, spriteGetter, modelState), bakedLayers);
	}

	public static class Layer {
		private final String model;
		public final List<MATERIAL_TYPES> validTypes;
		public Map<String, Map<String, UnbakedModel>> models = new HashMap<String, Map<String, UnbakedModel>>();

		public Layer(List<MATERIAL_TYPES> validTypes, String model) {
			this.model = model;
			this.validTypes = validTypes;

		}

		public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter,
				IGeometryBakingContext context) {

			Map<String, MultiStyleMaterialModel.Unbaked> locations = new HashMap<String, MultiStyleMaterialModel.Unbaked>();
			for (IIndexEntry i : CompendiumIndex.index) {
				if (i instanceof _MaterialBase mb) {
					if (validTypes.contains(mb.getType())) {

						locations.put(mb.name, new MultiStyleMaterialModel.Unbaked(mb, model, null));
					}
				}
			}
//
//			ResourceLocation rc = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
//					"block/material/" + validTypes.get(0).toString().toLowerCase() + "/invalid/" + model);
//
//			locations.put("invalid", rc);
//
//			locations.forEach((k, v) -> {
//				UnbakedModel um = modelGetter.apply(v);
//				if (um == null)
//					um = modelGetter.apply(ModelBakery.MISSING_MODEL_LOCATION);
//				else
//					um.resolveParents(modelGetter);
//				models.put(k, um);
//			});
		}

		public MultiStyleMaterialBakedModel.BakedLayer bake(IGeometryBakingContext context, ModelBaker baker,
				Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {

			Map<String, BakedModel> bakedModels = new HashMap<String, BakedModel>();
//			models.forEach((k, v) -> {
//				BakedModel baked = v.bake(baker, spriteGetter, modelState);
//
//				bakedModels.put(k, baked);
//			});
			return new MultiStyleMaterialBakedModel.BakedLayer(validTypes, bakedModels);
		}

		public static Layer read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException {
//			String inv = jsonObject.get("invalid").getAsString();
//			ResourceLocation invalid = ResourceLocation.parse(inv);

			List<MATERIAL_TYPES> ty = new ArrayList<MATERIAL_TYPES>();

			JsonArray t = jsonObject.getAsJsonArray("valid");
			t.asList().forEach(i -> ty.add(MATERIAL_TYPES.valueOf(i.getAsString())));

			String model = jsonObject.get("model").getAsString();

			return new Layer(ty, model);
		}

		public void toJson(JsonObject json, int layerID) {
			JsonObject l = new JsonObject();

			JsonArray t = new JsonArray();

			validTypes.forEach(i -> t.add(i.toString()));

			l.add("valid", t);

			l.addProperty("model", this.model);

			json.add("layer" + layerID, l);
		}
	}

	public static final class Loader implements IGeometryLoader<MultiStyleMaterialUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("multistylematerial");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MultiStyleMaterialUnbakedModel read(JsonObject jsonObject,
				JsonDeserializationContext deserializationContext) throws JsonParseException {

			BlockModel base = deserializationContext.deserialize(GsonHelper.getAsJsonObject(jsonObject, "base"),
					BlockModel.class);

			int count = jsonObject.get("layer_count").getAsInt();
			List<Layer> l = new ArrayList<Layer>();
			for (int i = 0; i < count; i++) {
				JsonObject j = jsonObject.get("layer" + i).getAsJsonObject();
				l.add(Layer.read(j, deserializationContext));
			}

			return new MultiStyleMaterialUnbakedModel(base, l);
		}
	}
}
