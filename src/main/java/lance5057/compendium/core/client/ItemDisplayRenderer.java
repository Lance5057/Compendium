package lance5057.compendium.core.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.tileentities.ItemDisplayTileEntity;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloatVector3;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemDisplayRenderer extends TileEntityRenderer<ItemDisplayTileEntity> {

    int timer = 0;
    AnimatedFloatVector3 ghost;

    public ItemDisplayRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
	super(rendererDispatcherIn);

	ghost = new AnimatedFloatVector3(10, 10, 0, 0.1f);
    }

    @Override
    public void render(ItemDisplayTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
	    IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

	if (!tileEntityIn.hasWorld()) {
	    return;
	}

	ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

	LazyOptional<IItemHandler> itemHandler = tileEntityIn
		.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

	itemHandler.ifPresent(r -> {
	    ItemStack item = r.getStackInSlot(0);

	    if (!item.isEmpty()) {

		Direction dir = tileEntityIn.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);

		float scale = 0.75f;
		float rot = dir.getHorizontalAngle();
		matrixStackIn.push();

		if(CompendiumConfig.getInstance().general.animated_displays.get())
		    matrixStackIn.translate(ghost.getX().getFloat(), ghost.getY().getFloat(), ghost.getZ().getFloat());

		if (item.getItem() instanceof BlockItem) {
		    matrixStackIn.translate(0.5, 0.5, 0.5);
		    matrixStackIn.rotate(new Quaternion(0, -rot, 0, true));
		    matrixStackIn.translate(0.0, 0.1, 0.25);

		} else if (item.getItem() instanceof TieredItem) {
		    matrixStackIn.translate(0.5, 0.5, 0.5);
		    matrixStackIn.rotate(new Quaternion(0, -rot, 0, true));
		    matrixStackIn.translate(-0.0, 0.2, 0.25);
		    matrixStackIn.rotate(new Quaternion(90, 0, -45, true));

		} else {
		    matrixStackIn.translate(0.5, 0.5, 0.5);
		    matrixStackIn.rotate(new Quaternion(0, -rot, 0, true));
		    matrixStackIn.translate(-0.0, 0.2, 0.25);
		}

		matrixStackIn.scale(scale, scale, scale);
		itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn,
			combinedOverlayIn, matrixStackIn, bufferIn);

		matrixStackIn.pop();
	    }
	});

	timer++;
	if (timer > 100)
	    timer = 0;

	ghost.animate();

	// For hotswapping, remove later!
	ghost.setMax(0, 0.01f, 0);
	ghost.setMin(0, -0.01f, 0);
	ghost.setSpeed(0.0001f);
    }
}