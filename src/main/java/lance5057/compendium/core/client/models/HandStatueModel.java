// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class handstatue<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "handstatue"), "main");
	private final ModelPart bone;

	public handstatue(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition palm = bone.addOrReplaceChild("palm", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition pointer = palm.addOrReplaceChild("pointer", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.5F, 0.4051F, -0.2415F, -0.1022F));

		PartDefinition pointertip = pointer.addOrReplaceChild("pointertip", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition middle = palm.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition middletip = middle.addOrReplaceChild("middletip", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.101F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition ring = palm.addOrReplaceChild("ring", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.4051F, 0.2415F, 0.1022F));

		PartDefinition ringtip = ring.addOrReplaceChild("ringtip", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition pinky = palm.addOrReplaceChild("pinky", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(3.0F, 0.0F, 1.0F, 0.4461F, 0.4802F, 0.2174F));

		PartDefinition pinkytip = pinky.addOrReplaceChild("pinkytip", CubeListBuilder.create().texOffs(4, 6).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.101F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition thumb = palm.addOrReplaceChild("thumb", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 0.0F, -2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition fingerbottom_r1 = thumb.addOrReplaceChild("fingerbottom_r1", CubeListBuilder.create().texOffs(3, 3).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition thumbtip = thumb.addOrReplaceChild("thumbtip", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition fingertop_r1 = thumbtip.addOrReplaceChild("fingertop_r1", CubeListBuilder.create().texOffs(3, 3).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}