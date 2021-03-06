package lance5057.compendium.core.workstations;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.toolrecipes.CompendiumShieldRecipe;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
import lance5057.compendium.core.workstations.recipes.serializers.HammeringStationRecipeSerializer;
import lance5057.compendium.core.workstations.recipes.serializers.SawhorseStationRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WorkstationRecipes {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
	    .create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<HammeringStationRecipe>> HAMMERING_STATION_SERIALIZER = RECIPE_SERIALIZERS
	    .register("hammering_station", HammeringStationRecipeSerializer::new);
    public static final RegistryObject<IRecipeSerializer<CraftingAnvilRecipe>> CRAFTING_ANVIL_SERIALIZER = RECIPE_SERIALIZERS
	    .register("crafting_anvil", CraftingAnvilRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<SawhorseStationRecipe>> SAWHORSE_STATION_SERIALIZER = RECIPE_SERIALIZERS
	    .register("sawhorse_station", SawhorseStationRecipeSerializer::new);

    public static final RegistryObject<SpecialRecipeSerializer<CompendiumShieldRecipe>> CRAFTING_SPECIAL_SHIELD = RECIPE_SERIALIZERS
	    .register("crafting_special_shielddecoration", () -> new SpecialRecipeSerializer<CompendiumShieldRecipe>(CompendiumShieldRecipe::new));


    public static final IRecipeType<HammeringStationRecipe> HAMMERING_STATION_RECIPE = IRecipeType
	    .register("hammering_station");
    public static final IRecipeType<CraftingAnvilRecipe> CRAFTING_ANVIL_RECIPE = IRecipeType.register("crafting_anvil");
    public static final IRecipeType<SawhorseStationRecipe> SAWHORSE_STATION_RECIPE = IRecipeType
	    .register("sawhorse_station");

    public static void register(IEventBus modBus) {
	RECIPE_SERIALIZERS.register(modBus);
    }
}
