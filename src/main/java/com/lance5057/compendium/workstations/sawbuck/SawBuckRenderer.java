package com.lance5057.compendium.workstations.sawbuck;

import org.joml.Quaternionf;

import com.lance5057.compendium.util.rendering.RenderUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations._bases.client.MultiToolBlockEntityRenderer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class SawBuckRenderer extends MultiToolBlockEntityRenderer<SawBuckBlockEntity> {
	int timer = 0;
	int toolRandom = 0;

	AnimationFloatTransform transform = new AnimationFloatTransform();

	public SawBuckRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void renderInventory(SawBuckBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

//		transform.setScale(0.5f);
//		transform.setRotation(45, 0, 90);
//		transform.setLocation(8f, 18f, 8f);

		if (tileEntityIn.getCurrentTool() != null && tileEntityIn.getCurrentTool().model() != null) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.5f, 1, 0.5f);
			Quaternionf q = tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING).getRotation();

			matrixStackIn.scale(0.5f, 0.5f, 0.5f);
			matrixStackIn.mulPose(q);
			matrixStackIn.mulPose(RenderUtil.createQuaternion(-90, 0, 0, true));

			matrixStackIn.translate(-0.5f, 0, -0.5f);

			tileEntityIn.getCurrentTool().model().forEach(b -> {
				RenderUtil.loadModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, b, timer);
			});

			matrixStackIn.popPose();
		}

		timer++;
	}

}
