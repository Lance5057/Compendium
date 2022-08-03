package lance5057.compendium.core.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import lance5057.compendium.Reference;
import lance5057.compendium.core.util.rendering.CompendiumModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.ForgeModelBakery;

public class CompendiumModelUtil {
    public static void loadModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
	    int combinedOverlayIn, BlacklistedModel model) {
	UnbakedModel um = ForgeModelBakery.instance().getModelOrMissing(model.rc);
	if (um instanceof BlockModel) {
	    BlockModel bm = (BlockModel) um;

	    blockModel(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, bm, model.blacklist);

	}
    }

    public static void blockModel(PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn,
	    int combinedOverlayIn, BlockModel bm, List<Integer> blacklist) {
	matrixStackIn.pushPose();
	{
	    matrixStackIn.translate(1f, 0, 0);

	    List<CompendiumModelPart> currentModel = convert(bm, blacklist);

	    for (CompendiumModelPart b : currentModel) {
		b.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	    }
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
