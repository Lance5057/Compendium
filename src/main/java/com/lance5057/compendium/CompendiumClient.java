package com.lance5057.compendium;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRenderer;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerScreen;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRenderer;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.table.TableBase;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.client.FancyItemRenderer;
import com.lance5057.compendium.client.armor.ModelBreastplate;
import com.lance5057.compendium.client.armor.ModelGreaves;
import com.lance5057.compendium.client.armor.ModelHelm;
import com.lance5057.compendium.client.armor.ModelSabatons;
import com.lance5057.compendium.client.models.blockstaterenderer.BlockStateItemGeometryLoader;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialUnbakedModel;
import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialUnbakedModel;
import com.lance5057.compendium.client.models.style.StyleUnbakedModel;
import com.lance5057.compendium.client.renderer.blockentity.SimpleStyleBlockRenderer;
import com.lance5057.compendium.client.renderer.entity.SeatRenderer;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialScreen;
import com.lance5057.compendium.gui.AdjustinatorWorkstationScreen;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedScreen;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRenderer;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRenderer;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchScreen;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery.ModelBakerImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.CLIENT)
public class CompendiumClient {
	public static final ModelLayerLocation HELM = register("helm", "main");
	public static final ModelLayerLocation BREASTPLATE = register("breastplate", "main");
	public static final ModelLayerLocation GREAVES = register("grieves", "main");
	public static final ModelLayerLocation SABATONS = register("sabatons", "main");

