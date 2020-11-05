package lance5057.compendium.core.workstations;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import lance5057.compendium.core.workstations.recipes.serializers.HammeringStationRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WorkstationRecipes {
	 private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

	    public static final RegistryObject<IRecipeSerializer<HammeringStationRecipe>> HAMMERING_STATION_SERIALIZER = RECIPE_SERIALIZERS.register("hammering_station", HammeringStationRecipeSerializer::new);

	    public static final IRecipeType<HammeringStationRecipe> HAMMERING_STATION_RECIPE = IRecipeType.register("hammering_station");
	    
	    public static void register(IEventBus modBus) {
	        RECIPE_SERIALIZERS.register(modBus);
	    }
}
