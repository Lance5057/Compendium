package com.lance5057.compendium.client.renderer.blockentity;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class SimpleStyleBlockRenderer implements BlockEntityRenderer<SimpleStyleBlockEntity> {
//	final ModelResourceLocation fallback_legs = ModelResourceLocation
//			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/legs"));
//	final ModelResourceLocation fallback_seat = ModelResourceLocation
//			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/seat"));
//	final ModelResourceLocation fallback_back = ModelResourceLocation
//			.standalone(Compendium.modLoc("extra/material/wood/invalid/chair/basic/back"));

	public SimpleStyleBlockRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(SimpleStyleBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

//		List<String> mats = blockEntity.getMaterials();
//
//		if (blockEntity.getStyles().size() > 0) {
//			for (int i = 0; i < blockEntity.getMaterialsCount(); i++) {
//				MultiStyle style = blockEntity.getStyles().get(i);
//				ModelResourceLocation legs = ModelResourceLocation.standalone(Compendium.modLoc("extra/material/wood/"
//						+ (mats.size() > 0 ? mats.get(0) : "invalid").toLowerCase() + "/" + blockEntity.getName() + "/"
//						+ style.getCurrentStyle().toLowerCase() + "/" + style.getName()));
//
//				IRenderable<ModelData> l = BakedModelRenderable.of(legs).withModelDataContext();
//
//				BlockState blockstate = blockEntity.getBlockState();
//				Direction dir = blockstate.getValue(HorizontalDirectionalBlock.FACING);
//
//		poseStack.pushPose();
//		{
//			poseStack.translate(0.5, 1, 0.5);
////					poseStack.mulPose(new Quaternionf().rotateXYZ(0, 0), 0));
//			poseStack.translate(-0.5, 0, -0.5);
//			poseStack.scale(1, 1, 1);
//
//			BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(new ItemStack(Items.STICK), null, null, 0);
//			Minecraft.getInstance().getItemRenderer().render(new ItemStack(Items.STICK), ItemDisplayContext.FIXED,
//					false, poseStack, bufferSource, packedLight, packedOverlay, bakedmodel);
//
//		}
//		poseStack.popPose();
//			}
//		ModelResourceLocation legs = ModelResourceLocation.standalone(
//				Compendium.modLoc("extra/material/wood/" + (mats.size() > 0 ? mats.get(0) : "invalid").toLowerCase()
//						+ "/chair/" + blockEntity.legsStyles.getCurrentStyle().toLowerCase() + "/legs"));
//		ModelResourceLocation seat = ModelResourceLocation.standalone(
//				Compendium.modLoc("extra/material/wood/" + (mats.size() > 1 ? mats.get(1) : "invalid").toLowerCase()
//						+ "/chair/" + blockEntity.legsStyles.getCurrentStyle().toLowerCase() + "/seat"));
//		ModelResourceLocation back = ModelResourceLocation.standalone(
//				Compendium.modLoc("extra/material/wood/" + (mats.size() > 2 ? mats.get(2) : "invalid").toLowerCase()
//						+ "/chair/" + blockEntity.legsStyles.getCurrentStyle().toLowerCase() + "/back"));
//
//		if (legs == null)
//			legs = fallback_legs;
//		if (seat == null)
//			seat = fallback_seat;
//		if (back == null)
//			back = fallback_back;

//		IRenderable<ModelData> l = BakedModelRenderable.of(legs).withModelDataContext();
//		IRenderable<ModelData> s = BakedModelRenderable.of(seat).withModelDataContext();
//		IRenderable<ModelData> b = BakedModelRenderable.of(back).withModelDataContext();

//		BlockState blockstate = blockEntity.getBlockState();
//		Direction dir = blockstate.getValue(HorizontalDirectionalBlock.FACING);
//
//		poseStack.pushPose();
//		{
//			poseStack.translate(0.5, 0, 0.5);
//			poseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-dir.toYRot()), 0));
//			poseStack.translate(-0.5, 0, -0.5);
//			poseStack.scale(1, 1, 1);
//
//			l.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
//					partialTick, ModelData.EMPTY);
//			s.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
//					partialTick, ModelData.EMPTY);
//			b.render(poseStack, bufferSource, texture -> RenderType.entitySolid(texture), packedLight, packedOverlay,
//					partialTick, ModelData.EMPTY);
//		}
//		poseStack.popPose();
//		}
	}
}
