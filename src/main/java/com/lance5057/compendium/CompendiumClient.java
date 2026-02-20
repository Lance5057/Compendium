package com.lance5057.compendium;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRenderer;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerScreen;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRenderer;
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
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
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

//	@SubscribeEvent
//	public static void RegisterExtraModels(ModelEvent.RegisterAdditional event) {
//		Map<ResourceLocation, Resource> rrs = Minecraft.getInstance().getResourceManager().listResources("models/extra",
//				(p_215600_) -> {
//					return p_215600_.getPath().endsWith(".json");
//				});
//
////		rrs.putAll(Minecraft.getInstance().getResourceManager().listResources("models/block/material", (p_215600_) -> {
////			return p_215600_.getPath().endsWith(".json");
////		}));
//
//		rrs.forEach((rl, r) -> {
//			String s = rl.toString();
//
//			s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));
//
//			ModelResourceLocation rl2 = ModelResourceLocation
//					.standalone(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s));
//
//			event.register(rl2);
//		});
//	}

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
//

//
//		ModelResourceLocation ml = ModelResourceLocation.inventory(rc);
//		models.put(ml, buildModel(event, ml, texture));
//
//		ModelResourceLocation ml2 = new ModelResourceLocation(rc, "");
//		models.put(ml2, buildModel(event, ml2, texture));

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
		if (mb.getType() == MATERIAL_TYPES.METAL) {

			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
						b.toLowerCase());

				ResourceLocation texture = Compendium
						.modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase());

				event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb, texture,
						loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
			});
		}
	}

	public static void doGlass(ModifyBakingResult event, _MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.GLASS) {
			StyleData.WINDOW_GLASS.getTypes().forEach(b -> {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_glass");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "glass", mb.name,
						b.toLowerCase());

				if (mb.name.equalsIgnoreCase("clear")) {
					ResourceLocation texture = TagUtil.mcLoc("block/glass");

					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
				} else {
					ResourceLocation texture = TagUtil.mcLoc("block/" + mb.name + "_glass");

					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
				}
			});
		}
	}

	public static void doTextile(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialTextile mt) {

			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name);
			if (mt.specialLocations != null) {
				if (mt.specialLocations.textures != null)
					if (mt.specialLocations.textures.blockLocation != null)
						texture = ResourceLocation.fromNamespaceAndPath(mb.namespace,
								mt.specialLocations.textures.blockLocation);
			}

			for (String b : StyleData.TABLE_CLOTH.getTypes()) {
				for (int i = 0b000000000; i < 0b111111111; i++) {
					String v = "e=" + bitToConditionString(i, 0b100000000) + ",n="
							+ bitToConditionString(i, 0b010000000) + ",ne=" + bitToConditionString(i, 0b001000000)
							+ ",nw=" + bitToConditionString(i, 0b000100000) + ",s="
							+ bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
							+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w="
							+ bitToConditionString(i, 0b000000001);

					ResourceLocation loc = Compendium.modLoc("extra/clothed_table/cloth/" + b.toLowerCase());
					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("clothed_table", "cloth",
							mb.name, b.toLowerCase());
					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);

					if (b.contains("angled")) {

						event.getModels().put(m, basicModelManyTexture(event, mb, loc, m, BlockModelRotation.X0_Y0,
								Pair.of("0", texture),Pair.of("1", Compendium.modLoc("block/material/textile/" + mb.name+"/diagonal_half"))));
					} else
						event.getModels().put(m,
								basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
				}
			}

