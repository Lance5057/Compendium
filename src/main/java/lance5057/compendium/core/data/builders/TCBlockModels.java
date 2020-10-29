package lance5057.compendium.core.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.core.blocks.ComponentStake;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCBlockModels extends BlockStateProvider {
	private final net.minecraftforge.common.data.ExistingFileHelper fh;

	public TCBlockModels(DataGenerator gen, String modid, ExistingFileHelper fh) {
		super(gen, modid, fh);
		this.fh = fh;
	}

	@Override
	protected void registerStatesAndModels() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();

				this.simpleBlock(mm.STORAGE_BLOCK.get());
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();

				this.simpleBlock(cm.STORAGE_BLOCK.get());
			}

			// Vanilla Component Materials
			if (mh.getVanillaComponents() != null) {
				MaterialVanillaComponents vc = mh.getVanillaComponents();

				doorBlock(vc.DOOR.get(), new ResourceLocation(mh.parentMod, "block/" + mh.name + "_door_bottom"),
						new ResourceLocation(mh.parentMod, "block/" + mh.name + "_door_top"));
				trapdoorBlock(vc.TRAPDOOR.get(), new ResourceLocation(mh.parentMod, "block/" + mh.name + "trapdoor"),
						true);
				paneBlock(vc.BARS.get(), new ResourceLocation(mh.parentMod, "block/" + mh.name + "bars"),
						new ResourceLocation(mh.parentMod, "block/" + mh.name + "bars"));

				lanternModel(mh);
			}

			// Extra Component Materials
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents me = mh.getExtraComponents();

				simpleBlock(me.SHINGLES_BLOCK.get(), models().cubeAll(me.SHINGLES_BLOCK.get().getRegistryName().getPath(), new ResourceLocation(Reference.MOD_ID, "block/" + mh.name +"shingles")));
				stakeModel(mh);
				this.shinglesModel(mh, "", me.SHINGLES.get());
				this.shinglesModel(mh, "alt", me.SHINGLES_ALT.get());
				this.sheetModel(mh);
				this.axisBlock(me.BIGCHAIN.get(), models().withExistingParent(mh.name + "bigchain", modLoc("block/bases/bigchain")).texture("0", modLoc("block/"+mh.name + "bigchain")), models().withExistingParent(mh.name + "bigchain", modLoc("block/bases/bigchain")).texture("0", modLoc("block/"+mh.name + "bigchain")));//.cross(me.BIGCHAIN.get().getRegistryName().getPath(), modLoc("block/" + mh.name + "bigchain")));
			}
			
			if(mh.getOre() != null)
			{
				MaterialOre mo = mh.getOre();
				
//				List<ConfiguredModel> models = new ArrayList<ConfiguredModel>();
//				models.addAll(Arrays.asList();
//				models.addAll(Arrays.asList();
//				models.addAll(Arrays.asList(ConfiguredModel.allRotations(models().withExistingParent(mh.name + "ore", modLoc("block/bases/ore_corner")).texture("1", modLoc("block/"+mh.name + "ore")), true)));

				getVariantBuilder(mo.ORE.get())
	            .partialState().addModels(ConfiguredModel.allRotations(models().withExistingParent(mh.name + "ore", modLoc("block/bases/ore")).texture("1", modLoc("block/"+mh.name + "ore")), true))
	            .partialState().addModels(ConfiguredModel.allRotations(models().withExistingParent(mh.name + "ore_sparse", modLoc("block/bases/ore_sparse")).texture("1", modLoc("block/"+mh.name + "ore")), true))
	            .partialState().addModels(ConfiguredModel.allRotations(models().withExistingParent(mh.name + "ore_corner", modLoc("block/bases/ore_corner")).texture("1", modLoc("block/"+mh.name + "ore")), true));
			}
		}
	}

	private void stakeModel(MaterialHelper mh) {
		ModelFile stakeModel = models().withExistingParent(mh.name + "stake", modLoc("block/bases/componentstake"))
				.texture("rod", "compendium:block/" + mh.name + "stake");
		ModelFile stakeBaseModel = models()
				.withExistingParent(mh.name + "stake_base", modLoc("block/bases/componentstake_base"))
				.texture("rod", "compendium:block/" + mh.name + "stake");

		VariantBlockStateBuilder builder = getVariantBuilder(mh.getExtraComponents().STAKE.get());

		for (Direction dir : ComponentStake.FACING.getAllowedValues()) {

			builder.partialState().with(ComponentStake.FACING, dir).with(ComponentStake.CONNECTED, true).modelForState()
					.modelFile(stakeModel).rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel()

					.partialState().with(ComponentStake.FACING, dir).with(ComponentStake.CONNECTED, false)
					.modelForState()
//				.modelFile(stake)
//				.rotationX(stakeXRotation(dir))
//				.rotationY(stakeYRotation(dir))
					// .nextModel()
					.modelFile(stakeBaseModel).rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel();
		}
	}

	private void shinglesModel(MaterialHelper mh, String suffix, Block b) {
		ModelFile shinglesModel = models()
				.withExistingParent(mh.name + "shingles"+suffix, modLoc("block/bases/shingles"+suffix))
				.texture("0", "compendium:block/" + mh.name + "shingles").texture("1", "compendium:block/shingles_log")
				.texture("2", "minecraft:block/oak_log");
		ModelFile shinglesInnerModel = models()
				.withExistingParent(mh.name + "shingles_inner"+suffix, modLoc("block/bases/shingles_inner_corner"+suffix))
				.texture("0", "compendium:block/" + mh.name + "shingles").texture("1", "compendium:block/shingles_log")
				.texture("2", "minecraft:block/oak_log");
		ModelFile shinglesOuterModel = models()
				.withExistingParent(mh.name + "shingles_outer"+suffix, modLoc("block/bases/shingles_outer_corner"+suffix))
				.texture("0", "compendium:block/" + mh.name + "shingles").texture("1", "compendium:block/shingles_log")
				.texture("2", "minecraft:block/oak_log");

		VariantBlockStateBuilder builder = getVariantBuilder(b);

		for (Direction dir : StairsBlock.FACING.getAllowedValues()) {

			// Bottom
			// Straight
			builder.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.BOTTOM)
					.with(StairsBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
					.rotationY(stakeYRotation(dir) - 180).addModel()

					// Inner

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.BOTTOM)
					.with(StairsBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
					.rotationY(stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.BOTTOM)
					.with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
					.rotationY(stakeYRotation(dir) - 90).addModel()

					// Outer

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.BOTTOM)
					.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
					.rotationY(stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.BOTTOM)
					.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
					.rotationY(stakeYRotation(dir) - 90).addModel()

					// Top
					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.TOP)
					.with(StairsBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
					.rotationX(180).rotationY(stakeYRotation(dir) - 180).addModel()

					// Inner

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.TOP)
					.with(StairsBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
					.rotationX(180).rotationY(stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.TOP)
					.with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
					.rotationX(180).rotationY(stakeYRotation(dir) - 180).addModel()

					// Outer

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.TOP)
					.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
					.rotationX(180).rotationY(stakeYRotation(dir) - 180).addModel()

					.partialState().with(StairsBlock.FACING, dir).with(StairsBlock.HALF, Half.TOP)
					.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
					.rotationX(180).rotationY(stakeYRotation(dir) - 180).addModel();
		}
	}

	private int stakeXRotation(Direction d) {
		if (d == Direction.UP)
			return 0;
		if (d == Direction.DOWN)
			return 180;
		return 90;
	}

	private int stakeYRotation(Direction d) {
		if (d == Direction.UP || d == Direction.DOWN || d == Direction.NORTH)
			return 0;
		if (d == Direction.WEST)
			return 270;
		if (d == Direction.SOUTH)
			return 180;
		return 90;
	}

	private void lanternModel(MaterialHelper mh) {
		ModelFile lanternModel = models()
				.withExistingParent(mh.name + "lantern", modLoc("block/bases/lantern"))
				.texture("all", "compendium:block/" + mh.name + "lantern")
				.texture("all2", "compendium:block/lanternflame");

		ModelFile lanternhangingModel = models()
				.withExistingParent(mh.name + "lanternhanging", modLoc("block/bases/hanging_lantern"))
				.texture("all", "compendium:block/" + mh.name + "lantern")
				.texture("all2", "compendium:block/lanternflame");

		VariantBlockStateBuilder builder = getVariantBuilder(mh.getVanillaComponents().LANTERN.get());

		builder.partialState().with(LanternBlock.HANGING, false).modelForState().modelFile(lanternModel).addModel()
				.partialState().with(LanternBlock.HANGING, true).modelForState().modelFile(lanternhangingModel)
				.addModel();

	}
	
	private void sheetModel(MaterialHelper mh)
	{
		ModelFile sheetBottom = models()
				.withExistingParent(mh.name + "sheet", modLoc("block/bases/carpet"))
				.texture("all", "compendium:block/" + mh.name + "sheet");

		ModelFile sheetTop = models()
				.withExistingParent(mh.name + "sheettop", modLoc("block/bases/carpet_top"))
				.texture("all", "compendium:block/" + mh.name + "sheet");

		VariantBlockStateBuilder builder = getVariantBuilder(mh.getExtraComponents().SHEET.get());

		builder.partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).modelForState().modelFile(sheetBottom).addModel()
				.partialState().with(SlabBlock.TYPE, SlabType.TOP).modelForState().modelFile(sheetTop).addModel()
				.partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).modelForState().modelFile(sheetTop).addModel();
	}
}