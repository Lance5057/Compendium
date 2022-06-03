//package lance5057.compendium.core.client.models.armor.heavy;
//
//import net.minecraft.client.model.HumanoidModel;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.world.entity.LivingEntity;
//
///**
// * ModelPlayer - Either Mojang or a mod author
// * Created using Tabula 7.0.0
// */
//public class ModelGrieves<T extends LivingEntity> extends HumanoidModel<T> {
//    public ModelPart LegPlateL;
//    public ModelPart CodPiece;
//    public ModelPart LegPlateBackL;
//    public ModelPart LegPlateBackR;
//    public ModelPart LegPlateR;
//    public ModelPart Belt;
//    public ModelPart BeltBuckle;
//    public ModelPart ThighR;
//    public ModelPart ThighL;
//    public ModelPart ThighRB;
//    public ModelPart ThighLB;
//    public ModelPart SecBeltL;
//
//    public ModelGrieves() {
//    	super(0.25f, 0, 96, 96);
//        this.textureWidth = 96;
//        this.textureHeight = 96;
//        
//        this.Belt = new ModelPart(this, 64, 0);
//        this.Belt.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.Belt.addBox(-4.5F, 10.0F, -2.8F, 9, 2, 6, 0.3F);
//        this.bipedBody.addChild(Belt);
//        
//        this.LegPlateR = new ModelPart(this, 64, 32);
//        this.LegPlateR.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.LegPlateR.addBox(-2.6F, 0.9F, -2.5F, 1, 5, 5, 0.1F);
//        this.setRotateAngle(LegPlateR, 0.0F, 0.0F, 0.2617993877991494F);
//        this.bipedRightLeg.addChild(LegPlateR);
//        
//        this.LegPlateL = new ModelPart(this, 64, 32);
//        this.LegPlateL.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.LegPlateL.addBox(1.8F, 0.8F, -2.5F, 1, 5, 5, 0.1F);
//        this.setRotateAngle(LegPlateL, 0.0F, 0.0F, -0.2617993877991494F);
//        this.bipedLeftLeg.addChild(LegPlateL);
//        
//        this.ThighLB = new ModelPart(this, 80, 8);
//        this.ThighLB.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ThighLB.addBox(-2.0F, 0.0F, -1.5F, 4, 6, 4, 0.3F);
//        this.setRotateAngle(ThighLB, -0.08726646259971647F, 0.0F, 0.0F);
//        this.bipedLeftLeg.addChild(ThighLB);
//        
//        this.ThighRB = new ModelPart(this, 80, 8);
//        this.ThighRB.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ThighRB.addBox(-2.0F, 0.0F, -1.5F, 4, 6, 4, 0.3F);
//        this.setRotateAngle(ThighRB, -0.08726646259971647F, 0.0F, 0.0F);
//        this.bipedRightLeg.addChild(ThighRB);
//        
//        this.CodPiece = new ModelPart(this, 64, 49);
//        this.CodPiece.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.CodPiece.addBox(-2.5F, 11.0F, -3.3F, 5, 5, 3, -0.4F);
//        this.bipedBody.addChild(CodPiece);
//        
//        this.BeltBuckle = new ModelPart(this, 64, 58);
//        this.BeltBuckle.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.BeltBuckle.addBox(-2.0F, 9.0F, -3.5F, 4, 4, 2, -0.3F);
//        this.bipedBody.addChild(BeltBuckle);
//
//        this.ThighR = new ModelPart(this, 64, 8);
//        this.ThighR.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ThighR.addBox(-2.0F, 1.0F, -2.8F, 4, 5, 4, 0.31F);
//        this.setRotateAngle(ThighR, 0.08726646259971647F, 0.0F, 0.0F);
//        this.bipedRightLeg.addChild(ThighR);
//
//        this.ThighL = new ModelPart(this, 64, 8);
//        this.ThighL.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ThighL.addBox(-2.0F, 1.0F, -2.8F, 4, 5, 4, 0.31F);
//        this.setRotateAngle(ThighL, 0.08726646259971647F, 0.0F, 0.0F);
//        this.bipedLeftLeg.addChild(ThighL);
//
//        this.LegPlateBackR = new ModelPart(this, 64, 43);
//        this.LegPlateBackR.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.LegPlateBackR.addBox(-2.0F, 0.9F, 2.2F, 4, 5, 1, 0.0F);
//        this.setRotateAngle(LegPlateBackR, 0.2617993877991494F, 0.0F, 0.0F);
//        this.bipedRightLeg.addChild(LegPlateBackR);
//
//        this.LegPlateBackL = new ModelPart(this, 64, 43);
//        this.LegPlateBackL.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.LegPlateBackL.addBox(-2.0F, 0.9F, 2.2F, 4, 5, 1, 0.0F);
//        this.setRotateAngle(LegPlateBackL, 0.2617993877991494F, 0.0F, 0.0F);
//        this.bipedLeftLeg.addChild(LegPlateBackL);
//
//        this.SecBeltL = new ModelPart(this, 64, 0);
//        this.SecBeltL.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.SecBeltL.addBox(-1.0F, 11.5F, -2.8F, 9, 2, 6, 0.26F);
//        this.setRotateAngle(SecBeltL, 0.0F, 0.0F, 0.2617993877991494F);
//        this.bipedBody.addChild(SecBeltL);
//    }
//
//    /**
//     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}
