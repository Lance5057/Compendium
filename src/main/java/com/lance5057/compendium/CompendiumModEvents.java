package com.lance5057.compendium;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CompendiumModEvents {
	@SubscribeEvent
	public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
	    event.register(CompendiumStyles.STYLE_DATA);
	}
}
