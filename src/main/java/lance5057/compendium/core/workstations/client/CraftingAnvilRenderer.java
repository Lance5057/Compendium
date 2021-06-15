package lance5057.compendium.core.workstations.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.rendering.RenderUtil;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloatVector3;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CraftingAnvilRenderer extends TileEntityRenderer<CraftingAnvilTE> {
    int timer = 0;
    int toolRandom = 0;
    AnimatedFloatVector3 ghost;

    public CraftingAnvilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
	super(rendererDispatcherIn);

	ghost = new AnimatedFloatVector3(10, 10, 0, 0.1f);
    }

    @Override
    public void render(CraftingAnvilTE tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
	    IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

	if (!tileEntityIn.hasWorld()) {
	    return;
	}

	ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

	LazyOptional<IItemHandler> itemHandler = tileEntityIn
		.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

	itemHandler.ifPresent(r -> {

	    float xoff = 0;
	    float yoff = 0;
	    for (int i = 0; i < 25; i++) {
		xoff = (i % 5) * 0.12f;
		if (i % 5 == 0)
		    yoff += 0.12f;
		ItemStack item = r.getStackInSlot(i);

		if (!item.isEmpty()) {
		    matrixStackIn.push();
		    matrixStackIn.translate(xoff + 0.26f, 1, yoff + 0.16);
		    matrixStackIn.rotate(new Quaternion(-90, 0, 0, true));
		    float uniscale = 0.2f;
		    matrixStackIn.scale(uniscale, uniscale, uniscale);
		    itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn,
			    combinedOverlayIn, matrixStackIn, bufferIn);
		    matrixStackIn.pop();
		}
	    }

	    ItemStack item = r.getStackInSlot(25);

	    if (!item.isEmpty()) {
		matrixStackIn.push();
		matrixStackIn.translate(1, 0.6, 0.5);
		matrixStackIn.rotate(new Quaternion(150, -60, 30, true));
		float uniscale = 1.7f;
		matrixStackIn.scale(uniscale, uniscale, uniscale);
		itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, combinedLightIn,
			combinedOverlayIn, matrixStackIn, bufferIn);

		matrixStackIn.pop();
	    }
	});

	// Render Recipe Tool
	if (tileEntityIn.getCurrentTool() != null) {
	    RecipeItemUse recipe = tileEntityIn.getCurrentTool();

	    if (toolRandom >= recipe.tool.getMatchingStacks().length)
		toolRandom = tileEntityIn.getWorld().getRandom().nextInt(recipe.tool.getMatchingStacks().length);
	    ItemStack tool = recipe.tool.getMatchingStacks()[toolRandom];

	    if (!tool.isEmpty()) {
		matrixStackIn.push();
		matrixStackIn.translate(0.75f, 1.1, 0.5f);
		matrixStackIn.rotate(new Quaternion(0 + ghost.getX().getFloat(), 0 + ghost.getY().getFloat(),
			45 + ghost.getZ().getFloat(), true));
		matrixStackIn.translate(0.125f, 0.125, 0.0f);
		float uniscale = 0.25f;
		matrixStackIn.scale(uniscale, uniscale, uniscale);

		float transparency = 0.5f;
		int color = RenderUtil.argbToHex(255, 255, 255, (int) (transparency * 255));
		RenderUtil.renderItemCustomColor(tileEntityIn, tool, color, matrixStackIn, bufferIn, combinedLightIn,
			combinedOverlayIn, null);

		matrixStackIn.pop();
	    }
	}

	// Render Recipe Output
	ItemStack item = tileEntityIn.getGhostStack();

	if (!item.isEmpty()) {
	    matrixStackIn.push();
	    matrixStackIn.translate(0.5f, 1.1, 0.5f);
	    matrixStackIn.rotate(new Quaternion(-90 + ghost.getX().getFloat(), 90 + ghost.getY().getFloat(),
		    45 + ghost.getZ().getFloat(), true));
	    float uniscale = 0.7f;
	    matrixStackIn.scale(uniscale, uniscale, uniscale);
//			if (tileEntityIn.maxProgress > 0) {
//				float transparency = (float) tileEntityIn.progress / (float) tileEntityIn.maxProgress;
//				int color = RenderUtil.argbToHex(255, 255, 255, (int) (transparency * 255));
//				RenderUtil.renderItemCustomColor(tileEntityIn, item, color, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, null);
//			}
	    matrixStackIn.pop();
	}

	timer++;
	if (timer > 100) {
	    timer = 0;
	    if (tileEntityIn.getCurrentTool() != null) {
		RecipeItemUse recipe = tileEntityIn.getCurrentTool();
		toolRandom = tileEntityIn.getWorld().getRandom().nextInt(recipe.tool.getMatchingStacks().length);
	    }
	}

	ghost.animate();

	// For hotswapping, remove later!
	ghost.setMax(0, 0, 90);
	ghost.setMin(0, 0, 0);
	ghost.setSpeed(3.1f);
    }
}
