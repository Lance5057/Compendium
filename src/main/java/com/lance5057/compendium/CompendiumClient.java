package com.lance5057.compendium;

import java.util.Comparator;
import java.util.Map;
import java.util.MissingResourceException;
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
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.gem.ClientGem;
import com.lance5057.compendium.index.material.base.gem.MaterialGem;
import com.lance5057.compendium.index.material.base.glass.ClientGlass;
import com.lance5057.compendium.index.material.base.glass.MaterialGlass;
import com.lance5057.compendium.index.material.base.metal.ClientMetal;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.material.base.stone.ClientStone;
import com.lance5057.compendium.index.material.base.stone.MaterialStone;
import com.lance5057.compendium.index.material.base.textile.ClientTextile;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.wood.ClientWood;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
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
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery.ModelBakerImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
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
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

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
			Compendium.LOGGER.debug(rl2);
		});

		event.register(ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
	}

	@SubscribeEvent
	public static void RegisterItemModels(ModelEvent.BakingCompleted event) {
		RegistryAwareItemModelShaper shaper = (RegistryAwareItemModelShaper) Minecraft.getInstance().getItemRenderer()
				.getItemModelShaper();

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				if (mb instanceof MaterialWood mw)
					ClientWood.doItems(shaper, mw);
				if (mb instanceof MaterialMetal mm)
					ClientMetal.doItems(shaper, mm);
				if (mb instanceof MaterialTextile mm)
					ClientTextile.doItems(shaper, mm);
				if (mb instanceof MaterialStone mm)
					ClientStone.doItems(shaper, mm);
				if (mb instanceof MaterialGem mm)
					ClientGem.doItems(shaper, mm);
				if (mb instanceof MaterialGlass mm)
					ClientGlass.doItems(shaper, mm);
			}
		});

	}

	@SubscribeEvent
	public static void extraModels(ModifyBakingResult event) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				if (mb instanceof MaterialMetal mw)
					ClientMetal.doMetal(event, mw);
				if (mb instanceof MaterialGlass mw)
					ClientGlass.doGlass(event, mw);
				if (mb instanceof MaterialTextile mw)
					ClientTextile.doTextile(event, mw);
				if (mb instanceof MaterialWood mw)
					ClientWood.doWood(event, mw);
				if (mb instanceof MaterialGem mw)
					ClientGem.doGem(event, mw);
				if (mb instanceof MaterialStone mw)
					ClientStone.doStone(event, mw);
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

	public static void buildStateModelAltLocation(ModifyBakingResult event,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation fromLocation, String toLocation) {
		ModelResourceLocation ml = new ModelResourceLocation(Compendium.modLoc(toLocation), "");
		BlockModel um = (BlockModel) event.getModelBakery().getModel(fromLocation);
		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
		um.resolveParents(i -> baker.getModel(i));

		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
		models.put(ml, bm);

//		Compendium.LOGGER.debug(ml.toString());
	}

	public static void buildStateModelVariantAltLocation(ModifyBakingResult event,
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

	@SafeVarargs
	public static void doStyleStairs(ModifyBakingResult event, String b, ResourceLocation modelLoc,
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
							basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
									BlockModelRotation.by(h, (int) dir.toYRot() + hy), textures));

					mmShape.add(s -> !s.getValue(StairBlock.WATERLOGGED),
							basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
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
		event.getModels().put(m_inv, basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""),
				BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	public static void doStylePipe(ModifyBakingResult event, MaterialWood mw, ResourceLocation logModelLoc,
			ResourceLocation logModelLocInventory, ResourceLocation model, ResourceLocation centerModel,
			ResourceLocation NSCenterModel, ResourceLocation EWCenterModel, ResourceLocation invModel,
			Pair<String, ResourceLocation>... textures) {

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(PipeBlock.WEST), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y270, textures));

		mmb.add(s -> s.getValue(PipeBlock.DOWN), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X180_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.EAST), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y90, textures));

		mmb.add(s -> s.getValue(PipeBlock.NORTH), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.SOUTH), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y180, textures));

		mmb.add(s -> s.getValue(PipeBlock.UP), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.EAST) || s.getValue(PipeBlock.WEST), basicModelManyTexture(event,
				EWCenterModel, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(PipeBlock.NORTH) || s.getValue(PipeBlock.SOUTH), basicModelManyTexture(event,
				NSCenterModel, new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> !s.getValue(PipeBlock.EAST) && !s.getValue(PipeBlock.WEST) && !s.getValue(PipeBlock.NORTH)
				&& !s.getValue(PipeBlock.SOUTH),
				basicModelManyTexture(event, centerModel, new ModelResourceLocation(logModelLoc, ""),
						BlockModelRotation.X0_Y0, textures));

		event.getModels().put(new ModelResourceLocation(logModelLoc, ""), mmb.build());

		event.getModels().put(new ModelResourceLocation(logModelLocInventory, ""), basicModelManyTexture(event,
				invModel, new ModelResourceLocation(logModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	public static void doStyleSlab(ModifyBakingResult event, MaterialWood mw, String slab_style,
			ResourceLocation plankSlabModelLoc, ResourceLocation plankSlabModelLocInventory, BlockModelRotation rot,
			Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder plank_slab = new MultiPartBakedModel.Builder();

		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.BOTTOM,
				basicModelManyTexture(event, TagUtil.modLoc("extra/log_slab/" + slab_style + "_bottom"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));
		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.TOP,
				basicModelManyTexture(event, TagUtil.modLoc("extra/log_slab/" + slab_style + "_top"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));
		plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.DOUBLE,
				basicModelManyTexture(event, TagUtil.modLoc("extra/log_slab/" + slab_style + "_full"),
						new ModelResourceLocation(plankSlabModelLoc, ""), rot, textures));

		event.getModels().put(new ModelResourceLocation(plankSlabModelLoc, ""), plank_slab.build());

//		ResourceLocation plankSlabModelLocInventory = ClientUtil.createStyleLocation(mw.name + "_log_slab_inventory",
//				slab_style.toLowerCase());

		event.getModels().put(new ModelResourceLocation(plankSlabModelLocInventory, ""),
				basicModelManyTexture(event, TagUtil.modLoc("extra/log_slab/" + slab_style + "_bottom"),
						new ModelResourceLocation(plankSlabModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	public static void doStyleLog(ModifyBakingResult event, MaterialWood mw, ResourceLocation logModelLoc,
			ResourceLocation logModelLocInventory, ResourceLocation model, Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.X, basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y90, textures));

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.Y, basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(RotatedPillarStyleBlock.AXIS) == Direction.Axis.Z, basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLoc, ""), BlockModelRotation.X90_Y0, textures));

		event.getModels().put(new ModelResourceLocation(logModelLoc, ""), mmb.build());

		event.getModels().put(new ModelResourceLocation(logModelLocInventory, ""), basicModelManyTexture(event, model,
				new ModelResourceLocation(logModelLocInventory, ""), BlockModelRotation.X0_Y0, textures));
	}

	@SafeVarargs
	public static BakedModel doBed(ModifyBakingResult event, ResourceLocation loc, ModelResourceLocation modelLoc,
			Pair<String, ResourceLocation>... textures) {
		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.WEST,
				basicModelManyTexture(event, loc, modelLoc, BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.SOUTH,
				basicModelManyTexture(event, loc, modelLoc, BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.NORTH,
				basicModelManyTexture(event, loc, modelLoc, BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> s.getValue(FancyBedBlock.FACING) == Direction.EAST,
				basicModelManyTexture(event, loc, modelLoc, BlockModelRotation.X0_Y270, textures));

		return mmb.build();
	}

	public static String bitToConditionString(int i, int b) {
		if ((i & b) == 0)
			return "false";
		return "true";
	}

	@SafeVarargs
	public static void doTableLeg(ModifyBakingResult event, _MaterialBase mb, String b, String table,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation(table, "legs", mb.name,
				b.toLowerCase());
		ResourceLocation loc = Compendium.modLoc("extra/" + table + "/legs/" + b);

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		mmb.add(s -> !s.getValue(TableBase.E) && !s.getValue(TableBase.NE) && !s.getValue(TableBase.N),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90,
						textures));

		mmb.add(s -> !s.getValue(TableBase.E) && !s.getValue(TableBase.SE) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180,
						textures));

		mmb.add(s -> !s.getValue(TableBase.W) && !s.getValue(TableBase.SW) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270,
						textures));

		mmb.add(s -> !s.getValue(TableBase.W) && !s.getValue(TableBase.NW) && !s.getValue(TableBase.N),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

		mmb.add(s -> s.getValue(TableBase.N) && !s.getValue(TableBase.NW) && s.getValue(TableBase.W),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

		mmb.add(s -> s.getValue(TableBase.N) && !s.getValue(TableBase.NE) && s.getValue(TableBase.E),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90,
						textures));

		mmb.add(s -> s.getValue(TableBase.S) && !s.getValue(TableBase.SE) && s.getValue(TableBase.E),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180,
						textures));

		mmb.add(s -> !s.getValue(TableBase.SW) && s.getValue(TableBase.W) && s.getValue(TableBase.S),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270,
						textures));

		mmb.add(s -> s.getValue(TableBase.NW) && !s.getValue(TableBase.W) && !s.getValue(TableBase.N),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0,
						textures));

		mmb.add(s -> s.getValue(TableBase.NE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.N),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90,
						textures));

		mmb.add(s -> s.getValue(TableBase.SE) && !s.getValue(TableBase.E) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180,
						textures));

		mmb.add(s -> s.getValue(TableBase.SW) && !s.getValue(TableBase.W) && !s.getValue(TableBase.S),
				basicModelManyTexture(event, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270,
						textures));

		event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmb.build());

		ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation(table, "legs", mb.name,
				b.toLowerCase(), "_inventory");
		ResourceLocation loc_inv = Compendium.modLoc("extra/table/legs/" + b + "_inventory");

		ModelResourceLocation w_inv = new ModelResourceLocation(modelLoc_inv, "");

		event.getModels().put(w_inv, basicModelManyTexture(event, loc_inv, w_inv, BlockModelRotation.X0_Y0, textures));
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
	public static void doShingleCap(ModifyBakingResult event, _MaterialBase mb, String type, String b,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("shingles_cap_slanted", type,
				mb.name, b.toLowerCase());

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();

		// All
		mmb.add(s -> shingleState(s) == 4 && s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/all/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 4 && !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/all/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// Tri
		// North
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// East
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP) && !s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// South
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// West
		mmb.add(s -> shingleState(s) == 3 && s.getValue(ShinglesCapSlanted.TOP) && !s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 3 && !s.getValue(ShinglesCapSlanted.TOP)
				&& !s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/tri/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// Straight
		// N-S
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.NORTH) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.NORTH) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// E-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.WEST))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.WEST))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// Corner
		// N-E
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// N-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.NORTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// S-W
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.WEST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// S-E
		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 2
				&& (s.getValue(ShinglesCapSlanted.EAST) && s.getValue(ShinglesCapSlanted.SOUTH))
				&& !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/corner/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// End
		// North
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP)
				&& s.getValue(ShinglesCapSlanted.NORTH),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y270, textures));

		// East
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.EAST),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		// South
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP)
				&& s.getValue(ShinglesCapSlanted.SOUTH),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y90, textures));

		// West
		mmb.add(s -> shingleState(s) == 1 && s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		mmb.add(s -> shingleState(s) == 1 && !s.getValue(ShinglesCapSlanted.TOP) && s.getValue(ShinglesCapSlanted.WEST),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/end/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y180, textures));

		// None
		mmb.add(s -> shingleState(s) == 0 && s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event, Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/top/none/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		mmb.add(s -> shingleState(s) == 0 && !s.getValue(ShinglesCapSlanted.TOP),
				basicModelManyTexture(event,
						Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/none/" + b),
						new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, textures));

		event.getModels().put(new ModelResourceLocation(modelLoc, ""), mmb.build());

		ModelResourceLocation m_inv = new ModelResourceLocation(modelLoc.withSuffix("_inventory"), "");
		ResourceLocation loc = Compendium.modLoc("extra/shingles_cap_slanted/" + type + "/no_top/straight/" + b);
		event.getModels().put(m_inv, basicModelManyTexture(event, loc, m_inv, BlockModelRotation.X0_Y0, textures));

	}

	@SafeVarargs
	public static void doChair(ModifyBakingResult event, _MaterialBase mb, String part, String b,
			Pair<String, ResourceLocation>... textures) {
		ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("chair", part, mb.name,
				b.toLowerCase());
		ResourceLocation loc = Compendium.modLoc("extra/" + "chair/" + part + "/" + b);

		MultiPartBakedModel.Builder mmb = new MultiPartBakedModel.Builder();
		ModelResourceLocation w = new ModelResourceLocation(modelLoc, "");
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.WEST,
				basicModelManyTexture(event, loc, w, BlockModelRotation.X0_Y90, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.NORTH,
				basicModelManyTexture(event, loc, w, BlockModelRotation.X0_Y180, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.EAST,
				basicModelManyTexture(event, loc, w, BlockModelRotation.X0_Y270, textures));
		mmb.add(s -> s.getValue(ChairBlock.FACING) == Direction.SOUTH,
				basicModelManyTexture(event, loc, w, BlockModelRotation.X0_Y0, textures));
		event.getModels().put(w, mmb.build());

		ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation("chair", part, mb.name,
				b.toLowerCase(), "_inventory");
		ResourceLocation loc_inv = Compendium.modLoc("extra/chair/" + part + "/" + b + "_inventory");

//		Compendium.LOGGER.debug(modelLoc_inv.toString());
//		Compendium.LOGGER.debug(loc_inv.toString());

		ModelResourceLocation w_inv = new ModelResourceLocation(modelLoc_inv, "");

		event.getModels().put(w_inv, basicModelManyTexture(event, loc_inv, w_inv, BlockModelRotation.X0_Y0, textures));
	}

	public static BakedModel basicModelAllTexture(ModifyBakingResult event, ResourceLocation blockTexture,
			ResourceLocation location, ModelResourceLocation modelLocation, ModelState state, String textureName) {

		BlockModel frame_model = (BlockModel) event.getModelBakery().getModel(location);

		return buildModel(event, frame_model, modelLocation, state, Pair.of(textureName, blockTexture));
	}

	@SafeVarargs
	public static BakedModel basicModelManyTexture(ModifyBakingResult event, ResourceLocation location,
			ModelResourceLocation modelLocation, ModelState state, Pair<String, ResourceLocation>... textures) {

		UnbakedModel um = event.getModelBakery().getModel(location);
		BlockModel frame_model = (BlockModel) um;

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
			}
		}

		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(),
				modelResource);

		model.resolveParents(i -> baker.getModel(i));
		BakedModel bm = model.bake(baker, event.getTextureGetter(), state);

		return bm;
	}
}