	private static ModelLayerLocation register(String model, String layer) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, model), layer);
	}

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(HELM, ModelHelm::createBodyLayer);
		event.registerLayerDefinition(BREASTPLATE, ModelBreastplate::createBodyLayer);
		event.registerLayerDefinition(GREAVES, ModelGreaves::createBodyLayer);
		event.registerLayerDefinition(SABATONS, ModelSabatons::createBodyLayer);
	}

	public static void setBERenderers() {
		BlockEntityRenderers.register(CompendiumBlockEntities.HAMMERING_STATION.get(), HammeringStationRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.SAW_BUCK.get(), SawBuckRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.SCRAPPING_TABLE.get(), ScrappingTableRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.WORKBENCH.get(), WorkbenchRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.TOOLRACK.get(), ToolRackRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.COMPONENT_DRAWER.get(), ComponentDrawerRenderer::new);
		BlockEntityRenderers.register(CompendiumBlockEntities.STYLE.get(), SimpleStyleBlockRenderer::new);
	}

	@SubscribeEvent
	public static void registerClient(RegisterMenuScreensEvent event) {
		event.register(CompendiumMenus.STYLE_MENU.get(), CosmeticToolboxScreen::new);
		event.register(CompendiumMenus.PLACED_STYLE_MENU.get(), CosmeticToolboxPlacedScreen::new);
		event.register(CompendiumMenus.ADJUSTINATOR_WORKSTATION_MENU.get(), AdjustinatorWorkstationScreen::new);
		event.register(CompendiumMenus.ADJUSTINATOR_MULTIMATERIAL_MENU.get(), AdjustinatorMultiMaterialScreen::new);
		event.register(CompendiumMenus.WORKBENCH_MENU.get(), WorkbenchScreen::new);
		event.register(CompendiumMenus.COMPONENT_DRAWER_MENU.get(), ComponentDrawerScreen::new);
	}

	@SubscribeEvent
	public static void registerLoader(ModelEvent.RegisterGeometryLoaders registerGeometryLoaders) {
		registerGeometryLoaders.register(MultiMaterialUnbakedModel.Loader.ID, new MultiMaterialUnbakedModel.Loader());
		registerGeometryLoaders.register(StyleUnbakedModel.Loader.ID, new StyleUnbakedModel.Loader());
		registerGeometryLoaders.register(BlockStateItemGeometryLoader.ID, new BlockStateItemGeometryLoader());
		registerGeometryLoaders.register(MultiStyleMaterialUnbakedModel.Loader.ID,
				new MultiStyleMaterialUnbakedModel.Loader());
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CompendiumEntities.SEAT.get(), c -> new SeatRenderer(c));
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		Compendium.styleItemRenderers.add(CompendiumItems.CHAIR);
		Compendium.styleItemRenderers.add(CompendiumItems.CLOTHED_TABLE);
		Compendium.styleItemRenderers.add(CompendiumItems.TABLE);
		Compendium.styleItemRenderers.add(CompendiumItems.SHINGLES_CAP_SLANTED);
		Compendium.styleItemRenderers.add(CompendiumItems.SHINGLES_SLANTED);
		Compendium.styleItemRenderers.add(CompendiumItems.FANCY_FENCE);
		Compendium.styleItemRenderers.add(CompendiumItems.FANCY_BED);
		Compendium.styleItemRenderers.add(CompendiumItems.WINDOW);

		event.registerItem(new IClientItemExtensions() {

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return FancyItemRenderer.getInstance();
			}
		}, Compendium.styleItemRenderers.stream().map(i -> i.get()).collect(Collectors.toList()).toArray(new Item[0]));

	}

	@SubscribeEvent
	public static void extraModels(ModifyBakingResult event) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				doMetal(event, mb);
				doGlass(event, mb);
				doTextile(event, mb);
				doWood(event, mb);
			}
		});

		buildStateModelBasic(event, models, "window");

		buildStateModelVariant(event, models, "chair", "facing=south");
		buildStateModelRotated(event, models, "chair", "facing=east", BlockModelRotation.X0_Y270);
		buildStateModelRotated(event, models, "chair", "facing=north", BlockModelRotation.X0_Y180);
		buildStateModelRotated(event, models, "chair", "facing=west", BlockModelRotation.X0_Y90);

		for (int i = 0b000000000; i < 0b111111111; i++) {
			String v = "e=" + bitToConditionString(i, 0b100000000) + ",n=" + bitToConditionString(i, 0b010000000)
					+ ",ne=" + bitToConditionString(i, 0b001000000) + ",nw=" + bitToConditionString(i, 0b000100000)
					+ ",s=" + bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
					+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w=" + bitToConditionString(i, 0b000000001);
			buildStateModelVariant(event, models, "table", v);
		}

		for (int i = 0b000000000; i < 0b111111111; i++) {
			String v = "e=" + bitToConditionString(i, 0b100000000) + ",n=" + bitToConditionString(i, 0b010000000)
					+ ",ne=" + bitToConditionString(i, 0b001000000) + ",nw=" + bitToConditionString(i, 0b000100000)
					+ ",s=" + bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
					+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w=" + bitToConditionString(i, 0b000000001);
			buildStateModelVariant(event, models, "clothed_table", v);
		}

		for (int i = 0b000000; i < 0b11111; i++) {
			String v = "east=" + bitToConditionString(i, 0b10000) + ",north=" + bitToConditionString(i, 0b01000)
					+ ",south=" + bitToConditionString(i, 0b00100) + ",waterlogged=" + bitToConditionString(i, 0b00010)
					+ ",west=" + bitToConditionString(i, 0b00001);
			buildStateModelVariant(event, models, "fancy_fence", v);
		}

		for (BedSideType sideType : BedSideType.values())
			for (BedPart part : BedPart.values()) {
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					for (int occupied = 0; occupied < 2; occupied++) {

						String sideString = sideType.toString().toLowerCase();
						String partString = part.toString().toLowerCase();
						String dirString = dir.toString().toLowerCase();
						String occupiedString = occupied != 0 ? "true" : "false";

						String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
								+ ",type=" + sideString;

						buildStateModelVariant(event, models, "fancy_bed", v);
					}
				}
			}

		for (Direction dir : Direction.Plane.HORIZONTAL) {
			for (Half half : Half.values()) {
				for (StairsShape shape : StairsShape.values()) {
					for (int water = 0; water < 2; water++) {
						String v = "facing=" + dir.toString().toLowerCase() + ",half=" + half.toString().toLowerCase()
								+ ",shape=" + shape.toString().toLowerCase() + ",waterlogged="
								+ (water != 0 ? "true" : "false");

						buildStateModelVariant(event, models, "shingles_slanted", v);
					}
				}
			}
		}

	}

	private static void buildStateModelRotated(ModifyBakingResult event, Map<ModelResourceLocation, BakedModel> models,
			String w, String variant, BlockModelRotation rotation) {
		ResourceLocation rc = Compendium.modLoc("extra/" + w);

		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(w), variant);
		BlockModel um = (BlockModel) event.getModelBakery().getModel(rc);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), rotation);
		models.put(ml, bm);
	}

	private static void buildStateModelVariant(ModifyBakingResult event, Map<ModelResourceLocation, BakedModel> models,
			String w, String variant) {
		ResourceLocation rc = Compendium.modLoc("extra/" + w);

		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(w), variant);
		BlockModel um = (BlockModel) event.getModelBakery().getModel(rc);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml, bm);
	}

	private static void buildStateModelBasic(ModifyBakingResult event, Map<ModelResourceLocation, BakedModel> models,
			String w) {
		ResourceLocation rc = Compendium.modLoc("extra/" + w);

		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(w), "");
		BlockModel um = (BlockModel) event.getModelBakery().getModel(rc);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml, bm);

		ModelResourceLocation ml2 = ModelResourceLocation.inventory(Compendium.modLoc(w));
		BlockModel um2 = (BlockModel) event.getModelBakery().getModel(rc);
		ModelBakerImpl baker2 = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(),
				ml2);
		um2.resolveParents(i -> baker2.getModel(i));

		BakedModel bm2 = um2.bake(baker2, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml2, bm2);
	}

	private static void doMetal(ModifyBakingResult event, _MaterialBase mb) {
//		if (mb.getType() == MATERIAL_TYPES.METAL) {
//
//			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
//				ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
//				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
//						b.toLowerCase());
//
//				ResourceLocation texture = Compendium
//						.modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase());
//
//				event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb, texture,
//						loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
//			});
//		}
	}

	public static void doGlass(ModifyBakingResult event, _MaterialBase mb) {
//		if (mb.getType() == MATERIAL_TYPES.GLASS) {
//			StyleData.WINDOW_GLASS.getTypes().forEach(b -> {
//				ResourceLocation loc = Compendium.modLoc("extra/window/window_glass");
//				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "glass", mb.name,
//						b.toLowerCase());
//
//				if (mb.name.equalsIgnoreCase("clear")) {
//					ResourceLocation texture = TagUtil.mcLoc("block/glass");
//
//					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
//							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
//				} else {
//					ResourceLocation texture = TagUtil.mcLoc("block/" + mb.name + "_glass");
//
//					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
//							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
//				}
//			});
//		}
	}

	public static void doTextile(ModifyBakingResult event, _MaterialBase mb) {
//		if (mb instanceof MaterialTextile mt) {
//
//			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name);
//			if (mt.specialLocations != null) {
//				if (mt.specialLocations.textures != null)
//					if (mt.specialLocations.textures.blockLocation != null)
//						texture = ResourceLocation.fromNamespaceAndPath(mb.namespace,
//								mt.specialLocations.textures.blockLocation);
//			}
//
//			for (String b : StyleData.TABLE_CLOTH.getTypes()) {
//				for (int i = 0b000000000; i < 0b111111111; i++) {
//					String v = "e=" + bitToConditionString(i, 0b100000000) + ",n="
//							+ bitToConditionString(i, 0b010000000) + ",ne=" + bitToConditionString(i, 0b001000000)
//							+ ",nw=" + bitToConditionString(i, 0b000100000) + ",s="
//							+ bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
//							+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w="
//							+ bitToConditionString(i, 0b000000001);
//
//					ResourceLocation loc = Compendium.modLoc("extra/clothed_table/cloth/" + b.toLowerCase());
//					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("clothed_table", "cloth",
//							mb.name, b.toLowerCase());
//					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//					if (b.contains("angled")) {
//
//						event.getModels().put(m, basicModelManyTexture(event, mb, loc, m, BlockModelRotation.X0_Y0,
//								Pair.of("0", texture), Pair.of("1",
//										Compendium.modLoc("block/material/textile/" + mb.name + "/diagonal_half"))));
//					} else
//						event.getModels().put(m,
//								basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
//				}
//			}
//
//			for (String b : StyleData.BED_MATTRESS.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed",
//										"mattress", mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								BakedModel bm = doBed(event, mb, "mattress", sideType, part, dir, occupied, b,
//										Pair.of("0", texture));
//
//								event.getModels().put(m, bm);
//							}
//						}
//					}
//				}
////						withExistingParent(
////								"block/material/textile/" + mb.name + "/bed/unoccupied/"
////										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
////										+ "/mattress/" + b.toLowerCase(),
////								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
////										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
////								.texture("0", blockTexture);
////
////						withExistingParent(
////								"block/material/textile/" + mb.name + "/bed/occupied/"
////										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
////										+ "/mattress/" + b.toLowerCase(),
////								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
////										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
////								.texture("0", blockTexture);
//
////				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/mattress/" + b.toLowerCase(),
////						modLoc("block/furniture/bed/inventory/mattress/" + b.toLowerCase())).texture("0", blockTexture);
//			}
//
//			for (String b : StyleData.BED_SHEET.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "sheet",
//										mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								BakedModel bm = doBed(event, mb, "sheet", sideType, part, dir, occupied, b,
//										Pair.of("0", texture));
//
//								event.getModels().put(m, bm);
//							}
//						}
//					}
//				}
//			}
//
//			for (String b : StyleData.BED_PILLOW.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "pillow",
//										mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								BakedModel bm = doBed(event, mb, "pillow", sideType, part, dir, occupied, b,
//										Pair.of("0", texture));
//
//								event.getModels().put(m, bm);
//							}
//						}
//					}
//				}
//			}
//
//			for (String b : StyleData.BED_BLANKET.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed",
//										"blanket", mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								if (b.equals("llama")) {
//									BakedModel bm = doBed(event, mb, "blanket", sideType, part, dir, occupied, b,
//											Pair.of("3", Compendium.modLoc(mb.blockFolder() + "llama_trim")),
//											Pair.of("2", Compendium.modLoc(mb.blockFolder() + "llama")));
//									event.getModels().put(m, bm);
//								} else if (b.equals("glazed")) {
//									BakedModel bm = doBed(event, mb, "blanket", sideType, part, dir, occupied, b,
//											Pair.of("0", Compendium.modLoc(mb.blockFolder() + "woolly_glazed")));
//									event.getModels().put(m, bm);
//								} else {
//									BakedModel bm = doBed(event, mb, "blanket", sideType, part, dir, occupied, b,
//											Pair.of("0", texture));
//									event.getModels().put(m, bm);
//								}
//
//							}
//						}
//					}
//				}
//			}
//		}
	}

	public static void doWood(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialWood mw) {

			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace,
					"block/" + mb.name + "_planks");
			if (mw.specialLocations != null) {
				if (mw.specialLocations.textures != null)
					if (mw.specialLocations.textures.plankLocation != null)
						texture = mw.specialLocations.textures.plankLocation;
			}

			for (String b : StyleData.WINDOW_TRIM.getTypes()) {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
						b.toLowerCase());
				ResourceLocation t = Compendium
						.modLoc("block/material/wood/" + mb.name + "/windows/" + b.toLowerCase());

				event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb, t, loc,
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
			}

			for (String b : StyleData.CHAIR_BACK.getTypes()) {
				if (b.contains("weave")) {
					doChair(event, mb, "back", b, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/weave")));
				} else if (b.contains("sheet")) {
					doChair(event, mb, "back", b,
							Pair.of("0", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet")));
				} else if (b.equals("windsor") || b.equals("slats")) {
					doChair(event, mb, "back", b, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/slats")));
				} else if (b.equals("lozenge")) {
					doChair(event, mb, "back", b, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/lozenge")));
				} else if (b.equals("live_edge")) {
					doChair(event, mb, "back", b,
							Pair.of("0", Compendium.modLoc("block/material/wood/" + mb.name + "/logs/log_split_side")));
				} else {
					doChair(event, mb, "back", b, Pair.of("0", texture));
				}
			}

			for (String b : StyleData.CHAIR_LEGS.getTypes()) {
				if (b.contains("rails_side_lath")) {
					doChair(event, mb, "legs", b, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/slats")));
				} else {
					doChair(event, mb, "legs", b, Pair.of("0", texture));
				}

			}

			for (String b : StyleData.CHAIR_SEAT.getTypes()) {
				if (b.equals("live_edge")) {
					doChair(event, mb, "seat", b,
							Pair.of("0", Compendium.modLoc("block/material/wood/" + mb.name + "/logs/log_split_side")));
				} else if (b.contains("weave")) {
					doChair(event, mb, "seat", b, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/weave")));
				} else if (b.contains("sheet")) {
					doChair(event, mb, "seat", b,
							Pair.of("0", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet")));
				} else {
					doChair(event, mb, "seat", b, Pair.of("0", texture));
				}
			}
//
//			for (String b : StyleData.TABLE_LEGS.getTypes()) {
//				doTableLeg(event, mb, b, "table", Pair.of("0", texture));
//				doTableLeg(event, mb, b, "clothed_table", Pair.of("0", texture));
//			}
//
//			for (String b : StyleData.TABLE_TOP.getTypes()) {
//				for (int i = 0b000000000; i < 0b111111111; i++) {
//					String v = "e=" + bitToConditionString(i, 0b100000000) + ",n="
//							+ bitToConditionString(i, 0b010000000) + ",ne=" + bitToConditionString(i, 0b001000000)
//							+ ",nw=" + bitToConditionString(i, 0b000100000) + ",s="
//							+ bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
//							+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w="
//							+ bitToConditionString(i, 0b000000001);
//
//					ResourceLocation loc = Compendium.modLoc("extra/table/top/" + b);
//					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("table", "top", mb.name,
//							b.toLowerCase());
//					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//					ResourceLocation loc_clothed = Compendium.modLoc("extra/clothed_table/top/" + b);
//					ResourceLocation modelLoc_clothed = ClientUtil.createMaterialStyleLayerLocation("clothed_table",
//							"top", mb.name, b.toLowerCase());
//					ModelResourceLocation m_clothed = new ModelResourceLocation(modelLoc_clothed, v);
//
//					if (b.equals("smooth")) {
//						event.getModels().put(m,
//								basicModelAllTexture(event, mb,
//										Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"), loc, m,
//										BlockModelRotation.X0_Y0, "0"));
//						event.getModels().put(m_clothed,
//								basicModelAllTexture(event, mb,
//										Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"),
//										loc_clothed, m_clothed, BlockModelRotation.X0_Y0, "0"));
//					} else {
//						event.getModels().put(m,
//								basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
//						event.getModels().put(m_clothed, basicModelAllTexture(event, mb, texture, loc_clothed,
//								m_clothed, BlockModelRotation.X0_Y0, "0"));
//					}
//				}
//			}
//
//			for (String b : StyleData.BED_FRAME.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "frame",
//										mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								if (b.equals("live_edge")) {
//
//									ResourceLocation tex = Compendium
//											.modLoc("block/material/wood/" + mb.name + "/logs/log_split_side");
//
//									BakedModel bm = doBed(event, mb, "frame", sideType, part, dir, occupied, b,
//											Pair.of("0", tex), Pair.of("1", texture));
//
//									event.getModels().put(m, bm);
//
//								} else if (b.equals("weave")) {
//
//									ResourceLocation tex = Compendium
//											.modLoc("block/material/wood/" + mb.name + "/weave");
//
//									BakedModel bm = doBed(event, mb, "frame", sideType, part, dir, occupied, b,
//											Pair.of("0", texture), Pair.of("1", tex));
//
//									event.getModels().put(m, bm);
//
//								} else if (b.equals("slats")) {
//									ResourceLocation tex = Compendium
//											.modLoc("block/material/wood/" + mb.name + "/slats");
//
//									BakedModel bm = doBed(event, mb, "frame", sideType, part, dir, occupied, b,
//											Pair.of("0", texture), Pair.of("1", tex));
//
//									event.getModels().put(m, bm);
//
//								} else {
//
//									BakedModel bm = doBed(event, mb, "frame", sideType, part, dir, occupied, b,
//											Pair.of("0", texture));
//
//									event.getModels().put(m, bm);
//
//								}
//							}
//						}
//					}
//				}
//			}
//
//			for (String b : StyleData.BED_BASE.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						for (Direction dir : Direction.Plane.HORIZONTAL) {
//							for (int occupied = 0; occupied < 2; occupied++) {
//								String sideString = sideType.toString().toLowerCase();
//								String partString = part.toString().toLowerCase();
//								String dirString = dir.toString().toLowerCase();
//								String occupiedString = occupied != 0 ? "true" : "false";
//
//								String v = "facing=" + dirString + ",occupied=" + occupiedString + ",part=" + partString
//										+ ",type=" + sideString;
//
//								ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "base",
//										mb.name, b.toLowerCase());
//								ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//								if (b.equals("weave")) {
//
//									ResourceLocation tex = Compendium
//											.modLoc("block/material/wood/" + mb.name + "/weave");
//
//									BakedModel bm = doBed(event, mb, "base", sideType, part, dir, occupied, b,
//											Pair.of("0", texture), Pair.of("1", tex));
//
//									event.getModels().put(m, bm);
//
//								} else {
//
//									BakedModel bm = doBed(event, mb, "base", sideType, part, dir, occupied, b,
//											Pair.of("0", texture));
//
//									event.getModels().put(m, bm);
//
//								}
//							}
//
//						}
//					}
//				}
//			}
//
//			for (String b : StyleData.FENCE_POST.getTypes()) {
//				for (int i = 0b00000; i < 0b11111; i++) {
//					String v = "east=" + bitToConditionString(i, 0b10000) + ",north=" + bitToConditionString(i, 0b01000)
//							+ ",south=" + bitToConditionString(i, 0b00100) + ",waterlogged="
//							+ bitToConditionString(i, 0b00010) + ",west=" + bitToConditionString(i, 0b00001);
//					ResourceLocation loc = Compendium.modLoc("extra/fence/post/" + b);
//					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("fence", "post", mb.name,
//							b.toLowerCase());
//					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//					if (b.contains("none")) {
//						event.getModels().put(m, basicModelManyTexture(event, mb, loc,
//								new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0));
//					} else
//						event.getModels().put(m,
//								basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
//				}
//			}
//
//			for (String b : StyleData.FENCE_SIDE.getTypes()) {
//				for (int i = 0b00000; i < 0b11111; i++) {
//					String v = "east=" + bitToConditionString(i, 0b10000) + ",north=" + bitToConditionString(i, 0b01000)
//							+ ",south=" + bitToConditionString(i, 0b00100) + ",waterlogged="
//							+ bitToConditionString(i, 0b00010) + ",west=" + bitToConditionString(i, 0b00001);
//					ResourceLocation loc = Compendium.modLoc("extra/fence/side/" + b);
//					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("fence", "side", mb.name,
//							b.toLowerCase());
//					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);
//
//					if (b.contains("sheet")) {
//
//						MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();
//
//						mmb.add(s -> s.getValue(FenceBlock.EAST),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y90, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.NORTH),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y0, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.SOUTH),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y180, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.WEST),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y270, Pair.of("0", texture)));
//
//						event.getModels().put(m, mmb.build());
//
//					} else {
//						MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();
//
//						mmb.add(s -> s.getValue(FenceBlock.EAST),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y90, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.NORTH),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y0, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.SOUTH),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y180, Pair.of("0", texture)));
//
//						mmb.add(s -> s.getValue(FenceBlock.WEST),
//								basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
//										BlockModelRotation.X0_Y270, Pair.of("0", texture)));
//
//						event.getModels().put(m, mmb.build());
//					}
//				}
//			}

			for (String b : StyleData.SHINGLES_SHINGLES.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("shingles_slanted", "shingles",
						mb.name, b.toLowerCase());
				MultiPartBakedModel.Builder mmAll = new MultiPartBakedModel.Builder();
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					MultiPartBakedModel.Builder mmDir = new MultiPartBakedModel.Builder();
					for (Half half : Half.values()) {
						MultiPartBakedModel.Builder mmHalf = new MultiPartBakedModel.Builder();
						for (StairsShape shape : StairsShape.values()) {
							MultiPartBakedModel.Builder mmShape = new MultiPartBakedModel.Builder();

							ResourceLocation loc = Compendium.modLoc("extra/shingles_slanted/shingles/straight/" + b);
							if (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT)
								loc = Compendium.modLoc("extra/shingles_slanted/shingles/inner_corner/" + b);
							else if (shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT)
								loc = Compendium.modLoc("extra/shingles_slanted/shingles/outer_corner/" + b);

							int h = half == Half.BOTTOM ? 0 : 180;
							int hy = half == Half.BOTTOM ? 0 : 180;

							if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT)
								hy += 90;

							mmShape.add(s -> s.getValue(StairBlock.WATERLOGGED),
									basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
											BlockModelRotation.by(h, (int) dir.toYRot() + hy), Pair.of("0", texture)));

							mmShape.add(s -> !s.getValue(StairBlock.WATERLOGGED),
									basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
											BlockModelRotation.by(h, (int) dir.toYRot() + hy), Pair.of("0", texture)));

							mmHalf.add(s -> s.getValue(StairBlock.SHAPE) == shape, mmShape.build());
						}
						mmDir.add(s -> s.getValue(StairBlock.HALF) == half, mmHalf.build());
					}
					mmAll.add(s -> s.getValue(StairBlock.FACING) == dir, mmDir.build());
				}
				event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmAll.build());
//				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase())).texture("0", planksTexture);
//
//				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/shingles/" + b.toLowerCase()
//						+ "_inventory", modLoc("block/bases/shingles_slanted/shingles/" + b.toLowerCase()))
//						.texture("0", planksTexture);
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/outer_corner/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/shingles/outer_corner/" + b.toLowerCase()))
//						.texture("0", planksTexture);
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_slanted/shingles/inner_corner/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/shingles/inner_corner/" + b.toLowerCase()))
//						.texture("0", planksTexture);
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/inventory/" + b.toLowerCase()
//								+ "_inventory",
//						modLoc("block/bases/shingles_cap_slanted/shingles/no_top/straight/" + b.toLowerCase()))
//						.texture("0", planksTexture);
//
//				for (String s : top) {
//					// caps
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "all/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "all/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "straight/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "straight/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "tri/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "tri/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "none/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "none/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "end/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "end/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/shingles/" + s + "corner/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/shingles/" + s + "corner/" + b.toLowerCase()))
//							.texture("0", planksTexture);
//				}
			}

			for (String b : StyleData.SUPPORT_SHINGLES.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("shingles_slanted", "support",
						mb.name, b.toLowerCase());
				MultiPartBakedModel.Builder mmAll = new MultiPartBakedModel.Builder();
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					MultiPartBakedModel.Builder mmDir = new MultiPartBakedModel.Builder();
					for (Half half : Half.values()) {
						MultiPartBakedModel.Builder mmHalf = new MultiPartBakedModel.Builder();
						for (StairsShape shape : StairsShape.values()) {
							MultiPartBakedModel.Builder mmShape = new MultiPartBakedModel.Builder();

							ResourceLocation loc = Compendium.modLoc("extra/shingles_slanted/support/straight/" + b);
							if (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT)
								loc = Compendium.modLoc("extra/shingles_slanted/support/inner_corner/" + b);
							else if (shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT)
								loc = Compendium.modLoc("extra/shingles_slanted/support/outer_corner/" + b);

							int hx = half == Half.BOTTOM ? 0 : 180;
							int hy = half == Half.BOTTOM ? 0 : 180;

							if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT)
								hy += 90;

							ResourceLocation log = Compendium
									.modLoc("block/material/wood/" + mb.name + "/logs/small_logs");
							ResourceLocation log_cap = Compendium
									.modLoc("block/material/wood/" + mb.name + "/logs/extra_caps");

							mmShape.add(s -> s.getValue(StairBlock.WATERLOGGED),
									basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
											BlockModelRotation.by(hx, (int) dir.toYRot() + hy), Pair.of("0", log),
											Pair.of("1", log_cap)));

							mmShape.add(s -> !s.getValue(StairBlock.WATERLOGGED),
									basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
											BlockModelRotation.by(hx, (int) dir.toYRot() + hy), Pair.of("0", log),
											Pair.of("1", log_cap)));

							mmHalf.add(s -> s.getValue(StairBlock.SHAPE) == shape, mmShape.build());
						}
						mmDir.add(s -> s.getValue(StairBlock.HALF) == half, mmHalf.build());
					}
					mmAll.add(s -> s.getValue(StairBlock.FACING) == dir, mmDir.build());
				}
				event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmAll.build());
			}
