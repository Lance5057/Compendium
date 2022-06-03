//package lance5057.compendium;
//
//import lance5057.compendium.core.recipes.HammerHandedToolRecipe;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.RecipeType;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class CompendiumRecipes {
//    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
//	    .create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);
//    
//    public static final RegistryObject<RecipeSerializer<HammerHandedToolRecipe>> HAMMERING_TOOL_SERIALIZER = RECIPE_SERIALIZERS
//	    .register("hammering_tool", HammerHandedToolRecipe.Serializer::new);
//    
//    public static final RecipeType<HammerHandedToolRecipe> HAMMERING_TOOL_RECIPE = RecipeType
//	    .register("hammering_tool");
//
//    public static void register(IEventBus modBus) {
//	RECIPE_SERIALIZERS.register(modBus); 
//    }
//}
