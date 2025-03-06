package com.lance5057.compendium.blocks.RecipeToolSupplier.drawer;

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

public class ComponentDrawerRenderer implements BlockEntityRenderer<ComponentDrawerBlockEntity> {

	public ComponentDrawerRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(ComponentDrawerBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		IItemHandler itemInteractionHandler = blockEntity.getItems();

		Direction dir = blockEntity.getBlockState().getValue(ComponentDrawerBlock.FACING);

		poseStack.pushPose();
		// poseStack.mulPose(new Quaternion(0, -dir.toYRot(), 0, true));
		for (int i = 0; i < 4; i++) {

			ItemStack item = itemInteractionHandler.getStackInSlot(i);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, blockEntity.getLevel(), null, 0);
				poseStack.pushPose();
				poseStack.translate(0.5f, 0.45, 0.5f);
				poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-dir.toYRot()), 0));
				poseStack.translate(0.05, -0.16, -0.22);

				if (i % 2 != 0) {
					poseStack.translate(0, 0.0, 0.44);
				}
				if (i < 2) {
					poseStack.translate(0, 0.43, 0.0);
				}

				poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-90), 0));
				float uniscale = 0.25f;
				poseStack.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemDisplayContext.GROUND, false, poseStack, bufferSource, packedLight,
						packedOverlay, bakedmodel);
				poseStack.popPose();
			}
		}
		poseStack.popPose();
	}

}
