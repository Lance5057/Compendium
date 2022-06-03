//package lance5057.compendium.core.world.features;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.core.world.features.feature.DryLakeFeature;
//import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class CompendiumFeatures {
//	private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
//			Reference.MOD_ID);
//
//	public static final RegistryObject<Feature<BlockStateConfiguration>> DRY_LAKE = FEATURES.register("drylake",
//			() -> new DryLakeFeature(BlockStateFeatureConfig.CODEC));
//
//	public static void register(IEventBus modBus) {
//		FEATURES.register(modBus);
//	}
//
//}
