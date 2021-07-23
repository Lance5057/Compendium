package lance5057.compendium.core.client.models.armor.heavy;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelHelm<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer Visor;
    public ModelRenderer Helm;
    public ModelRenderer Trim;
    public ModelRenderer MouthGuard;
    
    public ModelRenderer Head;

    public ModelHelm() {
	super(0.25f, 0, 64, 64);

	this.textureWidth = 64;
	this.textureHeight = 64;

	this.Trim = new ModelRenderer(this, 0, 12);
	this.Trim.setRotationPoint(0.0F, 0.0F, 0.0F);
	this.Trim.addBox(-1.0F, -8.9F, -5.0F, 2, 9, 10, 0.0F);

	this.Helm = new ModelRenderer(this, 26, 4);
	this.Helm.setRotationPoint(0.0F, 0.0F, 0.0F);
	this.Helm.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);

	this.MouthGuard = new ModelRenderer(this, 30, 20);
	this.MouthGuard.setRotationPoint(0.0F, -2.0F, 0.0F);
	this.MouthGuard.addBox(-4.5F, -3.0F, -5.4F, 9, 5, 8, 0.01F);
	this.setRotateAngle(MouthGuard, 0.3490658503988659F, 0.0F, 0.0F);

	this.Visor = new ModelRenderer(this, 0, 0);
	this.Visor.setRotationPoint(0.0F, -6.7F, 0.1F);
	this.Visor.addBox(-4.5F, 0.2F, -5.9F, 9, 4, 8, 0.0F);
	this.setRotateAngle(Visor, -0.08726646259971647F, 0.0F, 0.01F);

	this.Helm.addChild(this.Trim);
	this.Helm.addChild(this.MouthGuard);
	this.Helm.addChild(this.Visor);
	
	this.Head = new ModelRenderer(this, 1,1);
	this.Head.addBox(-4.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1f);
	this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
	
	this.bipedHead = this.Helm;
	this.bipedHeadwear = this.Head;

//	this.bipedHead.showModel = false;
//	this.bipedHeadwear.showModel = false;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}
