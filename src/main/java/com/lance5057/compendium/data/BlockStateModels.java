package com.lance5057.compendium.data;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlock;
import com.lance5057.compendium.blocks.shingles.slanted.cap.ShinglesCapSlanted;
import com.lance5057.compendium.blocks.table.TableBlock;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialBuilder;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialUnbakedModel;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.style.StyleData;
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

public class BlockStateModels extends BlockStateProvider {

	public BlockStateModels(PackOutput gen, ExistingFileHelper exFileHelper) {
		super(gen, Compendium.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		CompendiumIndex.index.forEach(i -> {
			i.blockStateModel(this);
		});

		this.simpleBlock(CompendiumBlocks.HAMMERING_STATION.get(),
				models().getExistingFile(modLoc("block/workstations/hammering_station")));

		this.horizontalBlock(CompendiumBlocks.SAW_BUCK.get(),
				models().getExistingFile(modLoc("block/workstations/sawbuck")));

		this.horizontalBlock(CompendiumBlocks.SCRAPPING_TABLE.get(),
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
			MultiStyleMaterialBuilder<BlockModelBuilder> msmb = models().getBuilder("window")
					.customLoader(MultiStyleMaterialBuilder::begin);
			msmb.base(models().cubeAll("window_base", mcLoc("block/glass")).renderType("cutout"));

			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("window", "glass", List.of(MATERIAL_TYPES.GLASS),
					StyleData.WINDOW_GLASS.getTypes(), 0, 0));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("window", "trim",
					List.of(MATERIAL_TYPES.WOOD, MATERIAL_TYPES.METAL), StyleData.WINDOW_TRIM.getTypes(), 1, 1));

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.build();
		});

		getVariantBuilder(CompendiumBlocks.CHAIR.get()).forAllStates(state -> {
			Builder<?> b = ConfiguredModel.builder();
			MultiStyleMaterialBuilder<BlockModelBuilder> msmb = models().getBuilder("chair")
					.customLoader(MultiStyleMaterialBuilder::begin);
			msmb.base(models().cubeAll("chair_base", mcLoc("block/oak_planks")));

			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "back", List.of(MATERIAL_TYPES.WOOD),
					StyleData.CHAIR_BACK.getTypes(), 0, 0));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "seat", List.of(MATERIAL_TYPES.WOOD),
					StyleData.CHAIR_SEAT.getTypes(), 1, 1));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "legs", List.of(MATERIAL_TYPES.WOOD),
					StyleData.CHAIR_LEGS.getTypes(), 2, 2));

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.rotationY(((int) state.getValue(ChairBlock.FACING).toYRot()) % 360).build();
		});

		ConfiguredModel.builder()
				.modelFile(models().getBuilder("extra/table").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_base", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top", List.of(MATERIAL_TYPES.WOOD),
								StyleData.TABLE_TOP.getTypes(), 0, 0, "_inventory"))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1, "_inventory"))
						.end())
				.build();

		ConfiguredModel.builder()
				.modelFile(models().getBuilder("extra/clothed_table").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_base", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("clothed_table", "top",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_TOP.getTypes(), 0, 0, "_inventory"))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("clothed_table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1, "_inventory"))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("clothed_table", "cloth",
								List.of(MATERIAL_TYPES.TEXTILE), StyleData.TABLE_CLOTH.getTypes(), 2, 2, "_inventory"))
						.end())
				.build();

		ConfiguredModel.builder()
				.modelFile(models().getBuilder("extra/chair").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("chair_base", mcLoc("block/oak_planks")))

						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "back",
								List.of(MATERIAL_TYPES.WOOD), StyleData.CHAIR_BACK.getTypes(), 0, 0))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "seat",
								List.of(MATERIAL_TYPES.WOOD), StyleData.CHAIR_SEAT.getTypes(), 1, 1))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.CHAIR_LEGS.getTypes(), 2, 2))
						.end())
				.build();

		ConfiguredModel.builder()
				.modelFile(models().getBuilder("extra/fancy_bed").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("bed_base", mcLoc("block/oak_planks")))

						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "frame", List.of(MATERIAL_TYPES.WOOD),
								StyleData.BED_FRAME.getTypes(), 0, 0))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "mattress",
								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_MATTRESS.getTypes(), 1, 1))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "sheet", List.of(MATERIAL_TYPES.WOOD),
								StyleData.BED_SHEET.getTypes(), 2, 2))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "pillow",
								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_PILLOW.getTypes(), 3, 3))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "blanket",
								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_BLANKET.getTypes(), 4, 4))
						.end())
				.build();

		getMultipartBuilder(CompendiumBlocks.TABLE.get())
				// Table Top
				.part()
				.modelFile(models().getBuilder("table_top").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_top_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top", List.of(MATERIAL_TYPES.WOOD),
								StyleData.TABLE_TOP.getTypes(), 0, 0))
						.end())
				.addModel()
				// Table Legs
				.end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, false).condition(TableBlock.E, false)
				.condition(TableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).condition(TableBlock.E, false)
				.condition(TableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, false).condition(TableBlock.W, false)
				.condition(TableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).condition(TableBlock.NW, false)
				.condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, true).condition(TableBlock.NW, false)
				.condition(TableBlock.W, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, true).condition(TableBlock.E, true)
				.condition(TableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, true).condition(TableBlock.E, true)
				.condition(TableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, false).condition(TableBlock.W, true)
				.condition(TableBlock.S, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).condition(TableBlock.NW, true)
				.condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, false).condition(TableBlock.E, false)
				.condition(TableBlock.NE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).condition(TableBlock.E, false)
				.condition(TableBlock.SE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, true).condition(TableBlock.W, false)
				.condition(TableBlock.S, false).end()
				// Table Leg Sides
				.part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.E, false).end();

		getMultipartBuilder(CompendiumBlocks.CLOTHED_TABLE.get())
				// Table Cloth
				.part()
				.modelFile(models().getBuilder("table_cloth").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_cloth_model", mcLoc("block/white_wool")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "cloth",
								List.of(MATERIAL_TYPES.TEXTILE), StyleData.TABLE_CLOTH.getTypes(), 2, 2))
						.end())
				.addModel().end()
				// Table Top
				.part()
				.modelFile(models().getBuilder("table_top").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_top_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top", List.of(MATERIAL_TYPES.WOOD),
								StyleData.TABLE_TOP.getTypes(), 0, 0))
						.end())
				.addModel()
				// Table Legs
				.end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, false).condition(ClothedTableBlock.W, false)
				.condition(ClothedTableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.NW, false)
				.condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, true).condition(ClothedTableBlock.NW, false)
				.condition(ClothedTableBlock.W, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, true).condition(ClothedTableBlock.E, true)
				.condition(ClothedTableBlock.NE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, true).condition(ClothedTableBlock.E, true)
				.condition(ClothedTableBlock.SE, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, false).condition(ClothedTableBlock.W, true)
				.condition(ClothedTableBlock.S, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.NW, true)
				.condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.N, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.NE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).condition(ClothedTableBlock.E, false)
				.condition(ClothedTableBlock.SE, true).end().part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 1, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.SW, true).condition(ClothedTableBlock.W, false)
				.condition(ClothedTableBlock.S, false).end()
				// Table Leg Sides
				.part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(180).addModel().condition(ClothedTableBlock.S, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(0).addModel().condition(ClothedTableBlock.N, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(270).addModel().condition(ClothedTableBlock.W, false).end().part()
				.modelFile(models().getBuilder("table_side").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_side_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs/side",
								List.of(MATERIAL_TYPES.WOOD), StyleData.TABLE_LEGS.getTypes(), 0, 1))
						.end())
				.rotationY(90).addModel().condition(ClothedTableBlock.E, false).end();

		MultiPartBlockStateBuilder mpbsb = getMultipartBuilder(CompendiumBlocks.FANCY_BED.get());

		for (BedPart tb : BedPart.values())
			for (String type : List.of("single", "left", "right", "center")) {
				mpbsb = this.bedPart(mpbsb, tb, type, "frame", StyleData.BED_FRAME, 0, 0, List.of(MATERIAL_TYPES.WOOD));
				mpbsb = this.bedPart(mpbsb, tb, type, "base", StyleData.BED_BASE, 1, 1, List.of(MATERIAL_TYPES.WOOD));
				mpbsb = this.bedPart(mpbsb, tb, type, "mattress", StyleData.BED_MATTRESS, 2, 2, List.of(MATERIAL_TYPES.TEXTILE));
				mpbsb = this.bedPart(mpbsb, tb, type, "sheet", StyleData.BED_SHEET, 3, 3, List.of(MATERIAL_TYPES.TEXTILE));
				mpbsb = this.bedPart(mpbsb, tb, type, "pillow", StyleData.BED_PILLOW, 4, 4, List.of(MATERIAL_TYPES.TEXTILE));
				mpbsb = this.bedPart(mpbsb, tb, type, "blanket", StyleData.BED_BLANKET, 5, 5, List.of(MATERIAL_TYPES.TEXTILE));
			}

		// Frame
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// Mattress
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_mattress").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// Sheet
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// pillow
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// blanket
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.HEAD).end().part()
//				.modelFile(models().getBuilder("fancy_bed_top_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_top_blanket_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "top/blanket",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.HEAD).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// Bed Bottom
//				// Base
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_base").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_base_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/base",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_BASE.getTypes(), 1, 1))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_base").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_base_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/base",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_BASE.getTypes(), 1, 1))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_base").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_base_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/base",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_BASE.getTypes(), 1, 1))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_base").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_base_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/base",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_BASE.getTypes(), 1, 1))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()
//				
//				// Frame
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_frame").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_frame_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/frame",
//								List.of(MATERIAL_TYPES.WOOD), StyleData.BED_FRAME.getTypes(), 0, 0))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// Mattress
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_mattress")
//						.customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_mattress")
//						.customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_mattress")
//						.customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_mattress")
//						.customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_mattress_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/mattress",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_MATTRESS.getTypes(), 2, 2))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// sheet
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_sheet").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_sheet_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/sheet",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_SHEET.getTypes(), 3, 3))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// pillow
//				.part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(models().getBuilder("fancy_bed_bottom_pillow").customLoader(MultiStyleMaterialBuilder::begin)
//						.base(models().cubeAll("fancy_bed_bottom_pillow_model", mcLoc("block/oak_planks")))
//						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/pillow",
//								List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_PILLOW.getTypes(), 4, 4))
//						.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end()
//
//				// blanket
//				.part()
//				.modelFile(
//						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//										List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//								.end())
//				.rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(
//						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//										List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//								.end())
//				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end().part()
//				.modelFile(
//						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//										List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//								.end())
//				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
//				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, BedPart.FOOT).end().part()
//				.modelFile(
//						models().getBuilder("fancy_bed_bottom_blanket").customLoader(MultiStyleMaterialBuilder::begin)
//								.base(models().cubeAll("fancy_bed_bottom_blanket_model", mcLoc("block/oak_planks")))
//								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", "bottom/blanket",
//										List.of(MATERIAL_TYPES.TEXTILE), StyleData.BED_BLANKET.getTypes(), 5, 5))
//								.end())
//				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
//				.condition(FancyBedBlock.PART, BedPart.FOOT).condition(FancyBedBlock.OCCUPIED, false).end();

		fence();

		getVariantBuilder(CompendiumBlocks.SHINGLES_SLANTED.get()).forAllStatesExcept(state -> {
			Direction facing = state.getValue(StairBlock.FACING);
			Half half = state.getValue(StairBlock.HALF);
			StairsShape shape = state.getValue(StairBlock.SHAPE);
			int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some
																// reason
			int xRot = half == Half.BOTTOM ? 0 : 180;
			if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
				yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
			}
			if (shape == StairsShape.STRAIGHT) {
				if (half == Half.BOTTOM)
					yRot -= 90;
				else
					yRot += 90;
			}
			if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
				yRot += 90; // Top stairs are rotated 90 degrees clockwise
			}
			yRot %= 360;
