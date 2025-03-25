package com.lance5057.compendium;

import com.lance5057.compendium.entities.SeatEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
			.create(BuiltInRegistries.ENTITY_TYPE, Compendium.MOD_ID);

	public static final DeferredHolder<EntityType<?>, EntityType<Entity>> SEAT = ENTITIES.register("seat",
			() -> EntityType.Builder.of(SeatEntity::new, MobCategory.MISC).build("seat"));
}
