package com.lance5057.compendium.client;

import java.util.List;

import org.joml.Quaternionf;

import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;
import net.neoforged.neoforge.client.model.renderable.IRenderable;

public class RenderUtil {

	public static void loadModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, BlacklistedModel model, float timer) {

		if (model.isBlock) {
			IRenderable<ModelData> bm = BakedModelRenderable.of(model.rc).withModelDataContext();
			blockModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, bm, model.blacklist,
					model.transform, timer);

		} else

		{
			Item item = BuiltInRegistries.ITEM.get(model.rc);
			itemModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, item, model.blacklist,
					model.transform, timer);
		}
	}

	public static void blockModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, IRenderable<ModelData> bm, List<Integer> blacklist,
			AnimationFloatTransform transform, float timer) {
		matrixStackIn.pushPose();
		{
			matrixStackIn.translate(transform.getLocation().getX().animate(timer) / 16,
					transform.getLocation().getY().animate(timer) / 16,
					transform.getLocation().getZ().animate(timer) / 16);
			matrixStackIn.mulPose(
					new Quaternionf().rotateXYZ((float) Math.toRadians(transform.getRotation().getX().animate(timer)),
							(float) Math.toRadians(transform.getRotation().getY().animate(timer)),
							(float) Math.toRadians(transform.getRotation().getZ().animate(timer))));
			matrixStackIn.scale(1f + transform.getScale().getX().animate(timer),
					1f + transform.getScale().getY().animate(timer), 1f + transform.getScale().getZ().animate(timer));

			bm.render(matrixStackIn, bufferIn, texture -> RenderType.entityTranslucent(texture), combinedLightIn,
					combinedOverlayIn, timer, ModelData.EMPTY);

		}
		matrixStackIn.popPose();

	}

	public static void itemModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, Item item, List<Integer> blacklist, AnimationFloatTransform transform, float timer) {
		matrixStackIn.pushPose();
		{
			ItemStack stack = new ItemStack(item);
			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			BakedModel bakedmodel = itemRenderer.getModel(stack, null, null, 0);

			matrixStackIn.translate(
					transform.getLocation().getX().getOffset() + transform.getLocation().getX().animate(timer) / 16,
					transform.getLocation().getY().getOffset() + transform.getLocation().getY().animate(timer) / 16,
					transform.getLocation().getZ().getOffset() + transform.getLocation().getZ().animate(timer) / 16);
			matrixStackIn.mulPose(new Quaternionf().rotateXYZ(
					(float) transform.getRotation().getX().getOffset() + transform.getRotation().getX().animate(timer),
					(float) transform.getRotation().getY().getOffset() + transform.getRotation().getY().animate(timer),
					(float) transform.getRotation().getZ().getOffset()
							+ transform.getRotation().getZ().animate(timer)));
			matrixStackIn.scale(1f + transform.getScale().getX().animate(timer),
					1f + transform.getScale().getY().animate(timer), 1f + transform.getScale().getZ().animate(timer));
			itemRenderer.render(stack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, combinedLightIn,
					combinedOverlayIn, bakedmodel);
		}
		matrixStackIn.popPose();

	}
}