//			for (String b : StyleData.BED_MATTRESS.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/unoccupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/mattress/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/occupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/mattress/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/mattress/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//
//					}
//				}
//
//				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/mattress/" + b.toLowerCase(),
//						modLoc("block/furniture/bed/inventory/mattress/" + b.toLowerCase())).texture("0", blockTexture);
//			}
//
//			for (String b : StyleData.BED_SHEET.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/unoccupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/sheet/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/occupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/sheet/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/sheet/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//					}
//				}
//
//				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/sheet/" + b.toLowerCase(),
//						modLoc("block/furniture/bed/inventory/sheet/" + b.toLowerCase())).texture("0", blockTexture);
//			}
//
//			for (String b : StyleData.BED_PILLOW.getTypes()) {
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/unoccupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/pillow/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//
//						withExistingParent(
//								"block/material/textile/" + mb.name + "/bed/occupied/"
//										+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//										+ "/pillow/" + b.toLowerCase(),
//								modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
//										+ part.toString().toLowerCase() + "/pillow/" + b.toLowerCase()))
//								.texture("0", blockTexture);
//					}
//				}
//
//				withExistingParent("block/material/textile/" + mb.name + "/bed/inventory/pillow/" + b.toLowerCase(),
//						modLoc("block/furniture/bed/inventory/pillow/" + b.toLowerCase())).texture("0", blockTexture);
//			}
//
//			for (String b : StyleData.BED_BLANKET.getTypes()) {
//				if (b.equals("llama"))
//					withExistingParent(
//							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
//							modLoc("block/furniture/bed/inventory/blanket/" + b.toLowerCase()))
//							.texture("2", modLoc(mb.blockFolder() + "llama"))
//							.texture("3", modLoc(mb.blockFolder() + "llama_trim"));
//
//				else if (b.equals("glazed"))
//					withExistingParent(
//							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
//							modLoc("block/furniture/bed/inventory/blanket/basic"))
//							.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));
//
//				else
//					withExistingParent(
//							"block/material/textile/" + mb.name + "/bed/inventory/blanket/" + b.toLowerCase(),
//							modLoc("block/furniture/bed/inventory/blanket/" + b.toLowerCase()))
//							.texture("0", blockTexture);
//
//				for (BedSideType sideType : BedSideType.values()) {
//					for (BedPart part : BedPart.values()) {
//						if (b.equals("llama")) {
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
//									.texture("2", modLoc(mb.blockFolder() + "llama"))
//									.texture("3", modLoc(mb.blockFolder() + "llama_trim"));
//
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
//									.texture("2", modLoc(mb.blockFolder() + "llama"))
//									.texture("3", modLoc(mb.blockFolder() + "llama_trim"));
//
//						} else if (b.equals("glazed")) {
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/basic"))
//									.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));
//
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/basic"))
//									.texture("0", modLoc(mb.blockFolder() + "woolly_glazed"));
//
//						} else {
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
//									.texture("0", blockTexture);
//
//							withExistingParent(
//									"block/material/textile/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/blanket/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/occupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/blanket/" + b.toLowerCase()))
//									.texture("0", blockTexture);
//
//						}
//					}
//				}
//			}
//
//			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled",
//					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
//			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_inventory",
//					modLoc("block/furniture/table/cloth/angled")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
//
//			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_short",
//					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
//			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_short_inventory",
//					modLoc("block/furniture/table/cloth/angled_short")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
//
//			withExistingParent("block/material/textile/" + mb.name + "/table/cloth/angled_long",
//					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
//			withExistingParent("block/material/textile/" + mb.name + "/clothed_table/cloth/angled_long_inventory",
//					modLoc("block/furniture/table/cloth/angled_long")).texture("0", mcLoc("block/" + mb.name))
//					.texture("0", blockTexture).texture("1", ResourceLocation.fromNamespaceAndPath("compendium",
//							"block/material/textile/" + mb.name + "/diagonal_half"))
//					.renderType("cutout");
		}

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

			for (String b : StyleData.TABLE_LEGS.getTypes()) {
				doTableLeg(event, mb, b, "table", Pair.of("0", texture));
				doTableLeg(event, mb, b, "clothed_table", Pair.of("0", texture));
//				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase(),
//						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg")).texture("0", planksTexture);
//
//				withExistingParent("block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase(),
//						modLoc("block/furniture/table/legs/side/" + b.toLowerCase())).texture("0", planksTexture);
//
//				withExistingParent("block/material/wood/" + mb.name + "/table/legs/" + b.toLowerCase() + "_inventory",
//						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
//						.texture("0", planksTexture);
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/clothed_table/legs/" + b.toLowerCase() + "_inventory",
//						modLoc("block/furniture/table/legs/" + b.toLowerCase() + "_leg_inventory"))
//						.texture("0", planksTexture);
//
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/table/legs/side/" + b.toLowerCase() + "_inventory",
//						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
//						.texture("0", planksTexture);
//				withExistingParent(
//						"block/material/wood/" + mb.name + "/clothed_table/legs/side/" + b.toLowerCase() + "_inventory",
//						modLoc("block/furniture/table/legs/side/" + b.toLowerCase() + "_inventory"))
//						.texture("0", planksTexture);
			}

			for (String b : StyleData.TABLE_TOP.getTypes()) {
				for (int i = 0b000000000; i < 0b111111111; i++) {
					String v = "e=" + bitToConditionString(i, 0b100000000) + ",n="
							+ bitToConditionString(i, 0b010000000) + ",ne=" + bitToConditionString(i, 0b001000000)
							+ ",nw=" + bitToConditionString(i, 0b000100000) + ",s="
							+ bitToConditionString(i, 0b000001000) + ",se=" + bitToConditionString(i, 0b000000100)
							+ ",sw=" + bitToConditionString(i, 0b000000010) + ",w="
							+ bitToConditionString(i, 0b000000001);

					ResourceLocation loc = Compendium.modLoc("extra/table/top/" + b);
					ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("table", "top", mb.name,
							b.toLowerCase());
					ModelResourceLocation m = new ModelResourceLocation(modelLoc, v);

					ResourceLocation loc_clothed = Compendium.modLoc("extra/clothed_table/top/" + b);
					ResourceLocation modelLoc_clothed = ClientUtil.createMaterialStyleLayerLocation("clothed_table",
							"top", mb.name, b.toLowerCase());
					ModelResourceLocation m_clothed = new ModelResourceLocation(modelLoc_clothed, v);

					if (b.equals("smooth")) {
						event.getModels().put(m,
								basicModelAllTexture(event, mb,
										Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"), loc, m,
										BlockModelRotation.X0_Y0, "0"));
						event.getModels().put(m_clothed,
								basicModelAllTexture(event, mb,
										Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"),
										loc_clothed, m_clothed, BlockModelRotation.X0_Y0, "0"));
					} else {
						event.getModels().put(m,
								basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
						event.getModels().put(m_clothed, basicModelAllTexture(event, mb, texture, loc_clothed,
								m_clothed, BlockModelRotation.X0_Y0, "0"));
					}
				}
			}
//
//			for (String b : StyleData.BED_FRAME.getTypes()) {
//				for (BedSideType sideType : BedSideType.values())
//					for (BedPart part : BedPart.values()) {
//						if (b.equals("live_edge")) {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
//									.texture("1", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
//									.texture("1", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
//									.texture("0", modLoc("block/material/wood/" + mb.name + "/logs/log_split_side"))
//									.texture("1", planksTexture);
//						} else if (b.equals("weave")) {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//						} else if (b.equals("slats")) {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/slats"));
//						} else {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/frame/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/frame/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//						}
//					}
//			}
//
//			for (String b : StyleData.BED_BASE.getTypes()) {
//				for (BedSideType sideType : BedSideType.values())
//					for (BedPart part : BedPart.values()) {
//						if (b.equals("weave")) {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/base/" + b.toLowerCase()))
//									.texture("0", planksTexture)
//									.texture("1", modLoc("block/material/wood/" + mb.name + "/weave"));
//						} else {
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/unoccupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/occupied/"
//											+ sideType.toString().toLowerCase() + "/" + part.toString().toLowerCase()
//											+ "/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/unoccupied/" + sideType.toString().toLowerCase() + "/"
//											+ part.toString().toLowerCase() + "/base/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//
//							withExistingParent(
//									"block/material/wood/" + mb.name + "/bed/inventory/base/" + b.toLowerCase(),
//									modLoc("block/furniture/bed/inventory/base/" + b.toLowerCase()))
//									.texture("0", planksTexture);
//						}
//					}
//			}
//
//			for (String b : StyleData.FENCE_POST.getTypes()) {
//				withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase(),
//						modLoc("block/bases/fence/post/" + b.toLowerCase())).texture("0", planksTexture);
//				withExistingParent("block/material/wood/" + mb.name + "/fence/post/" + b.toLowerCase() + "_inventory",
//						modLoc("block/bases/fence/post/" + b.toLowerCase() + "_inventory")).texture("0", planksTexture);
//			}
//
//			for (String b : StyleData.FENCE_SIDE.getTypes()) {
//				if (b.contains("sheet")) {
//					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
//							modLoc("block/bases/fence/side/" + b.toLowerCase()))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase() + "_inventory",
//							modLoc("block/bases/fence/side/" + b.toLowerCase() + "_inventory"))
//							.texture("0", modLoc("block/material/wood/" + mb.name + "/planks/sheet"));
//				} else {
//					withExistingParent("block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase(),
//							modLoc("block/bases/fence/side/" + b.toLowerCase())).texture("0", planksTexture);
//
//					withExistingParent(
//							"block/material/wood/" + mb.name + "/fence/side/" + b.toLowerCase() + "_inventory",
//							modLoc("block/bases/fence/side/" + b.toLowerCase() + "_inventory"))
//							.texture("0", planksTexture);
//				}
//			}
//
//			for (String b : StyleData.SHINGLES_SHINGLES.getTypes()) {
//				String[] top = new String[] { "no_top/", "top/" };
//
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
//			}
//
//			for (String b : StyleData.SUPPORT_SHINGLES.getTypes()) {
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

//		basicModelManyTexture(event, mb, loc,
//				new ModelResourceLocation(modelLoc,
//						"e=false,n=false,ne=false,nw=false,s=false,se=false,sw=false,w=false"),
//				BlockModelRotation.X0_Y90, textures);
//		basicModelManyTexture(event, mb, loc,
//				new ModelResourceLocation(modelLoc,
//						"e=false,n=false,ne=false,nw=false,s=false,se=false,sw=false,w=false"),
//				BlockModelRotation.X0_Y180, textures);
//		basicModelManyTexture(event, mb, loc,
//				new ModelResourceLocation(modelLoc,
//						"e=false,n=false,ne=false,nw=false,s=false,se=false,sw=false,w=false"),
//				BlockModelRotation.X0_Y270, textures);
//		basicModelManyTexture(event, mb, loc,
//				new ModelResourceLocation(modelLoc,
//						"e=false,n=false,ne=false,nw=false,s=false,se=false,sw=false,w=false"),
//				BlockModelRotation.X0_Y0, textures);

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

		ModelResourceLocation w = new ModelResourceLocation(modelLoc, "facing=west");
		event.getModels().put(w, basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y90, textures));
		ModelResourceLocation n = new ModelResourceLocation(modelLoc, "facing=north");
		event.getModels().put(n, basicModelManyTexture(event, mb, loc, n, BlockModelRotation.X0_Y180, textures));
		ModelResourceLocation e = new ModelResourceLocation(modelLoc, "facing=east");
		event.getModels().put(e, basicModelManyTexture(event, mb, loc, e, BlockModelRotation.X0_Y270, textures));
		ModelResourceLocation s = new ModelResourceLocation(modelLoc, "facing=south");
		event.getModels().put(s, basicModelManyTexture(event, mb, loc, s, BlockModelRotation.X0_Y0, textures));
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

