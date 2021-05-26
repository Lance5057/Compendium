package lance5057.compendium;

import lance5057.compendium.core.entities.GrenadeEntity;
import lance5057.compendium.core.workstations.client.CraftingAnvilRenderer;
import lance5057.compendium.core.workstations.client.HammeringStationRenderer;
import lance5057.compendium.core.workstations.client.SawhorseStationRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Reference.MOD_ID)
public class CompendiumClient {
    public static void setRenderLayers() {
//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    mh.client();
//	}

	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(CompendiumBlocks.HAMMERING_STATION.get(), cutout);
	// RenderTypeLookup.setRenderLayer(CompendiumBlocks.CRAFTING_ANVIL.get(),
	// RenderType.getTranslucent());
    }

    public static void setTERenderers() {
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.HAMMERING_STATION_TE.get(),
		HammeringStationRenderer::new);
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.CRAFTING_ANVIL_TE.get(),
		CraftingAnvilRenderer::new);
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SAWHORSE_STATION_TE.get(),
		SawhorseStationRenderer::new);

//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.VAULT_TE.get(), VaultRenderer::new);
//	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.ITEM_DISPLAY_TE.get(), ItemDisplayRenderer::new);
    }

    public static void setEntityRenderers() {

    }

    @SubscribeEvent
    public static void onRegisterModelsEvent(ModelRegistryEvent event) {
	RenderingRegistry.registerEntityRenderingHandler(CompendiumEntities.GRENADE_ENTITY.get(),
		new IRenderFactory<GrenadeEntity>() {
		    @Override
		    public EntityRenderer<GrenadeEntity> createRenderFor(EntityRendererManager manager) {
			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			return new SpriteRenderer<>(manager, itemRenderer);
		    }
		});
    }

    public static class GrenadeRenderFactory implements IRenderFactory<GrenadeEntity> {
	@Override
	public EntityRenderer<? super GrenadeEntity> createRenderFor(EntityRendererManager manager) {
	    ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	    return new SpriteRenderer<>(manager, itemRenderer);
	}
    }
}
