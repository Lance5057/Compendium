package com.lance5057.compendium.workstations;

import java.util.function.Supplier;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRecipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorkstationRecipes {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(BuiltInRegistries.RECIPE_SERIALIZER, Compendium.MOD_ID);

	public static final Supplier<HammeringStationRecipe.Serializer> HAMMERINGSTATION_SERIALIZER = RECIPE_SERIALIZERS
			.register("hammeringstation", HammeringStationRecipe.Serializer::new);

	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
			.create(BuiltInRegistries.RECIPE_TYPE, Compendium.MOD_ID);

	public static final Supplier<RecipeType<HammeringStationRecipe>> HAMMERINGSTATION_RECIPE = RECIPE_TYPES
			.register("hammeringstation_recipe_type", () -> new RecipeType<HammeringStationRecipe>() {
			});

	public static void register(IEventBus modBus) {
		RECIPE_SERIALIZERS.register(modBus);
		RECIPE_TYPES.register(modBus);
	}
}
