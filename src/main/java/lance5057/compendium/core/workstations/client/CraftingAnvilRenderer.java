package lance5057.compendium.core.workstations.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.Reference;
import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.rendering.RenderUtil;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloatVector3;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CraftingAnvilRenderer implements BlockEntityRenderer<CraftingAnvilTE> {
	int timer = 0;
	int toolRandom = 0;
	AnimatedFloatVector3 ghost;

	public CraftingAnvilRenderer(BlockEntityRendererProvider.Context cxt) {
		// super(rendererDispatcherIn);

		ghost = new AnimatedFloatVector3(10, 10, 0, 0.1f);
	}

	@Override
	public void render(CraftingAnvilTE tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

		Minecraft minecraft = Minecraft.getInstance();
		BlockRenderDispatcher brd = minecraft.getBlockRenderer();
		BakedModel bm = brd.getBlockModel(CompendiumBlocks.CRAFTING_ANVIL.get().defaultBlockState());
		
		UnbakedModel um = ForgeModelBakery.instance().getModelOrMissing(new ResourceLocation(Reference.MOD_ID, "block/workstations/anvil"));

		matrixStackIn.pushPose();
		{
			matrixStackIn.translate(0.26f, 1, 0.16);
			float uniscale2 = 0.2f;
			matrixStackIn.scale(uniscale2, uniscale2, uniscale2);
			brd.renderSingleBlock(CompendiumBlocks.CRAFTING_ANVIL.get().defaultBlockState(), matrixStackIn, bufferIn,
					combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
		}
		matrixStackIn.popPose();

		if (!tileEntityIn.hasLevel()) {
			return;
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
					matrixStackIn.pushPose();
					matrixStackIn.translate(xoff + 0.26f, 1, yoff + 0.16);
					matrixStackIn.mulPose(new Quaternion(-90, 0, 0, true));
					float uniscale = 0.2f;
					matrixStackIn.scale(uniscale, uniscale, uniscale);
//					itemRenderer.render(item, ItemTransforms.TransformType.GROUND, combinedLightIn,
//							combinedOverlayIn, matrixStackIn, bufferIn);
					matrixStackIn.popPose();
				}
			}

			ItemStack item = r.getStackInSlot(25);

			if (!item.isEmpty()) {
				matrixStackIn.pushPose();
				matrixStackIn.translate(1, 0.6, 0.5);
				matrixStackIn.mulPose(new Quaternion(150, -60, 30, true));
				float uniscale = 1.7f;
				matrixStackIn.scale(uniscale, uniscale, uniscale);
//				itemRenderer.render(item, ItemTransforms.TransformType.GROUND, combinedLightIn, combinedOverlayIn,
//						matrixStackIn, bufferIn);

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
				matrixStackIn.pushPose();
				matrixStackIn.translate(0.75f, 1.1, 0.5f);
				matrixStackIn.mulPose(new Quaternion(0 + ghost.getX().getFloat(), 0 + ghost.getY().getFloat(),
						45 + ghost.getZ().getFloat(), true));
				matrixStackIn.translate(0.125f, 0.125, 0.0f);
				float uniscale = 0.25f;
				matrixStackIn.scale(uniscale, uniscale, uniscale);

				float transparency = 0.5f;
				int color = RenderUtil.argbToHex(255, 255, 255, (int) (transparency * 255));
				RenderUtil.renderItemCustomColor(tileEntityIn, tool, color, matrixStackIn, bufferIn, combinedLightIn,
						combinedOverlayIn, null);

				matrixStackIn.popPose();
			}
		}

		// Render Recipe Output
		ItemStack item = tileEntityIn.getGhostStack();

		if (!item.isEmpty()) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.5f, 1.1, 0.5f);
			matrixStackIn.mulPose(new Quaternion(-90 + ghost.getX().getFloat(), 90 + ghost.getY().getFloat(),
					45 + ghost.getZ().getFloat(), true));
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
		if (timer > 100) {
			timer = 0;
			if (tileEntityIn.getCurrentTool() != null) {
				RecipeItemUse recipe = tileEntityIn.getCurrentTool();
				toolRandom = tileEntityIn.getLevel().getRandom().nextInt(recipe.tool.getItems().length);
			}
		}

		ghost.animate();

		// For hotswapping, remove later!
		ghost.setMax(0, 0, 90);
		ghost.setMin(0, 0, 0);
		ghost.setSpeed(3.1f);
	}
}
