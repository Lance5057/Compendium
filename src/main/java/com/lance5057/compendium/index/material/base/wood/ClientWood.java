package com.lance5057.compendium.index.material.base.wood;

import java.util.Map;

import com.google.common.collect.Maps;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientWood {
	public static void doWood(ModifyBakingResult event, MaterialWood mw) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();
		ClientWood.doStyleWood(event, models, mw);

		ResourceLocation planksTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/planks");
		ResourceLocation logTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/log");
		ResourceLocation strippedLogTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/stripped_log");
		ResourceLocation logTopTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/log_top");
		ResourceLocation strippedLogTopTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/stripped_log_top");

		ResourceLocation logExtraCaps = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps");
		ResourceLocation logStrippedExtraCaps = TagUtil
				.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps");

		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null) {
				if (mw.specialLocations.textures.plankLocation != null)
					planksTexture = mw.specialLocations.textures.plankLocation;
				if (mw.specialLocations.textures.logLocation != null)
					logTexture = mw.specialLocations.textures.logLocation;
				if (mw.specialLocations.textures.strippedLogLocation != null)
					strippedLogTexture = mw.specialLocations.textures.strippedLogLocation;
				if (mw.specialLocations.textures.logTopLocation != null)
					logTopTexture = mw.specialLocations.textures.logTopLocation;
				if (mw.specialLocations.textures.strippedLogTopLocation != null)
					strippedLogTopTexture = mw.specialLocations.textures.strippedLogTopLocation;
			}
		}

		if (mw.PLANKS.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mw.name + "_planks");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, planksTexture, loc, m,
					BlockModelRotation.X0_Y0, "all");
			models.put(m, bm);
		}

		if (mw.WOOD.shouldGenerate()) {
			String n = mw.name + "_wood";
			ResourceLocation modelLoc = TagUtil.modLoc(n);
			ResourceLocation inventoryModelLoc = TagUtil.modLoc(mw.name + "_wood_inventory");

			ResourceLocation model = TagUtil.modLoc("extra/wood_basic/wood");

			doLog(event, mw, models, logTexture, logTexture, n, modelLoc, inventoryModelLoc, model);
//			CompendiumClient.doStyleLog(event, mw, modelLoc, inventoryModelLoc, model, Pair.of("side", logTexture),
//					Pair.of("end", logTexture));

		}

		if (mw.LOG.shouldGenerate()) {
			String n = mw.name + "_log";
			ResourceLocation modelLoc = TagUtil.modLoc(n);
			ResourceLocation inventoryModelLoc = TagUtil.modLoc(mw.name + "_log_inventory");

			ResourceLocation model = TagUtil.modLoc("extra/wood_basic/log");

			doLog(event, mw, models, logTexture, logTopTexture, n, modelLoc, inventoryModelLoc, model);
		}
		if (mw.LOG.isNotIgnored()) {
			event.getModels().put(
					ModelResourceLocation.standalone(TagUtil.modLoc("recipes/" + mw.name + "_split_log_stage0")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage0"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", logTexture), Pair.of("1", logTopTexture)));

			event.getModels().put(
					ModelResourceLocation.standalone(TagUtil.modLoc("recipes/" + mw.name + "_split_log_stage1")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage1"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", logTexture), Pair.of("1", logTopTexture),
							Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side"))));

			event.getModels().put(
					ModelResourceLocation.standalone(TagUtil.modLoc("recipes/" + mw.name + "_split_log_stage2")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage2"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", logTexture), Pair.of("1", logTopTexture),
							Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side"))));

			event.getModels().put(
					ModelResourceLocation.standalone(TagUtil.modLoc("recipes/" + mw.name + "_split_log_stage3")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage3"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", logTexture), Pair.of("1", logExtraCaps)));
		}

		if (mw.STRIPPED_WOOD.shouldGenerate()) {
			String n = "stripped_" + mw.name + "_wood";
			ResourceLocation modelLoc = TagUtil.modLoc(n);
			ResourceLocation inventoryModelLoc = TagUtil.modLoc(mw.name + "_stripped_wood_inventory");

			ResourceLocation model = TagUtil.modLoc("extra/wood_basic/stripped_wood");

			doLog(event, mw, models, strippedLogTexture, strippedLogTexture, n, modelLoc, inventoryModelLoc, model);
		}

		if (mw.STRIPPED_LOG.shouldGenerate()) {
			String n = "stripped_" + mw.name + "_log";
			ResourceLocation modelLoc = TagUtil.modLoc(n);
			ResourceLocation inventoryModelLoc = TagUtil.modLoc(mw.name + "_stripped_log_inventory");

			ResourceLocation model = TagUtil.modLoc("extra/wood_basic/stripped_log");

			doLog(event, mw, models, strippedLogTexture, strippedLogTopTexture, n, modelLoc, inventoryModelLoc, model);
		}
		if (mw.STRIPPED_LOG.isNotIgnored()) {
			event.getModels()
					.put(ModelResourceLocation
							.standalone(TagUtil.modLoc("recipes/" + mw.name + "_stripped_split_log_stage0")),
							CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage0"),
									new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
									BlockModelRotation.X0_Y0, Pair.of("0", strippedLogTexture),
									Pair.of("1", strippedLogTopTexture)));

			event.getModels().put(
					ModelResourceLocation
							.standalone(TagUtil.modLoc("recipes/" + mw.name + "_stripped_split_log_stage1")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage1"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", strippedLogTexture),
							Pair.of("1", strippedLogTopTexture), Pair.of("2", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side"))));

			event.getModels().put(
					ModelResourceLocation
							.standalone(TagUtil.modLoc("recipes/" + mw.name + "_stripped_split_log_stage2")),
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage2"),
							new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
							BlockModelRotation.X0_Y0, Pair.of("0", strippedLogTexture),
							Pair.of("1", strippedLogTopTexture), Pair.of("2", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side"))));

			event.getModels()
					.put(ModelResourceLocation
							.standalone(TagUtil.modLoc("recipes/" + mw.name + "_stripped_split_log_stage3")),
							CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("recipes/split_log_stage3"),
									new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""),
									BlockModelRotation.X0_Y0, Pair.of("0", strippedLogTexture),
									Pair.of("1", logStrippedExtraCaps)));
		}

		for (String b : StyleData.WINDOW_TRIM.getTypes()) {
			ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("window", "trim", mw.name,
					b.toLowerCase());
			ResourceLocation t = Compendium.modLoc("block/material/wood/" + mw.name + "/windows/" + b.toLowerCase());

			event.getModels().put(new ModelResourceLocation(modelLoc, ""), CompendiumClient.basicModelAllTexture(event,
					t, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

			ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation("window", "trim", mw.name,
					b.toLowerCase(), "_inventory");
			ResourceLocation loc_inv = Compendium.modLoc("extra/window/trim/" + b + "_inventory");
			event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
					CompendiumClient.basicModelAllTexture(event, planksTexture, loc_inv,
							new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "all"));
		}

		for (String b : StyleData.CHAIR_BACK.getTypes()) {
			if (b.contains("weave")) {
				CompendiumClient.doChair(event, mw, "back", b, Pair.of("0", planksTexture),
						Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/weave")));
			} else if (b.contains("sheet")) {
				CompendiumClient.doChair(event, mw, "back", b,
						Pair.of("0", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet")));
			} else if (b.equals("windsor") || b.equals("slats")) {
				CompendiumClient.doChair(event, mw, "back", b, Pair.of("0", planksTexture),
						Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/slats")));
			} else if (b.equals("lozenge")) {
				CompendiumClient.doChair(event, mw, "back", b, Pair.of("0", planksTexture),
						Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/lozenge")));
			} else if (b.equals("live_edge")) {
				CompendiumClient.doChair(event, mw, "back", b,
						Pair.of("0", Compendium.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));
			} else {
				CompendiumClient.doChair(event, mw, "back", b, Pair.of("0", planksTexture));
			}
		}

		for (String b : StyleData.CHAIR_LEGS.getTypes()) {
			if (b.contains("rails_side_lath")) {
				CompendiumClient.doChair(event, mw, "legs", b, Pair.of("0", planksTexture),
						Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/slats")));
			} else {
				CompendiumClient.doChair(event, mw, "legs", b, Pair.of("0", planksTexture));
			}

		}

		for (String b : StyleData.CHAIR_SEAT.getTypes()) {
			if (b.equals("live_edge")) {
				CompendiumClient.doChair(event, mw, "seat", b,
						Pair.of("0", Compendium.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));
			} else if (b.contains("weave")) {
				CompendiumClient.doChair(event, mw, "seat", b, Pair.of("0", planksTexture),
						Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/weave")));
			} else if (b.contains("sheet")) {
				CompendiumClient.doChair(event, mw, "seat", b,
						Pair.of("0", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet")));
			} else {
				CompendiumClient.doChair(event, mw, "seat", b, Pair.of("0", planksTexture));
			}
		}

		for (String b : StyleData.TABLE_LEGS.getTypes()) {
			CompendiumClient.doTableLeg(event, mw, b, "table", Pair.of("0", planksTexture));
			CompendiumClient.doTableLeg(event, mw, b, "clothed_table", Pair.of("0", planksTexture));
		}

		for (String b : StyleData.TABLE_TOP.getTypes()) {

			ResourceLocation loc = Compendium.modLoc("extra/table/top/" + b);
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("table", "top", mw.name,
					b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			ResourceLocation loc_clothed = Compendium.modLoc("extra/clothed_table/top/" + b);
			ResourceLocation modelLoc_clothed = ClientUtil.createMaterialStyleLayerBlockLocation("clothed_table", "top",
					mw.name, b.toLowerCase());
			ModelResourceLocation m_clothed = new ModelResourceLocation(modelLoc_clothed, "");

			if (b.equals("smooth")) {
//					CompendiumClient.basicModelManyTexture(event, loc, w, BlockModelRotation.X0_Y90, textures));
				event.getModels().put(m,
						CompendiumClient.basicModelManyTexture(event, loc, m, BlockModelRotation.X0_Y0,
								Pair.of("0", planksTexture),
								Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));
				event.getModels().put(m_clothed,
						CompendiumClient.basicModelManyTexture(event, loc_clothed, m_clothed, BlockModelRotation.X0_Y0,
								Pair.of("0", planksTexture),
								Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

				ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ModelResourceLocation m_clothed_inventory = new ModelResourceLocation(
						modelLoc_clothed.withSuffix("_inventory"), "");

				event.getModels().put(m_inventory,
						CompendiumClient.basicModelManyTexture(event, loc.withSuffix("_inventory"), m_inventory,
								BlockModelRotation.X0_Y0, Pair.of("0", planksTexture),
								Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));
				event.getModels().put(m_clothed_inventory,
						CompendiumClient.basicModelManyTexture(event, loc_clothed.withSuffix("_inventory"),
								m_clothed_inventory, BlockModelRotation.X0_Y0, Pair.of("0", planksTexture),
								Pair.of("1", Compendium.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));
			} else {
				event.getModels().put(m, CompendiumClient.basicModelAllTexture(event, planksTexture, loc, m,
						BlockModelRotation.X0_Y0, "0"));
				event.getModels().put(m_clothed, CompendiumClient.basicModelAllTexture(event, planksTexture,
						loc_clothed, m_clothed, BlockModelRotation.X0_Y0, "0"));

				ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ModelResourceLocation m_clothed_inventory = new ModelResourceLocation(
						modelLoc_clothed.withSuffix("_inventory"), "");

				event.getModels().put(m_inventory, CompendiumClient.basicModelAllTexture(event, planksTexture,
						loc.withSuffix("_inventory"), m_inventory, BlockModelRotation.X0_Y0, "0"));
				event.getModels().put(m_clothed_inventory, CompendiumClient.basicModelAllTexture(event, planksTexture,
						loc_clothed.withSuffix("_inventory"), m_clothed_inventory, BlockModelRotation.X0_Y0, "0"));
			}

		}

		for (String b : StyleData.BED_FRAME.getTypes()) {
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "frame", mw.name,
					b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			MultiPartBakedModel.Builder mmAll = new MultiPartBakedModel.Builder();
			for (BedSideType sideType : BedSideType.values()) {
				MultiPartBakedModel.Builder mmSide = new MultiPartBakedModel.Builder();
				for (BedPart part : BedPart.values()) {
					MultiPartBakedModel.Builder mmPart = new MultiPartBakedModel.Builder();
					for (int occupied = 0; occupied < 2; occupied++) {

						String sideString = sideType.toString().toLowerCase();
						String partString = part.toString().toLowerCase();
						String occupiedString = occupied != 0 ? "occupied" : "unoccupied";

						ResourceLocation loc = Compendium.modLoc(
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/frame/" + b);

						boolean bo = (occupied != 0 ? true : false);

						if (b.equals("live_edge")) {

							ResourceLocation tex = Compendium
									.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side");

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", planksTexture), Pair.of("1", tex)));

						} else if (b.equals("weave")) {

							ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mw.name + "/weave");

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", planksTexture), Pair.of("1", tex)));

						} else if (b.equals("slats")) {
							ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mw.name + "/slats");

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", planksTexture), Pair.of("1", tex)));
						} else if (b.contains("ornate")) {
							ResourceLocation tex = Compendium
									.modLoc("block/material/wood/" + mw.name + "/windows/grill");

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", planksTexture), Pair.of("1", tex)));
						} else {

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									CompendiumClient.doBed(event, loc, m, Pair.of("0", planksTexture)));

						}

					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/frame/" + b);
			event.getModels().put(m_inv, CompendiumClient.basicModelAllTexture(event, planksTexture, loc, m_inv,
					BlockModelRotation.X0_Y0, "0"));
		}

		for (String b : StyleData.BED_BASE.getTypes()) {
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "base", mw.name,
					b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			MultiPartBakedModel.Builder mmAll = new MultiPartBakedModel.Builder();
			for (BedSideType sideType : BedSideType.values()) {
				MultiPartBakedModel.Builder mmSide = new MultiPartBakedModel.Builder();
				for (BedPart part : BedPart.values()) {
					MultiPartBakedModel.Builder mmPart = new MultiPartBakedModel.Builder();
					for (int occupied = 0; occupied < 2; occupied++) {

						String sideString = sideType.toString().toLowerCase();
						String partString = part.toString().toLowerCase();
						String occupiedString = occupied != 0 ? "occupied" : "unoccupied";

						ResourceLocation loc = Compendium.modLoc(
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/base/" + b);
						boolean bo = (occupied != 0 ? true : false);

						if (b.equals("weave")) {
							ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mw.name + "/weave");

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", planksTexture), Pair.of("1", tex)));
						} else {
							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									CompendiumClient.doBed(event, loc, m, Pair.of("0", planksTexture)));
						}
					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/base/" + b);
			event.getModels().put(m_inv, CompendiumClient.basicModelAllTexture(event, planksTexture, loc, m_inv,
					BlockModelRotation.X0_Y0, "0"));
		}

		for (String b : StyleData.FENCE_POST.getTypes()) {
			ResourceLocation loc = Compendium.modLoc("extra/fence/post/" + b);
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("fence", "post", mw.name,
					b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");
			ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");

			if (b.contains("none")) {
				event.getModels().put(m, CompendiumClient.basicModelManyTexture(event, loc,
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0));

				event.getModels().put(m_inventory,
						CompendiumClient.basicModelManyTexture(event, loc.withSuffix("_inventory"),
								new ModelResourceLocation(modelLoc.withSuffix("_inventory"), ""),
								BlockModelRotation.X0_Y90));
			} else {
				event.getModels().put(m, CompendiumClient.basicModelAllTexture(event, planksTexture, loc, m,
						BlockModelRotation.X0_Y0, "0"));

				event.getModels().put(m_inventory, CompendiumClient.basicModelAllTexture(event, planksTexture,
						loc.withSuffix("_inventory"), m_inventory, BlockModelRotation.X0_Y90, "0"));
			}

		}

		for (String b : StyleData.FENCE_SIDE.getTypes()) {
			ResourceLocation loc = Compendium.modLoc("extra/fence/side/" + b);
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("fence", "side", mw.name,
					b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");
			ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");

			if (b.equals("sheet")) {

				MultiPartBakedModel.Builder mmw = new MultiPartBakedModel.Builder();

				mmw.add(s -> s.getValue(FenceBlock.EAST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y90, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.NORTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y0, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.SOUTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y180, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.WEST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y270, Pair.of("0", planksTexture)));

				event.getModels().put(m, mmw.build());

				event.getModels().put(m_inventory,
						CompendiumClient.basicModelManyTexture(event, loc.withSuffix("_inventory"), m_inventory,
								BlockModelRotation.X0_Y90, Pair.of("0", planksTexture)));
			} else if (b.equals("solid_sheet")) {

				MultiPartBakedModel.Builder mmw = new MultiPartBakedModel.Builder();

				mmw.add(s -> s.getValue(FenceBlock.EAST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y90,
								Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

				mmw.add(s -> s.getValue(FenceBlock.NORTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y0,
								Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

				mmw.add(s -> s.getValue(FenceBlock.SOUTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y180,
								Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

				mmw.add(s -> s.getValue(FenceBlock.WEST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y270,
								Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

				event.getModels().put(m, mmw.build());

				event.getModels().put(m_inventory,
						CompendiumClient.basicModelManyTexture(event, loc.withSuffix("_inventory"), m_inventory,
								BlockModelRotation.X0_Y90,
								Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));
			} else {
				MultiPartBakedModel.Builder mmw = new MultiPartBakedModel.Builder();

				mmw.add(s -> s.getValue(FenceBlock.EAST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y90, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.NORTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y0, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.SOUTH),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y180, Pair.of("0", planksTexture)));

				mmw.add(s -> s.getValue(FenceBlock.WEST),
						CompendiumClient.basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
								BlockModelRotation.X0_Y270, Pair.of("0", planksTexture)));

				event.getModels().put(m, mmw.build());

				event.getModels().put(m_inventory,
						CompendiumClient.basicModelManyTexture(event, loc.withSuffix("_inventory"), m_inventory,
								BlockModelRotation.X0_Y90, Pair.of("0", planksTexture)));
			}

		}

		for (String b : StyleData.SHINGLES_SHINGLES.getTypes()) {
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("shingles_slanted", "shingles",
					mw.name, b.toLowerCase());

			ResourceLocation straight = Compendium.modLoc("extra/shingles_slanted/shingles/straight/" + b);
			ResourceLocation inner = Compendium.modLoc("extra/shingles_slanted/shingles/inner_corner/" + b);
			ResourceLocation outer = Compendium.modLoc("extra/shingles_slanted/shingles/outer_corner/" + b);
			CompendiumClient.doStyleStairs(event, b, modelLoc, modelLoc.withSuffix("_inventory"), straight, inner,
					outer, 0, 0, Pair.of("0", planksTexture));
		}

		if (mw.LOG.isNotIgnored())
			for (String b : StyleData.SUPPORT_SHINGLES.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("shingles_slanted",
						"support", mw.name, b.toLowerCase());

				ResourceLocation straight = Compendium.modLoc("extra/shingles_slanted/support/straight/" + b);
				ResourceLocation inner = Compendium.modLoc("extra/shingles_slanted/support/inner_corner/" + b);
				ResourceLocation outer = Compendium.modLoc("extra/shingles_slanted/support/outer_corner/" + b);

				CompendiumClient.doStyleStairs(event, b, modelLoc, modelLoc.withSuffix("_inventory"), straight, inner,
						outer, 0, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

				CompendiumClient.doShingleCap(event, mw, "support", b,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			}

		for (String b : StyleData.SHINGLES_CAP_SHINGLES.getTypes()) {
			CompendiumClient.doShingleCap(event, mw, "shingles", b, Pair.of("0", planksTexture));
		}

		if (mw.LOG.isNotIgnored())
			for (String b : StyleData.SUPPORT_CAP_SHINGLES.getTypes()) {
				CompendiumClient.doShingleCap(event, mw, "support", b,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			}

	}

	private static void doLog(ModifyBakingResult event, MaterialWood mw, Map<ModelResourceLocation, BakedModel> models,
			ResourceLocation sideTex, ResourceLocation endTex, String n, ResourceLocation modelLoc,
			ResourceLocation inventoryModelLoc, ResourceLocation model) {
		for (BlockState state : mw.WOOD.BLOCK.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = CompendiumClient.stateToString(propertyValues);

			CompendiumClient.buildStateModelVariantAltLocation(event, models, model, n, v);
		}

		event.getModels().put(new ModelResourceLocation(modelLoc, "axis=y"),
				CompendiumClient.basicModelManyTexture(event, model, new ModelResourceLocation(modelLoc, "axis=y"),
						BlockModelRotation.X0_Y0, Pair.of("side", sideTex), Pair.of("end", endTex)));

		event.getModels().put(new ModelResourceLocation(modelLoc, "axis=x"),
				CompendiumClient.basicModelManyTexture(event, model, new ModelResourceLocation(modelLoc, "axis=x"),
						BlockModelRotation.X90_Y90, Pair.of("side", sideTex), Pair.of("end", endTex)));

		event.getModels().put(new ModelResourceLocation(modelLoc, "axis=z"),
				CompendiumClient.basicModelManyTexture(event, model, new ModelResourceLocation(modelLoc, "axis=z"),
						BlockModelRotation.X90_Y0, Pair.of("side", sideTex), Pair.of("end", endTex)));

		event.getModels().put(new ModelResourceLocation(inventoryModelLoc, ""),
				CompendiumClient.basicModelManyTexture(event, model, new ModelResourceLocation(inventoryModelLoc, ""),
						BlockModelRotation.X0_Y0, Pair.of("side", sideTex), Pair.of("end", endTex)));
	}

	public static void doStyleWood(ModifyBakingResult event, Map<ModelResourceLocation, BakedModel> models,
			MaterialWood mw) {
//		Map<ModelResourceLocation, BakedModel> models = event.getModels();
		for (_MaterialExtension me : mw.extensions) {
			if (me instanceof ExtensionExtraPlanks eep) {
				doExtraPlanks(event, mw, eep, models);
			} else if (me instanceof ExtensionExtraLogs eel) {
				doExtraLogs(event, mw, eel, models);
			}
		}
	}

	private static void doExtraPlanks(ModifyBakingResult event, MaterialWood mw, ExtensionExtraPlanks eep,
			Map<ModelResourceLocation, BakedModel> models) {

//		ResourceLocation plankTexture = TagUtil.mcLoc("block/" + mw.name + "_planks");
//		if (mw.specialLocations != null) {
//			if (mw.specialLocations.textures != null)
//				if (mw.specialLocations.textures.plankLocation != null)
//					plankTexture = mw.specialLocations.textures.plankLocation;
//		}

		if (eep.PLANK_BLOCK.isNotIgnored()) {
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks"),
					mw.name + "_styled_planks", "");

		}

		if (eep.PLANK.isNotIgnored()) {
			for (BlockState state : eep.PLANK.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank"),
						mw.name + "_plank", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank_inventory"),
					mw.name + "_plank_inventory", "");

			for (String plank_style : StyleData.PLANK.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_plank",
						plank_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_plank_inventory", plank_style.toLowerCase());
				CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
						TagUtil.modLoc("extra/plank/" + plank_style + "_cap"),
						TagUtil.modLoc("extra/plank/" + plank_style),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal2"),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal"),
						TagUtil.modLoc("extra/plank/" + plank_style + "_inventory"),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/plank")));
			}
		}

		if (eep.PLANK_SLAB.isNotIgnored()) {
			for (BlockState state : eep.PLANK_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_slab"),
						mw.name + "_styled_planks_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/planks_slab_inventory"), mw.name + "_styled_planks_slab_inventory", "");
		}

		if (eep.PLANK_STAIRS.isNotIgnored()) {
			for (BlockState state : eep.PLANK_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_stairs"),
						mw.name + "_styled_planks_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/planks_stairs_inventory"), mw.name + "_styled_planks_stairs_inventory", "");
		}

		for (String planks_style : StyleData.PLANKS.getTypes()) {
			// planks
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_styled_planks",
					planks_style.toLowerCase());
			ResourceLocation t = Compendium
					.modLoc("block/material/wood/" + mw.name + "/planks/" + planks_style.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			if (eep.PLANK_BLOCK.isNotIgnored()) {
				BakedModel bm = CompendiumClient.basicModelAllTexture(event, t, loc, m, BlockModelRotation.X0_Y0,
						"all");
				event.getModels().put(m, bm);
			}

			if (eep.PLANK_SLAB.isNotIgnored()) {
				// slabs
				ResourceLocation plankSlabModelLoc = ClientUtil
						.createStyleBlockLocation(mw.name + "_styled_planks_slab", planks_style.toLowerCase());
				MultiPartBakedModel.Builder plank_slab = new MultiPartBakedModel.Builder();

				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.BOTTOM,
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.TOP,
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab_top"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.DOUBLE,
						CompendiumClient.basicModelAllTexture(event, t, loc,
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0, "all"));

				event.getModels().put(new ModelResourceLocation(plankSlabModelLoc, ""), plank_slab.build());

				ResourceLocation plankSlabModelLocInventory = ClientUtil.createStyleBlockLocation(
						mw.name + "_styled_planks_slab_inventory", planks_style.toLowerCase());

				event.getModels().put(new ModelResourceLocation(plankSlabModelLocInventory, ""),
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLocInventory, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
			}

			if (eep.PLANK_STAIRS.isNotIgnored()) {
				// stairs
				ResourceLocation plankStairsModelLoc = ClientUtil
						.createStyleBlockLocation(mw.name + "_styled_planks_stairs", planks_style.toLowerCase());
				ResourceLocation plankStairsModelLocInventory = ClientUtil.createStyleBlockLocation(
						mw.name + "_styled_planks_stairs_inventory", planks_style.toLowerCase());

				ResourceLocation straight = TagUtil.mcLoc("block/acacia_stairs");
				ResourceLocation inner = TagUtil.mcLoc("block/acacia_stairs_inner");
				ResourceLocation outer = TagUtil.mcLoc("block/acacia_stairs_outer");

				CompendiumClient.doStyleStairs(event, planks_style, plankStairsModelLoc, plankStairsModelLocInventory,
						straight, inner, outer, 90, 0, Pair.of("top", t), Pair.of("bottom", t), Pair.of("side", t));
			}
		}

	}

	private static void doExtraLogs(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		ResourceLocation logSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/log");
		ResourceLocation logStrippedSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/stripped_log");
		ResourceLocation logEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/log_top");
		ResourceLocation logStrippedEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/wood/" + mw.name + "/stripped_log_top");

		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null) {
				if (mw.specialLocations.textures.logLocation != null)
					logSideTexture = mw.specialLocations.textures.logLocation;
				if (mw.specialLocations.textures.logTopLocation != null)
					logEndTexture = mw.specialLocations.textures.logTopLocation;
				if (mw.specialLocations.textures.strippedLogLocation != null)
					logStrippedSideTexture = mw.specialLocations.textures.strippedLogLocation;
				if (mw.specialLocations.textures.logTopLocation != null)
					logStrippedEndTexture = mw.specialLocations.textures.strippedLogTopLocation;
			}
		}

		doSmallLog(event, mw, eel, models);
		doStyleLog(event, mw, eel, models, logEndTexture, logStrippedEndTexture);
		doLogSlab(event, mw, eel, models, logSideTexture, logEndTexture, logStrippedSideTexture, logStrippedEndTexture);
		doLogStairs(event, mw, eel, models, logSideTexture, logEndTexture, logStrippedSideTexture,
				logStrippedEndTexture);

	}

	public static void doLogStairs(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logSideTexture,
			ResourceLocation logEndTexture, ResourceLocation logStrippedSideTexture,
			ResourceLocation logStrippedEndTexture) {

		if (eel.STRIPPED_LOG_STAIRS.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_log_stairs"), "stripped_" + mw.name + "_small_logs_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_stairs_inventory"), mw.name + "_stripped_log_stairs_inventory",
					"");
		}

		if (eel.LOG_STAIRS.isNotIgnored()) {
			for (BlockState state : eel.LOG_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_stairs"),
						mw.name + "_small_logs_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/log_stairs_inventory"), mw.name + "_log_stairs_inventory", "");
		}
		for (String stair_style : StyleData.LOG_STAIRS.getTypes()) {
			// stairs
			ResourceLocation plankStairsModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_logs_stairs",
					stair_style.toLowerCase());
			ResourceLocation plankStairsModelLocInventory = ClientUtil
					.createStyleBlockLocation(mw.name + "_log_stairs_inventory", stair_style.toLowerCase());

			ResourceLocation plankStrippedStairsModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_small_logs_stairs", stair_style.toLowerCase());
			ResourceLocation plankStrippedStairsModelLocInventory = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_log_stairs_inventory", stair_style.toLowerCase());

			if (stair_style.equals("small_logs")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_side")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_front")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_top")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("split_log_rotated_side")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_front")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_top")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("small_wood")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_wood_rotated")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("wood")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logSideTexture), Pair.of("2", logSideTexture));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedSideTexture),
						Pair.of("2", logStrippedSideTexture));
			} else if (stair_style.equals("wood_rotated")) {
				CompendiumClient.doStyleStairs(event, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0, Pair.of("0", logSideTexture));

				CompendiumClient.doStyleStairs(event, stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture));
			}
		}
	}

	public static void doLogSlab(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logSideTexture,
			ResourceLocation logEndTexture, ResourceLocation logStrippedSideTexture,
			ResourceLocation logStrippedEndTexture) {
		if (eel.STRIPPED_LOG_SLAB.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_log_slab"), "stripped_" + mw.name + "_small_logs_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_slab_inventory"), mw.name + "_stripped_log_slab_inventory", "");
		}

		if (eel.LOG_SLAB.isNotIgnored()) {
			for (BlockState state : eel.LOG_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_slab"),
						mw.name + "_small_logs_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/log_slab_inventory"), mw.name + "_log_slab_inventory", "");
		}

		for (String slab_style : StyleData.LOG_SLAB.getTypes()) {
			// slabs
			ResourceLocation plankSlabModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_logs_slab",
					slab_style.toLowerCase());
			ResourceLocation plankSlabIventoryModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_log_slab_inventory", slab_style.toLowerCase());

			ResourceLocation strippedSlabModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_small_logs_slab", slab_style.toLowerCase());
			ResourceLocation strippedSlabIventoryModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_log_slab_inventory", slab_style.toLowerCase());

			if (slab_style.equals("small_logs") || slab_style.equals("small_logs_rotated")
					|| slab_style.equals("crosscut_small")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("particle", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("particle",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

			} else if (slab_style.equals("split") || slab_style.equals("split_rotated")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")),
						Pair.of("particle", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", logStrippedEndTexture),
						Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")),
						Pair.of("particle",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));

			} else if (slab_style.equals("crosscut")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("0", logSideTexture),
						Pair.of("1", logEndTexture), Pair.of("particle", logEndTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture),
						Pair.of("particle", logStrippedEndTexture));

			} else if (slab_style.equals("small_wood") || slab_style.equals("small_wood_rotated")) {
				ResourceLocation logTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs");
				ResourceLocation logStrippedTexture = TagUtil
						.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs");

				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logTexture), Pair.of("bottom", logTexture), Pair.of("side", logTexture),
						Pair.of("particle", logTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("top", logStrippedTexture), Pair.of("bottom", logStrippedTexture),
						Pair.of("side", logStrippedTexture), Pair.of("particle", logStrippedTexture));

			} else if (slab_style.equals("wood") || slab_style.equals("wood_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logSideTexture), Pair.of("bottom", logSideTexture),
						Pair.of("side", logSideTexture), Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("top", logStrippedSideTexture), Pair.of("bottom", logStrippedSideTexture),
						Pair.of("side", logStrippedSideTexture), Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("campfire")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("firewood")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("smaller_logs") || slab_style.equals("smaller_logs_rotated")
					|| slab_style.equals("smallest_logs") || slab_style.equals("smallest_logs_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("trellis")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));
			}
		}
	}

	public static void doStyleLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logEndTexture,
			ResourceLocation logStrippedEndTexture) {

		if (eel.STRIPPED_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log"),
						"stripped_" + mw.name + "_small_logs", v);
			}

			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_inventory"), mw.name + "_stripped_log_inventory", "");

			for (String log_style : StyleData.LOG.getTypes()) {
				ResourceLocation strippedLogModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_stripped_log",
						log_style.toLowerCase());
				ResourceLocation strippedLogModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_stripped_log_inventory", log_style.toLowerCase());

				if (log_style.equals("basic")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

				} else if (log_style.equals("small_wood")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
				} else if (log_style.equals("corner")) {
					ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("2", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));
				} else {
					ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
					ResourceLocation sideStrippedTexture = TagUtil
							.modLoc("block/material/wood/" + mw.name + "/logs/stripped_" + log_style);
					if (log_style.contains("1") || log_style.contains("2")) {

						CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("bottom", logEndTexture),
								Pair.of("top", logStrippedEndTexture));

					} else {
						CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("top", logEndTexture),
								Pair.of("bottom", logStrippedEndTexture));

					}
				}
			}
		}

		if (eel.LOG.isNotIgnored()) {
			for (BlockState state : eel.LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log"),
						mw.name + "_small_logs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_inventory"),
					mw.name + "_log_inventory", "");
		}

		for (String log_style : StyleData.LOG.getTypes()) {
			ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_log",
					log_style.toLowerCase());
			ResourceLocation logModelLocInventory = ClientUtil.createStyleBlockLocation(mw.name + "_log_inventory",
					log_style.toLowerCase());

			if (log_style.equals("basic")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

			} else if (log_style.equals("small_wood")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));
			} else if (log_style.equals("corner")) {
				ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			} else {
				ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
				ResourceLocation sideTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/" + log_style);

				if (log_style.contains("1") || log_style.contains("2")) {

					CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
							Pair.of("side", sideTexture), Pair.of("top", logEndTexture),
							Pair.of("bottom", logStrippedEndTexture));

				} else {
					CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
							Pair.of("side", sideTexture), Pair.of("bottom", logEndTexture),
							Pair.of("top", logStrippedEndTexture));

				}
			}
		}

	}

	public static void doSmallLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		if (eel.STRIPPED_SMALL_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_SMALL_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_small_log"), "stripped_" + mw.name + "_small_log", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_small_log_inventory"), mw.name + "_stripped_small_log_inventory",
					"");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logStrippedModelLoc = ClientUtil
						.createStyleBlockLocation(mw.name + "_stripped_small_log", small_log_style.toLowerCase());
				ResourceLocation logStrippedModelLocInventory = ClientUtil.createStyleBlockLocation(
						mw.name + "_stripped_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							TagUtil.modLoc("extra/small_log/small_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smaller_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smallest_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				}
			}

		}

		if (eel.SMALL_LOG.isNotIgnored()) {
			for (BlockState state : eel.SMALL_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/small_log"),
						mw.name + "_small_log", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/small_log_inventory"), mw.name + "_small_log_inventory", "");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							TagUtil.modLoc("extra/small_log/small_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smaller_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smallest_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				}
			}

		}

	}

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialWood mw) {
		if (mw.PLANKS.shouldGenerate())
			shaper.register(mw.PLANKS.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_planks"), ""));

		if (mw.LOG.shouldGenerate())
			shaper.register(mw.LOG.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_log_inventory"), ""));

		if (mw.STRIPPED_LOG.shouldGenerate())
			shaper.register(mw.STRIPPED_LOG.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_stripped_log_inventory"), ""));

		if (mw.WOOD.shouldGenerate())
			shaper.register(mw.WOOD.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_wood_inventory"), ""));

		if (mw.STRIPPED_WOOD.shouldGenerate())
			shaper.register(mw.STRIPPED_WOOD.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_stripped_wood_inventory"), ""));

//		for (CompendiumItemHandler i : mw.ITEMS) {
//			if (i.shouldGenerate())
//				shaper.register(i.ITEM.asItem(), new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
//		}
//
//		if (mw.BLOCK.shouldGenerate())
//			shaper.register(mw.BLOCK.BLOCK_ITEM.asItem(),
//					new ModelResourceLocation(TagUtil.modLoc(mw.name + "_block"), ""));
//
//		for (_MaterialExtension me : mw.extensions) {
//			for (CompendiumItemHandler i : me.ITEMS) {
//				if (i.shouldGenerate())
//					shaper.register(i.ITEM.asItem(),
//							new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
//			}
//
//		}

//		if (mw.LOG.shouldGenerate())
//			shaper.register(mw.LOG.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));

		mw.extensions.forEach(i -> {
			if (i instanceof ExtensionExtraPlanks eep) {
				if (eep.PLANK.shouldGenerate())
					shaper.register(eep.PLANK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_BLOCK.shouldGenerate())
					shaper.register(eep.PLANK_BLOCK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_SLAB.shouldGenerate())
					shaper.register(eep.PLANK_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_STAIRS.shouldGenerate())
					shaper.register(eep.PLANK_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
			if (i instanceof ExtensionExtraLogs eep) {
				if (eep.LOG.shouldGenerate())
					shaper.register(eep.LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_SLAB.shouldGenerate())
					shaper.register(eep.LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_STAIRS.shouldGenerate())
					shaper.register(eep.LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.SMALL_LOG.shouldGenerate())
					shaper.register(eep.SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_SLAB.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_STAIRS.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_SMALL_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
		});
	}
}
