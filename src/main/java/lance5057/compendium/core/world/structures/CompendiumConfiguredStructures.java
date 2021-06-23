package lance5057.compendium.core.world.structures;

import java.util.function.Supplier;

import lance5057.compendium.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class CompendiumConfiguredStructures {

	public static final Supplier<StructureFeature<?, ?>> CONFIGURED_DUNGEON = () -> CompendiumStructures.DUNGEON.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

	public static void registerConfiguredStructures() {

		final Registry<StructureFeature<?, ?>> structureRegistry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

		Registry.register(structureRegistry, new ResourceLocation(Reference.MOD_ID, "configured_dungeon"), CONFIGURED_DUNGEON.get());
	}
}
