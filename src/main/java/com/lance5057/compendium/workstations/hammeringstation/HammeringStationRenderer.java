package com.lance5057.compendium.workstations.hammeringstation;

import com.lance5057.compendium.util.rendering.RenderUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public class HammeringStationRenderer implements BlockEntityRenderer<HammeringStationBlockEntity> {
	int timer = 0;
	int toolRandom = 0;

	AnimationFloatTransform transform = new AnimationFloatTransform();

	public HammeringStationRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(HammeringStationBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

		ItemStackHandler inv = tileEntityIn.getInventory().get();

		transform.setScale(0.5f);
		transform.setLocation(8f, 18f, 8f);

		ItemStack input = inv.getStackInSlot(0);

		RenderUtil.itemModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, input, transform, timer);

		timer++;
	}

}
