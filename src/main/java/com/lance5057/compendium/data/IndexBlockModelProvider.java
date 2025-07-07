package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.chair.ChairBlockEntity;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlockEntity;
import com.lance5057.compendium.blocks.table.TableBlockEntity;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class IndexBlockModelProvider extends BlockModelProvider {

	public IndexBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Compendium.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
//		ConfiguredModel.builder()
//				.modelFile(
//						getBuilder("block/furniture/table/legs/bar").customLoader(MultiMaterialModelBuilder::begin)
//								.base(cubeAll("bar_leg_base", mcLoc("block/oak_planks")).renderType("cutout"))
//
////		msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), "table/legs/bar_side", 0));
//								.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD),
//										"table/legs/bar", 1))
//
//								.end())
//				.build();

//		ConfiguredModel.builder()
//				.modelFile(
//						getBuilder("block/furniture/table/legs/side/bar").customLoader(MultiMaterialModelBuilder::begin)
//								.base(cubeAll("bar_side_base", mcLoc("block/oak_planks")).renderType("cutout"))
//
////				msmb.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD), "table/legs/bar_side", 0));
//								.addLayer(new Layer(List.of(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD),
//										"table/legs/side/bar", 0))
//
//								.end())
//				.build();

		CompendiumIndex.index.forEach(i -> {
			i.blockModel(this);

			if (i instanceof _MaterialBase mb) {
				if (mb.getType() == MATERIAL_TYPES.WOOD) {
					ChairBlockEntity.back.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
								modLoc("block/furniture/chair/back/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ChairBlockEntity.legs.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
								modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ChairBlockEntity.seat.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
								modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					TableBlockEntity.legs.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase(),
								modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg"))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));

						withExistingParent("block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase(),
								modLoc("block/furniture/table/legs/side/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					TableBlockEntity.top.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase(),
								modLoc("block/furniture/table/top/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					// special cases!

					withExistingParent("block/material/wood/" + mb.name + "/table/top/smooth",
							modLoc("block/furniture/table/top/smooth"))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/" + mb.name + "_sheet"));
				}

				if (mb.getType() == MATERIAL_TYPES.TEXTILE) {
					ClothedTableBlockEntity.cloth.forEach(b -> {
						withExistingParent("block/material/textile/" + mb.name + "/table/cloth/" + b.toLowerCase(),
								modLoc("block/furniture/table/cloth/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					});

				}
			}
		});

		singleTexture("block/material/glass/clear_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/glass")).renderType("cutout");

		singleTexture("block/material/glass/white_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/white_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/light_gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
				"0", mcLoc("block/light_gray_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/gray_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/black_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/black_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/brown_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/brown_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/red_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/red_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/orange_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/orange_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/yellow_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/yellow_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/lime_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/lime_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/green_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/green_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/cyan_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/cyan_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/light_blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
				"0", mcLoc("block/light_blue_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/blue_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/purple_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/purple_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/magenta_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/magenta_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/pink_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/pink_stained_glass")).renderType("translucent");

		singleTexture("block/material/glass/invalid/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				modLoc("block/prototypium_stained_glass")).renderType("translucent");

	}

}