//			boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
			return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT
					? models().getBuilder("shingles_slanted_straight").customLoader(MultiStyleMaterialBuilder::begin)
							.base(models().cubeAll("shingles_slanted_straight_model", mcLoc("block/oak_planks")))
							.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_slanted", "shingles",
									List.of(MATERIAL_TYPES.WOOD), StyleData.SHINGLES.getTypes(), 0, 0))
							.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_slanted", "support",
									List.of(MATERIAL_TYPES.WOOD), StyleData.SUPPORT.getTypes(), 1, 1))
							.end()
					: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT
							? models().getBuilder("shingles_slanted_inner")
									.customLoader(MultiStyleMaterialBuilder::begin)
									.base(models().cubeAll("shingles_slanted_inner_model", mcLoc("block/oak_planks")))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer(
											"shingles_slanted", "shingles/inner_corner", List.of(MATERIAL_TYPES.WOOD),
											StyleData.SHINGLES.getTypes(), 0, 0))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_slanted",
											"support/inner_corner", List.of(MATERIAL_TYPES.WOOD),
											StyleData.SUPPORT.getTypes(), 1, 1))
									.end()
							: models().getBuilder("shingles_slanted_outer")
									.customLoader(MultiStyleMaterialBuilder::begin)
									.base(models().cubeAll("shingles_slanted_outer_model", mcLoc("block/oak_planks")))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_slanted",
											"shingles/outer_corner", List.of(MATERIAL_TYPES.WOOD),
											StyleData.SHINGLES.getTypes(), 0, 0))
									.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_slanted",
											"support/outer_corner", List.of(MATERIAL_TYPES.WOOD),
											StyleData.SUPPORT.getTypes(), 1, 1))
									.end())
					.rotationX(xRot).rotationY(yRot).build();
		}, StairBlock.WATERLOGGED);

		getVariantBuilder(CompendiumBlocks.SHINGLES_CAP_SLANTED.get()).forAllStates(s -> {
			boolean N = s.getValue(ShinglesCapSlanted.NORTH);
			boolean S = s.getValue(ShinglesCapSlanted.SOUTH);
			boolean W = s.getValue(ShinglesCapSlanted.WEST);
			boolean E = s.getValue(ShinglesCapSlanted.EAST);

			int i = N ? 1 : 0;
			i += S ? 1 : 0;
			i += W ? 1 : 0;
			i += E ? 1 : 0;

			switch (i) {
			case 4:
				return shingle("all", 0);
			case 3:
				return shingle("end", shingleRotation(!N, !S, !W, !E) - 90);
			case 2:
				return shingle("straight", N ? 0 : 90);
			case 1:
				return shingle("tri", shingleRotation(N, S, W, E) + 180);
			case 0:
			default:
				return shingle("none", 0);
			}

		});
	}

	ConfiguredModel[] shingle(String suffix, int rotation) {
		return ConfiguredModel.builder()
				.modelFile(models().getBuilder("shingles_cap_slanted_" + suffix)
						.customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("shingles_cap_slanted_" + suffix + "_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_cap_slanted", "shingles/" + suffix,
								List.of(MATERIAL_TYPES.WOOD), StyleData.SHINGLES.getTypes(), 0, 0))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("shingles_cap_slanted", "support/" + suffix,
								List.of(MATERIAL_TYPES.WOOD), StyleData.SUPPORT.getTypes(), 1, 1))
						.end())
				.rotationY(rotation).build();
	}

	int shingleRotation(boolean N, boolean S, boolean W, boolean E) {
		if (N)
			return 0;
		if (S)
			return 180;
		if (W)
			return 270;
		return 90;
	}

	public void fence() {
		MultiPartBlockStateBuilder builder = getMultipartBuilder(CompendiumBlocks.FANCY_FENCE.get()).part()
				.modelFile(models().getBuilder("fancy_fence_post").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_fence_post_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("fence", "post",
								List.of(MATERIAL_TYPES.WOOD), StyleData.FENCE_POST.getTypes(), 0, 0))
						.end())
				.addModel().end();
		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
			Direction dir = e.getKey();
			if (dir.getAxis().isHorizontal()) {
				builder.part()
						.modelFile(models().getBuilder("fancy_fence_side")
								.customLoader(MultiStyleMaterialBuilder::begin)
								.base(models().cubeAll("fancy_fence_side_model", mcLoc("block/oak_planks")))
								.addLayer(new MultiStyleMaterialUnbakedModel.Layer("fence", "side",
										List.of(MATERIAL_TYPES.WOOD), StyleData.FENCE_SIDE.getTypes(), 1, 1))
								.end())
						.rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel()
						.condition(e.getValue(), true).end();
			}
		});
	}

	private MultiPartBlockStateBuilder bedPart(MultiPartBlockStateBuilder mpbsb, BedPart topBottom, String bedSideType,
			String part, StyleData data, int material, int style, List<MATERIAL_TYPES> mats) {
		return mpbsb.part().modelFile(models().getBuilder("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part)
				.customLoader(MultiStyleMaterialBuilder::begin)
				.base(models().cubeAll("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part + "_model",
						mcLoc("block/oak_planks")))
				.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed", bedSideType + "/" + topBottom.toString().toLowerCase() + "/" + part,
						mats, data.getTypes(), material, style))
				.end()).rotationY(180).addModel().condition(FancyBedBlock.FACING, Direction.NORTH)
				.condition(FancyBedBlock.PART, topBottom).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part)
						.customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part + "_model",
								mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed",
								bedSideType + "/" + topBottom.toString().toLowerCase() + "/" + part, mats, data.getTypes(),
								material, style))
						.end())
				.rotationY(90).addModel().condition(FancyBedBlock.FACING, Direction.WEST)
				.condition(FancyBedBlock.PART, topBottom).condition(FancyBedBlock.OCCUPIED, false).end().part()
				.modelFile(models().getBuilder("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part)
						.customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part + "_model",
								mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed",
								bedSideType + "/" + topBottom.toString().toLowerCase() + "/" + part, mats, data.getTypes(),
								material, style))
						.end())
				.rotationY(0).addModel().condition(FancyBedBlock.FACING, Direction.SOUTH)
				.condition(FancyBedBlock.OCCUPIED, false).condition(FancyBedBlock.PART, topBottom).end().part()
				.modelFile(models().getBuilder("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part)
						.customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("fancy_bed_" + bedSideType + "_" + topBottom.toString().toLowerCase() + "_" + part + "_model",
								mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("bed",
								bedSideType + "/" + topBottom.toString().toLowerCase() + "/" + part, mats, data.getTypes(),
								material, style))
						.end())
				.rotationY(270).addModel().condition(FancyBedBlock.FACING, Direction.EAST)
				.condition(FancyBedBlock.PART, topBottom).condition(FancyBedBlock.OCCUPIED, false).end();
	}

}
