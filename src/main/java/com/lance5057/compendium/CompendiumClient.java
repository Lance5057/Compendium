package com.lance5057.compendium;

import java.util.Comparator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.lance5057.compendium.blocks.RotatedPillarStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRenderer;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerScreen;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRenderer;
import com.lance5057.compendium.blocks.bed.BedSideType;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.shingles.slanted.cap.ShinglesCapSlanted;
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
import com.lance5057.compendium.index.material.base.MaterialGlass;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedScreen;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRenderer;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchScreen;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery.ModelBakerImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
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
//		BlockEntityRenderers.register(CompendiumBlockEntities.SCRAPPING_TABLE.get(), ScrappingTableRenderer::new);
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
	public static void RegisterExtraModels(ModelEvent.RegisterAdditional event) {
		Map<ResourceLocation, Resource> rrs = Minecraft.getInstance().getResourceManager()
				.listResources("models/recipes", (p_215600_) -> {
					return p_215600_.getPath().endsWith(".json");
				});

		rrs.forEach((rl, r) -> {
			String s = rl.toString();

			s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));

			ModelResourceLocation rl2 = ModelResourceLocation
					.standalone(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s));

			event.register(rl2);
		});
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

		buildStateModelBasic(event, models, "window_inventory");
		buildStateModelBasic(event, models, "window");

		buildStateModelVariant(event, models, "chair_inventory", "");

		buildStateModelVariant(event, models, "chair", "facing=south");
		buildStateModelRotated(event, models, "chair", "facing=east", BlockModelRotation.X0_Y270);
		buildStateModelRotated(event, models, "chair", "facing=north", BlockModelRotation.X0_Y180);
		buildStateModelRotated(event, models, "chair", "facing=west", BlockModelRotation.X0_Y90);

		buildStateModelVariant(event, models, "table_inventory", "");
		buildStateModelVariant(event, models, "clothed_table_inventory", "");

		for (BlockState state : CompendiumBlocks.TABLE.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);
			buildStateModelVariant(event, models, "table", v);
		}

		for (BlockState state : CompendiumBlocks.CLOTHED_TABLE.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);
			buildStateModelVariant(event, models, "clothed_table", v);
		}

		buildStateModelVariant(event, models, "fancy_fence_inventory", "");

		for (BlockState state : CompendiumBlocks.FANCY_FENCE.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);

			buildStateModelVariant(event, models, "fancy_fence", v);
		}

		buildStateModelVariant(event, models, "fancy_bed_inventory", "");

		for (BlockState state : CompendiumBlocks.FANCY_BED.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);

			buildStateModelVariant(event, models, "fancy_bed", v);
		}

		buildStateModelVariant(event, models, "shingles_slanted_inventory", "");

		for (BlockState state : CompendiumBlocks.SHINGLES_SLANTED.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);
			buildStateModelVariant(event, models, "shingles_slanted", v);
		}

		buildStateModelVariant(event, models, "shingles_cap_slanted_inventory", "");

		for (BlockState state : CompendiumBlocks.SHINGLES_CAP_SLANTED.get().getStateDefinition().getPossibleStates()) {
			Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

			String v = stateToString(propertyValues);
			buildStateModelVariant(event, models, "shingles_cap_slanted", v);
		}

	}

	public static String stateToString(Map<Property<?>, Comparable<?>> s) {
		SortedMap<Property<?>, Comparable<?>> setStates = Maps.newTreeMap(Comparator.comparing(Property::getName));
		setStates.putAll(s);
		StringBuilder ret = new StringBuilder();
		for (Map.Entry<Property<?>, Comparable<?>> entry : setStates.entrySet()) {
			if (ret.length() > 0) {
				ret.append(',');
			}
			ret.append(entry.getKey().getName()).append('=')
					.append(((Property) entry.getKey()).getName(entry.getValue()));
		}
		return ret.toString();
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
		buildStateModelVariant(event, models, "extra", w, variant);
	}

	private static void buildStateModelVariant(ModifyBakingResult event, Map<ModelResourceLocation, BakedModel> models,
			String folder, String w, String variant) {
		ResourceLocation rc = Compendium.modLoc(folder + "/" + w);

		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(w), variant);
		BlockModel um = (BlockModel) event.getModelBakery().getModel(rc);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml, bm);

//		Compendium.LOGGER.debug(ml.toString());
	}

	private static void buildStateModelVariantAltLocation(ModifyBakingResult event,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation fromLocation, String toLocation,
			String variant) {
		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(toLocation), variant);
		BlockModel um = (BlockModel) event.getModelBakery().getModel(fromLocation);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml, bm);

//		Compendium.LOGGER.debug(ml.toString());
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
	}

	private static void doMetal(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialMetal mm) {

			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
						b.toLowerCase());

				ResourceLocation texture = Compendium
						.modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase());

				event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb, texture,
						loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

				ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
						b.toLowerCase(), "_inventory");
				ResourceLocation loc_inv = Compendium.modLoc("extra/window/trim/" + b + "_inventory");

