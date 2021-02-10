package lance5057.compendium;

import lance5057.compendium.core.recipes.HammerHandedToolRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumRecipes {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
	    .create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);
    
    public static final RegistryObject<IRecipeSerializer<HammerHandedToolRecipe>> HAMMERING_TOOL_SERIALIZER = RECIPE_SERIALIZERS
	    .register("hammering_tool", HammerHandedToolRecipe.Serializer::new);
    
    public static final IRecipeType<HammerHandedToolRecipe> HAMMERING_TOOL_RECIPE = IRecipeType
	    .register("hammering_tool");

    public static void register(IEventBus modBus) {
	RECIPE_SERIALIZERS.register(modBus); 
    }
}
