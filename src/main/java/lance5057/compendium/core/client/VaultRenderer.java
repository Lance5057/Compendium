////package lance5057.compendium.core.client;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//
//import lance5057.compendium.core.tileentities.VaultTileEntity;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
//import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.item.ItemStack;
//import net.minecraft.state.Property;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.vector.Quaternion;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//
//public class VaultRenderer extends TileEntityRenderer<VaultTileEntity> {
//
//    public VaultRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
//	super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(VaultTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
//	    IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//
//	if (!tileEntityIn.hasWorld()) {
//	    return;
//	}
//
//	ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//	FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
//
//	LazyOptional<IItemHandler> itemHandler = tileEntityIn
//		.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
//
//	itemHandler.ifPresent(r -> {
//	    ItemStack item = r.getStackInSlot(0);
//
//	    if (!item.isEmpty()) {
//
//		Direction dir = tileEntityIn.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
//
//		float scale = 0.75f;
//		float rot = dir.getHorizontalAngle();
//		matrixStackIn.push();
//
//		matrixStackIn.translate(0.5, 0.5, 0.5);
//		matrixStackIn.rotate(new Quaternion(0, -rot, 0, true));
//		matrixStackIn.translate(0.0, 0.0, 0.5);
//		matrixStackIn.scale(scale, scale, scale);
//		itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn,
//			combinedOverlayIn, matrixStackIn, bufferIn);
//
//		scale = 0.02f;
//		float w = (16 / (float) (fontRenderer.getStringWidth("" + item.getCount()))) / 16;
//
//		matrixStackIn.rotate(new Quaternion(0, 180, 180, true));
//
//		matrixStackIn.translate(-0.2 + w, 0.15, -0.002);
//		matrixStackIn.scale(scale, scale, scale);
//
//		fontRenderer.drawString(matrixStackIn, "" + item.getCount(), 0, 0, 0xFFFFFF);
//
//		matrixStackIn.pop();
//	    }
//	});
//    }
//}
