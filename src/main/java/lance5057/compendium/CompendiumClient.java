package lance5057.compendium;

import lance5057.compendium.core.client.HandDisplayRenderer;
import lance5057.compendium.core.client.ItemDisplayRenderer;
import lance5057.compendium.core.workstations.client.CraftingAnvilRenderer;
import lance5057.compendium.shaders.ShaderAttempts;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Reference.MOD_ID)
public class CompendiumClient {
	public static void setRenderLayers() {
//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    mh.client();
//	}

		RenderType bright = ShaderAttempts.brightSolid(new ResourceLocation("compendium:block/test"));
		
		ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.TEST.get(), bright);
//	RenderType cutout = RenderType.cutout();
//	ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.HAMMERING_STATION.get(), cutout);
//	ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.SCRAPPING_TABLE.get(), cutout);
		// ItemBlockRenderTypes.setRenderLayer(CompendiumBlocks.CRAFTING_ANVIL.get(),
		// RenderType.getTranslucent());
	}

	@SubscribeEvent
	public static void setTERenderers(EntityRenderersEvent.RegisterRenderers event) {
//	ClientRegistry.bindBlockEntityRenderer(CompendiumTileEntities.HAMMERING_STATION_TE.get(),
//		HammeringStationRenderer::new);
		event.registerBlockEntityRenderer(CompendiumTileEntities.CRAFTING_ANVIL_TE.get(), CraftingAnvilRenderer::new);
		
		event.registerBlockEntityRenderer(CompendiumTileEntities.ITEM_DISPLAY_TE.get(), ItemDisplayRenderer::new);
		event.registerBlockEntityRenderer(CompendiumTileEntities.HAND_DISPLAY_TE.get(), HandDisplayRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SAWHORSE_STATION_TE.get(),
//		SawhorseStationRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SCRAPPING_TABLE_TE.get(),
//		ScrappingTableRenderer::new);

//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.VAULT_TE.get(), VaultRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.ITEM_DISPLAY_TE.get(), ItemDisplayRenderer::new);
	}

	public static void setEntityRenderers() {

	}

	@SubscribeEvent
	public static void onRegisterModelsEvent(ModelRegistryEvent event) {
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
	}

	@SubscribeEvent
	public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
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
	}

//    public static class GrenadeRenderFactory implements IRenderFactory<GrenadeEntity> {
//	@Override
//	public EntityRenderer<? super GrenadeEntity> createRenderFor(EntityRenderDispatcher manager) {
//	    ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//	    return new ThrownItemRenderer(manager, itemRenderer);
//	}
//    }
}
