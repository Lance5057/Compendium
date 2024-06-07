package com.lance5057.compendium.client.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class CustomModel<T extends LivingEntity> extends HumanoidModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "custommodel"), "main");

	public CustomModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition KneeGuardCapR_r1 = rightLeg.addOrReplaceChild("KneeGuardCapR_r1",
				CubeListBuilder.create().texOffs(80, 36).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(1.8F, -4.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition KneeGuardR_r1 = RightLeg.addOrReplaceChild("KneeGuardR_r1",
				CubeListBuilder.create().texOffs(80, 36).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(-0.2F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackR_r1 = RightLeg.addOrReplaceChild("LegBackR_r1",
				CubeListBuilder.create().texOffs(64, 40).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F,
						new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-2.1F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardR_r1 = RightLeg.addOrReplaceChild("ShinGuardR_r1",
				CubeListBuilder.create().texOffs(64, 48).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F,
						new CubeDeformation(0.26F)),
				PartPose.offsetAndRotation(-2.1F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(16, 48)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 48)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(64, 56)
						.addBox(-2.0F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.31F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition KneeGuardCapL_r1 = LeftLeg.addOrReplaceChild("KneeGuardCapL_r1",
				CubeListBuilder.create().texOffs(80, 36).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(2.0F, -4.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition KneeGuardL_r1 = LeftLeg.addOrReplaceChild("KneeGuardL_r1",
				CubeListBuilder.create().texOffs(80, 36).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackL_r1 = LeftLeg.addOrReplaceChild("LegBackL_r1",
				CubeListBuilder.create().texOffs(64, 40).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F,
						new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardL_r1 = LeftLeg.addOrReplaceChild("ShinGuardL_r1",
				CubeListBuilder.create().texOffs(64, 48).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F,
						new CubeDeformation(0.26F)),
				PartPose.offsetAndRotation(-1.9F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}
}