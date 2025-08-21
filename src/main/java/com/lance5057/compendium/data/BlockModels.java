package com.lance5057.compendium.data;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.blocks.bed.FancyBedBlockEntity;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.chair.ChairBlockEntity;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlock;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlockEntity;
import com.lance5057.compendium.blocks.fence.FancyFenceBlockEntity;
import com.lance5057.compendium.blocks.shingles.slanted.ShinglesSlantedBlockEntity;
import com.lance5057.compendium.blocks.table.TableBlock;
import com.lance5057.compendium.blocks.table.TableBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelBuilder;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialUnbakedModel.Layer;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialBuilder;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialUnbakedModel;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.workstations.workbench.WorkbenchBlock;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel.Builder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModels extends BlockStateProvider {

	public BlockModels(PackOutput gen, ExistingFileHelper exFileHelper) {
		super(gen, Compendium.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		CompendiumIndex.index.forEach(i -> {
			i.blockStateModel(this);
		});

		this.simpleBlock(CompendiumBlocks.HAMMERING_STATION.get(),
				models().getExistingFile(modLoc("block/workstations/hammering_station")));

		this.simpleBlock(CompendiumBlocks.SAW_BUCK.get(),
				models().getExistingFile(modLoc("block/workstations/sawbuck")));

		this.simpleBlock(CompendiumBlocks.SCRAPPING_TABLE.get(),
				models().getExistingFile(modLoc("block/workstations/dismantling_table")));

		this.horizontalBlock(CompendiumBlocks.COSMETIC_TOOLBOX.get(),
				models().getExistingFile(modLoc("block/cosmetic_toolbox_open")));

		getVariantBuilder(CompendiumBlocks.WORKBENCH.get()).forAllStates(state -> {
			Half side = state.getValue(WorkbenchBlock.HALF);
			return ConfiguredModel.builder()
					.modelFile(new ModelFile.ExistingModelFile(
							ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
									"block/workstations/workbench_" + side.name().toLowerCase()),
							models().existingFileHelper))
					.rotationY(((int) state.getValue(WorkbenchBlock.FACING).toYRot() - 90) % 360).build();
		});

		getVariantBuilder(CompendiumBlocks.TOOLRACK.get()).forAllStates(state -> {
			return ConfiguredModel.builder()
					.modelFile(new ModelFile.ExistingModelFile(
							ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/toolrack"),
							models().existingFileHelper))
					.rotationY(((int) state.getValue(WorkbenchBlock.FACING).toYRot() - 90) % 360).build();
		});

		getVariantBuilder(CompendiumBlocks.COMPONENT_DRAWER.get()).forAllStates(state -> {
			return ConfiguredModel.builder()
					.modelFile(new ModelFile.ExistingModelFile(
							ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/component_drawer"),
							models().existingFileHelper))
					.rotationY(((int) state.getValue(WorkbenchBlock.FACING).toYRot() - 90) % 360).build();
		});

		getVariantBuilder(CompendiumBlocks.WINDOW.get()).forAllStates(state -> {
			Builder<?> b = ConfiguredModel.builder();
			MultiMaterialModelBuilder<BlockModelBuilder> msmb = models().getBuilder("window")
					.customLoader(MultiMaterialModelBuilder::begin);
			msmb.base(models().cubeAll("window_base", mcLoc("block/glass")).renderType("cutout"));

			msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), "window_trim", 0));
			msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.GLASS), "window", 1));

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.build();
		});

