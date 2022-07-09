package lance5057.compendium;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class CompendiumWorldGen {

	public static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedOreFeature(
			String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
		return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
	}

	public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
		for (MaterialHelper m : CompendiumMaterials.materials) {
			m.doBiomeEvent(event);
		}
	}
}
