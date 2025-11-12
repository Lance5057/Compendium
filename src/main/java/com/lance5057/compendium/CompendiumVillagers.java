package com.lance5057.compendium;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumVillagers {
	public static DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE,
			Compendium.MOD_ID);
	public static DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister
			.create(Registries.VILLAGER_PROFESSION, Compendium.MOD_ID);
	
	
	
	public static void register(IEventBus modEventBus) {
		
		POI_TYPES.register(modEventBus);
		PROFESSIONS.register(modEventBus);
	}
}
