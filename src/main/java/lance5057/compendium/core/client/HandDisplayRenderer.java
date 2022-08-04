package lance5057.compendium.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import lance5057.compendium.core.tileentities.HandTileEntity;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class HandDisplayRenderer implements BlockEntityRenderer<HandTileEntity> {

	int timer = 0;
	AnimationFloatTransform ghost;

	public HandDisplayRenderer(BlockEntityRendererProvider.Context cxt) {
		// super(rendererDispatcherIn);

		ghost = new AnimationFloatTransform();
		// timer = rendererDispatcherIn.world.rand.nextInt(100);
	}

	@Override
	public void render(HandTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		LazyOptional<IItemHandler> itemInteractionHandler = tileEntityIn
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

		itemInteractionHandler.ifPresent(r -> {
			ItemStack item = r.getStackInSlot(0);

			if (!item.isEmpty()) {

				Direction dir = tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING);

				float scale = 0.75f;
				float rot = dir.toYRot();
				matrixStackIn.pushPose();

				// if (CompendiumConfig.getInstance().general.animated_displays.get())
				// matrixStackIn.translate(ghost.getX().getFloat(), ghost.getY().getFloat(),
				// ghost.getZ().getFloat());

				if (item.getItem() instanceof BlockItem) {
					scale = 1.5f;
					matrixStackIn.translate(0.5, 0.5, 0.5);
					matrixStackIn.mulPose(new Quaternion(0, -rot, 0, true));
					matrixStackIn.translate(0.0, -0.04, 0.0);

				} 
				else if (item.getItem() instanceof SwordItem) {
					scale = 2f;
					matrixStackIn.translate(0.5, 0.5, 0.5);
					matrixStackIn.mulPose(new Quaternion(0, -rot, 0, true));
					matrixStackIn.translate(0.1, 0.1, 0.3);
					matrixStackIn.mulPose(new Quaternion(90, 0, -15-180, true));
				}
				
				else if (item.getItem() instanceof TieredItem) {
					scale = 2f;
					matrixStackIn.translate(0.5, 0.5, 0.5);
					matrixStackIn.mulPose(new Quaternion(0, -rot, 0, true));
					matrixStackIn.translate(-0.0, 0.1, -0.1);
					matrixStackIn.mulPose(new Quaternion(-90, 0, -45-180, true));

				} else {
					scale = 1.5f;
					matrixStackIn.translate(0.5, 0.5, 0.5);
					matrixStackIn.mulPose(new Quaternion(0, -rot, 0, true));
					matrixStackIn.translate(-0.0, 0.2, 0.075);
					matrixStackIn.mulPose(new Quaternion(-55, 0, 0, true));
				}

				matrixStackIn.scale(scale, scale, scale);
				itemRenderer.renderStatic(item, ItemTransforms.TransformType.GROUND, combinedLightIn, combinedOverlayIn,
						matrixStackIn, bufferIn, (int) tileEntityIn.getBlockPos().asLong());

				matrixStackIn.popPose();
			}
		});

		timer++;
		if (timer > 100)
			timer = 0;

		//ghost.animate();
	}
}
