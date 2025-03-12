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

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class MaterialSwapElementsUnbakedModel implements IUnbakedGeometry<MaterialSwapElementsUnbakedModel> {
	private final BlockModel baseModel;
	private final MATERIAL_TYPES materialType;

	public MaterialSwapElementsUnbakedModel(BlockModel baseModel2, MATERIAL_TYPES type) {
		this.baseModel = baseModel2;
		this.materialType = type;
	}

//	protected void addQuads(IGeometryBakingContext context, IModelBuilder<?> modelBuilder, ModelBaker baker,
//			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, String name) {
//		var postTransform = QuadTransformers.empty();
//		var rootTransform = context.getRootTransform();
//		if (!rootTransform.isIdentity())
//			postTransform = UnbakedGeometryHelper.applyRootTransform(modelState, rootTransform);
//
//		for (BlockElement element : elements) {
//			for (Direction direction : element.faces.keySet()) {
////				do this!
//				var face = element.faces.get(direction);
//				var sprite = spriteGetter.apply(context.getMaterial(name + "_" + face.texture()));
//				var quad = BlockModel.bakeFace(element, face, sprite, direction, modelState);
//				postTransform.processInPlace(quad);
//
//				if (face.cullForDirection() == null)
//					modelBuilder.addUnculledFace(quad);
//				else
//					modelBuilder.addCulledFace(modelState.getRotation().rotateTransform(face.cullForDirection()), quad);
//			}
//		}
//	}

//	@Override
//	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
//		this.baseModel.resolveParents(modelGetter);
//	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Map<String, BasicIndexQuad> quads = new HashMap<String, BasicIndexQuad>();

		UnbakedModel unbaked = baseModel;
		BakedModel baseBakedModel = unbaked.bake(baker, spriteGetter, modelState);

		for (IIndexEntry i : CompendiumIndex.index) {
			if (i instanceof _MaterialBase mb) {
				if (mb.getType() == materialType) {
					BasicIndexQuad biq = new BasicIndexQuad(mb.name);

					for (Direction d : Direction.values()) {
						List<BakedQuad> q = baseBakedModel.getQuads(null, d, RandomSource.create(), null, null);
						List<BakedQuad> indexQuads = new ArrayList<BakedQuad>();
						for (BakedQuad quad : q) {

							// grab the sprite and change it!
							ResourceLocation atlas = quad.getSprite().atlasLocation();
							ResourceLocation sprite = quad.getSprite().contents().name();

							TextureAtlasSprite tas = spriteGetter
									.apply(new Material(InventoryMenu.BLOCK_ATLAS, sprite));

//							TextureAtlasSprite tas = Minecraft.getInstance().getTextureAtlas(atlas).apply(sprite);

							BakedQuad bq = new BakedQuad(quad.getVertices(), quad.getTintIndex(), quad.getDirection(),
									tas, quad.isShade());

							indexQuads.add(bq);

						}

						biq.quads.put(d, indexQuads);
					}
					quads.put(mb.name, biq);
				}
			}
		}

		return new MaterialSwapElementsBakedModel(baseBakedModel, materialType, quads);
	}

	public static final class Loader implements IGeometryLoader<MaterialSwapElementsUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("material_swap");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MaterialSwapElementsUnbakedModel read(JsonObject jsonObject,
				JsonDeserializationContext deserializationContext) throws JsonParseException {

			MATERIAL_TYPES type = MATERIAL_TYPES.INVALID;
			if (jsonObject.has("material_type")) {
				type = CompendiumIndex.MATERIAL_TYPES.valueOf(jsonObject.get("material_type").getAsString());
			}

			BlockModel baseModel = deserializationContext.deserialize(GsonHelper.getAsJsonObject(jsonObject, "base"),
					BlockModel.class);
//			ResourceLocation parentLocation = ResourceLocation.parse(jsonObject.get("parent").getAsString());

			return new MaterialSwapElementsUnbakedModel(baseModel, type);
		}
	}

}