package com.lance5057.compendium.client.models.multimaterial;

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
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialBakedModel.BakedLayer;
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
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class MultiMaterialUnbakedModel implements IUnbakedGeometry<MultiMaterialUnbakedModel> {
	private final BlockModel baseModel;

	private List<Layer> layers = new ArrayList<Layer>();

	public MultiMaterialUnbakedModel(BlockModel baseModel2, List<Layer> layers) {
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

		List<BakedLayer> bakedLayers = new ArrayList<BakedLayer>();

		for (int i = 0; i < layers.size(); i++) {
			bakedLayers.add(layers.get(i).bake(context, baker, spriteGetter, modelState, overrides));
		}

		return new MultiMaterialBakedModel(baseModel.bake(baker, spriteGetter, modelState), bakedLayers);
	}

	public static class Layer {
		private final String model;
		public final List<MATERIAL_TYPES> validTypes;
		public Map<String, UnbakedModel> models = new HashMap<String, UnbakedModel>();
		public final int materialLayer;

		public Layer(List<MATERIAL_TYPES> validTypes, String model, int materialLayer) {
			this.model = model;
			this.validTypes = validTypes;
			this.materialLayer = materialLayer;

		}

		public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter,
				IGeometryBakingContext context) {

			Map<String, ResourceLocation> locations = new HashMap<String, ResourceLocation>();
			for (IIndexEntry i : CompendiumIndex.index) {
				if (i instanceof _MaterialBase mb) {
					if (validTypes.contains(mb.getType())) {
						ResourceLocation rc = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/material/"
								+ mb.getType().toString().toLowerCase() + "/" + mb.name + "/" + model);

						locations.put(mb.name, rc);
					}
				}
			}

			ResourceLocation rc = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
					"block/material/" + validTypes.get(0).toString().toLowerCase() + "/invalid/" + model);

			locations.put("invalid", rc);

			locations.forEach((k, v) -> {
				UnbakedModel um = modelGetter.apply(v);
				if (um == null)
					um = modelGetter.apply(ModelBakery.MISSING_MODEL_LOCATION);
				else
					um.resolveParents(modelGetter);
				models.put(k, um);
			});
		}

		public BakedLayer bake(IGeometryBakingContext context, ModelBaker baker,
				Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {

			Map<String, BakedModel> bakedModels = new HashMap<String, BakedModel>();
			models.forEach((k, v) -> {
				BakedModel baked = v.bake(baker, spriteGetter, modelState);

				bakedModels.put(k, baked);
			});
			return new BakedLayer(validTypes, bakedModels, this.materialLayer);
		}

		public static Layer read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException {
//			String inv = jsonObject.get("invalid").getAsString();
//			ResourceLocation invalid = ResourceLocation.parse(inv);

			List<MATERIAL_TYPES> ty = new ArrayList<MATERIAL_TYPES>();

			JsonArray t = jsonObject.getAsJsonArray("valid");
			t.asList().forEach(i -> ty.add(MATERIAL_TYPES.valueOf(i.getAsString())));

			String model = jsonObject.get("model").getAsString();
			int matLayer = jsonObject.get("materialLayer").getAsInt();

			return new Layer(ty, model, matLayer);
		}

		public void toJson(JsonObject json, int layerID) {
			JsonObject l = new JsonObject();

			JsonArray t = new JsonArray();

			validTypes.forEach(i -> t.add(i.toString()));

			l.add("valid", t);

			l.addProperty("model", this.model);
			l.addProperty("materialLayer", this.materialLayer);

			json.add("layer" + layerID, l);
		}
	}

	public static final class Loader implements IGeometryLoader<MultiMaterialUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("multi_material");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MultiMaterialUnbakedModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException {

			BlockModel base = deserializationContext.deserialize(GsonHelper.getAsJsonObject(jsonObject, "base"),
					BlockModel.class);

			int count = jsonObject.get("layer_count").getAsInt();
			List<Layer> l = new ArrayList<Layer>();
			for (int i = 0; i < count; i++) {
				JsonObject j = jsonObject.get("layer" + i).getAsJsonObject();
				l.add(Layer.read(j, deserializationContext));
			}

			return new MultiMaterialUnbakedModel(base, l);
		}
	}

}