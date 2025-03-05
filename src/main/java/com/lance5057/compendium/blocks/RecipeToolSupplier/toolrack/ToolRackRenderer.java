package com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class ToolRackRenderer implements BlockEntityRenderer<ToolRackBlockEntity> {

	public ToolRackRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(ToolRackBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		IItemHandler itemInteractionHandler = blockEntity.getItems();

		Direction dir = blockEntity.getBlockState().getValue(ToolRackBlock.FACING);

		poseStack.pushPose();
		// poseStack.mulPose(new Quaternion(0, -dir.toYRot(), 0, true));
		for (int i = 0; i < 2; i++) {

			ItemStack item = itemInteractionHandler.getStackInSlot(i);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, blockEntity.getLevel(), null, 0);
				poseStack.pushPose();
				poseStack.translate(0.5f, 0.35, 0.5f);
				poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-dir.toYRot()), 0));
				poseStack.translate(0.4, 0.0, -0.1);

				if (i == 1) {
					poseStack.translate(0, 0.5, 0.2);
					poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(180), 0));
				}

				poseStack.mulPose(
						new Quaternionf().rotateXYZ((float) Math.toRadians(135), (float) Math.toRadians(90), 0));
				float uniscale = 1.75f;
				poseStack.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemDisplayContext.GROUND, false, poseStack, bufferSource, packedLight,
						packedOverlay, bakedmodel);
				poseStack.popPose();
			}
		}
		poseStack.popPose();
	}

}
