package com.lance5057.compendium;

import java.util.Map;
import java.util.stream.Collectors;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRenderer;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerScreen;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRenderer;
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
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedScreen;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRenderer;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRenderer;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchScreen;
import com.mojang.datafixers.util.Either;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery.ModelBakerImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
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
//		Map<ModelResourceLocation, BakedModel> models = event.getModels();
//
//		ResourceLocation rc = Compendium.modLoc("window");
//		ResourceLocation texture = Compendium.modLoc("block/material/wood/acacia/windows/bars");
//
//		ModelResourceLocation ml = ModelResourceLocation.inventory(rc);
//		models.put(ml, buildModel(event, ml, texture));
//
//		ModelResourceLocation ml2 = new ModelResourceLocation(rc, "");
//		models.put(ml2, buildModel(event, ml2, texture));

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				doMetal(event, mb);
			}
		});

	}

	private static void doMetal(ModifyBakingResult event, _MaterialBase mb) {
		if (mb.getType() == MATERIAL_TYPES.METAL) {
			Map<ModelResourceLocation, BakedModel> models = event.getModels();
			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				ResourceLocation rc = Compendium.modLoc("extra/window_frame");

				BlockModel frame_model = (BlockModel) event.getModelBakery().getModel(rc);
				ResourceLocation texture = Compendium
						.modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase());
				ResourceLocation output_location = Compendium
						.modLoc("block/material/metal/" + mb.name + "/window/trim/" + b.toLowerCase());

				ModelResourceLocation inventory_model = ModelResourceLocation.inventory(output_location);
				ModelResourceLocation block_model = new ModelResourceLocation(output_location, "");

				ResourceLocation rl = frame_model.textureMap.get("all").left().get().atlasLocation();

				frame_model.textureMap.put("all", Either.left(new Material(rl, texture)));

				ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl(
						(modelLoc, material) -> material.sprite(), inventory_model);

				frame_model.resolveParents(i -> baker.getModel(i));

				BakedModel bm = frame_model.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);

				models.put(inventory_model, bm);
				models.put(block_model, bm);
			});
		}
	}

//	private static BakedModel buildModel(ModifyBakingResult event, ModelResourceLocation ml, ResourceLocation texture) {
//		BlockModel um = (BlockModel) event.getModelBakery().getModel(Compendium.modLoc("extra/window_base"));
//
//		ResourceLocation rl = um.textureMap.get("all").left().get().atlasLocation();
//
//		um.textureMap.put("all", Either.left(new Material(rl, texture)));
//
//		ModelBakerImpl baker = event.getModelBakery().new ModelBakerImpl((modelLoc, material) -> material.sprite(), ml);
//
//		um.resolveParents(i -> baker.getModel(i));
//
//		BakedModel bm = um.bake(baker, event.getTextureGetter(), BlockModelRotation.X0_Y0);
//		return bm;
//	}

}
