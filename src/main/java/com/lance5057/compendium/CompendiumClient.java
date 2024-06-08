package com.lance5057.compendium;

import com.lance5057.compendium.client.armor.ModelBoots;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CompendiumClient {
	public static final ModelLayerLocation SABATONS = register("sabatons", "main");

	private static ModelLayerLocation register(String model, String layer) {
		return new ModelLayerLocation(new ResourceLocation(Compendium.MOD_ID, model), layer);
	}

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(SABATONS, ModelBoots::createBodyLayer);
	}
}
