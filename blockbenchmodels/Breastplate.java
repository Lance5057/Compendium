// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Breastplate<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "breastplate"), "main");
	private final ModelPart leg_arm;
	private final ModelPart right_arm;
	private final ModelPart body;

	public Breastplate(ModelPart root) {
		this.leg_arm = root.getChild("leg_arm");
		this.right_arm = root.getChild("right_arm");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg_arm = partdefinition.addOrReplaceChild("leg_arm", CubeListBuilder.create().texOffs(32, 48).addBox(4.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 12).mirror().addBox(-9.0F, -9.7F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Pauldron2L_r1 = leg_arm.addOrReplaceChild("Pauldron2L_r1", CubeListBuilder.create().texOffs(88, 0).addBox(-4.5F, -2.5F, -3.5F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(7.0F, -14.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition PauldronL_r1 = leg_arm.addOrReplaceChild("PauldronL_r1", CubeListBuilder.create().texOffs(88, 0).addBox(-4.5F, -3.0F, -3.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(9.0F, -11.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 12).addBox(4.0F, -9.7F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition PauldronR_r1 = right_arm.addOrReplaceChild("PauldronR_r1", CubeListBuilder.create().texOffs(88, 0).mirror().addBox(0.6F, -3.0F, -3.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-9.0F, -11.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Pauldron2R_r1 = right_arm.addOrReplaceChild("Pauldron2R_r1", CubeListBuilder.create().texOffs(88, 0).mirror().addBox(0.5F, -2.5F, -3.5F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -14.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 10).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.3F))
		.texOffs(64, 0).addBox(-4.0F, -12.1F, -3.6F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.41F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition BackPlate_r1 = body.addOrReplaceChild("BackPlate_r1", CubeListBuilder.create().texOffs(64, 24).addBox(-4.0F, -4.9F, 1.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.41F)), PartPose.offsetAndRotation(0.0F, -7.5F, -0.5F, -0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}