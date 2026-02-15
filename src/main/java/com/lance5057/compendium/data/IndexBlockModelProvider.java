package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
//				doWood(mb);
//				doTextile(mb);
//				doGlass(mb);
//				doMetal(mb);
			}
		});

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
					if (mb.namespace.compareTo("minecraft") == 0) {
						withExistingParent("block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase(),
								Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
								.renderType("cutout");

						withExistingParent(
								"block/material/glass/" + mb.name + "/window/glass/" + b.toLowerCase() + "_inventory",
								Compendium.modLoc("block/slightlysmallerblock")).texture("0", mcLoc("block/glass"))
								.renderType("cutout");
					}
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
		if (mb instanceof MaterialTextile mt) {

			String blockTexture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name).toString();
			if (mt.specialLocations != null) {
				if (mt.specialLocations.textures != null)
					if (mt.specialLocations.textures.blockLocation != null)
						blockTexture = ResourceLocation
								.fromNamespaceAndPath(mb.namespace, mt.specialLocations.textures.blockLocation)
								.toString();
			}

			if (mb.namespace.compareTo("minecraft") == 0) {
				for (String b : StyleData.TABLE_CLOTH.getTypes()) {
					withExistingParent("block/material/textile/" + mb.name + "/table/cloth/" + b.toLowerCase(),
							modLoc("block/furniture/table/cloth/" + b.toLowerCase())).texture("0", blockTexture);

					withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/" + b.toLowerCase()
							+ "_inventory", modLoc("block/furniture/table/cloth/" + b.toLowerCase()))
							.texture("0", blockTexture);
				}
			} else {

				for (String b : StyleData.TABLE_CLOTH.getTypes()) {
					withExistingParent("block/material/textile/" + mb.name + "/table/cloth/" + b.toLowerCase(),
							modLoc("block/furniture/table/cloth/" + b.toLowerCase())).texture("0", blockTexture);

					withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/" + b.toLowerCase()
							+ "_inventory", modLoc("block/furniture/table/cloth/" + b.toLowerCase()))
							.texture("0", blockTexture);
				}
			}

			for (String b : StyleData.BED_MATTRESS.getTypes()) {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/unoccupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
								.texture("0", blockTexture);

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/mattress/" + b.toLowerCase(),
								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
								.texture("0", blockTexture);

					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/mattress/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/mattress/" + b.toLowerCase())).texture("0", blockTexture);
			}

			for (String b : StyleData.BED_SHEET.getTypes()) {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/unoccupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
								.texture("0", blockTexture);

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/sheet/" + b.toLowerCase(),
								modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
								.texture("0", blockTexture);
					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/sheet/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/sheet/" + b.toLowerCase())).texture("0", blockTexture);
			}

			for (String b : StyleData.BED_PILLOW.getTypes()) {
				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/unoccupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/pillow/" + b.toLowerCase(),
								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
								.texture("0", blockTexture);

						withExistingParent(
								"block/material/textile/" + mb.name + "/bed/occupied/"
										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
										+ "/pillow/" + b.toLowerCase(),
								modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
								.texture("0", blockTexture);
					}
				}

				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/pillow/" + b.toLowerCase(),
						modLoc("block/furniture/bed/inventory/pillow/" + b.toLowerCase())).texture("0", blockTexture);
			}

			for (String b : StyleData.BED_BLANKET.getTypes()) {
				if (b.equals("llama"))
					withExistingParent(
							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
							modLoc("block/furniture/bed/inventory/blanket/" + b.toLowerCase()))
							.texture("2", modLoc(mb.blockFolder() + "llama"))
							.texture("3", modLoc(mb.blockFolder() + "llama_trim"));

				else if (b.equals("glazed"))
					withExistingParent(
							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
							modLoc("block/furniture/bed/inventory/blanket/basic"))
							.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));

				else
					withExistingParent(
							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
							modLoc("block/furniture/bed/inventory/blanket/" + b.toLowerCase()))
							.texture("0", blockTexture);

				for (BedSideType sideType : BedSideType.values()) {
					for (BedPart part : BedPart.values()) {
						if (b.equals("llama")) {
							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
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

						} else if (b.equals("glazed")) {
							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/basic"))
									.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));

							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/basic"))
									.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));

						} else {
							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("0", blockTexture);

							withExistingParent(
									"block/material/textile/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/blanket/" + b.toLowerCase(),
									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
									.texture("0", blockTexture);

						}
					}
				}
			}

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled",
					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_inventory",
					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_short",
					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_short_inventory",
					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");

			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_long",
					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");
			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_long_inventory",
					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
							"block/material/textile/" + mb.name + "/diagonal_half"))
					.renderType("cutout");
		}

	}

	public void doWood(_MaterialBase mb) {
		if (mb instanceof MaterialWood mw) {
			String logstem;
			if (mb.name.equals("warped") || mb.name.equals("crimson")) {
				logstem = "stem";
			} else {
				logstem = "log";
			}

			ResourceLocation planksTexture = ResourceLocation.fromNamespaceAndPath(mb.namespace,
					"block/" + mb.name + "_planks");
			if (mw.specialLocations != null) {
				if (mw.specialLocations.textures != null)
					if (mw.specialLocations.textures.plankLocation != null)
						planksTexture = mw.specialLocations.textures.plankLocation;
			}

			for (String b : StyleData.WINDOW_TRIM.getTypes()) {
				withExistingParent("block/material/wood/" + mb.name + "/window/trim/" + b.toLowerCase(),
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/wood/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");

				withExistingParent("block/material/wood/" + mb.name + "/window/trim/" + b.toLowerCase() + "_inventory",
						mcLoc("cube_all"))
						.texture("all", modLoc("block/material/wood/" + mb.name + "/windows/" + b.toLowerCase()))
						.renderType("cutout");
			}

			for (String b : StyleData.CHAIR_BACK.getTypes())
				if (b.contains("weave")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
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
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
				} else if (b.equals("lozenge")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/lozenge"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture)
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
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/back/" + b.toLowerCase())).texture("0", planksTexture);
				}

			for (String b : StyleData.CHAIR_LEGS.getTypes()) {
				if (b.contains("rails_side_lath")) {
					withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
							modLoc("block/furniture/chair/legs/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/legs/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
							modLoc("block/furniture/chair/legs/" + b.toLowerCase())).texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/legs/" + b.toLowerCase())).texture("0", planksTexture);
				}
			}

			for (String b : StyleData.CHAIR_SEAT.getTypes()) {
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
							modLoc("block/furniture/chair/seat/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase())).texture("0", planksTexture)
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
							modLoc("block/furniture/chair/seat/" + b.toLowerCase())).texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/chair/seat/" + b.toLowerCase())).texture("0", planksTexture);
				}
			}

			for (String b : StyleData.TABLE_LEGS.getTypes()) {
				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase(),
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg")).texture("0", planksTexture);

				withExistingParent("block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase(),
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase())).texture("0", planksTexture);

				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
						.texture("0", planksTexture);
				withExistingParent(
						"block/material/wood/" + mb.name + "/clothed_table/legs/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
						.texture("0", planksTexture);

				withExistingParent(
						"block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
						.texture("0", planksTexture);
				withExistingParent(
						"block/material/wood/" + mb.name + "/clothed_table/legs/side/" + b.toLowerCase() + "_inventory",
						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
						.texture("0", planksTexture);
			}

			for (String b : StyleData.TABLE_TOP.getTypes()) {
				if (b.equals("smooth")) {
					withExistingParent("block/material/wood/" + mb.name + "/table/top/smooth",
							modLoc("block/furniture/table/top/smooth")).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/table/top/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
					withExistingParent(
							"block/material/wood/" + mb.name + "/clothed_table/top/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/table/top/" + b.toLowerCase())).texture("0", planksTexture)
							.texture("1", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase(),
							modLoc("block/furniture/table/top/" + b.toLowerCase())).texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/table/top/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/table/top/" + b.toLowerCase())).texture("0", planksTexture);
					withExistingParent(
							"block/material/wood/" + mb.name + "/clothed_table/top/" + b.toLowerCase() + "_inventory",
							modLoc("block/furniture/table/top/" + b.toLowerCase())).texture("0", planksTexture);
				}
			}

			for (String b : StyleData.BED_FRAME.getTypes()) {
				for (BedSideType sideType : BedSideType.values())
					for (BedPart part : BedPart.values()) {
						if (b.equals("live_edge")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
									.texture("1", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
									.texture("1", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
									.texture("1", planksTexture);
						} else if (b.equals("weave")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
						} else if (b.equals("slats")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
						} else {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
									.texture("0", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
									.texture("0", planksTexture);
						}
					}
			}

			for (String b : StyleData.BED_BASE.getTypes()) {
				for (BedSideType sideType : BedSideType.values())
					for (BedPart part : BedPart.values()) {
						if (b.equals("weave")) {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/base/" + b.toLowerCase()))
									.texture("0", planksTexture)
									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
						} else {
							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/unoccupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/occupied/"
											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
											+ "/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
									.texture("0", planksTexture);

							withExistingParent(
									"block/material/wood/" + mb.name + "/bed/inventory/base/" + b.toLowerCase(),
									modLoc("block/furniture/bed/inventory/base/" + b.toLowerCase()))
									.texture("0", planksTexture);
						}
					}
			}

			for (String b : StyleData.FENCE_POST.getTypes()) {
				withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase(),
						modLoc("block/bases/fence/post/" + b.toLowerCase())).texture("0", planksTexture);
				withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase() + "_inventory",
						modLoc("block/bases/fence/post/" + b.toLowerCase() + "_inventory")).texture("0", planksTexture);
			}

			for (String b : StyleData.FENCE_SIDE.getTypes()) {
				if (b.contains("sheet")) {
					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
							modLoc("block/bases/fence/side/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase() + "_inventory",
							modLoc("block/bases/fence/side/" + b.toLowerCase() + "_inventory"))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
				} else {
					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
							modLoc("block/bases/fence/side/" + b.toLowerCase())).texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase() + "_inventory",
							modLoc("block/bases/fence/side/" + b.toLowerCase() + "_inventory"))
							.texture("0", planksTexture);
				}
			}

			for (String b : StyleData.SHINGLES_SHINGLES.getTypes()) {
				String[] top = new String[] { "no_top/", "top/" };

				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase())).texture("0", planksTexture);

				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase()
						+ "_inventory", modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase()))
						.texture("0", planksTexture);

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/outer_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/outer_corner/" + b.toLowerCase()))
						.texture("0", planksTexture);

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/inner_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/shingles/inner_corner/" + b.toLowerCase()))
						.texture("0", planksTexture);

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/inventory/" + b.toLowerCase()
								+ "_inventory",
						modLoc("block/bases/shingles_cap_slanted/shingles/no_top/straight/" + b.toLowerCase()))
						.texture("0", planksTexture);

				for (String s : top) {
					// caps
					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "all/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "all/" + b.toLowerCase()))
							.texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "straight/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "straight/" + b.toLowerCase()))
							.texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "tri/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "tri/" + b.toLowerCase()))
							.texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "none/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "none/" + b.toLowerCase()))
							.texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "end/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "end/" + b.toLowerCase()))
							.texture("0", planksTexture);

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "corner/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "corner/" + b.toLowerCase()))
							.texture("0", planksTexture);
				}
			}

			for (String b : StyleData.SUPPORT_SHINGLES.getTypes()) {
				String[] top = new String[] { "no_top/", "top/" };

				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase()
						+ "_inventory", modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/support/outer_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/outer_corner/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_slanted/support/inner_corner/" + b.toLowerCase(),
						modLoc("block/bases/shingles_slanted/support/inner_corner/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

				withExistingParent(
						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/inventory/" + b.toLowerCase()
								+ "_inventory",
						modLoc("block/bases/shingles_cap_slanted/support/no_top/straight/" + b.toLowerCase()))
						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

				for (String s : top) {
					// caps
					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "all/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "all/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "straight/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "straight/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "tri/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "tri/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "none/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "none/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "end/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "end/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));

					withExistingParent(
							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "corner/"
									+ b.toLowerCase(),
							modLoc("block/bases/shingles_cap_slanted/support/" + s + "corner/" + b.toLowerCase()))
							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
				}
			}
		}
	}

}
