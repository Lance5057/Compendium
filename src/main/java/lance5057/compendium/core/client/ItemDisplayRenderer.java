package lance5057.compendium.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import lance5057.compendium.core.library.IDisplayItem;
import lance5057.compendium.core.tileentities.ItemDisplayTileEntity;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemDisplayRenderer implements BlockEntityRenderer<ItemDisplayTileEntity> {

	int timer = 0;
	AnimationFloatTransform ghost;

	public ItemDisplayRenderer(BlockEntityRendererProvider.Context cxt) {
		// super(rendererDispatcherIn);

		ghost = new AnimationFloatTransform();
		// timer = rendererDispatcherIn.world.rand.nextInt(100);
	}

	@Override
	public void render(ItemDisplayTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
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

				float blockRot = dir.toYRot();
				matrixStackIn.pushPose();

				// if (CompendiumConfig.getInstance().general.animated_displays.get())
				// matrixStackIn.translate(ghost.getX().getFloat(), ghost.getY().getFloat(),
				// ghost.getZ().getFloat());
				Block b = tileEntityIn.getBlockState().getBlock();
				if (b instanceof IDisplayItem) {

					IDisplayItem d = (IDisplayItem) b;

					Vec3 pos = d.getDisplayPosition(item);
					matrixStackIn.translate(pos.x, pos.y, pos.z);
					
					Vec3 rot = d.getDisplayRotation(item);
					matrixStackIn.mulPose(new Quaternion((float)rot.x, (float)rot.y - blockRot, (float)rot.z, true));
					
					Vec3 sca = d.getDisplayScale(item);
					matrixStackIn.scale((float)sca.x, (float)sca.y, (float)sca.z);
				}

				itemRenderer.renderStatic(item, ItemTransforms.TransformType.GROUND, combinedLightIn, combinedOverlayIn,
						matrixStackIn, bufferIn, (int) tileEntityIn.getBlockPos().asLong());

				matrixStackIn.popPose();
			}
		});

		timer++;
		if (timer > 100)
			timer = 0;

		ghost.animate();
	}
}