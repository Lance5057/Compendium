package com.lance5057.compendium.client.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelBreastplate {
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(
				-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmL = left_arm.addOrReplaceChild("ArmL", CubeListBuilder.create().texOffs(92, 12)
				.addBox(-1.0F, -2.3F, -3.0F, 5, 6, 6, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmR = right_arm.addOrReplaceChild("ArmR", CubeListBuilder.create().texOffs(92, 12).mirror()
				.addBox(-4.0F, -2.3F, -3.0F, 5, 6, 6, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Plackart = body.addOrReplaceChild("Plackart",
				CubeListBuilder.create().texOffs(64, 10).addBox(-4.0F, 5.0F, -3.0F, 8, 7, 6, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Breastplate = body.addOrReplaceChild("Breastplate",
				CubeListBuilder.create().texOffs(64, 0).addBox(-4.0F, 0.1F, -3.6F, 8, 6, 4, new CubeDeformation(0.41F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BackPlate = body.addOrReplaceChild("BackPlate",
				CubeListBuilder.create().texOffs(64, 24).addBox(-4.0F, -0.1F, 1.0F, 8, 5, 3,
						new CubeDeformation(0.41F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Pauldron2L = left_arm.addOrReplaceChild("Pauldron2L",
				CubeListBuilder.create().texOffs(88, 0).addBox(0.5F, -1.5F, -3.5F, 4, 4, 7, new CubeDeformation(-0.4F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition PauldronL = left_arm.addOrReplaceChild("PauldronL",
				CubeListBuilder.create().texOffs(88, 0).addBox(0.5F, -2.0F, -3.5F, 4, 5, 7, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition PauldronR = right_arm
				.addOrReplaceChild("PauldronR",
						CubeListBuilder.create().texOffs(88, 0).mirror().addBox(-4.6F, -2.0F, -3.5F, 4, 5, 7,
								new CubeDeformation(0.1F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Pauldron2R = right_arm
				.addOrReplaceChild("Pauldron2R",
						CubeListBuilder.create().texOffs(88, 0).mirror().addBox(-4.5F, -1.5F, -3.5F, 4, 4, 7,
								new CubeDeformation(-0.4F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}
}