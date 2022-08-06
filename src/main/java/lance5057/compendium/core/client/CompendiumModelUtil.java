package lance5057.compendium.core.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import lance5057.compendium.core.util.rendering.CompendiumModelPart;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumModelUtil {
	public static void loadModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, BlacklistedModel model, float timer) {

		if (model.isBlock) {
			UnbakedModel um = ForgeModelBakery.instance().getModelOrMissing(model.rc);
			if (um instanceof BlockModel) {
				BlockModel bm = (BlockModel) um;

				blockModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, bm, model.blacklist,
						model.transform, timer);

			}
		} else {
			Item item = ForgeRegistries.ITEMS.getValue(model.rc);
			itemModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, item, model.blacklist,
					model.transform, timer);
		}
	}

	public static void blockModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, BlockModel bm, List<Integer> blacklist, AnimationFloatTransform transform,
			float timer) {
		matrixStackIn.pushPose();
		{
			List<CompendiumModelPart> currentModel = convert(bm, blacklist);

			matrixStackIn.translate(transform.getLocation().getX().animate(timer) / 16,
					transform.getLocation().getY().animate(timer) / 16,
					transform.getLocation().getZ().animate(timer) / 16);
			matrixStackIn.mulPose(new Quaternion(transform.getRotation().getX().animate(timer),
					transform.getRotation().getY().animate(timer), transform.getRotation().getZ().animate(timer),
					true));
			matrixStackIn.scale(1f + transform.getScale().getX().animate(timer),
					1f + transform.getScale().getY().animate(timer), 1f + transform.getScale().getZ().animate(timer));
			for (CompendiumModelPart b : currentModel) {
				b.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
			}
		}
		matrixStackIn.popPose();

	}

	public static void itemModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
			int combinedOverlayIn, Item item, List<Integer> blacklist, AnimationFloatTransform transform, float timer) {
		matrixStackIn.pushPose();
		{
			ItemStack stack = new ItemStack(item);
			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			BakedModel bakedmodel = itemRenderer.getModel(stack, null, null, 0);
			// List<CompendiumModelPart> currentModel = convert(bm, blacklist);

			matrixStackIn.translate(transform.getLocation().getX().animate(timer) / 16,
					transform.getLocation().getY().animate(timer) / 16,
					transform.getLocation().getZ().animate(timer) / 16);
			matrixStackIn.mulPose(new Quaternion(transform.getRotation().getX().animate(timer),
					transform.getRotation().getY().animate(timer), transform.getRotation().getZ().animate(timer),
					true));
			matrixStackIn.scale(1f + transform.getScale().getX().animate(timer),
					1f + transform.getScale().getY().animate(timer), 1f + transform.getScale().getZ().animate(timer));
			itemRenderer.render(stack, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn,
					combinedLightIn, combinedOverlayIn, bakedmodel);
		}
		matrixStackIn.popPose();

	}

	public static List<CompendiumModelPart> convert(BlockModel bm, List<Integer> blacklist) {

		List<CompendiumModelPart> mpl = new ArrayList<CompendiumModelPart>();

		for (int i = 0; i < bm.getElements().size(); i++) {
			if (!blacklist.contains(i)) {
				BlockElement e = bm.getElements().get(i);
				List<CompendiumModelPart.Cube> cubeList = new ArrayList<CompendiumModelPart.Cube>();

				CompendiumModelPart.Cube cube = new CompendiumModelPart.Cube(1, 1, e.from, e.to, new Vector3f(0, 0, 0),
						false, e.faces.getOrDefault(Direction.UP, null), e.faces.getOrDefault(Direction.DOWN, null),
						e.faces.getOrDefault(Direction.NORTH, null), e.faces.getOrDefault(Direction.SOUTH, null),
						e.faces.getOrDefault(Direction.WEST, null), e.faces.getOrDefault(Direction.EAST, null),
						bm.textureMap);
				cubeList.add(cube);

				CompendiumModelPart mp = new CompendiumModelPart(cubeList, Collections.emptyMap());

				if (e.rotation != null) {
					switch (e.rotation.axis) {
					case X:
						mp.setRotation(e.rotation.angle, 0, 0);
						break;
					case Y:
						mp.setRotation(0, e.rotation.angle, 0);
						break;
					case Z:
						mp.setRotation(0, 0, e.rotation.angle);
						break;
					default:
						mp.setRotation(0, 0, 0);
						break;
					}
				}

				mpl.add(mp);
			}
		}
		return mpl;
	}
}