//				Compendium.LOGGER.debug(modelLoc_inv.toString());
//				Compendium.LOGGER.debug(loc_inv.toString());

				event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
						basicModelAllTexture(event, mb, texture, loc_inv, new ModelResourceLocation(modelLoc_inv, ""),
								BlockModelRotation.X0_Y0, "all"));
			});
		}
	}

	public static void doGlass(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialGlass mg) {
			StyleData.WINDOW_GLASS.getTypes().forEach(b -> {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_glass");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("window", "glass", mb.name,
						b.toLowerCase());

				if (mb.name.equalsIgnoreCase("clear")) {
					ResourceLocation texture = TagUtil.mcLoc("block/glass");
					if (!b.equals("basic")) {
						texture = TagUtil.modLoc("block/material/glass/" + mb.name + "/" + b);
					}

					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

					ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("window", "glass",
							mb.name, b.toLowerCase(), "_inventory");
					ResourceLocation loc_inv = Compendium.modLoc("extra/window/glass/" + b + "_inventory");

//					Compendium.LOGGER.debug(modelLoc_inv.toString());
//					Compendium.LOGGER.debug(loc_inv.toString());

					event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
							basicModelAllTexture(event, mb, texture, loc_inv,
									new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "all"));
				} else {
					ResourceLocation texture = TagUtil.mcLoc("block/" + mb.name + "_glass");
					if (!b.equals("basic")) {
						texture = TagUtil.modLoc("block/material/glass/" + mb.name + "/" + b);
					}

					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event, mb,
							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

					ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("window", "glass",
							mb.name, b.toLowerCase(), "_inventory");
					ResourceLocation loc_inv = Compendium.modLoc("extra/window/glass/" + b + "_inventory");

//					Compendium.LOGGER.debug(modelLoc_inv.toString());
//					Compendium.LOGGER.debug(loc_inv.toString());

					event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
							basicModelAllTexture(event, mb, texture, loc_inv,
									new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "all"));
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
				ResourceLocation loc = Compendium.modLoc("extra/clothed_table/cloth/" + b.toLowerCase());
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("clothed_table", "cloth",
						mb.name, b.toLowerCase());
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

				ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("clothed_table", "cloth",
						mb.name, b.toLowerCase(), "_inventory");
				ResourceLocation loc_inv = Compendium.modLoc("extra/clothed_table/cloth/" + b + "_inventory");

				if (b.contains("angled")) {

					event.getModels().put(m, basicModelManyTexture(event, mb, loc, m, BlockModelRotation.X0_Y0,
							Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/textile/" + mb.name + "/diagonal_half"))));

					event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""), basicModelManyTexture(event, mb,
							loc_inv, new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0,
							Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/textile/" + mb.name + "/diagonal_half"))));
				} else {
					event.getModels().put(m,
							basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));

					event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
							basicModelAllTexture(event, mb, texture, loc_inv,
									new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "0"));
				}

			}

			for (String b : StyleData.BED_MATTRESS.getTypes()) {

				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "mattress", mb.name,
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

							ResourceLocation loc = Compendium.modLoc("extra/bed/" + occupiedString + "/" + sideString
									+ "/" + partString + "/mattress/" + b);

							boolean bo = (occupied != 0 ? true : false);

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									doBed(event, mb, loc, m, Pair.of("0", texture)));

						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/mattress/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}

			for (String b : StyleData.BED_SHEET.getTypes()) {

				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "sheet", mb.name,
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

							ResourceLocation loc = Compendium.modLoc("extra/bed/" + occupiedString + "/" + sideString
									+ "/" + partString + "/sheet/" + b);

							boolean bo = (occupied != 0 ? true : false);

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									doBed(event, mb, loc, m, Pair.of("0", texture)));

						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/sheet/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}

			for (String b : StyleData.BED_PILLOW.getTypes()) {

				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "pillow", mb.name,
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

							ResourceLocation loc = Compendium.modLoc("extra/bed/" + occupiedString + "/" + sideString
									+ "/" + partString + "/pillow/" + b);

							boolean bo = (occupied != 0 ? true : false);

							mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
									doBed(event, mb, loc, m, Pair.of("0", texture)));

						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/pillow/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}

			for (String b : StyleData.BED_BLANKET.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "blanket", mb.name,
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

							ResourceLocation loc = Compendium.modLoc("extra/bed/" + occupiedString + "/" + sideString
									+ "/" + partString + "/blanket/" + b);

							boolean bo = (occupied != 0 ? true : false);

							if (b.equals("llama")) {
								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m,
												Pair.of("3", Compendium.modLoc(mb.blockFolder() + "llama_trim")),
												Pair.of("2", Compendium.modLoc(mb.blockFolder() + "llama"))));

							} else if (b.equals("glazed")) {

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo, doBed(event, mb, loc, m,
										Pair.of("0", Compendium.modLoc(mb.blockFolder() + "woolly_glazed"))));
							} else {

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture)));

							}

						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/blanket/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}
		}

	}

	public static void doWood(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialWood mw) {

			doStyleWood(event, mw);

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

				ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
						b.toLowerCase(), "_inventory");
				ResourceLocation loc_inv = Compendium.modLoc("extra/window/trim/" + b + "_inventory");
				event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
						basicModelAllTexture(event, mb, texture, loc_inv, new ModelResourceLocation(modelLoc_inv, ""),
								BlockModelRotation.X0_Y0, "all"));
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
			}

			for (String b : StyleData.TABLE_TOP.getTypes()) {

				ResourceLocation loc = Compendium.modLoc("extra/table/top/" + b);
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("table", "top", mb.name,
						b.toLowerCase());
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

				ResourceLocation loc_clothed = Compendium.modLoc("extra/clothed_table/top/" + b);
				ResourceLocation modelLoc_clothed = ClientUtil.createMaterialStyleLayerLocation("clothed_table", "top",
						mb.name, b.toLowerCase());
				ModelResourceLocation m_clothed = new ModelResourceLocation(modelLoc_clothed, "");

				if (b.equals("smooth")) {
//					basicModelManyTexture(event, mb, loc, w, BlockModelRotation.X0_Y90, textures));
					event.getModels().put(m, basicModelManyTexture(event, mb, loc, m, BlockModelRotation.X0_Y0,
							Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"))));
					event.getModels().put(m_clothed, basicModelManyTexture(event, mb, loc_clothed, m_clothed,
							BlockModelRotation.X0_Y0, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"))));

					ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"),
							"");
					ModelResourceLocation m_clothed_inventory = new ModelResourceLocation(
							modelLoc_clothed.withSuffix("_inventory"), "");

					event.getModels().put(m_inventory, basicModelManyTexture(event, mb, texture, m_inventory,
							BlockModelRotation.X0_Y0, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"))));
					event.getModels().put(m_clothed_inventory, basicModelManyTexture(event, mb, texture,
							m_clothed_inventory, BlockModelRotation.X0_Y0, Pair.of("0", texture),
							Pair.of("1", Compendium.modLoc("block/material/wood/" + mb.name + "/planks/sheet"))));
				} else {
					event.getModels().put(m,
							basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));
					event.getModels().put(m_clothed, basicModelAllTexture(event, mb, texture, loc_clothed, m_clothed,
							BlockModelRotation.X0_Y0, "0"));

					ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"),
							"");
					ModelResourceLocation m_clothed_inventory = new ModelResourceLocation(
							modelLoc_clothed.withSuffix("_inventory"), "");

					event.getModels().put(m_inventory, basicModelAllTexture(event, mb, texture,
							loc.withSuffix("_inventory"), m_inventory, BlockModelRotation.X0_Y0, "0"));
					event.getModels().put(m_clothed_inventory, basicModelAllTexture(event, mb, texture,
							loc_clothed.withSuffix("_inventory"), m_clothed_inventory, BlockModelRotation.X0_Y0, "0"));
				}

			}

			for (String b : StyleData.BED_FRAME.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "frame", mb.name,
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

							ResourceLocation loc = Compendium.modLoc("extra/bed/" + occupiedString + "/" + sideString
									+ "/" + partString + "/frame/" + b);

							boolean bo = (occupied != 0 ? true : false);

							if (b.equals("live_edge")) {

								ResourceLocation tex = Compendium
										.modLoc("block/material/wood/" + mb.name + "/logs/log_split_side");

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture), Pair.of("1", tex)));

							} else if (b.equals("weave")) {

								ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mb.name + "/weave");

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture), Pair.of("1", tex)));

							} else if (b.equals("slats")) {
								ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mb.name + "/slats");

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture), Pair.of("1", tex)));
							} else if (b.contains("ornate")) {
								ResourceLocation tex = Compendium
										.modLoc("block/material/wood/" + mb.name + "/windows/grill");

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture), Pair.of("1", tex)));
							} else {

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture)));

							}

						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/frame/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}

			for (String b : StyleData.BED_BASE.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("bed", "base", mb.name,
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
								ResourceLocation tex = Compendium.modLoc("block/material/wood/" + mb.name + "/weave");

								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture), Pair.of("1", tex)));
							} else {
								mmPart.add(s -> s.getValue(FancyBedBlock.OCCUPIED) == bo,
										doBed(event, mb, loc, m, Pair.of("0", texture)));
							}
						}
						mmSide.add(s -> s.getValue(FancyBedBlock.PART) == part, mmPart.build());
					}
					mmAll.add(s -> s.getValue(FancyBedBlock.SIDE) == sideType, mmSide.build());
				}
				event.getModels().put(m, mmAll.build());

				ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
				ResourceLocation loc = Compendium.modLoc("extra/bed/inventory/base/" + b);
				event.getModels().put(m_inv,
						basicModelAllTexture(event, mb, texture, loc, m_inv, BlockModelRotation.X0_Y0, "0"));
			}

			for (String b : StyleData.FENCE_POST.getTypes()) {
				ResourceLocation loc = Compendium.modLoc("extra/fence/post/" + b);
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("fence", "post", mb.name,
						b.toLowerCase());
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");
				ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");

				if (b.contains("none")) {
					event.getModels().put(m, basicModelManyTexture(event, mb, loc,
							new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0));

					event.getModels().put(m_inventory,
							basicModelManyTexture(event, mb, loc.withSuffix("_inventory"),
									new ModelResourceLocation(modelLoc.withSuffix("_inventory"), ""),
									BlockModelRotation.X0_Y90));
				} else {
					event.getModels().put(m,
							basicModelAllTexture(event, mb, texture, loc, m, BlockModelRotation.X0_Y0, "0"));

					event.getModels().put(m_inventory, basicModelAllTexture(event, mb, texture,
							loc.withSuffix("_inventory"), m_inventory, BlockModelRotation.X0_Y90, "0"));
				}

			}

			for (String b : StyleData.FENCE_SIDE.getTypes()) {
				ResourceLocation loc = Compendium.modLoc("extra/fence/side/" + b);
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("fence", "side", mb.name,
						b.toLowerCase());
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");
				ModelResourceLocation m_inventory = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");

				if (b.equals("sheet")) {

					MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

					mmb.add(s -> s.getValue(FenceBlock.EAST), basicModelManyTexture(event, mb, loc,
							new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.NORTH), basicModelManyTexture(event, mb, loc,
							new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.SOUTH),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y180, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.WEST),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y270, Pair.of("0", texture)));

					event.getModels().put(m, mmb.build());

					event.getModels().put(m_inventory, basicModelManyTexture(event, mb, loc.withSuffix("_inventory"),
							m_inventory, BlockModelRotation.X0_Y90, Pair.of("0", texture)));
				} else if (b.equals("solid_sheet")) {

					MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

					mmb.add(s -> s.getValue(FenceBlock.EAST),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y90,
									Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

					mmb.add(s -> s.getValue(FenceBlock.NORTH),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y0,
									Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

					mmb.add(s -> s.getValue(FenceBlock.SOUTH),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y180,
									Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

					mmb.add(s -> s.getValue(FenceBlock.WEST),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y270,
									Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));

					event.getModels().put(m, mmb.build());

					event.getModels().put(m_inventory,
							basicModelManyTexture(event, mb, loc.withSuffix("_inventory"), m_inventory,
									BlockModelRotation.X0_Y90,
									Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/sheet"))));
				} else {
					MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

					mmb.add(s -> s.getValue(FenceBlock.EAST), basicModelManyTexture(event, mb, loc,
							new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.NORTH), basicModelManyTexture(event, mb, loc,
							new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.SOUTH),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y180, Pair.of("0", texture)));

					mmb.add(s -> s.getValue(FenceBlock.WEST),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.X0_Y270, Pair.of("0", texture)));

					event.getModels().put(m, mmb.build());

					event.getModels().put(m_inventory, basicModelManyTexture(event, mb, loc.withSuffix("_inventory"),
							m_inventory, BlockModelRotation.X0_Y90, Pair.of("0", texture)));
				}

			}

			for (String b : StyleData.SHINGLES_SHINGLES.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("shingles_slanted", "shingles",
						mb.name, b.toLowerCase());

				ResourceLocation straight = Compendium.modLoc("extra/shingles_slanted/shingles/straight/" + b);
				ResourceLocation inner = Compendium.modLoc("extra/shingles_slanted/shingles/inner_corner/" + b);
				ResourceLocation outer = Compendium.modLoc("extra/shingles_slanted/shingles/outer_corner/" + b);
				doStyleStairs(event, mb, b, modelLoc, modelLoc.withSuffix("_inventory"), straight, inner, outer, 0, 0,
						Pair.of("0", texture));

				doShingleCap(event, mb, "shingles", b, Pair.of("0", texture));
			}

			for (String b : StyleData.SUPPORT_SHINGLES.getTypes()) {
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("shingles_slanted", "support",
						mb.name, b.toLowerCase());

				ResourceLocation straight = Compendium.modLoc("extra/shingles_slanted/support/straight/" + b);
				ResourceLocation inner = Compendium.modLoc("extra/shingles_slanted/support/inner_corner/" + b);
				ResourceLocation outer = Compendium.modLoc("extra/shingles_slanted/support/outer_corner/" + b);

				doStyleStairs(event, mb, b, modelLoc, modelLoc.withSuffix("_inventory"), straight, inner, outer, 0, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

				doShingleCap(event, mb, "support", b,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			}
		}
	}

	@SafeVarargs
	private static void doStyleStairs(ModifyBakingResult event, _MaterialBase mb, String b, ResourceLocation modelLoc,
			ResourceLocation modelLocInventory, ResourceLocation straight, ResourceLocation inner,
			ResourceLocation outer, int straightRot, int cornerRot, Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmAll = new MultiPartBakedModel.Builder();

		for (Direction dir : Direction.Plane.HORIZONTAL) {
			MultiPartBakedModel.Builder mmDir = new MultiPartBakedModel.Builder();
			for (Half half : Half.values()) {
				MultiPartBakedModel.Builder mmHalf = new MultiPartBakedModel.Builder();
				for (StairsShape shape : StairsShape.values()) {
					MultiPartBakedModel.Builder mmShape = new MultiPartBakedModel.Builder();

					ResourceLocation loc = straight;
					if (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT) {
						loc = inner;
					} else if (shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT) {

						loc = outer;
					}

					int h = half == Half.BOTTOM ? 0 : 180;
					int hy = half == Half.BOTTOM ? 0 : 180;

					if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT)
						hy += 90 + cornerRot;
					else if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT)
						hy += cornerRot;
					else
						hy += straightRot;

					mmShape.add(s -> s.getValue(StairBlock.WATERLOGGED),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.by(h, (int) dir.toYRot() + hy), textures));

					mmShape.add(s -> !s.getValue(StairBlock.WATERLOGGED),
							basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.by(h, (int) dir.toYRot() + hy), textures));

					mmHalf.add(s -> s.getValue(StairBlock.SHAPE) == shape, mmShape.build());
				}
				mmDir.add(s -> s.getValue(StairBlock.HALF) == half, mmHalf.build());
			}
			mmAll.add(s -> s.getValue(StairBlock.FACING) == dir, mmDir.build());
		}
		event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmAll.build());

		ModelResourceLocation m_inv = new ModelResourceLocation(modelLocInventory, "");
		ResourceLocation loc = straight;
		event.getModels().put(m_inv, basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
				BlockModelRotation.X0_Y0, textures));
	}

	private static void doStyleWood(ModifyBakingResult event, MaterialWood mb) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();
		for (_MaterialExtension me : mb.extensions) {
			if (me instanceof ExtensionExtraPlanks eep) {
				doExtraPlanks(event, mb, eep, models);
			} else if (me instanceof ExtensionExtraLogs eel) {
				doExtraLogs(event, mb, eel, models);
			}
		}
	}

	private static void doExtraLogs(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		ResourceLocation logSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/" + mw.name + "_log");
		ResourceLocation logEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/" + mw.name + "_log_top");
		ResourceLocation logStrippedSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/stripped_" + mw.name + "_log");
		ResourceLocation logStrippedEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/stripped_" + mw.name + "_log_top");

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
		doLog(event, mw, eel, models, logEndTexture, logStrippedEndTexture);
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

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log_stairs"),
						"stripped_" + mw.name + "_small_logs_stairs", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log_stairs_inventory"),
					mw.name + "_stripped_log_stairs_inventory", "");
		}

		if (eel.LOG_STAIRS.isNotIgnored()) {
			for (BlockState state : eel.LOG_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_stairs"),
						mw.name + "_small_logs_stairs", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_stairs_inventory"),
					mw.name + "_log_stairs_inventory", "");
		}
		for (String stair_style : StyleData.LOG_STAIRS.getTypes()) {
			// stairs
			ResourceLocation plankStairsModelLoc = ClientUtil.createStyleLocation(mw.name + "_small_logs_stairs",
					stair_style.toLowerCase());
			ResourceLocation plankStairsModelLocInventory = ClientUtil
					.createStyleLocation(mw.name + "_log_stairs_inventory", stair_style.toLowerCase());

			ResourceLocation plankStrippedStairsModelLoc = ClientUtil
					.createStyleLocation(mw.name + "_stripped_small_logs_stairs", stair_style.toLowerCase());
			ResourceLocation plankStrippedStairsModelLocInventory = ClientUtil
					.createStyleLocation(mw.name + "_stripped_log_stairs_inventory", stair_style.toLowerCase());

			if (stair_style.equals("small_logs")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_side")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_front")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_top")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("split_log_rotated_side")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_front")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_top")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("small_wood")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_wood_rotated")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("wood")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logSideTexture), Pair.of("2", logSideTexture));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedSideTexture),
						Pair.of("2", logStrippedSideTexture));
			} else if (stair_style.equals("wood_rotated")) {
				doStyleStairs(event, mw, stair_style, plankStairsModelLoc, plankStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0, Pair.of("0", logSideTexture));

				doStyleStairs(event, mw, stair_style, plankStrippedStairsModelLoc, plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
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

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log_slab"),
						"stripped_" + mw.name + "_small_logs_slab", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log_slab_inventory"),
					mw.name + "_stripped_log_slab_inventory", "");
		}

		if (eel.LOG_SLAB.isNotIgnored()) {
			for (BlockState state : eel.LOG_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_slab"),
						mw.name + "_small_logs_slab", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_slab_inventory"),
					mw.name + "_log_slab_inventory", "");
		}

		for (String slab_style : StyleData.LOG_SLAB.getTypes()) {
			// slabs
			ResourceLocation plankSlabModelLoc = ClientUtil.createStyleLocation(mw.name + "_small_logs_slab",
					slab_style.toLowerCase());
			ResourceLocation plankSlabIventoryModelLoc = ClientUtil.createStyleLocation(mw.name + "_log_slab_inventory",
					slab_style.toLowerCase());

			ResourceLocation strippedSlabModelLoc = ClientUtil
					.createStyleLocation(mw.name + "_stripped_small_logs_slab", slab_style.toLowerCase());
			ResourceLocation strippedSlabIventoryModelLoc = ClientUtil
					.createStyleLocation(mw.name + "_stripped_log_slab_inventory", slab_style.toLowerCase());

			if (slab_style.equals("small_logs") || slab_style.equals("small_logs_rotated")
					|| slab_style.equals("crosscut_small")) {
				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

			} else if (slab_style.equals("split") || slab_style.equals("split_rotated")) {
				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));

			} else if (slab_style.equals("crosscut")) {
				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("0", logSideTexture),
						Pair.of("1", logEndTexture));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture));

			} else if (slab_style.equals("small_wood") || slab_style.equals("small_wood_rotated")) {
				ResourceLocation logTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs");
				ResourceLocation logStrippedTexture = TagUtil
						.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs");

				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logTexture), Pair.of("bottom", logTexture), Pair.of("side", logTexture));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc, rot,
						Pair.of("top", logStrippedTexture), Pair.of("bottom", logStrippedTexture),
						Pair.of("side", logStrippedTexture));

			} else if (slab_style.equals("wood") || slab_style.equals("wood_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logSideTexture), Pair.of("bottom", logSideTexture),
						Pair.of("side", logSideTexture));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc, rot,
						Pair.of("top", logStrippedSideTexture), Pair.of("bottom", logStrippedSideTexture),
						Pair.of("side", logStrippedSideTexture));

			} else if (slab_style.equals("campfire")) {
				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));

			} else if (slab_style.equals("firewood")) {
				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));

			} else if (slab_style.equals("smaller_logs") || slab_style.equals("smaller_logs_rotated")
					|| slab_style.equals("smallest_logs") || slab_style.equals("smallest_logs_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc, rot,
						Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));

			} else if (slab_style.equals("trellis")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));

				doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc, rot,
						Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
			}
		}
	}

	public static void doLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logEndTexture,
			ResourceLocation logStrippedEndTexture) {

		if (eel.STRIPPED_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log"),
						"stripped_" + mw.name + "_small_logs", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log_inventory"),
					mw.name + "_stripped_log_inventory", "");

			for (String log_style : StyleData.LOG.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleLocation(mw.name + "_log",
						log_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_log_inventory",
						log_style.toLowerCase());

				ResourceLocation strippedLogModelLoc = ClientUtil.createStyleLocation(mw.name + "_stripped_log",
						log_style.toLowerCase());
				ResourceLocation strippedLogModelLocInventory = ClientUtil
						.createStyleLocation(mw.name + "_stripped_log_inventory", log_style.toLowerCase());

				if (log_style.equals("basic")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

				} else if (log_style.equals("small_wood")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
				} else if (log_style.equals("corner")) {
					ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

					doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("2", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));
				} else {
					ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
					ResourceLocation sideTexture = TagUtil
							.modLoc("block/material/wood/" + mw.name + "/logs/" + log_style);
					ResourceLocation sideStrippedTexture = TagUtil
							.modLoc("block/material/wood/" + mw.name + "/logs/stripped_" + log_style);
					if (log_style.contains("1") || log_style.contains("2")) {

						doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("bottom", logEndTexture),
								Pair.of("top", logStrippedEndTexture));

					} else {
						doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("top", logEndTexture),
								Pair.of("bottom", logStrippedEndTexture));

					}
				}
			}
		}

		if (eel.LOG.isNotIgnored()) {
			for (BlockState state : eel.LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log"), mw.name + "_small_logs",
						v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_inventory"),
					mw.name + "_log_inventory", "");
		}

		for (String log_style : StyleData.LOG.getTypes()) {
			ResourceLocation logModelLoc = ClientUtil.createStyleLocation(mw.name + "_log", log_style.toLowerCase());
			ResourceLocation logModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_log_inventory",
					log_style.toLowerCase());

			ResourceLocation strippedLogModelLoc = ClientUtil.createStyleLocation(mw.name + "_stripped_log",
					log_style.toLowerCase());
			ResourceLocation strippedLogModelLocInventory = ClientUtil
					.createStyleLocation(mw.name + "_stripped_log_inventory", log_style.toLowerCase());

			if (log_style.equals("basic")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

			} else if (log_style.equals("small_wood")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));
			} else if (log_style.equals("corner")) {
				ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

				doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			} else {
				ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
				ResourceLocation sideTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/" + log_style);
				ResourceLocation sideStrippedTexture = TagUtil
						.modLoc("block/material/wood/" + mw.name + "/logs/stripped_" + log_style);
				if (log_style.contains("1") || log_style.contains("2")) {

					doStyleLog(event, mw, logModelLoc, logModelLocInventory, model, Pair.of("side", sideTexture),
							Pair.of("top", logEndTexture), Pair.of("bottom", logStrippedEndTexture));

				} else {
					doStyleLog(event, mw, logModelLoc, logModelLocInventory, model, Pair.of("side", sideTexture),
							Pair.of("bottom", logEndTexture), Pair.of("top", logStrippedEndTexture));

				}
			}
		}

	}

	public static void doSmallLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		if (eel.STRIPPED_SMALL_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_SMALL_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_small_log"),
						"stripped_" + mw.name + "_small_log", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_small_log_inventory"),
					mw.name + "_stripped_small_log_inventory", "");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleLocation(mw.name + "_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_small_log_inventory",
						small_log_style.toLowerCase());

				ResourceLocation logStrippedModelLoc = ClientUtil.createStyleLocation(mw.name + "_stripped_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logStrippedModelLocInventory = ClientUtil
						.createStyleLocation(mw.name + "_stripped_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
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

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/small_log"),
						mw.name + "_small_log", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/small_log_inventory"),
					mw.name + "_small_log_inventory", "");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleLocation(mw.name + "_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_small_log_inventory",
						small_log_style.toLowerCase());

				ResourceLocation logStrippedModelLoc = ClientUtil.createStyleLocation(mw.name + "_stripped_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logStrippedModelLocInventory = ClientUtil
						.createStyleLocation(mw.name + "_stripped_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				}
			}

		}

	}

	@SafeVarargs
	private static void doStylePipe(ModifyBakingResult event, MaterialWood mw, ResourceLocation logModelLoc,
			ResourceLocation logModelLocInventory, ResourceLocation model, ResourceLocation centerModel,
			ResourceLocation NSCenterModel, ResourceLocation EWCenterModel,
			Pair<String, ResourceLocation>... textures) {

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(PipeBlock.WEST), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y270, textures));

		mmb.add(s -> s.getValue(PipeBlock.DOWN), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X180_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.EAST), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y90, textures));

		mmb.add(s -> s.getValue(PipeBlock.NORTH), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.SOUTH), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y180, textures));

		mmb.add(s -> s.getValue(PipeBlock.UP), basicModelManyTexture(event, mw, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.EAST) || s.getValue(PipeBlock.WEST), basicModelManyTexture(event, mw,
				EWCenterModel, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.NORTH) || s.getValue(PipeBlock.SOUTH), basicModelManyTexture(event, mw,
				NSCenterModel, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> !s.getValue(PipeBlock.EAST) && !s.getValue(PipeBlock.WEST) && !s.getValue(PipeBlock.NORTH)
				&& !s.getValue(PipeBlock.SOUTH),
				basicModelManyTexture(event, mw, centerModel, new ModelResourceLocation(logModelLoc, ""),
						BlockModelRotation.X0_Y0, textures));

		event.getModels().put(new ModelResourceLocation(logModelLoc, ""), mmb.build());

		event.getModels().put(new ModelResourceLocation(logModelLocInventory, ""), basicModelManyTexture(event, mw,
				model, new ModelResourceLocation(logModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	public static void doStyleSlab(ModifyBakingResult event, MaterialWood mw, String slab_style,
			ResourceLocation plankSlabModelLoc, ResourceLocation plankSlabModelLocInventory, BlockModelRotation rot,
			Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder plank_slab = new MultiPartBakedModel.Builder();

		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.BOTTOM,
				basicModelManyTexture(event, mw, TagUtil.modLoc("extra/log_slab/" + slab_style + "_bottom"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));
		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.TOP,
				basicModelManyTexture(event, mw, TagUtil.modLoc("extra/log_slab/" + slab_style + "_top"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));
		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.DOUBLE,
				basicModelManyTexture(event, mw, TagUtil.modLoc("extra/log_slab/" + slab_style + "_full"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));

		event.getModels().put(new ModelResourceLocation(plankSlabModelLoc, ""), plank_slab.build());

//		ResourceLocation plankSlabModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_log_slab_inventory",
//				slab_style.toLowerCase());

		event.getModels().put(new ModelResourceLocation(plankSlabModelLocInventory, ""),
				basicModelManyTexture(event, mw, TagUtil.modLoc("extra/log_slab/" + slab_style + "_bottom"),
						new ModelResourceLocation(plankSlabModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	private static void doStyleLog(ModifyBakingResult event, MaterialWood mw, ResourceLocation logModelLoc,
			ResourceLocation logModelLocInventory, ResourceLocation model, Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.X, basicModelManyTexture(event, mw,
				model, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y90, textures));

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.Y, basicModelManyTexture(event, mw,
				model, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.Z, basicModelManyTexture(event, mw,
				model, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y0, textures));

		event.getModels().put(new ModelResourceLocation(logModelLoc, ""), mmb.build());

		event.getModels().put(new ModelResourceLocation(logModelLocInventory, ""), basicModelManyTexture(event, mw,
				model, new ModelResourceLocation(logModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	private static void doExtraPlanks(ModifyBakingResult event, MaterialWood mw, ExtensionExtraPlanks eep,
			Map<ModelResourceLocation, BakedModel> models) {

		ResourceLocation plankTexture = TagUtil.mcLoc("block/" + mw.name + "_planks");
		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null)
				if (mw.specialLocations.textures.plankLocation != null)
					plankTexture = mw.specialLocations.textures.plankLocation;
		}

		if (eep.PLANK_BLOCK.isNotIgnored()) {
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank"), mw.name + "_plank", "");

		}

		if (eep.PLANK.isNotIgnored()) {
			for (BlockState state : eep.PLANK.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank"), mw.name + "_plank", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks"), mw.name + "_planks", "");

			for (String plank_style : StyleData.PLANK.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleLocation(mw.name + "_plank",
						plank_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_plank_inventory",
						plank_style.toLowerCase());
				doStylePipe(event, mw, logModelLoc, logModelLocInventory,
						TagUtil.modLoc("extra/plank/" + plank_style + "_cap"),
						TagUtil.modLoc("extra/plank/" + plank_style),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal2"),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal"),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/plank")));
			}
		}

		if (eep.PLANK_SLAB.isNotIgnored()) {
			for (BlockState state : eep.PLANK_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_slab"),
						mw.name + "_planks_slab", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_slab_inventory"),
					mw.name + "_planks_slab_inventory", "");
		}

		if (eep.PLANK_STAIRS.isNotIgnored()) {
			for (BlockState state : eep.PLANK_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = stateToString(propertyValues);

				buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_stairs"),
						mw.name + "_planks_stairs", v);
			}
			buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_stairs_inventory"),
					mw.name + "_planks_stairs_inventory", "");
		}

		for (String planks_style : StyleData.PLANKS.getTypes()) {
			// planks
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = ClientUtil.createStyleLocation(mw.name + "_planks", planks_style.toLowerCase());
			ResourceLocation t = Compendium
					.modLoc("block/material/wood/" + mw.name + "/planks/" + planks_style.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			if (eep.PLANK_BLOCK.isNotIgnored()) {
				BakedModel bm = basicModelAllTexture(event, mw, t, loc, m, BlockModelRotation.X0_Y0, "all");
				event.getModels().put(m, bm);
			}

			if (eep.PLANK_SLAB.isNotIgnored()) {
				// slabs
				ResourceLocation plankSlabModelLoc = ClientUtil.createStyleLocation(mw.name + "_planks_slab",
						planks_style.toLowerCase());
				MultiPartBakedModel.Builder plank_slab = new MultiPartBakedModel.Builder();

				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.BOTTOM,
						basicModelManyTexture(event, mw, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.TOP,
						basicModelManyTexture(event, mw, TagUtil.mcLoc("block/acacia_slab_top"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.DOUBLE, basicModelAllTexture(event, mw,
						t, loc, new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0, "all"));

				event.getModels().put(new ModelResourceLocation(plankSlabModelLoc, ""), plank_slab.build());

				ResourceLocation plankSlabModelLocInventory = ClientUtil
						.createStyleLocation(mw.name + "_planks_slab_inventory", planks_style.toLowerCase());

				event.getModels().put(new ModelResourceLocation(plankSlabModelLocInventory, ""),
						basicModelManyTexture(event, mw, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLocInventory, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
			}

			if (eep.PLANK_STAIRS.isNotIgnored()) {
				// stairs
				ResourceLocation plankStairsModelLoc = ClientUtil.createStyleLocation(mw.name + "_planks_stairs",
						planks_style.toLowerCase());
				ResourceLocation plankStairsModelLocInventory = ClientUtil
						.createStyleLocation(mw.name + "_planks_stairs_inventory", planks_style.toLowerCase());

				ResourceLocation straight = TagUtil.mcLoc("block/acacia_stairs");
				ResourceLocation inner = TagUtil.mcLoc("block/acacia_stairs_inner");
				ResourceLocation outer = TagUtil.mcLoc("block/acacia_stairs_outer");

				doStyleStairs(event, mw, planks_style, plankStairsModelLoc, plankStairsModelLocInventory, straight,
						inner, outer, 90, 0, Pair.of("top", t), Pair.of("bottom", t), Pair.of("side", t));
			}
		}

	}

	@SafeVarargs
	private static BakedModel doBed(ModifyBakingResult event, _MaterialBase mb, ResourceLocation loc,
			ModelResourceLocation modelLoc, Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.WEST,
				basicModelManyTexture(event, mb, loc, modelLoc, BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.SOUTH,
				basicModelManyTexture(event, mb, loc, modelLoc, BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.NORTH,
				basicModelManyTexture(event, mb, loc, modelLoc, BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.EAST,
				basicModelManyTexture(event, mb, loc, modelLoc, BlockModelRotation.X0_Y270, textures));

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
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

		mmb.add(s -> s.getValue(TableBase.N) && !s.getValue(TableBase.NW) && s.getValue(TableBase.W),
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

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
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

		mmb.add(s -> s.getValue(TableBase.NE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.N),
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
						BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> s.getValue(TableBase.SE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
						BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> s.getValue(TableBase.SW) && !s.getValue(TableBase.W) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, mb, loc, new ModelResourceLocation(modelLoc, ""),
						BlockModelRotation.X0_Y270, textures));

		event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmb.build());

		ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation(table, "legs", mb.name,
				b.toLowerCase(), "_inventory");
		ResourceLocation loc_inv = Compendium.modLoc("extra/table/legs/" + b + "_inventory");

		ModelResourceLocation w_inv = new ModelResourceLocation(modelLoc_inv, "");

		event.getModels().put(w_inv,
				basicModelManyTexture(event, mb, loc_inv, w_inv, BlockModelRotation.X0_Y0, textures));
	}

	public static int shingleState(BlockState s) {
		boolean N = s.getValue(ShinglesCapSlanted.NORTH);
		boolean S = s.getValue(ShinglesCapSlanted.SOUTH);
		boolean W = s.getValue(ShinglesCapSlanted.WEST);
		boolean E = s.getValue(ShinglesCapSlanted.EAST);

		int i = N ? 1 : 0;
		i += S ? 1 : 0;
		i += W ? 1 : 0;
		i += E ? 1 : 0;

		return i;
	}

	static BlockModelRotation shingleRotation(boolean N, boolean S, boolean W, boolean E) {
		if (N)
			return BlockModelRotation.X0_Y0;
		if (S)
			return BlockModelRotation.X0_Y180;
		if (W)
			return BlockModelRotation.X0_Y270;
		return BlockModelRotation.X0_Y90;
	}

	@SafeVarargs
	private static void doShingleCap(ModifyBakingResult event, _MaterialBase mb, String type, String b,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerLocation("shingles_cap_slanted", type, mb.name,
				b.toLowerCase());

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		// All
		mmb.add(s -> shingleState(s) == 4 && s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/all/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 4 && !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/all/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// Tri
		// North
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// East
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP) && !s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// South
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// West
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP) && !s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// Straight
		// N-S
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.NORTH) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.NORTH) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// E-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.WEST))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.WEST))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// Corner
		// N-E
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// N-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// S-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// S-E
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// End
		// North
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP)
				&& s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// East
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// South
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP)
				&& s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// West
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// None
		mmb.add(s -> shingleState(s) == 0 && s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/none/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 0 && !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, mb,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/none/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmb.build());

		ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
		ResourceLocation loc = Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b);
		event.getModels().put(m_inv, basicModelManyTexture(event, mb, loc, m_inv, BlockModelRotation.X0_Y0, textures));

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
		ResourceLocation loc_inv = Compendium.modLoc("extra/chair/" + part + "/" + b + "_inventory");

//		Compendium.LOGGER.debug(modelLoc_inv.toString());
//		Compendium.LOGGER.debug(loc_inv.toString());

		ModelResourceLocation w_inv = new ModelResourceLocation(modelLoc_inv, "");

		event.getModels().put(w_inv,
				basicModelManyTexture(event, mb, loc_inv, w_inv, BlockModelRotation.X0_Y0, textures));
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
