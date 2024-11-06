package com.lance5057.compendium.client.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelSabatons {
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();
		
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(16, 7)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 7)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(0, 24)
						.addBox(-2.2F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.31F)).texOffs(0, 0)
						.addBox(-2.2F, 3.9F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.31F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition KneeGuardCapR_r1 = RightLeg.addOrReplaceChild("KneeGuardCapR_r1",
				CubeListBuilder.create().texOffs(16, 4).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(1.8F, -4.2F, -1.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition ToeGuardR_r1 = RightLeg.addOrReplaceChild("ToeGuardR_r1",
				CubeListBuilder.create().texOffs(16, 4).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(-0.2F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackR_r1 = RightLeg.addOrReplaceChild("LegBackR_r1",
				CubeListBuilder.create().texOffs(0, 8).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F,
						new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-2.1F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardR_r1 = RightLeg.addOrReplaceChild("ShinGuardR_r1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F,
						new CubeDeformation(0.26F)),
				PartPose.offsetAndRotation(-2.1F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(16, 7)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 7)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(0, 24)
						.addBox(-2.0F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.31F)).texOffs(0, 0)
						.addBox(-2.0F, 3.9F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.31F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition KneeGuardCapL_r1 = LeftLeg.addOrReplaceChild("KneeGuardCapL_r1",
				CubeListBuilder.create().texOffs(16, 4).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(2.0F, -4.2F, -1.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition ToeGuardL_r1 = LeftLeg.addOrReplaceChild("ToeGuardL_r1",
				CubeListBuilder.create().texOffs(16, 4).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackL_r1 = LeftLeg.addOrReplaceChild("LegBackL_r1",
				CubeListBuilder.create().texOffs(0, 8).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F,
						new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-1.9F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardL_r1 = LeftLeg.addOrReplaceChild("ShinGuardL_r1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F,
						new CubeDeformation(0.26F)),
				PartPose.offsetAndRotation(-1.9F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}