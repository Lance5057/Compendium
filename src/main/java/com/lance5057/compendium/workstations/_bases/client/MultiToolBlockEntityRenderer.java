package com.lance5057.compendium.workstations._bases.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class MultiToolBlockEntityRenderer<T extends BlockEntity>  implements BlockEntityRenderer<T> {

	@Override
	public final void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
			int packedLight, int packedOverlay) {
		this.renderInventory(blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
	}

	
	public abstract void renderInventory(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
			int packedLight, int packedOverlay);
}
