package com.lance5057.compendium.workstations.hammeringstation;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public class HammeringStationRenderer implements BlockEntityRenderer<HammeringStationBlockEntity> {
	int timer = 0;
	int toolRandom = 0;

	public HammeringStationRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(HammeringStationBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}
		
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		ItemStackHandler inv = tileEntityIn.item

		ItemStack input = inv.getStackInSlot(0);

		timer++;
	}

}
