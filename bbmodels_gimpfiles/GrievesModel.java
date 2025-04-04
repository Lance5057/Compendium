// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class GrievesModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "grievesmodel"), "main");
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart bb_main;

	public GrievesModel(ModelPart root) {
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LegPlateBackR_r1 = left_leg.addOrReplaceChild("LegPlateBackR_r1", CubeListBuilder.create().texOffs(64, 43).addBox(-2.0F, -5.9F, 2.2F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.6F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition ThighL_r1 = left_leg.addOrReplaceChild("ThighL_r1", CubeListBuilder.create().texOffs(64, 8).addBox(-2.0F, -6.0F, -2.8F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offsetAndRotation(2.0F, -4.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition ThighLB_r1 = left_leg.addOrReplaceChild("ThighLB_r1", CubeListBuilder.create().texOffs(80, 8).addBox(-2.0F, -6.0F, -1.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(2.0F, -4.0F, -0.8F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LegPlateR_r1 = left_leg.addOrReplaceChild("LegPlateR_r1", CubeListBuilder.create().texOffs(64, 32).addBox(-2.8F, -5.8F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(7.5F, -5.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LegPlateBackL_r1 = right_leg.addOrReplaceChild("LegPlateBackL_r1", CubeListBuilder.create().texOffs(64, 43).addBox(-2.0F, -5.9F, 2.2F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.6F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition ThighR_r1 = right_leg.addOrReplaceChild("ThighR_r1", CubeListBuilder.create().texOffs(64, 8).addBox(-2.0F, -6.0F, -2.8F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offsetAndRotation(-2.0F, -4.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition ThighRB_r1 = right_leg.addOrReplaceChild("ThighRB_r1", CubeListBuilder.create().texOffs(80, 8).addBox(-2.0F, -6.0F, -1.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -0.8F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LegPlateL_r1 = right_leg.addOrReplaceChild("LegPlateL_r1", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(1.6F, -5.9F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-7.5F, -5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 0).addBox(-4.5F, -12.0F, -2.8F, 9.0F, 2.0F, 6.0F, new CubeDeformation(0.3F))
		.texOffs(64, 49).addBox(-2.5F, -11.0F, -3.3F, 5.0F, 5.0F, 3.0F, new CubeDeformation(-0.4F))
		.texOffs(64, 58).addBox(-2.0F, -13.0F, -3.5F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}