//				String[] top = new String[] { "no_top/", "top/" };
//
//				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
//						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//				withExistingParent("block/material/wood/" + mb.name + "/shingles_slanted/support/" + b.toLowerCase()
//						+ "_inventory", modLoc("block/bases/shingles_slanted/support/" + b.toLowerCase()))
//						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_slanted/support/outer_corner/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/support/outer_corner/" + b.toLowerCase()))
//						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_slanted/support/inner_corner/" + b.toLowerCase(),
//						modLoc("block/bases/shingles_slanted/support/inner_corner/" + b.toLowerCase()))
//						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/inventory/" + b.toLowerCase()
//								+ "_inventory",
//						modLoc("block/bases/shingles_cap_slanted/support/no_top/straight/" + b.toLowerCase()))
//						.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//						.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//				for (String s : top) {
//					// caps
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "all/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "all/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "straight/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "straight/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "tri/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "tri/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "none/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "none/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "end/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "end/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/shingles_cap_slanted/support/" + s + "corner/"
//									+ b.toLowerCase(),
//							modLoc("block/bases/shingles_cap_slanted/support/" + s + "corner/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/small_logs"))
//							.texture("1", modLoc("block/material/wood/" + mb.name + "/logs/small_logs_top"));
//				}
//			}
		}
	}

	@SafeVarargs
	private static BakedModel doBed(ModifyBakingResult event, _MaterialBase mb, String multiPart, BedSideType sideType,
			BedPart part, Direction dir, int occupied, String style, Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(FancyBedBlock.SIDE) == BedSideType.SINGLE,
				doBedPartType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.SIDE) == BedSideType.CENTER,
				doBedPartType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.SIDE) == BedSideType.LEFT,
				doBedPartType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.SIDE) == BedSideType.RIGHT,
				doBedPartType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		return mmb.build();
	}

	private static BakedModel doBedPartType(ModifyBakingResult event, _MaterialBase mb, String multiPart,
			BedSideType sideType, BedPart part, Direction dir, int occupied, String style,
			Pair<String, ResourceLocation>[] textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(FancyBedBlock.PART) == BedPart.HEAD,
				doBedOccupiedType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.PART) == BedPart.FOOT,
				doBedOccupiedType(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		return mmb.build();
	}

	private static BakedModel doBedOccupiedType(ModifyBakingResult event, _MaterialBase mb, String multiPart,
			BedSideType sideType, BedPart part, Direction dir, int occupied, String style,
			Pair<String, ResourceLocation>[] textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == (occupied != 0),
				doBedDirectionStep(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == (occupied == 0),
				doBedDirectionStep(event, mb, multiPart, sideType, part, dir, occupied, style, textures));

		return mmb.build();
	}

	@SafeVarargs
	private static BakedModel doBedDirectionStep(ModifyBakingResult event, _MaterialBase mb, String multiPart,
			BedSideType sideType, BedPart part, Direction dir, int occupied, String style,
			Pair<String, ResourceLocation>... textures) {
		String sideString = sideType.toString().toLowerCase();
		String partString = part.toString().toLowerCase();
//		String dirString = dir.toString().toLowerCase();
		String occupiedString = occupied != 0 ? "occupied" : "unoccupied";

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		ResourceLocation loc = Compendium.modLoc(
				"extra/bed/" + occupiedString + "/" + sideString + "/" + partString + "/" + multiPart + "/" + style);
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", multiPart, mb.name,
				style.toLowerCase());

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.WEST, basicModelManyTexture(event, mb, loc,
				new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.SOUTH, basicModelManyTexture(event, mb, loc,
				new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.NORTH, basicModelManyTexture(event, mb, loc,
				new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.EAST, basicModelManyTexture(event, mb, loc,
				new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		return mmb.build();
	}

	public static String bitToConditionString(int i, int b) {
		if ((i & b) == 0)
			return "false";
		return "true";
	}

	@SafeVarargs
	private static void doTableLeg(ModifyBakingResult event, _MaterialBase mb, String b, String table,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation(table, "legs", mb.name,
				b.toLowerCase());
		ResourceLocation loc = Compendium.modLoc("extra/" + table + "/legs/" + b);

		for (int i = 0b000000000; i < 0b111111111; i++) {
			String v = "e=" + bitToConditionString(i, 0b100000000) + ",n=" + bitToConditionString(i, 0b010000000)
					+ ",ne=" + bitToConditionString(i, 0b001000000) + ",nw=" + bitToConditionString(i, 0b000100000)
					+ ",s=" + bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
					+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w=" + bitToConditionString(i, 0b000000001);

			MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

			mmb.add(s -> !s.getValue(TableBase.E) && !s.getValue(TableBase.NE) && !s.getValue(TableBase.N),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y90, textures));

			mmb.add(s -> !s.getValue(TableBase.E) && !s.getValue(TableBase.SE) && !s.getValue(TableBase.S),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y180, textures));

			mmb.add(s -> !s.getValue(TableBase.W) && !s.getValue(TableBase.SW) && !s.getValue(TableBase.S),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y270, textures));

			mmb.add(s -> !s.getValue(TableBase.W) && !s.getValue(TableBase.NW) && !s.getValue(TableBase.N),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y0, textures));

			mmb.add(s -> s.getValue(TableBase.N) && !s.getValue(TableBase.NW) && s.getValue(TableBase.W),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y0, textures));

			mmb.add(s -> s.getValue(TableBase.N) && !s.getValue(TableBase.NE) && s.getValue(TableBase.E),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y90, textures));

			mmb.add(s -> s.getValue(TableBase.S) && !s.getValue(TableBase.SE) && s.getValue(TableBase.E),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y180, textures));

			mmb.add(s -> !s.getValue(TableBase.SW) && s.getValue(TableBase.W) && s.getValue(TableBase.S),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y270, textures));

			mmb.add(s -> s.getValue(TableBase.NW) && !s.getValue(TableBase.W) && !s.getValue(TableBase.N),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y0, textures));

			mmb.add(s -> s.getValue(TableBase.NE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.N),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y90, textures));

			mmb.add(s -> s.getValue(TableBase.SE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.S),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y180, textures));

			mmb.add(s -> s.getValue(TableBase.SW) && !s.getValue(TableBase.W) && !s.getValue(TableBase.S),
					basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
							BlockModelRotation.X0_Y270, textures));

			event.getModels().put(new ModelResourceLocation(modelLoc, v), mmb.build());
		}
	}

	@SafeVarargs
	private static void doChair(ModifyBakingResult event, _MaterialBase mb, String part, String b,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("chair", part, mb.name,
				b.toLowerCase());
		ResourceLocation loc = Compendium.modLoc("extra/" + "chair/" + part + "/" + b);

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();
		ModelResourceLocation w = new ModelResourceLocation(modelLoc, "");
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.WEST,
				basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y90, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.NORTH,
				basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y180, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.EAST,
				basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y270, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.SOUTH,
				basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y0, textures));
		event.getModels().put(w, mmb.build());

		ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("chair", part, mb.name,
				b.toLowerCase(), "_inventory");
		ResourceLocation loc_inv = Compendium.modLoc("extra/" + "chair/" + part + "/" + b + "_inventory");

		ModelResourceLocation w_inv = new ModelResourceLocation(modelLoc_inv, "");

		event.getModels().put(w_inv,
				basicModelManyTexture(event, mb, loc_inv, w_inv, BlockModelRotation.X0_Y90, textures));
	}

	private static BakedModel basicModelAllTexture(ModifyBakingResult event, _MaterialBase mb,
			ResourceLocation blockTexture, ResourceLocation location, ModelResourceLocation modelLocation,
			ModelState state, String textureName) {
//		ResourceLocation rc = Compendium.modLoc("extra/" + modelExtraName);

//		ResourceLocation output_location = ClientUtil.createMaterialStyleLayerLocation(modelBase, modelLayer, mb.name,
//				style.toLowerCase());

		BlockModel frame_model = (BlockModel) event.getModelBakery().getModel(location);

		return buildModel(event, frame_model, modelLocation, state, Pair.of(textureName, blockTexture));
	}

	@SafeVarargs
	private static BakedModel basicModelManyTexture(ModifyBakingResult event, _MaterialBase mb,
			ResourceLocation location, ModelResourceLocation modelLocation, ModelState state,
			Pair<String, ResourceLocation>... textures) {
//		ResourceLocation rc = Compendium.modLoc("extra/" + modelExtraName);

//		ResourceLocation output_location = ClientUtil.createMaterialStyleLayerLocation(modelBase, modelLayer, mb.name,
//				style.toLowerCase());

		BlockModel frame_model = (BlockModel) event.getModelBakery().getModel(location);

		return buildModel(event, frame_model, modelLocation, state, textures);
	}

	@SafeVarargs
	public static BakedModel buildModel(ModifyBakingResult event, BlockModel model, ModelResourceLocation modelResource,
			ModelState state, Pair<String, ResourceLocation>... textures) {
//		Map<ModelResourceLocation, BakedModel> models = event.getModels();

//		ModelResourceLocation block_model = new ModelResourceLocation(output_location, variant);

		for (Pair<String, ResourceLocation> p : textures) {

			if (model.textureMap.containsKey(p.getFirst())) {
				Either<Material, String> e = model.textureMap.get(p.getFirst());
				if (e.left().isPresent()) {
					ResourceLocation rl = e.left().get().atlasLocation();

					model.textureMap.put(p.getFirst(), Either.left(new Material(rl, p.getSecond())));
				} else {
					throw new MissingResourceException("missing atlas location, texture likely incorrect",
							"CompendiumClient::buildModel", "");
				}
			} else {
				Compendium.LOGGER.error("textureMap does not contain key: " + p.getFirst() + " - for model: "
						+ modelResource.toString());
			}
		}

		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(),
				modelResource);

		model.resolveParents(i -> baker.getModel(i));

		return model.bake(baker, event.getTextureGetter(), state);
	}

}
