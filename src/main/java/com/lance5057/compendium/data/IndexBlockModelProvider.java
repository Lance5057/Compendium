package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class IndexBlockModelProvider extends BlockModelProvider {

	public IndexBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Compendium.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {

		CompendiumIndex.index.forEach(i -> {
			i.blockModel(this);

			if (i instanceof _MaterialBase mb) {
				doWood(mb);
				doTextile(mb);
				doGlass(mb);
				doMetal(mb);
			}
		});

//		withExistingParent("block/material/glass/clear/window/glass/clear",
//				Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
//				.renderType("cutout");
//
//		withExistingParent("block/material/glass/clear/window/glass/clear_inventory",
//				Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
//				.renderType("cutout");

//		singleTexture("block/material/glass/clear_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/glass")).renderType("cutout");

//		singleTexture("block/material/glass/white_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/white_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/light_gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
//				"0", mcLoc("block/light_gray_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/gray_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/black_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/black_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/brown_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/brown_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/red_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/red_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/orange_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/orange_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/yellow_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/yellow_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/lime_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/lime_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/green_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/green_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/cyan_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/cyan_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/light_blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
//				"0", mcLoc("block/light_blue_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/blue_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/purple_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/purple_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/magenta_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/magenta_stained_glass")).renderType("translucent");
//		singleTexture("block/material/glass/pink_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
//				mcLoc("block/pink_stained_glass")).renderType("translucent");

	}

	private void doMetal(_MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.METAL) {
			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				withExistingParent("block/material/metal/" + mb.name + "/window/trim/" + b.toLowerCase(),
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");

				withExistingParent("block/material/metal/" + mb.name + "/window/trim/" + b.toLowerCase() + "_inventory",
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");
			});
		}
	}

	public void doGlass(_MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.GLASS) {
			StyleData.WINDOW_GLASS.getTypes().forEach(b -> {
				if (mb.name.equalsIgnoreCase("clear")) {
					withExistingParent("block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase(),
							Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
							.renderType("cutout");

					withExistingParent(
							"block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase() + "_inventory",
							Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
							.renderType("cutout");
				} else if (mb.name.equalsIgnoreCase("tinted")) {

				} else {
					withExistingParent("block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase(),
							Compendium.modLoc("block/slightlysmallerblock"))
							.texture("0", mcLoc("block/" + mb.name + "_glass")).renderType("translucent");

					withExistingParent(
							"block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase() + "_inventory",
							Compendium.modLoc("block/slightlysmallerblock"))
							.texture("0", mcLoc("block/" + mb.name + "_glass")).renderType("translucent");
				}
			});
		}
	}

	public void doTextile(_MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.TEXTILE) {
			StyleData.TABLE_CLOTH.getTypes().forEach(b -> {
				withExistingParent("block/material/textile/" + mb.name + "/table/cloth/" + b.toLowerCase(),
						modLoc("block/furniture/table/cloth/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));

				withExistingParent(
						"block/material/textile/" + mb.name + "/clothed_table/cloth/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/cloth/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));
			});

			StyleData.BED_MATTRESS.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/mattress/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/mattress/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));
			});

			StyleData.BED_SHEET.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/sheet/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/sheet/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));
			});

			StyleData.BED_PILLOW.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase(),
								modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/pillow/" + b.toLowerCase(),
								modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name));
					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/pillow/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/pillow/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));
			});

			StyleData.BED_BLANKET.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						if (b == "llama") {
							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/" + sideType.toString().toLowerCase()
											+ "/" + part.toString().toLowerCase() + "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("2", modLoc(mb.blockFolder() + "llama"))
									.texture("3", modLoc(mb.blockFolder() + "llama_trim"));

							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("2", modLoc(mb.blockFolder() + "llama"))
									.texture("3", modLoc(mb.blockFolder() + "llama_trim"));
						} else {
							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/" + sideType.toString().toLowerCase()
											+ "/" + part.toString().toLowerCase() + "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name));

							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name));
						}
					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/blanket/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name));
			});

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled",
					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_inventory",
					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_short",
					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_short_inventory",
					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_long",
					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_long_inventory",
					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
					.texture("1", modLoc("block/material/textile/" + mb.name + "/diagonal_half")).renderType("cutout");
		}

	}

	public void doWood(_MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.WOOD) {
			String logstem;
			if (mb.name.equals("warped") || mb.name.equals("crimson")) {
				logstem = "stem";
			} else {
				logstem = "log";
			}

			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/window/trim/" + b.toLowerCase(),
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/wood/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");

				withExistingParent("block/material/wood/" + mb.name + "/window/trim/" + b.toLowerCase() + "_inventory",
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/wood/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");
			});

			StyleData.CHAIR_BACK.getTypes().forEach(b -> {
				if (b.contains("weave")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
				} else if (b.contains("sheet")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
				} else if (b.equals("windsor") || b.equals("slats")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
				} else if (b.equals("lozenge")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/lozenge"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/lozenge"));
				} else if (b.equals("live_edge")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));
				}
			});

			StyleData.CHAIR_LEGS.getTypes().forEach(b -> {
				if (b.contains("rails_side_lath")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
							modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
							modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));
				}
			});

			StyleData.CHAIR_SEAT.getTypes().forEach(b -> {
				if (b.equals("live_edge")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"));
				} else if (b.contains("weave")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
				} else if (b.contains("sheet")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));
				}
			});

			StyleData.TABLE_LEGS.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase(),
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent("block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase(),
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
				withExistingParent(
						"block/material/wood/" + mb.name + "/clothed_table/legs/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
				withExistingParent(
						"block/material/wood/" + mb.name + "/clothed_table/legs/side/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.TABLE_TOP.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase(),
						modLoc("block/furniture/table/top/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent("block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/top/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
				withExistingParent(
						"block/material/wood/" + mb.name + "/clothed_table/top/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/top/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.BED_FRAME.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values())
					for (BedPart part : BedPart.values()) {
						if (b.equals("live_edge")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
									.texture("1", mcLoc("block/" + mb.name + "_" + logstem));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
									.texture("1", mcLoc("block/" + mb.name + "_" + logstem));
						} else if (b.equals("weave")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
						} else if (b.equals("slats")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
						} else {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"));

						}
					}

				withExistingParent("block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.BED_BASE.getTypes().forEach(b -> {
				for (BedSideType sideType : BedSideType.values())
					for (BedPart part : BedPart.values()) {
						if (b.equals("weave")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"))
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
						} else {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", mcLoc("block/" + mb.name + "_planks"));
						}
					}

				withExistingParent("block/material/wood/" + mb.name + "/bed/inventory/base/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/base/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.FENCE_POST.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase(),
						modLoc("block/bases/fence/post/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.FENCE_SIDE.getTypes().forEach(b -> {
				if (b.contains("sheet")) {
					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
							modLoc("block/bases/fence/side/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
							modLoc("block/bases/fence/side/" + b.toLowerCase()))
							.texture("0", mcLoc("block/" + mb.name + "_planks"));
				}
			});

			StyleData.SHINGLES_SHINGLES.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/outer_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/outer_corner/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/inner_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/inner_corner/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));
			});

			StyleData.SUPPORT_SHINGLES.getTypes().forEach(b -> {
				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/support/outer_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/outer_corner/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/support/inner_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/inner_corner/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				// caps
				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/all/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/shingles/all/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/all/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/support/all/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/straight/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/shingles/straight/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/straight/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/support/straight/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/tri/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/shingles/tri/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/tri/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/support/tri/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/none/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/shingles/none/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/none/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/support/none/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/end/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/shingles/end/" + b.toLowerCase()))
						.texture("0", mcLoc("block/" + mb.name + "_planks"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/end/" + b.toLowerCase(),
						modLoc("block/bases/shingles_cap_slanted/support/end/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_corner"));
			});

			// special cases!

			withExistingParent("block/material/wood/" + mb.name + "/table/top/smooth",
					modLoc("block/furniture/table/top/smooth")).texture("0", mcLoc("block/" + mb.name + "_planks"))
					.texture("1", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));

		}
	}

}
