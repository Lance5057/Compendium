package lance5057.compendium.core.world.features;

import lance5057.compendium.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.util.NonNullLazy;

public class CompendiumConfiguredFeatures {

    public static final NonNullLazy<ConfiguredFeature<?, ?>> CONFIGURED_DRY_LAKE = () -> CompendiumFeatures.DRY_LAKE
	    .get().withConfiguration(new BlockStateFeatureConfig(Blocks.AIR.getDefaultState()))
	    .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4)));

    public static void registerFeatures() {
	final Registry<ConfiguredFeature<?, ?>> featureRegistry = WorldGenRegistries.CONFIGURED_FEATURE;

	Registry.register(featureRegistry, new ResourceLocation(Reference.MOD_ID, "drylake"),
		CONFIGURED_DRY_LAKE.get());
    }
}
