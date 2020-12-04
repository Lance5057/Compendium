package lance5057.compendium.core.workstations.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class HammeringStationRenderer extends TileEntityRenderer<HammeringStationTE> {

	public HammeringStationRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(HammeringStationTE tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasWorld()) {
            return;
        }

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		
		final IItemHandler itemHandler = tileEntityIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(ItemStackHandler::new);
		
		ItemStack item = itemHandler.getStackInSlot(0);
		
		if (!item.isEmpty()) {
            matrixStackIn.push();
            matrixStackIn.translate(0.7, 0.9, 0.5);
            matrixStackIn.rotate(new Quaternion(90, 0, 90, true));
            itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }
	}

}
