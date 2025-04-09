package com.lance5057.compendium.client.renderer.blockentity;

import java.util.List;

import org.joml.Quaternionf;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.entities.ChairBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;
import net.neoforged.neoforge.client.model.renderable.IRenderable;

public class ChairRenderer implements BlockEntityRenderer<ChairBlockEntity> {
	final ModelResourceLocation fallback_legs = ModelResourceLocation
			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/legs"));
	final ModelResourceLocation fallback_seat = ModelResourceLocation
			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/seat"));
	final ModelResourceLocation fallback_back = ModelResourceLocation
			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/back"));

	public ChairRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(ChairBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

		List<String> mats = blockEntity.getMaterials();

		ModelResourceLocation legs = ModelResourceLocation.standalone(
				Compendium.modLoc("extra/material/wood/" + (mats.size() > 0 ? mats.get(0) : "invalid").toLowerCase()
						+ "/chair/" + blockEntity.legsStyles.toString().toLowerCase() + "/legs"));
		ModelResourceLocation seat = ModelResourceLocation.standalone(
				Compendium.modLoc("extra/material/wood/" + (mats.size() > 1 ? mats.get(1) : "invalid").toLowerCase()
						+ "/chair/" + blockEntity.legsStyles.toString().toLowerCase() + "/seat"));
		ModelResourceLocation back = ModelResourceLocation.standalone(
				Compendium.modLoc("extra/material/wood/" + (mats.size() > 2 ? mats.get(2) : "invalid").toLowerCase()
						+ "/chair/" + blockEntity.legsStyles.toString().toLowerCase() + "/back"));

		if (legs == null)
			legs = fallback_legs;
		if (seat == null)
			seat = fallback_seat;
		if (back == null)
			back = fallback_back;

		IRenderable<ModelData> l = BakedModelRenderable.of(legs).withModelDataContext();
		IRenderable<ModelData> s = BakedModelRenderable.of(seat).withModelDataContext();
		IRenderable<ModelData> b = BakedModelRenderable.of(back).withModelDataContext();

		BlockState blockstate = blockEntity.getBlockState();
		Direction dir = blockstate.getValue(HorizontalDirectionalBlock.FACING);

		poseStack.pushPose();
		{
			poseStack.translate(0.5, 0, 0.5);
			poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-dir.toYRot()), 0));
			poseStack.translate(-0.5, 0, -0.5);
			poseStack.scale(1, 1, 1);

			l.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
					partialTick, ModelData.EMPTY);
			s.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
					partialTick, ModelData.EMPTY);
			b.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
					partialTick, ModelData.EMPTY);
		}
		poseStack.popPose();
	}

}
