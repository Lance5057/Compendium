package com.lance5057.compendium.workstations;

import java.util.function.Supplier;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRecipe;
import com.lance5057.compendium.workstations.workstation.WorkbenchRecipe;
import com.lance5057.compendium.workstations.workstation.WorkbenchRecipeSerializer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorkstationRecipes {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(BuiltInRegistries.RECIPE_SERIALIZER, Compendium.MOD_ID);

//	public static final Supplier<CraftingAnvilRecipeSerializer> CRAFTING_ANVIL_SERIALIZER = RECIPE_SERIALIZERS
//			.register("crafting_anvil", CraftingAnvilRecipeSerializer::new);
	public static final Supplier<WorkbenchRecipeSerializer> WORKSTATION_SERIALIZER = RECIPE_SERIALIZERS
			.register("workstation", WorkbenchRecipeSerializer::new);
//	public static final Supplier<Serializer> SAWBUCK_SERIALIZER = RECIPE_SERIALIZERS
//			.register("sawbuck", SawBuckRecipe.Serializer::new);
	public static final Supplier<HammeringStationRecipe.Serializer> HAMMERINGSTATION_SERIALIZER = RECIPE_SERIALIZERS
			.register("hammeringstation", HammeringStationRecipe.Serializer::new);
//
	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
			.create(BuiltInRegistries.RECIPE_TYPE, Compendium.MOD_ID);
//
//	public static final Supplier<RecipeType<CraftingAnvilRecipe>> CRAFTING_ANVIL_RECIPE = RECIPE_TYPES
//			.register("crafting_anvil_recipe_type", () -> new RecipeType<CraftingAnvilRecipe>() {
//			});
	public static final Supplier<RecipeType<WorkbenchRecipe>> WORKSTATION_RECIPE = RECIPE_TYPES
			.register("workstation_recipe_type", () -> new RecipeType<WorkbenchRecipe>() {
			});
//	public static final Supplier<RecipeType<SawBuckRecipe>> SAWBUCK_RECIPE = RECIPE_TYPES
//			.register("sawbuck_recipe_type", () -> new RecipeType<SawBuckRecipe>() {
//			});
//	public static final Supplier<RecipeType<HammeringStationRecipe>> HAMMERINGSTATION_RECIPE = RECIPE_TYPES
//			.register("hammeringstation_recipe_type", () -> new RecipeType<HammeringStationRecipe>() {
//			});

	// public static RecipeType<CraftingAnvilRecipe> CRAFTING_ANVI L_RECIPE;
//
//	public static final Supplier<RecipeType<HammeringStationRecipe>> HAMMERING_STATION_SERIALIZER = RECIPE_SERIALIZERS
//			.register("hammering_station", HammeringStationRecipeSerializer::new);
//	
//	public static final Supplier<RecipeType<SawhorseStationRecipe>> SAWHORSE_STATION_SERIALIZER = RECIPE_SERIALIZERS
//			.register("sawhorse_station", SawhorseStationRecipeSerializer::new);
//	public static final Supplier<RecipeType<ScrappingTableRecipe>> SCRAPPING_TABLE_SERIALIZER = RECIPE_SERIALIZERS
//			.register("scrapping_table", ScrappingTableRecipeSerializer::new);
//
//	public static final RegistryObject<SimpleRecipeSerializer<CompendiumShieldRecipe>> CRAFTING_SPECIAL_SHIELD = RECIPE_SERIALIZERS
//			.register("crafting_special_shielddecoration",
//					() -> new SimpleRecipeSerializer<CompendiumShieldRecipe>(CompendiumShieldRecipe::new));
//
//	public static final RecipeType<HammeringStationRecipe> HAMMERING_STATION_RECIPE = RecipeType
//			.register("hammering_station");
//	
//	public static final RecipeType<SawhorseStationRecipe> SAWHORSE_STATION_RECIPE = RecipeType
//			.register("sawhorse_station");
//	public static final RecipeType<ScrappingTableRecipe> SCRAPPING_TABLE_RECIPE = RecipeType
//			.register("scrapping_table");

//	@SubscribeEvent
//	public static void registerRecipeType(RegistryEvent.Register<Block> event) {
//		CRAFTING_ANVIL_RECIPE = RecipeType.register("crafting_anvil");
//	}

	public static void register(IEventBus modBus) {
		RECIPE_SERIALIZERS.register(modBus);
		RECIPE_TYPES.register(modBus);
	}
}
