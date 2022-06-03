//package lance5057.compendium.core.world.structures;
//
//import java.util.function.Supplier;
//
//import lance5057.compendium.Reference;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.registry.WorldGenRegistries;
//import net.minecraft.world.gen.feature.IFeatureConfig;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//
//public class CompendiumConfiguredStructures {
//
//	public static final Supplier<StructureFeature<?, ?>> CONFIGURED_DUNGEON = () -> CompendiumStructures.DUNGEON.get().configured(IFeatureConfig.NONE);
//
//	public static void registerConfiguredStructures() {
//
//		final Registry<StructureFeature<?, ?>> structureRegistry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
//
//		Registry.register(structureRegistry, new ResourceLocation(Reference.MOD_ID, "configured_dungeon"), CONFIGURED_DUNGEON.get());
//	}
//}
