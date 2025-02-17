package com.lance5057.compendium.workstations.hammeringstation;

import org.joml.Quaternionf;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.util.rendering.RenderUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations._bases.client.MultiToolBlockEntityRenderer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;
import net.neoforged.neoforge.client.model.renderable.IRenderable;
import net.neoforged.neoforge.items.ItemStackHandler;

public class HammeringStationRenderer extends MultiToolBlockEntityRenderer<HammeringStationBlockEntity> {
	int timer = 0;
	int toolRandom = 0;

	AnimationFloatTransform transform = new AnimationFloatTransform();

	public HammeringStationRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void renderInventory(HammeringStationBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

		ItemStackHandler inv = tileEntityIn.getInventory();

		transform.setScale(0.5f);
		transform.setLocation(8f, 20f, 8f);

		ItemStack input = inv.getStackInSlot(0);

		RenderUtil.itemModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, input, transform, timer);

		if (tileEntityIn.getCurrentTool() != null && tileEntityIn.getCurrentTool().model() != null) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.5f, 1, 0.5f);
			Quaternionf q = tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING).getRotation();

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
