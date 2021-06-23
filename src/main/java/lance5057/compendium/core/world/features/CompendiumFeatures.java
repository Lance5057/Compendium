package lance5057.compendium.core.world.features;

import lance5057.compendium.Reference;
import lance5057.compendium.core.world.features.feature.DryLakeFeature;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumFeatures {
    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
	    Reference.MOD_ID);

    public static final RegistryObject<Feature<BlockStateFeatureConfig>> DRY_LAKE = FEATURES.register("drylake",
	    () -> new DryLakeFeature(BlockStateFeatureConfig.field_236455_a_));

    public static void register(IEventBus modBus) {
	FEATURES.register(modBus);
    }

}
