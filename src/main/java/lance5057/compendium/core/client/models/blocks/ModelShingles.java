package lance5057.compendium.core.client.models.blocks;

import lance5057.compendium.core.data.builders.TCBlockModels;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public class ModelShingles {
	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String suffix,
			String parent, CrossCollisionBlock b) {
		shinglesCapModel(model, name, shingle_texture, shingle_texture, shingle_texture, suffix, parent, b);
	}

	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
			String suffix, String parent, CrossCollisionBlock b) {
		shinglesCapModel(model, name, shingle_texture, log_texture, log_texture + "_top", suffix, parent, b);
	}

	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
			String log_top_texture, String suffix, String parent, CrossCollisionBlock b) {
		ModelFile shinglesPostModel = model.models()
				.withExistingParent(name + "shingles_cap_post" + suffix, model.modLoc(parent + "_post"))
				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);

		ModelFile shinglesSideModel = model.models()
				.withExistingParent(name + "shingles_cap_side" + suffix, model.modLoc(parent + "_side"))
				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);

		ModelFile shinglesBraceModel = model.models()
				.withExistingParent(name + "shingles_cap_brace" + suffix, model.modLoc(parent + "_brace" + suffix))
				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);
		ModelFile shinglesTopModel = model.models()
				.withExistingParent(name + "shingles_cap_top" + suffix, model.modLoc(parent + "_top"))
				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);

		// model.fourWayBlock(b, shinglesPostModel, shinglesSideModel);

		MultiPartBlockStateBuilder builder = model.getMultipartBuilder(b).part().modelFile(shinglesPostModel).addModel()
				.end();

		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
			Direction dir = e.getKey();
			if (dir.getAxis().isHorizontal()) {
				builder.part().modelFile(shinglesSideModel).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true)
						.addModel().condition(e.getValue(), true);
				builder.part().modelFile(shinglesBraceModel).rotationY((((int) dir.getOpposite().toYRot())) % 360)
						.uvLock(true).addModel().condition(e.getValue(), false);
			}

			if (dir == Direction.UP) {
				builder.part().modelFile(shinglesTopModel).addModel().condition(e.getValue(), true);
			}
		});
	}

	public static void shinglesModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
			String suffix, String parent, Block b) {
		ModelFile shinglesModel = model.models()
				.withExistingParent(name + "shingles" + suffix, model.modLoc(parent + suffix))
				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);
		ModelFile shinglesInnerModel = model.models()
				.withExistingParent(name + "shingles_inner" + suffix, model.modLoc(parent + "_inner_corner" + suffix))
				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);
		ModelFile shinglesOuterModel = model.models()
				.withExistingParent(name + "shingles_outer" + suffix, model.modLoc(parent + "_outer_corner" + suffix))
				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);

		VariantBlockStateBuilder builder = model.getVariantBuilder(b);

		for (Direction dir : StairBlock.FACING.getPossibleValues()) {

			// Bottom
			// Straight
			builder.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
					.with(StairBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					// Inner

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
					.with(StairBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
					.with(StairBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
					.rotationY(TCBlockModels.stakeYRotation(dir) - 90).addModel()

					// Outer

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
					.with(StairBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
					.with(StairBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
					.rotationY(TCBlockModels.stakeYRotation(dir) - 90).addModel()

					// Top
					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
					.with(StairBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					// Inner

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
					.with(StairBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
					.with(StairBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					// Outer

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
					.with(StairBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
					.with(StairBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel();
		}
	}
}
