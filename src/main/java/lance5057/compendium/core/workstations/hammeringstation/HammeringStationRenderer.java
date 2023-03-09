package lance5057.compendium.core.workstations.hammeringstation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.client.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class HammeringStationRenderer implements BlockEntityRenderer<HammeringStationTE> {
	int timer = 0;
	int toolRandom = 0;

	public HammeringStationRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(HammeringStationTE tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

		if (tileEntityIn.getCurrentTool() != null) {
			for (BlacklistedModel b : tileEntityIn.getCurrentTool().model)
				RenderUtil.loadModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, b, timer);
		}

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		LazyOptional<IItemHandler> itemInteractionHandler = tileEntityIn
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

		itemInteractionHandler.ifPresent(r -> {

			float xoff = 0;
			float yoff = 0;
			ItemStack item = r.getStackInSlot(0);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, tileEntityIn.getLevel(), null, 0);
				matrixStackIn.pushPose();
				matrixStackIn.translate(1.28f, 1.1, 0.5);
				matrixStackIn.mulPose(new Quaternion(45, 0, 90, true));
				float uniscale = 4.2f;
				matrixStackIn.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn,
						combinedLightIn, combinedOverlayIn, bakedmodel);
				matrixStackIn.popPose();
			}
		});

		timer++;
	}

}
