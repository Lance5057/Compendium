package lance5057.compendium.core.workstations;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.SawBuckRecipe;
import lance5057.compendium.core.workstations.recipes.WorkstationRecipe;
import lance5057.compendium.core.workstations.recipes.serializers.CraftingAnvilRecipeSerializer;
import lance5057.compendium.core.workstations.recipes.serializers.SawBuckRecipeSerializer;
import lance5057.compendium.core.workstations.recipes.serializers.WorkstationRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorkstationRecipes {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

	public static final RegistryObject<RecipeSerializer<CraftingAnvilRecipe>> CRAFTING_ANVIL_SERIALIZER = RECIPE_SERIALIZERS
			.register("crafting_anvil", CraftingAnvilRecipeSerializer::new);
	public static final RegistryObject<RecipeSerializer<WorkstationRecipe>> WORKSTATION_SERIALIZER = RECIPE_SERIALIZERS
			.register("workstation", WorkstationRecipeSerializer::new);
	public static final RegistryObject<RecipeSerializer<SawBuckRecipe>> SAWBUCK_SERIALIZER = RECIPE_SERIALIZERS
			.register("sawbuck", SawBuckRecipeSerializer::new);
 
	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
			.create(Registry.RECIPE_TYPE_REGISTRY, Reference.MOD_ID);

	public static final RegistryObject<RecipeType<CraftingAnvilRecipe>> CRAFTING_ANVIL_RECIPE = RECIPE_TYPES
			.register("crafting_anvil_recipe_type", () -> new RecipeType<CraftingAnvilRecipe>() {
			});
	public static final RegistryObject<RecipeType<WorkstationRecipe>> WORKSTATION_RECIPE = RECIPE_TYPES
			.register("workstation_recipe_type", () -> new RecipeType<WorkstationRecipe>() {
			});
	public static final RegistryObject<RecipeType<SawBuckRecipe>> SAWBUCK_RECIPE = RECIPE_TYPES
			.register("sawbuck_recipe_type", () -> new RecipeType<SawBuckRecipe>() {
			});

	// public static RecipeType<CraftingAnvilRecipe> CRAFTING_ANVIL_RECIPE;
//
//	public static final RegistryObject<RecipeSerializer<HammeringStationRecipe>> HAMMERING_STATION_SERIALIZER = RECIPE_SERIALIZERS
//			.register("hammering_station", HammeringStationRecipeSerializer::new);
//	
//	public static final RegistryObject<RecipeSerializer<SawhorseStationRecipe>> SAWHORSE_STATION_SERIALIZER = RECIPE_SERIALIZERS
//			.register("sawhorse_station", SawhorseStationRecipeSerializer::new);
//	public static final RegistryObject<RecipeSerializer<ScrappingTableRecipe>> SCRAPPING_TABLE_SERIALIZER = RECIPE_SERIALIZERS
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
