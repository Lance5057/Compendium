package com.lance5057.compendium.index.material.base.textile;

import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientTextile {

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialTextile mm) {

		if (mm.BLOCK.shouldGenerate())
			shaper.register(mm.BLOCK.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mm.name + "_block"), ""));

		if (mm.CARPET.shouldGenerate())
			shaper.register(mm.CARPET.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mm.name + "_carpet"), ""));

	}

	public static void doTextile(ModifyBakingResult event, MaterialTextile mb) {
//		if (mb instanceof MaterialTextile mt) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();

		ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name);

		if (mb.BLOCK.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mb.name + "_block");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, texture, loc, m, BlockModelRotation.X0_Y0,
					"all");
			models.put(m, bm);
		}

		if (mb.CARPET.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/carpet");
			ResourceLocation modelLoc = TagUtil.modLoc(mb.name + "_carpet");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, texture, loc, m, BlockModelRotation.X0_Y0,
					"wool");
			models.put(m, bm);
		}

		for (String b : StyleData.TABLE_CLOTH.getTypes()) {
			ResourceLocation loc = Compendium.modLoc("extra/clothed_table/cloth/" + b.toLowerCase());
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("clothed_table", "cloth",
					mb.name, b.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation("clothed_table", "cloth",
					mb.name, b.toLowerCase(), "_inventory");
			ResourceLocation loc_inv = Compendium.modLoc("extra/clothed_table/cloth/" + b + "_inventory");

			if (b.contains("angled")) {

				event.getModels().put(m, CompendiumClient.basicModelManyTexture(event, loc, m, BlockModelRotation.X0_Y0,
						Pair.of("0", texture),
						Pair.of("1", Compendium.modLoc("block/material/textile/" + mb.name + "/diagonal_half"))));

				event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
						CompendiumClient.basicModelManyTexture(event, loc_inv,
								new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0,
								Pair.of("0", texture), Pair.of("1",
										Compendium.modLoc("block/material/textile/" + mb.name + "/diagonal_half"))));
			} else {
				event.getModels().put(m,
						CompendiumClient.basicModelAllTexture(event, texture, loc, m, BlockModelRotation.X0_Y0, "0"));

				event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
						CompendiumClient.basicModelAllTexture(event, texture, loc_inv,
								new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "0"));
			}

		}

		for (String b : StyleData.BED_MATTRESS.getTypes()) {

			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "mattress", mb.name,
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
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/mattress/" + b);

						boolean bo = (occupied != 0 ? true : false);

						mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
								CompendiumClient.doBed(event, loc, m, Pair.of("0", texture)));

					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/mattress/" + b);
			event.getModels().put(m_inv,
					CompendiumClient.basicModelAllTexture(event, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
		}

		for (String b : StyleData.BED_SHEET.getTypes()) {

			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "sheet", mb.name,
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
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/sheet/" + b);

						boolean bo = (occupied != 0 ? true : false);

						mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
								CompendiumClient.doBed(event, loc, m, Pair.of("0", texture)));

					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/sheet/" + b);
			event.getModels().put(m_inv,
					CompendiumClient.basicModelAllTexture(event, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
		}

		for (String b : StyleData.BED_PILLOW.getTypes()) {

			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "pillow", mb.name,
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
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/pillow/" + b);

						boolean bo = (occupied != 0 ? true : false);

						mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
								CompendiumClient.doBed(event, loc, m, Pair.of("0", texture)));

					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/pillow/" + b);
			event.getModels().put(m_inv,
					CompendiumClient.basicModelAllTexture(event, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
		}

		for (String b : StyleData.BED_BLANKET.getTypes()) {
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("bed", "blanket", mb.name,
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
								"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/blanket/" + b);

						boolean bo = (occupied != 0 ? true : false);

						if (b.equals("llama")) {
							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									CompendiumClient.doBed(event, loc, m,
											Pair.of("3", Compendium.modLoc(mb.blockFolder() + "llama_trim")),
											Pair.of("2", Compendium.modLoc(mb.blockFolder() + "llama"))));

						} else if (b.equals("glazed")) {

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, CompendiumClient.doBed(event, loc,
									m, Pair.of("0", Compendium.modLoc(mb.blockFolder() + "woolly_glazed"))));
						} else {

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									CompendiumClient.doBed(event, loc, m, Pair.of("0", texture)));

						}

					}
					mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
				}
				mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
			}
			event.getModels().put(m, mmAll.build());

			ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
			ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/blanket/" + b);

			if (b.equals("llama")) {
				event.getModels().put(m_inv,
						CompendiumClient.basicModelManyTexture(event, loc, m_inv, BlockModelRotation.X0_Y0,
								Pair.of("3", Compendium.modLoc(mb.blockFolder() + "llama_trim")),
								Pair.of("2", Compendium.modLoc(mb.blockFolder() + "llama"))));
			} else if (b.equals("glazed")) {
				event.getModels().put(m_inv,
						CompendiumClient.basicModelAllTexture(event,
								Compendium.modLoc(mb.blockFolder() + "woolly_glazed"), loc, m_inv,
								BlockModelRotation.X0_Y0, "0"));
			} else {
				event.getModels().put(m_inv, CompendiumClient.basicModelAllTexture(event, texture, loc, m_inv,
						BlockModelRotation.X0_Y0, "0"));
			}
		}
	}

}
//}
