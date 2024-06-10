// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class CustomModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custommodel"), "main");
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public CustomModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(16, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(0, 24).addBox(-2.2F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.31F))
		.texOffs(0, 0).addBox(-2.2F, 3.9F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition KneeGuardCapR_r1 = RightLeg.addOrReplaceChild("KneeGuardCapR_r1", CubeListBuilder.create().texOffs(16, 4).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(1.8F, -4.2F, -1.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition ToeGuardR_r1 = RightLeg.addOrReplaceChild("ToeGuardR_r1", CubeListBuilder.create().texOffs(16, 4).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.2F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackR_r1 = RightLeg.addOrReplaceChild("LegBackR_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-2.1F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardR_r1 = RightLeg.addOrReplaceChild("ShinGuardR_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.26F)), PartPose.offsetAndRotation(-2.1F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(0, 24).addBox(-2.0F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.31F))
		.texOffs(0, 0).addBox(-2.0F, 3.9F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition KneeGuardCapL_r1 = LeftLeg.addOrReplaceChild("KneeGuardCapL_r1", CubeListBuilder.create().texOffs(16, 4).addBox(-3.4F, 7.6F, -3.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(2.0F, -4.2F, -1.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition ToeGuardL_r1 = LeftLeg.addOrReplaceChild("ToeGuardL_r1", CubeListBuilder.create().texOffs(16, 4).addBox(-1.5F, -3.4F, -4.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LegBackL_r1 = LeftLeg.addOrReplaceChild("LegBackL_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-0.1F, -8.0F, 0.6F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-1.9F, 12.0F, -0.8F, -0.0576F, 0.0F, 0.0F));

		PartDefinition ShinGuardL_r1 = LeftLeg.addOrReplaceChild("ShinGuardL_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-0.1F, -7.0F, -2.5F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.26F)), PartPose.offsetAndRotation(-1.9F, 11.0F, 0.5F, 0.0576F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}