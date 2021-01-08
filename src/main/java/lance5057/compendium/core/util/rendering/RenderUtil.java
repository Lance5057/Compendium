package lance5057.compendium.core.util.rendering;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderUtil {

	public static int argbToHex(int r, int g, int b, int a) {
		return (a << 24) | (r << 16) | (g << 8) | (b);
	}

	public static void renderItemCustomColor(TileEntity tileEntityIn, ItemStack stack, int color, MatrixStack ms, IRenderTypeBuffer buffers, int light, int overlay, @Nullable IBakedModel model) {
		ms.push();
		if (model == null) {
			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			model = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
		}
		model = ForgeHooksClient.handleCameraTransforms(ms, model, ItemCameraTransforms.TransformType.NONE, false);
		ms.translate(-0.5D, -0.5D, -0.5D);

		if (!model.isBuiltInRenderer() && (stack.getItem() != Items.TRIDENT)) {
			RenderType rendertype = RenderTypeLookup.func_239219_a_(stack, true);
			IVertexBuilder ivertexbuilder = ItemRenderer.getEntityGlintVertexBuilder(buffers, rendertype, true, stack.hasEffect());
			renderBakedItemModel(model, stack, color, light, overlay, ms, ivertexbuilder);
		} else {
			stack.getItem().getItemStackTileEntityRenderer().func_239207_a_(stack, ItemCameraTransforms.TransformType.NONE, ms, buffers, light, overlay);
		}

		ms.pop();
	}

	// [VanillaCopy] ItemRenderer with custom color
	private static void renderBakedItemModel(IBakedModel model, ItemStack stack, int color, int light, int overlay, MatrixStack ms, IVertexBuilder buffer) {
		Random random = new Random();
		long i = 42L;

		for (Direction direction : Direction.values()) {
			random.setSeed(42L);
			renderBakedItemQuads(ms, buffer, color, model.getQuads((BlockState) null, direction, random), stack, light, overlay);
		}

		random.setSeed(42L);
		renderBakedItemQuads(ms, buffer, color, model.getQuads((BlockState) null, (Direction) null, random), stack, light, overlay);
	}

	// [VanillaCopy] ItemRenderer, with custom color + alpha support
	private static void renderBakedItemQuads(MatrixStack ms, IVertexBuilder buffer, int color, List<BakedQuad> quads, ItemStack stack, int light, int overlay) {
		MatrixStack.Entry matrixstack$entry = ms.getLast();

		for (BakedQuad bakedquad : quads) {
			int i = color;

			float f = (float) (i >> 16 & 255) / 255.0F;
			float f1 = (float) (i >> 8 & 255) / 255.0F;
			float f2 = (float) (i & 255) / 255.0F;
			float alpha = ((color >> 24) & 0xFF) / 255.0F;
			buffer.addVertexData(matrixstack$entry, bakedquad, f, f1, f2, alpha, light, overlay, true);
		}

	}
}
