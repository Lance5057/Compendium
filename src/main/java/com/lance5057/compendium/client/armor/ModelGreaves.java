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
public class ModelGreaves {
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48)
				.addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16)
				.addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(
				-4.0F, 12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition belt = body.addOrReplaceChild("belt",
				CubeListBuilder.create().texOffs(64, 0).addBox(-4.5F, 10.0F, -2.8F, 9, 2, 6, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LegPlateR = left_leg.addOrReplaceChild("LegPlateR",
				CubeListBuilder.create().texOffs(64, 32).mirror()
						.addBox(-6F, 0.9F, -2.5F, 1, 5, 5, new CubeDeformation(0.1F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LegPlateL = right_leg.addOrReplaceChild("LegPlateL",
				CubeListBuilder.create().texOffs(64, 32).addBox(5.2F, 0.8F, -2.5F, 1, 5, 5, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ThighLB = left_leg.addOrReplaceChild("ThighLB",
				CubeListBuilder.create().texOffs(80, 8).addBox(-2.0F, 0.0F, -1.5F, 4, 6, 4, new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition ThighRB = right_leg.addOrReplaceChild("ThighRB",
				CubeListBuilder.create().texOffs(80, 8).addBox(-2.0F, 0.0F, -1.5F, 4, 6, 4, new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition CodPiece = body.addOrReplaceChild("CodPiece ", CubeListBuilder.create().texOffs(64, 49)
				.addBox(-2.5F, 11.0F, -3.6F, 5, 5, 3, new CubeDeformation(-0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition beltBuckle = body.addOrReplaceChild("beltBuckle", CubeListBuilder.create().texOffs(64, 58)
				.addBox(-2.0F, 9.0F, -3.8F, 4, 4, 2, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ThighL = left_leg.addOrReplaceChild("ThighL",
				CubeListBuilder.create().texOffs(64, 8).addBox(-2.0F, 1.0F, -2.8F, 4, 5, 4, new CubeDeformation(0.31F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition ThighR = right_leg.addOrReplaceChild("ThighR",
				CubeListBuilder.create().texOffs(64, 8).addBox(-2.0F, 1.0F, -2.8F, 4, 5, 4, new CubeDeformation(0.31F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition LegPlateBackL = right_leg.addOrReplaceChild("LegPlateBackL",
				CubeListBuilder.create().texOffs(64, 43).addBox(-2.0F, 0.9F, 2.2F, 4, 5, 1, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition LegPlateBackR = left_leg.addOrReplaceChild("LegPlateBackR_",
				CubeListBuilder.create().texOffs(64, 43).addBox(-2.0F, 0.9F, 2.2F, 4, 5, 1, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0F, 0F, 0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}
}