//		this.simpleBlock(CompendiumBlocks.CHAIR.get(), models().getExistingFile(mcLoc("air")));

		getVariantBuilder(CompendiumBlocks.CHAIR.get()).forAllStates(state -> {
			Builder<?> b = ConfiguredModel.builder();
			MultiStyleMaterialBuilder<BlockModelBuilder> msmb = models().getBuilder("chair")
					.customLoader(MultiStyleMaterialBuilder::begin);
			msmb.base(models().cubeAll("chair_base", mcLoc("block/oak_planks")));

			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "back",
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.back, 0, 0));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "seat",
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.seat, 1, 1));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "legs",
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.legs, 2, 2));

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.rotationY(((int) state.getValue(ChairBlock.FACING).toYRot()) % 360).build();
		});

		getMultipartBuilder(CompendiumBlocks.TABLE.get())
				// Table Top
				.part()
				.modelFile(models().getBuilder("table_top").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_top_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.top, 0, 0))
						.end())
				.addModel()
				// Table Legs
				.end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, false).condition(TableBlock.E, false)
				.condition(TableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).condition(TableBlock.E, false)
				.condition(TableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, false).condition(TableBlock.W, false)
				.condition(TableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).condition(TableBlock.NW, false)
				.condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, true).condition(TableBlock.NW, false)
				.condition(TableBlock.W, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, true).condition(TableBlock.E, true)
				.condition(TableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, true).condition(TableBlock.E, true)
				.condition(TableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, false).condition(TableBlock.W, true)
				.condition(TableBlock.S, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).condition(TableBlock.NW, true)
				.condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, false).condition(TableBlock.E, false)
				.condition(TableBlock.NE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).condition(TableBlock.E, false)
				.condition(TableBlock.SE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, true).condition(TableBlock.W, false)
				.condition(TableBlock.S, false).end()
				// Table Leg Sides
				.part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(270).addModel().condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(90).addModel().condition(TableBlock.E, false).end();

		getMultipartBuilder(CompendiumBlocks.CLOTHED_TABLE.get())
				// Table Cloth
				.part()
				.modelFile(models().getBuilder("table_cloth").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_cloth_model", mcLoc("block/white_wool")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "cloth",
								List.of(MATERIAL_TYPES.TEXTILE), ClothedTableBlockEntity.cloth, 2, 2))
						.end())
				.addModel().end()
				// Table Top
				.part()
				.modelFile(models().getBuilder("table_top").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_top_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.top, 0, 0))
						.end())
				.addModel()
				// Table Legs
				.end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, false).condition(ClothedTableBlock.W, false)
				.condition(ClothedTableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.NW, false)
				.condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, true).condition(ClothedTableBlock.NW, false)
				.condition(ClothedTableBlock.W, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, true).condition(ClothedTableBlock.E, true)
				.condition(ClothedTableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, true).condition(ClothedTableBlock.E, true)
				.condition(ClothedTableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, false).condition(ClothedTableBlock.W, true)
				.condition(ClothedTableBlock.S, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.NW, true)
				.condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.NE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.SE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, true).condition(ClothedTableBlock.W, false)
				.condition(ClothedTableBlock.S, false).end()
				// Table Leg Sides
				.part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ClothedTableBlockEntity.legs, 0, 1,
								"_leg"))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.E, false).end();

		getMultipartBuilder(CompendiumBlocks.FANCY_BED.get())
				// Bed Top

				// Frame
				.part()
				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()

				// Mattress
				.part()
				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()

				// Sheet
				.part()
				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()

				// pillow
				.part()
				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()

				// blanket
				.part()
				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()

				// Bed Bottom

				// Frame
				.part()
				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyBedBlockEntity.frame, 0, 0))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()

				// Mattress
				.part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_mattress").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
								.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_mattress").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
								.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_mattress").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
								.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_mattress").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.mattress, 1, 1))
								.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()

				// sheet
				.part()
				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.sheet, 2, 2))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()

				// pillow
				.part()
				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.pillow, 3, 3))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()

				// blanket
				.part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
								.end())
				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
								.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
								.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
				.modelFile(
						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
										List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
								.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end();

		fence();

		getVariantBuilder(CompendiumBlocks.SHINGLES_SLANTED.get()).forAllStatesExcept(state -> {
			Direction facing = state.getValue(StairBlock.FACING);
			Half half = state.getValue(StairBlock.HALF);
			StairsShape shape = state.getValue(StairBlock.SHAPE);
			int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some
																// reason
			if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
				yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
			}
			if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
				yRot += 90; // Top stairs are rotated 90 degrees clockwise
			}
			yRot %= 360;
			boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
			return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT
					? models().getBuilder("shingles_slanted_shingles").customLoader(MultiStyleMaterialBuilder::begin)
							.base(models().cubeAll("shingles_slanted_shingles_model", mcLoc("block/oak_planks")))
							.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles", "shingles_slanted",
									List.of(MATERIAL_TYPES.TEXTILE), ShinglesSlantedBlockEntity.shingles, 0, 0))
							.end()
					: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT
							? models().getBuilder("shingles_slanted_shingles")
									.customLoader(MultiStyleMaterialBuilder::begin)
									.base(models().cubeAll("shingles_slanted_shingles_model",
											mcLoc("block/oak_planks")))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles", "shingles_slanted",
											List.of(MATERIAL_TYPES.TEXTILE), ShinglesSlantedBlockEntity.shingles, 0, 0))
									.end()
							: models().getBuilder("shingles_slanted_shingles")
									.customLoader(MultiStyleMaterialBuilder::begin)
									.base(models().cubeAll("shingles_slanted_shingles_model",
											mcLoc("block/oak_planks")))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles", "shingles_slanted",
											List.of(MATERIAL_TYPES.TEXTILE), ShinglesSlantedBlockEntity.shingles, 0, 0))
									.end())
					.rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(uvlock).build();
		}, StairBlock.WATERLOGGED);

//		this.stairsBlock(CompendiumBlocks.SHINGLES_SLANTED.get(),
//				,
//				models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
//						.end(),
//				models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), FancyBedBlockEntity.blanket, 4, 4))
//						.end());
	}

	public void fence() {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(CompendiumBlocks.FANCY_FENCE.get()).part()
				.modelFile(models().getBuilder("fancy_fence_post").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_fence_post_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("fence", "post",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyFenceBlockEntity.post, 0, 0))
						.end())
				.addModel().end();
		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
			Direction dir = e.getKey();
			if (dir.getAxis().isHorizontal()) {
				builder.part().modelFile(models().getBuilder("fancy_fence_side")
						.customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_fence_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("fence", "side",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), FancyFenceBlockEntity.side, 1, 1))
						.end()).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel()
						.condition(e.getValue(), true).end();
			}
		});
	}
}
