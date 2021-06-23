package lance5057.compendium.core.world.structures;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import lance5057.compendium.Reference;
import lance5057.compendium.core.world.dungeon.CompendiumDungeonStructure;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumStructures {

    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister
	    .create(ForgeRegistries.STRUCTURE_FEATURES, Reference.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> DUNGEON = registerStructure("dungeon",
	    () -> (new CompendiumDungeonStructure(NoFeatureConfig.field_236558_a_)));

    private static <T extends Structure<?>> RegistryObject<T> registerStructure(String name, Supplier<T> structure) {
	return DEFERRED_REGISTRY_STRUCTURE.register(name, structure);
    }

    public static void setupStructures() {
	setupMapSpacingAndLand(DUNGEON.get(), new StructureSeparationSettings(50, 30, 2057103), true);

	// Add more structures here and so on
    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure,
	    StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {

	Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

	if (transformSurroundingLand) {
	    Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_)
		    .add(structure).build();
	}

	DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
		.putAll(DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings)
		.build();
    }

    public static void register(IEventBus modBus) {
	DEFERRED_REGISTRY_STRUCTURE.register(modBus);
    }
}
