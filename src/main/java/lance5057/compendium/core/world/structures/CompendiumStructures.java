//package lance5057.compendium.core.world.structures;
//
//import java.util.function.Supplier;
//
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableMap;
//import com.sun.jna.Structure;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.core.world.dungeon.CompendiumDungeonStructure;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class CompendiumStructures {
//
//    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister
//	    .create(ForgeRegistries.STRUCTURE_FEATURES, Reference.MOD_ID);
//
//    public static final RegistryObject<Structure<NoFeatureConfig>> DUNGEON = registerStructure("dungeon",
//	    () -> (new CompendiumDungeonStructure(NoFeatureConfig.CODEC)));
//
//    private static <T extends Structure<?>> RegistryObject<T> registerStructure(String name, Supplier<T> structure) {
//	return DEFERRED_REGISTRY_STRUCTURE.register(name, structure);
//    }
//
//    public static void setupStructures() {
//	setupMapSpacingAndLand(DUNGEON.get(), new StructureSeparationSettings(50, 30, 2057103), true);
//
//	// Add more structures here and so on
//    }
//
//    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure,
//	    StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
//
//	Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
//
//	if (transformSurroundingLand) {
//	    Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES)
//		    .add(structure).build();
//	}
//
//	DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
//		.putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings)
//		.build();
//    }
//
//    public static void register(IEventBus modBus) {
//	DEFERRED_REGISTRY_STRUCTURE.register(modBus);
//    }
//}
