package com.lance5057.compendium;

import java.util.Map;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRenderer;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerScreen;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRenderer;
import com.lance5057.compendium.client.armor.ModelBreastplate;
import com.lance5057.compendium.client.armor.ModelGreaves;
import com.lance5057.compendium.client.armor.ModelHelm;
import com.lance5057.compendium.client.armor.ModelSabatons;
import com.lance5057.compendium.client.models.MaterialSwapElementsUnbakedModel;
import com.lance5057.compendium.client.renderer.blockentity.StyleBlockRenderer;
import com.lance5057.compendium.client.renderer.entity.SeatRenderer;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialScreen;
import com.lance5057.compendium.gui.AdjustinatorWorkstationScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedScreen;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRenderer;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRenderer;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchRenderer;
import com.lance5057.compendium.workstations.workbench.WorkbenchScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

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
		BlockEntityRenderers.register(CompendiumBlockEntities.STYLE.get(), StyleBlockRenderer::new);
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
	public static void RegisterExtraModels(ModelEvent.RegisterAdditional event) {
		Map<ResourceLocation, Resource> rrs = Minecraft.getInstance().getResourceManager().listResources("models/extra",
				(p_215600_) -> {
					return p_215600_.getPath().endsWith(".json");
				});

//		rrs.putAll(Minecraft.getInstance().getResourceManager().listResources("models/block/material", (p_215600_) -> {
//			return p_215600_.getPath().endsWith(".json");
//		}));

		rrs.forEach((rl, r) -> {
			String s = rl.toString();

			s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));

			ModelResourceLocation rl2 = ModelResourceLocation
					.standalone(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s));

			event.register(rl2);
		});
	}

	@SubscribeEvent
	public static void registerLoader(ModelEvent.RegisterGeometryLoaders registerGeometryLoaders) {
//		registerGeometryLoaders.register(MetalTileGeometryLoader.ID, new MetalTileGeometryLoader());
		registerGeometryLoaders.register(MaterialSwapElementsUnbakedModel.Loader.ID,
				new MaterialSwapElementsUnbakedModel.Loader());
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CompendiumEntities.SEAT.get(), c -> new SeatRenderer(c));
	}
}
