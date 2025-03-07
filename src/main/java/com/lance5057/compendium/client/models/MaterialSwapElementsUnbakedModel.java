package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;

import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.IModelBuilder;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;
import net.neoforged.neoforge.client.model.geometry.UnbakedGeometryHelper;

public class MaterialSwapElementsUnbakedModel implements IUnbakedGeometry<MaterialSwapElementsUnbakedModel> {
	private final List<BlockElement> elements;

	public MaterialSwapElementsUnbakedModel(List<BlockElement> elements) {
		this.elements = elements;
	}

	protected void addQuads(IGeometryBakingContext context, IModelBuilder<?> modelBuilder, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState) {
		var postTransform = QuadTransformers.empty();
		var rootTransform = context.getRootTransform();
		if (!rootTransform.isIdentity())
			postTransform = UnbakedGeometryHelper.applyRootTransform(modelState, rootTransform);

		for (BlockElement element : elements) {
			for (Direction direction : element.faces.keySet()) {
				do this!
				var face = element.faces.get(direction)
				var sprite = spriteGetter.apply(context.getMaterial(face.texture()))
				var quad = BlockModel.bakeFace(element, face, sprite, direction, modelState)
				postTransform.processInPlace(quad);

				if (face.cullForDirection() == null)
					modelBuilder.addUnculledFace(quad);
				else
					modelBuilder.addCulledFace(modelState.getRotation().rotateTransform(face.cullForDirection()), quad);
			}
		}
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		TextureAtlasSprite particle = spriteGetter.apply(context.getMaterial("particle"));

		var renderTypeHint = context.getRenderTypeHint();
		var renderTypes = renderTypeHint != null ? context.getRenderType(renderTypeHint) : RenderTypeGroup.EMPTY;
		IModelBuilder<?> builder = IModelBuilder.of(context.useAmbientOcclusion(), context.useBlockLight(),
				context.isGui3d(), context.getTransforms(), overrides, particle, renderTypes);

		addQuads(context, builder, baker, spriteGetter, modelState);

		return builder.build();
	}

	public static final class Loader implements IGeometryLoader<MaterialSwapElementsUnbakedModel> {
		public static ResourceLocation ID = Compendium.modLoc("material_swap");
		public static final Loader INSTANCE = new Loader();

		public Loader() {
		}

		@Override
		public MaterialSwapElementsUnbakedModel read(JsonObject jsonObject,
				JsonDeserializationContext deserializationContext) throws JsonParseException {
			if (!jsonObject.has("elements"))
				throw new JsonParseException("An element model must have an \"elements\" member.");

			List<BlockElement> elements = new ArrayList<>();
			for (JsonElement element : GsonHelper.getAsJsonArray(jsonObject, "elements")) {
				elements.add(deserializationContext.deserialize(element, BlockElement.class));
			}

			return new MaterialSwapElementsUnbakedModel(elements);
		}
	}

}