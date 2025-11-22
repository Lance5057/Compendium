package com.lance5057.compendium.workstations;

import java.util.function.Supplier;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationRecipe;
import com.lance5057.compendium.workstations.sawbuck.SawBuckRecipe;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableRecipe;
import com.lance5057.compendium.workstations.workbench.WorkbenchMaterialRecipe;
import com.lance5057.compendium.workstations.workbench.WorkbenchRecipe;

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

	public static final Supplier<SawBuckRecipe.Serializer> SAWBUCK_SERIALIZER = RECIPE_SERIALIZERS.register("sawbuck",
			SawBuckRecipe.Serializer::new);

	public static final Supplier<ScrappingTableRecipe.Serializer> SCRAPPINGTABLE_SERIALIZER = RECIPE_SERIALIZERS
			.register("scrappingtable", ScrappingTableRecipe.Serializer::new);

	public static final Supplier<WorkbenchRecipe.Serializer> WORKBENCH_SERIALIZER = RECIPE_SERIALIZERS
			.register("workbench", WorkbenchRecipe.Serializer::new);

	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
			.create(BuiltInRegistries.RECIPE_TYPE, Compendium.MOD_ID);

	public static final Supplier<RecipeType<HammeringStationRecipe>> HAMMERINGSTATION_RECIPE = RECIPE_TYPES
			.register("hammeringstation_recipe_type", () -> new RecipeType<HammeringStationRecipe>() {
			});

	public static final Supplier<RecipeType<SawBuckRecipe>> SAWBUCK_RECIPE = RECIPE_TYPES
			.register("sawbuck_recipe_type", () -> new RecipeType<SawBuckRecipe>() {
			});

	public static final Supplier<RecipeType<ScrappingTableRecipe>> SCRAPPINGTABLE_RECIPE = RECIPE_TYPES
			.register("scrappingtable_recipe_type", () -> new RecipeType<ScrappingTableRecipe>() {
			});

	public static final Supplier<RecipeType<WorkbenchRecipe>> WORKBENCH_RECIPE = RECIPE_TYPES
			.register("workbench_recipe_type", () -> new RecipeType<WorkbenchRecipe>() {
			});

	public static final Supplier<RecipeType<WorkbenchMaterialRecipe>> WORKBENCH_MATERIAL_RECIPE = RECIPE_TYPES
			.register("workbench_material_recipe_type", () -> new RecipeType<WorkbenchMaterialRecipe>() {
			});

	public static void register(IEventBus modBus) {
		RECIPE_SERIALIZERS.register(modBus);
		RECIPE_TYPES.register(modBus);
	}
}
