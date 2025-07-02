package com.lance5057.compendium.data;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.chair.ChairBlockEntity;
import com.lance5057.compendium.blocks.table.TableBlock;
import com.lance5057.compendium.blocks.table.TableBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelBuilder;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialUnbakedModel.Layer;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialBuilder;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialUnbakedModel;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.workstations.workbench.WorkbenchBlock;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Half;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel.Builder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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

//			msmb.addInvalidLocation(modLoc("block/material/invalid/invalid/window_trim"));
//			msmb.addModelName("window_trim");
//			msmb.addModName(Compendium.MOD_ID);

			msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), "window_trim"));
			msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.GLASS), "window"));

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
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.back, 0));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "seat",
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.seat, 1));
			msmb.addLayer(new MultiStyleMaterialUnbakedModel.Layer("chair", "legs",
					List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), ChairBlockEntity.legs, 2));

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.rotationY(((int) state.getValue(ChairBlock.FACING).toYRot()) % 360).build();
		});

		getMultipartBuilder(CompendiumBlocks.TABLE.get())
				.part()
				.modelFile(models().getBuilder("table_top").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_top_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "top",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.top, 0))
						.end())
				.addModel()
				.end()
				.part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1))
						.end())
				.rotationY(90).addModel().condition(TableBlock.N, false).condition(TableBlock.E, false).condition(TableBlock.NE, false)
				.end()
				.part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1))
						.end())
				.rotationY(180).addModel().condition(TableBlock.S, false).condition(TableBlock.E, false).condition(TableBlock.SE, false)
				.end()
				.part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1))
						.end())
				.rotationY(270).addModel().condition(TableBlock.SW, false).condition(TableBlock.W, false).condition(TableBlock.S, false)
				.end()
				.part()
				.modelFile(models().getBuilder("table_legs").customLoader(MultiStyleMaterialBuilder::begin)
						.base(models().cubeAll("table_legs_model", mcLoc("block/oak_planks")))
						.addLayer(new MultiStyleMaterialUnbakedModel.Layer("table", "legs",
								List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), TableBlockEntity.legs, 1))
						.end())
				.rotationY(0).addModel().condition(TableBlock.N, false).condition(TableBlock.NW, false).condition(TableBlock.W, false)
				.end();
	}

}
