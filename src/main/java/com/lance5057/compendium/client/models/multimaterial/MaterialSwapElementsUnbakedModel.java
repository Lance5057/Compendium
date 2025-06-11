package com.lance5057.compendium.client.models.multimaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.multimaterial.model.BasicIndexModel;
import com.lance5057.compendium.client.models.multimaterial.model.IndexModel;
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

public class MaterialSwapElementsUnbakedModel implements IUnbakedGeometry<MaterialSwapElementsUnbakedModel> {
	private final BlockModel baseModel;
	private final List<IndexModel> indexModels;

	public MaterialSwapElementsUnbakedModel(BlockModel baseModel2) {
		this.baseModel = baseModel2;
		this.indexModels = List.of();
	}

	public MaterialSwapElementsUnbakedModel(BlockModel baseModel2, List<IndexModel> models) {
		this.baseModel = baseModel2;
		this.indexModels = models;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
		this.baseModel.resolveParents(modelGetter);
		for (IndexModel im : indexModels) {
			im.model = modelGetter.apply(im.modelRC);
			im.model.resolveParents(modelGetter);
			if (im.model == null)
				im.model = modelGetter.apply(ModelBakery.MISSING_MODEL_LOCATION);
		}
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Map<String, BasicIndexModel> quads = new HashMap<String, BasicIndexModel>();

		for (IndexModel im : indexModels) {

//			for (IIndexEntry i : CompendiumIndex.index) {
//				if (i instanceof _MaterialBase mb) {
//					if (mb.getType() == im.type) {
						replaceAndBake(spriteGetter, baker, modelState, quads, im, im.material);

//					}
//				}
//			}

			// add invalid
			
		}
		
//		replaceAndBake(spriteGetter, baker, modelState, quads, im, "invalid");

		return new MaterialSwapElementsBakedModel(baseModel.bake(baker, spriteGetter, modelState), quads);
	}

	private void replaceAndBake(Function<Material, TextureAtlasSprite> spriteGetter, ModelBaker baker,
			ModelState modelState, Map<String, BasicIndexModel> quads, IndexModel im, String name) {

		BakedModel baked = im.model.bake(baker, spriteGetter, modelState);

		BasicIndexModel bim = new BasicIndexModel(name, baked);

		quads.put(name, bim);
	}

	public static final class Loader implements IGeometryLoader<MaterialSwapElementsUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("material_swap");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MaterialSwapElementsUnbakedModel read(JsonObject jsonObject,
				JsonDeserializationContext deserializationContext) throws JsonParseException {

			BlockModel base = deserializationContext.deserialize(GsonHelper.getAsJsonObject(jsonObject, "base"),
					BlockModel.class);

			List<IndexModel> models = new ArrayList<IndexModel>();

			int count = jsonObject.get("count").getAsInt();

			for (int i = 0; i < count; i++) {
				JsonObject mat = jsonObject.get("model" + i).getAsJsonObject();
				
				MATERIAL_TYPES s = MATERIAL_TYPES.valueOf(mat.get("type").getAsString());
				String t = mat.get("material").getAsString();
				String m = mat.get("model").getAsString();

				models.add(new IndexModel(s, t, ResourceLocation.parse(m)));
			}

			return new MaterialSwapElementsUnbakedModel(base, models);
		}
	}

}