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
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MultiStyleMaterialUnbakedModel implements IUnbakedGeometry<MultiStyleMaterialUnbakedModel> {

	private UnbakedModel baseModel;

	private List<MultiStyleMaterialUnbakedModel.Layer> layers = new ArrayList<MultiStyleMaterialUnbakedModel.Layer>();

	public MultiStyleMaterialUnbakedModel(BlockModel baseModel2, List<MultiStyleMaterialUnbakedModel.Layer> layers) {
		this.baseModel = baseModel2;
		this.layers = layers;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
		if (baseModel != null)
			this.baseModel.resolveParents(modelGetter);
		else
		{
			this.baseModel = modelGetter.apply(ResourceLocation.fromNamespaceAndPath("minecraft", "block/block"));
			this.baseModel.resolveParents(modelGetter);
		}

		if (layers != null)
			this.layers.forEach(l -> l.resolveParents(modelGetter, context));
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {

		List<MultiStyleMaterialBakedModel.BakedLayer> bakedLayers = new ArrayList<MultiStyleMaterialBakedModel.BakedLayer>();
		if (layers != null) {
			for (int i = 0; i < layers.size(); i++) {
				bakedLayers.add(layers.get(i).bake(context, baker, spriteGetter, modelState, overrides));
			}
		}
		return new MultiStyleMaterialBakedModel(baseModel.bake(baker, spriteGetter, modelState), bakedLayers);
	}

	public static class Layer {
		public final String modelBase;
		public final List<MATERIAL_TYPES> validTypes;
		public final List<String> styles;

		public Map<String, Map<String, UnbakedModel>> models = new HashMap<String, Map<String, UnbakedModel>>();

		public Layer(String modelBase, List<MATERIAL_TYPES> validTypes, List<String> styles) {
			this.modelBase = modelBase;
			this.styles = styles;
			this.validTypes = validTypes;

		}

		public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter,
				IGeometryBakingContext context) {

			Map<String, Map<String, ResourceLocation>> locations = new HashMap<String, Map<String, ResourceLocation>>();
			for (IIndexEntry i : CompendiumIndex.index) {
				if (i instanceof _MaterialBase mb) {
					if (validTypes.contains(mb.getType())) {
						Map<String, ResourceLocation> l = new HashMap<String, ResourceLocation>();

						for (String s : styles) {
							ResourceLocation rc = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
									"block/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name
											+ "/chair/" + modelBase + "/" + s);

							l.put(s, rc);
						}

						locations.put(mb.name, l);
					}
				}
			}

			locations.forEach((k, v) -> {
				Map<String, UnbakedModel> m = new HashMap<String, UnbakedModel>();

				v.forEach((key, value) -> {
					UnbakedModel um = modelGetter.apply(value);
					if (um == null)
						um = modelGetter.apply(ModelBakery.MISSING_MODEL_LOCATION);
					else
						um.resolveParents(modelGetter);

					m.put(key, um);
				});
				models.put(k, m);
			});
		}

		public MultiStyleMaterialBakedModel.BakedLayer bake(IGeometryBakingContext context, ModelBaker baker,
				Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {

			Map<String, Map<String, BakedModel>> bakedModels = new HashMap<String, Map<String, BakedModel>>();
			models.forEach((k, v) -> {
				Map<String, BakedModel> bm = new HashMap<String, BakedModel>();
				v.forEach((key, value) -> {
					BakedModel baked = value.bake(baker, spriteGetter, modelState);
					bm.put(key, baked);
				});

				bakedModels.put(k, bm);
			});
			return new MultiStyleMaterialBakedModel.BakedLayer(validTypes, bakedModels);
		}

		public static Layer read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException {

			List<MATERIAL_TYPES> ty = new ArrayList<MATERIAL_TYPES>();
			List<String> st = new ArrayList<String>();

			JsonArray t = jsonObject.getAsJsonArray("valid");
			t.asList().forEach(i -> ty.add(MATERIAL_TYPES.valueOf(i.getAsString())));

			JsonArray s = jsonObject.getAsJsonArray("styles");
			s.asList().forEach(i -> st.add(i.getAsString()));

			String model = jsonObject.get("modelBase").getAsString();

			return new Layer(model, ty, st);
		}

		public void toJson(JsonObject json, int layerID) {
			JsonObject l = new JsonObject();

			JsonArray t = new JsonArray();

			validTypes.forEach(i -> t.add(i.toString()));

			l.add("valid", t);

			JsonArray v = new JsonArray();

			styles.forEach(i -> v.add(i.toString()));

			l.add("styles", v);

			l.addProperty("modelBase", this.modelBase);

			json.add("layer" + layerID, l);
		}
	}

	public static final class Loader implements IGeometryLoader<MultiStyleMaterialUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("multi_style_material");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MultiStyleMaterialUnbakedModel read(JsonObject jsonObject,
				JsonDeserializationContext deserializationContext) throws JsonParseException {

			if (jsonObject.has("base")) {
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
			return new MultiStyleMaterialUnbakedModel(null, null);
		}

		public static <T extends ModelBuilder<T>> CustomLoaderBuilder<T> builder(T parent,
				ExistingFileHelper existingFileHelper) {
			return new CustomLoaderBuilder<T>(ID, parent, existingFileHelper, true) {
			};
		}
	}
}
