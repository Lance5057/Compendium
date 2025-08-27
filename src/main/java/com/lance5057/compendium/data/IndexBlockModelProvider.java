package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.bed.FancyBedBlockEntity;
import com.lance5057.compendium.blocks.chair.ChairBlockEntity;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlockEntity;
import com.lance5057.compendium.blocks.fence.FancyFenceBlockEntity;
import com.lance5057.compendium.blocks.shingles.slanted.ShinglesSlantedBlockEntity;
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

					FancyBedBlockEntity.frame.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/bed/top/frame/" + b.toLowerCase(),
								modLoc("block/furniture/bed/top/frame/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));

						withExistingParent("block/material/wood/" + mb.name + "/bed/bottom/frame/" + b.toLowerCase(),
								modLoc("block/furniture/bed/bottom/frame/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					FancyFenceBlockEntity.post.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase(),
								modLoc("block/bases/fence/post/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					FancyFenceBlockEntity.side.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
								modLoc("block/bases/fence/side/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ShinglesSlantedBlockEntity.shingles.forEach(b -> {
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));

						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/shingles/outer_corner/"
										+ b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/shingles/outer_corner/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));

						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/shingles/inner_corner/"
										+ b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/shingles/inner_corner/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ShinglesSlantedBlockEntity.support.forEach(b -> {
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
								.texture("0", modLoc(
										"block/material/wood/" + mb.name + "/" + mb.name + "_small_logs_corner"));

						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/support/outer_corner/"
										+ b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/support/outer_corner/" + b.toLowerCase()))
								.texture("0", modLoc(
										"block/material/wood/" + mb.name + "/" + mb.name + "_small_logs_corner"));

						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_slanted/support/inner_corner/"
										+ b.toLowerCase(),
								modLoc("block/bases/shingles_slanted/support/inner_corner/" + b.toLowerCase()))
								.texture("0", modLoc(
										"block/material/wood/" + mb.name + "/" + mb.name + "_small_logs_corner"));
						
						//caps
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/all/" + b.toLowerCase(),
								modLoc("block/bases/shingles_cap_slanted/shingles/all/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
						
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/all/" + b.toLowerCase(),
								modLoc("block/bases/shingles_cap_slanted/support/all/" + b.toLowerCase()))
								.texture("0", modLoc(
										"block/material/wood/" + mb.name + "/" + mb.name + "_small_logs_corner"));
						
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/straight/" + b.toLowerCase(),
								modLoc("block/bases/shingles_cap_slanted/shingles/straight/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
						
						withExistingParent(
								"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/straight/" + b.toLowerCase(),
								modLoc("block/bases/shingles_cap_slanted/support/straight/" + b.toLowerCase()))
								.texture("0", modLoc(
										"block/material/wood/" + mb.name + "/" + mb.name + "_small_logs_corner"));
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

					FancyBedBlockEntity.mattress.forEach(b -> {
						withExistingParent("block/material/textile/" + mb.name + "/bed/top/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/top/mattress/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/bottom/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/bottom/mattress/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					});

					FancyBedBlockEntity.sheet.forEach(b -> {
						withExistingParent("block/material/textile/" + mb.name + "/bed/top/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/top/sheet/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent("block/material/textile/" + mb.name + "/bed/bottom/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/bottom/sheet/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					});

					FancyBedBlockEntity.pillow.forEach(b -> {
						withExistingParent("block/material/textile/" + mb.name + "/bed/top/pillow/" + b.toLowerCase(),
								modLoc("block/furniture/bed/top/pillow/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/bottom/pillow/" + b.toLowerCase(),
								mcLoc("block/air"));
					});

					FancyBedBlockEntity.blanket.forEach(b -> {
						withExistingParent("block/material/textile/" + mb.name + "/bed/top/blanket/" + b.toLowerCase(),
								modLoc("block/furniture/bed/top/blanket/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/bottom/blanket/" + b.toLowerCase(),
								modLoc("block/furniture/bed/bottom/blanket/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					});

					withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled",
							modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
							.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half"))
							.renderType("cutout");

					withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_short",
							modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
							.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half"))
							.renderType("cutout");

					withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_long",
							modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
							.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half"))
							.renderType("cutout");

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
