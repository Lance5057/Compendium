package lance5057.compendium.core.workstations.hammeringstation;
//package lance5057.compendium.core.workstations.client;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.math.Quaternion;
//
//import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.entity.ItemRenderer;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
//import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemInteractionHandler;
//
//public class HammeringStationRenderer extends TileEntityRenderer<HammeringStationTE> {
//
//    public HammeringStationRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
//	super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(HammeringStationTE tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
//	    IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//	if (!tileEntityIn.hasWorld()) {
//	    return;
//	}
//
//	ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//
//	LazyOptional<IItemInteractionHandler> itemInteractionHandler = tileEntityIn
//		.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY);
//
//	itemInteractionHandler.ifPresent(r -> {
//	    ItemStack item = r.getStackInSlot(0);
//
//	    if (!item.isEmpty()) {
//		matrixStackIn.push();
//		matrixStackIn.translate(0.7, 0.9, 0.5);
//		matrixStackIn.rotate(new Quaternion(90, 0, 90, true));
//		itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn,
//			combinedOverlayIn, matrixStackIn, bufferIn);
//		matrixStackIn.pop();
//	    }
//	});
//    }
//
//}
