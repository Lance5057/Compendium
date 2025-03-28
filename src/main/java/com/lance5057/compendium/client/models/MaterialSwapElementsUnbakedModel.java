package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class MaterialSwapElementsUnbakedModel implements IUnbakedGeometry<MaterialSwapElementsUnbakedModel> {
	private final BlockModel baseModel;
	private final List<IndexModel> models;

	public MaterialSwapElementsUnbakedModel(BlockModel baseModel2) {
		this.baseModel = baseModel2;
		this.models = List.of();
	}

	public MaterialSwapElementsUnbakedModel(BlockModel baseModel2, List<IndexModel> models) {
		this.baseModel = baseModel2;
		this.models = models;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
		this.baseModel.resolveParents(modelGetter);
//		for (IndexModel im : models) {
//			im.model.resolveParents(modelGetter);
//		}
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Map<String, BasicIndexModel> quads = new HashMap<String, BasicIndexModel>();

		for (IndexModel im : models) {

			for (IIndexEntry i : CompendiumIndex.index) {
				if (i instanceof _MaterialBase mb) {
					if (mb.getType() == im.type) {
						replaceAndBake(spriteGetter, baker, modelState, quads, im, mb.name);

					}
				}
			}

			// add invalid
			replaceAndBake(spriteGetter, baker, modelState, quads, im, "invalid");
		}

		return new MaterialSwapElementsBakedModel(baseModel.bake(baker, spriteGetter, modelState), quads);
	}

	private void replaceAndBake(Function<Material, TextureAtlasSprite> spriteGetter, ModelBaker baker,
			ModelState modelState, Map<String, BasicIndexModel> quads, IndexModel im, String name) {
		ResourceLocation r = ResourceLocation.parse(im.getModel().toString().replace("invalid", name));
		BlockModel bm = (BlockModel) baker.getModel(r);
		BakedModel baked = bm.bake(baker, spriteGetter, modelState);

		BasicIndexModel bim = new BasicIndexModel(name, baked);
//		List<BlockElement> e = bm.getElements();
//		BasicIndexQuad biq = new BasicIndexQuad(name);
//		for (BlockElement element : e) {b
//
//			for (Direction d : Direction.values()) {
//				List<BakedQuad> indexQuads = new ArrayList<BakedQuad>();
//				BlockElementFace face = element.faces.get(d);
//
//				Material mat = bm.getMaterial(face.texture());
//				String s = mat.texture().toString().replace("invalid", name);
//				ResourceLocation r = ResourceLocation.parse(s);
//				Material newMat = new Material(mat.atlasLocation(), r);
//				TextureAtlasSprite sprite = spriteGetter.apply(newMat);
//
//				BakedQuad quad = BlockModel.bakeFace(element, face, sprite, d, modelState);
//
//				indexQuads.add(quad);
//				biq.addAll(d, indexQuads);
//			}
//
//		}
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

//			MATERIAL_TYPES type = MATERIAL_TYPES.INVALID;
			if (jsonObject.has("count")) {
				int count = jsonObject.get("count").getAsInt();

				List<IndexModel> models = new ArrayList<IndexModel>();

//				for (int i = 0; i < count; i++) {
				if (jsonObject.has("model")) {
					JsonObject m = jsonObject.getAsJsonObject("model");

					String s = m.get("type").getAsString();
					MATERIAL_TYPES t = MATERIAL_TYPES.valueOf(s);
					String mat = m.get("model").getAsString();

//					BlockModel b = deserializationContext.deserialize(GsonHelper.getAsJsonObject(m, "model"),
//							BlockModel.class);

					models.add(new IndexModel(t, ResourceLocation.parse(mat)));
				}
//				}
				return new MaterialSwapElementsUnbakedModel(base, models);
			}

//			ResourceLocation parentLocation = ResourceLocation.parse(jsonObject.get("parent").getAsString());

			return new MaterialSwapElementsUnbakedModel(base, List.of());
		}
	}

}