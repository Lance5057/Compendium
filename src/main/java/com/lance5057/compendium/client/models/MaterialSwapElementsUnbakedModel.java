package com.lance5057.compendium.client.models;

import java.util.function.Function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;
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
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class MaterialSwapElementsUnbakedModel implements IUnbakedGeometry<MaterialSwapElementsUnbakedModel> {
	private final ResourceLocation baseModel;
	private final MATERIAL_TYPES materialType;

	public MaterialSwapElementsUnbakedModel(ResourceLocation baseModel, MATERIAL_TYPES type) {
		this.baseModel = baseModel;
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
		UnbakedModel unbaked = baker.getModel(baseModel);
		BakedModel baseBakedModel = unbaked.bake(baker, spriteGetter, modelState);

		return new MaterialSwapElementsBakedModel(baseBakedModel, materialType);
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

			ResourceLocation parentLocation = ResourceLocation.parse(jsonObject.get("parent").getAsString());
//			BlockModel baseModel = deserializationContext.deserialize(jsonObject, BlockModel.class); /* FIX THIS */

			return new MaterialSwapElementsUnbakedModel(parentLocation, type);
		}
	}

}