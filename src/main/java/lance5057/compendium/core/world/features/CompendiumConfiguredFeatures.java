//package lance5057.compendium.core.world.features;
//
//import lance5057.compendium.Reference;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraftforge.common.util.NonNullLazy;
//
//public class CompendiumConfiguredFeatures {
//
//	public static final NonNullLazy<ConfiguredFeature<?, ?>> CONFIGURED_DRY_LAKE = () -> CompendiumFeatures.DRY_LAKE
//			.get().configured(new BlockStateFeatureConfig(Blocks.AIR.defaultBlockState()))
//			.decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4)));
//
//	public static void registerFeatures() {
//		final Registry<ConfiguredFeature<?, ?>> featureRegistry = WorldGenRegistries.CONFIGURED_FEATURE;
//
//		Registry.register(featureRegistry, new ResourceLocation(Reference.MOD_ID, "drylake"),
//				CONFIGURED_DRY_LAKE.get());
//	}
//}
