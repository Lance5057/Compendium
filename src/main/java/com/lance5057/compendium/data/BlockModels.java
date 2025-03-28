package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.client.models.IndexModelBuilder;
import com.lance5057.compendium.client.models.MaterialSwapModelBuilder;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;
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
			i.blockModel(this);
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
			MaterialSwapModelBuilder<BlockModelBuilder> msmb = models().getBuilder("window")
					.customLoader(MaterialSwapModelBuilder::begin);
			msmb.base(models().cubeAll("window_base", mcLoc("block/glass")).renderType("cutout"));

//			for (IIndexEntry i : CompendiumIndex.index) {
//				if (i instanceof _MaterialBase mb)
//					if (mb.getType() == MATERIAL_TYPES.METAL)
			msmb.add(new IndexModelBuilder(MATERIAL_TYPES.METAL, modLoc("block/material/metal/invalid/window_trim")));
//			}

			BlockModelBuilder bmb = msmb.end();
			b.modelFile(bmb);
			return b.build();
		});

		this.simpleBlock(CompendiumBlocks.CHAIR.get(), models().getExistingFile(mcLoc("air")));

//		getVariantBuilder(CompendiumBlocks.CHAIR.get()).forAllStates(state -> {
//			return ConfiguredModel.builder().modelFile(models().getBuilder("chair_back")
//					.customLoader(MaterialSwapModelBuilder::begin)
//					.base(models().withExistingParent("chair_base", mcLoc("air")))
//					.add(new IndexModelBuilder<BlockModelBuilder>(MATERIAL_TYPES.WOOD,
//							models().withExistingParent("index_basic_chair_back", modLoc("extra/furniture/chair/basic_chair_back"))
//									.texture("0", modLoc("block/material/wood/invalid/planks"))))
//					.end())
//
//					.build();
//
//		});
	}

}
