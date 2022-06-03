//package lance5057.compendium;
//
//import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
//import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
//import lance5057.compendium.core.client.SeatRenderer;
//import lance5057.compendium.core.entities.GrenadeEntity;
//import lance5057.compendium.core.entities.SeatEntity;
//import lance5057.compendium.core.workstations.client.CraftingAnvilRenderer;
//import lance5057.compendium.core.workstations.client.HammeringStationRenderer;
//import lance5057.compendium.core.workstations.client.SawhorseStationRenderer;
//import lance5057.compendium.core.workstations.client.ScrappingTableRenderer;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
//import net.minecraft.client.renderer.entity.EntityRenderer;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.ItemRenderer;
//import net.minecraft.client.renderer.entity.SpriteRenderer;
//import net.minecraft.client.renderer.entity.ThrownItemRenderer;
//import net.minecraft.client.renderer.texture.AtlasTexture;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.ClientRegistry;
//import net.minecraftforge.client.event.ModelRegistryEvent;
//import net.minecraftforge.client.event.TextureStitchEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//import net.minecraftforge.fml.client.registry.RenderingRegistry;
//import net.minecraftforge.fml.common.Mod;
//
//@OnlyIn(Dist.CLIENT)
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Reference.MOD_ID)
//public class CompendiumClient {
//    public static void setRenderLayers() {
////	for (MaterialHelper mh : CompendiumMaterials.materials) {
////	    mh.client();
////	}
//
//	RenderType cutout = RenderType.cutout();
//	ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.HAMMERING_STATION.get(), cutout);
//	ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.SCRAPPING_TABLE.get(), cutout);
//	// ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.CRAFTING_ANVIL.get(),
//	// RenderType.getTranslucent());
//    }
//
//    public static void setTERenderers() {
//	ClientRegistry.bindBlockEntityRenderer(CompendiumTileEntities.HAMMERING_STATION_TE.get(),
//		HammeringStationRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.CRAFTING_ANVIL_TE.get(),
//		CraftingAnvilRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SAWHORSE_STATION_TE.get(),
//		SawhorseStationRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SCRAPPING_TABLE_TE.get(),
//		ScrappingTableRenderer::new);
//
////	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.VAULT_TE.get(), VaultRenderer::new);
////	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.ITEM_DISPLAY_TE.get(), ItemDisplayRenderer::new);
//    }
//
//    public static void setEntityRenderers() {
//
//    }
//
//    @SubscribeEvent
//    public static void onRegisterModelsEvent(ModelRegistryEvent event) {
//	RenderingRegistry.registerEntityRenderingInteractionHandler(CompendiumEntities.GRENADE_ENTITY.get(),
//		new IRenderFactory<GrenadeEntity>() {
//		    @Override
//		    public EntityRenderer<GrenadeEntity> createRenderFor(EntityRendererManager manager) {
//			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//			return new SpriteRenderer<>(manager, itemRenderer);
//		    }
//		});
//	
//	RenderingRegistry.registerEntityRenderingInteractionHandler(CompendiumEntities.SEAT_ENTITY.get(),
//		new IRenderFactory<SeatEntity>() {
//		    @Override
//		    public EntityRenderer<SeatEntity> createRenderFor(EntityRendererManager manager) {
//			return new SeatRenderer(manager);
//		    }
//		});
//    }
//
//    @SubscribeEvent
//    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
//	if (event.getMap().location() == AtlasTexture.LOCATION_BLOCKS) {
//	    for (MetallurgyMaterialHelper m : AppendixMetallurgy.metals) {
//		if (m.hasDefense()) {
//		    event.addSprite(new ResourceLocation(Reference.MOD_ID,
//			    "entity/material/" + m.name + "/" + m.name + "shield_base"));
//		    event.addSprite(new ResourceLocation(Reference.MOD_ID,
//			    "entity/material/" + m.name + "/" + m.name + "shield_base_nopattern"));
//		}
//	    }
//	}
//    }
//
//    public static class GrenadeRenderFactory implements IRenderFactory<GrenadeEntity> {
//	@Override
//	public EntityRenderer<? super GrenadeEntity> createRenderFor(EntityRenderDispatcher manager) {
//	    ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//	    return new ThrownItemRenderer(manager, itemRenderer);
//	}
//    }
//}
