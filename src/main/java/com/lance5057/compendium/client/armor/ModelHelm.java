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
public class ModelHelm {
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(26, 4).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition trim = head.addOrReplaceChild("trim", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F,
				-8.9F, -5.0F, 2, 9, 10, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition MouthGuard = head.addOrReplaceChild("MouthGuard",
				CubeListBuilder.create().texOffs(30, 20).addBox(-4.5F, -3.0F, -5.4F, 9, 5, 8,
						new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition visor = head.addOrReplaceChild("visor",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0.2F, -5.9F, 9, 4, 8, new CubeDeformation(0.02F)),
				PartPose.offsetAndRotation(0.0F, -6.7F, 0.1F, -0.0872F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}