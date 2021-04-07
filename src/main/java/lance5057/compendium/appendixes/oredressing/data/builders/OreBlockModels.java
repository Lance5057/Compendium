package lance5057.compendium.appendixes.oredressing.data.builders;

import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialDenseOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialSparseStoneOre;
import lance5057.compendium.core.data.builders.TCBlockModels;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;

public class OreBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {

	    if (mh.hasOre()) {
		MaterialOre b = mh.getOre();

		model.getVariantBuilder(b.ORE.get()).partialState().addModels(ConfiguredModel.allRotations(
			model.models().withExistingParent(mh.name + "ore", model.modLoc("block/bases/ore")).texture("1",
				model.modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
			true));
	    }
	    if (mh.hasDenseOre()) {
		MaterialDenseOre b = mh.getDenseOre();

		model.getVariantBuilder(b.ORE.get()).partialState().addModels(ConfiguredModel.allRotations(
			model.models().withExistingParent(mh.name + "denseore", model.modLoc("block/bases/denseore"))
				.texture("1", model.modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
			true));
	    }
	    if (mh.hasSparseOre()) {
		MaterialSparseStoneOre b = mh.getSparseOre();

		model.getVariantBuilder(b.ORE.get()).partialState().addModels(ConfiguredModel.allRotations(
			model.models().withExistingParent(mh.name + "sparseore", model.modLoc("block/bases/sparseore"))
				.texture("1", model.modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
			true));
	    }
	}
    }
}
