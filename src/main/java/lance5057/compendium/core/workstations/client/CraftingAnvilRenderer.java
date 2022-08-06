package lance5057.compendium.core.workstations.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.client.CompendiumModelUtil;
import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloat;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloatVector3;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
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

public class CraftingAnvilRenderer implements BlockEntityRenderer<CraftingAnvilTE> {
	int timer = 0;
	int toolRandom = 0;
	AnimationFloatTransform ghost;

	// List<CompendiumModelPart> currentModel;
//	List<Integer> blacklist;

	public CraftingAnvilRenderer(BlockEntityRendererProvider.Context cxt) {
		// super(rendererDispatcherIn);

		ghost = new AnimationFloatTransform()
				.setLocation(new AnimatedFloatVector3().setY(new AnimatedFloat(-0.1f, 0.1f, 0.001f, false, false)))
				.setRotation(new AnimatedFloatVector3().setX(new AnimatedFloat(0f, 360f, 1f, false, false)));
		// currentModel = new ArrayList<CompendiumModelPart>();

		// Remove this later
		// blacklist = new ArrayList<Integer>();
	}

	@Override
	public void render(CraftingAnvilTE tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (!tileEntityIn.hasLevel()) {
			return;
		}

		if (tileEntityIn.getCurrentTool() != null) {
			for (BlacklistedModel b : tileEntityIn.getCurrentTool().model)
				CompendiumModelUtil.loadModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, b, timer);
		}

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		LazyOptional<IItemHandler> itemInteractionHandler = tileEntityIn
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

		itemInteractionHandler.ifPresent(r -> {

			float xoff = 0;
			float yoff = 0;
			for (int i = 0; i < 25; i++) {
				xoff = (i % 5) * 0.12f;
				if (i % 5 == 0)
					yoff += 0.12f;
				ItemStack item = r.getStackInSlot(i);

				if (!item.isEmpty()) {
					BakedModel bakedmodel = itemRenderer.getModel(item, tileEntityIn.getLevel(), null, 0);
					matrixStackIn.pushPose();
					matrixStackIn.translate(xoff + 0.26f, 1, yoff + 0.16);
					matrixStackIn.mulPose(new Quaternion(-90, 0, 0, true));
					float uniscale = 0.2f;
					matrixStackIn.scale(uniscale, uniscale, uniscale);
					itemRenderer.render(item, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn,
							combinedLightIn, combinedOverlayIn, bakedmodel);
					matrixStackIn.popPose();
				}
			}

			ItemStack item = r.getStackInSlot(25);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, tileEntityIn.getLevel(), null, 0);
				matrixStackIn.pushPose();
				matrixStackIn.translate(1, 0.6, 0.5);
				matrixStackIn.mulPose(new Quaternion(150, -60, 30, true));
				float uniscale = 1.7f;
				matrixStackIn.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn,
						combinedLightIn, combinedOverlayIn, bakedmodel);
				matrixStackIn.popPose();
			}
		});

		// Render Recipe Tool
		if (tileEntityIn.getCurrentTool() != null) {
			RecipeItemUse recipe = tileEntityIn.getCurrentTool();

			if (toolRandom >= recipe.tool.getItems().length)
				toolRandom = tileEntityIn.getLevel().getRandom().nextInt(recipe.tool.getItems().length);
			ItemStack tool = recipe.tool.getItems()[toolRandom];

			if (!tool.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(tool, tileEntityIn.getLevel(), null, 0);

				matrixStackIn.pushPose();

//				matrixStackIn.translate(0.75f + ghost.getLocation().getX().getFloat(),
//						1.1 + ghost.getLocation().getY().getFloat(), 0.5f + ghost.getLocation().getZ().getFloat());

				matrixStackIn.translate(0, 1, 0.0f);
				// RenderUtil.debugPart.render(matrixStackIn, bufferIn, combinedLightIn,
				// combinedOverlayIn);
				matrixStackIn.mulPose(new Quaternion(0 + ghost.getRotation().getX().animate(timer),
						0 + ghost.getRotation().getY().animate(timer), 0 + ghost.getRotation().getZ().animate(timer),
						true));
				matrixStackIn.translate(0, -1, 0.0f);

				// matrixStackIn.translate(0.125f, 0.125, 0.0f);

				float uniscale = 0.25f;
				matrixStackIn.scale(uniscale + ghost.getScale().getX().animate(timer),
						uniscale + ghost.getScale().getY().animate(timer),
						uniscale + ghost.getScale().getZ().animate(timer));

//				float transparency = 0.5f;
//				int color = RenderUtil.argbToHex(255, 255, 255, (int) (transparency * 255));
//				RenderUtil.renderItemCustomColor(tileEntityIn, tool, color, matrixStackIn, bufferIn, combinedLightIn,
//						combinedOverlayIn, null);

				itemRenderer.render(tool, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn,
						combinedLightIn, combinedOverlayIn, bakedmodel);

				matrixStackIn.popPose();
			}
		}

		// Render Recipe Output
		ItemStack item = tileEntityIn.getGhostStack();

		if (!item.isEmpty()) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.5f, 1.1, 0.5f);
//			matrixStackIn.mulPose(new Quaternion(-90 + ghost.getX().getFloat(), 90 + ghost.getY().getFloat(),
//					45 + ghost.getZ().getFloat(), true));
			float uniscale = 0.7f;
			matrixStackIn.scale(uniscale, uniscale, uniscale);
//			if (tileEntityIn.maxProgress > 0) {
//				float transparency = (float) tileEntityIn.progress / (float) tileEntityIn.maxProgress;
//				int color = RenderUtil.argbToHex(255, 255, 255, (int) (transparency * 255));
//				RenderUtil.renderItemCustomColor(tileEntityIn, item, color, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, null);
//			}
			matrixStackIn.popPose();
		}

		timer++;
		if (timer % 100 == 0) {
			if (tileEntityIn.getCurrentTool() != null) {
				RecipeItemUse recipe = tileEntityIn.getCurrentTool();
				toolRandom = tileEntityIn.getLevel().getRandom().nextInt(recipe.tool.getItems().length);
			}
		}

		ghost.animate(timer);

		// For hotswapping, remove later!
//		ghost.setMax(0, 0, 10);
//		ghost.setMin(0, 0, 0);
//		ghost.setSpeed(5);
	}

}
