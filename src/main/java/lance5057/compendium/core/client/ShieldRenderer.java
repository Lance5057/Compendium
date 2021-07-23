package lance5057.compendium.core.client;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;

import lance5057.compendium.Reference;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShieldRenderer extends ItemStackTileEntityRenderer {

    private final ShieldModel modelShield = new ShieldModel();

    public final RenderMaterial LOCATION_SHIELD_BASE;
    public final RenderMaterial LOCATION_SHIELD_NO_PATTERN;

    public ShieldRenderer(String name) {
	LOCATION_SHIELD_BASE = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
		new ResourceLocation(Reference.MOD_ID, "entity/material/" + name + "/" + name + "shield_base"));
	LOCATION_SHIELD_NO_PATTERN = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
		new ResourceLocation(Reference.MOD_ID,"entity/material/" + name + "/" + name + "shield_base_nopattern"));
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack,
	    IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
	Item item = stack.getItem();
	if (item instanceof ShieldItem) {
	    boolean flag = stack.getChildTag("BlockEntityTag") != null;
	    matrixStack.push();
	    matrixStack.scale(1.0F, -1.0F, -1.0F);
	    RenderMaterial rendermaterial = flag ? LOCATION_SHIELD_BASE : LOCATION_SHIELD_NO_PATTERN;
	    IVertexBuilder ivertexbuilder = rendermaterial.getSprite()
		    .wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer,
			    this.modelShield.getRenderType(rendermaterial.getAtlasLocation()), true,
			    stack.hasEffect()));
	    this.modelShield.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F,
		    1.0F, 1.0F, 1.0F);
	    if (flag) {
		List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity
			.getPatternColorData(ShieldItem.getColor(stack), BannerTileEntity.getPatternData(stack));
		BannerTileEntityRenderer.func_241717_a_(matrixStack, buffer, combinedLight, combinedOverlay,
			this.modelShield.func_228293_a_(), rendermaterial, false, list, stack.hasEffect());
	    } else {
		this.modelShield.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay,
			1.0F, 1.0F, 1.0F, 1.0F);
	    }

	    matrixStack.pop();
	}

    }
}
