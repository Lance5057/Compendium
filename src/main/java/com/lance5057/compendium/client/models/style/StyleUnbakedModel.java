package com.lance5057.compendium.client.models.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.multimaterial.MaterialSwapElementsUnbakedModel;
import com.lance5057.compendium.client.models.multimaterial.model.IndexModel;
import com.lance5057.compendium.client.models.style.model.StyleModel;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

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

public class StyleUnbakedModel implements IUnbakedGeometry<StyleUnbakedModel> {
	private final BlockModel baseModel;
	private final List<StyleModel> indexModels;

	public StyleUnbakedModel(BlockModel baseModel2) {
		this.baseModel = baseModel2;
		this.indexModels = List.of();
	}

	public StyleUnbakedModel(BlockModel baseModel2, List<StyleModel> models) {
		this.baseModel = baseModel2;
		this.indexModels = models;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
		this.baseModel.resolveParents(modelGetter);
		for (StyleModel im : indexModels) {
			im.model = modelGetter.apply(im.modelRC);
			im.model.resolveParents(modelGetter);
		}
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Map<String, BakedModel> quads = new HashMap<String, BakedModel>();

		for (StyleModel im : indexModels) {
			replaceAndBake(spriteGetter, baker, modelState, quads, im);
		}

		return new StyleBakedModel(baseModel.bake(baker, spriteGetter, modelState), quads);
	}

	private void replaceAndBake(Function<Material, TextureAtlasSprite> spriteGetter, ModelBaker baker,
			ModelState modelState, Map<String, BakedModel> quads, StyleModel im) {

		BakedModel baked = im.model.bake(baker, spriteGetter, modelState);

//		BasicIndexModel bim = new BasicIndexModel(im.style, baked);

		quads.put(im.style, baked);
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

			if (jsonObject.has("model")) {
				JsonObject m = jsonObject.getAsJsonObject("model");

				String s = m.get("type").getAsString();
				MATERIAL_TYPES t = MATERIAL_TYPES.valueOf(s);
				String mat = m.get("model").getAsString();

				models.add(new IndexModel(t, ResourceLocation.parse(mat)));

				return new MaterialSwapElementsUnbakedModel(base, models);
			}

			return new MaterialSwapElementsUnbakedModel(base, List.of());
		}
	}

}