//	@SafeVarargs
//	public static void buildModel(ModifyBakingResult event, BlockModel model, ResourceLocation output_location,
//			String variant, ModelState state, Pair<String, ResourceLocation>... textures) {
//		Map<ModelResourceLocation, BakedModel> models = event.getModels();
//
//		ModelResourceLocation block_model = new ModelResourceLocation(output_location, variant);
//
//		for (Pair<String, ResourceLocation> p : textures) {
//
//			if (model.textureMap.containsKey(p.getFirst())) {
//				Either<Material, String> e = model.textureMap.get(p.getFirst());
//				if (e.left().isPresent()) {
//					ResourceLocation rl = e.left().get().atlasLocation();
//
//					model.textureMap.put(p.getFirst(), Either.left(new Material(rl, p.getSecond())));
//				} else {
//					throw new MissingResourceException("missing atlas location, texture likely incorrect",
//							"CompendiumClient::buildModel", "");
//				}
//			} else {
//				Compendium.LOGGER.error(
//						"textureMap does not contain key: " + p.getFirst() + " - for model: " + block_model.toString());
//			}
//		}
//
//		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(),
//				block_model);
//
//		model.resolveParents(i -> baker.getModel(i));
//
//		BakedModel bm = model.bake(baker, event.getTextureGetter(), state);
//
//		models.put(block_model, bm);
//	}

	@SafeVarargs
	public static BakedModel buildModel(ModifyBakingResult event, BlockModel model, ModelResourceLocation modelResource,
			ModelState state, Pair<String, ResourceLocation>... textures) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();

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
