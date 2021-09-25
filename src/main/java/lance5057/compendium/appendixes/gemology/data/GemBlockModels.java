package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class GemBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (GemologyMaterialHelper mh : AppendixGemology.gems) {

	    if (mh.hasBase())
		BasicGemMaterial.registerBlockModels(mh.getBase(), model, mh.name);

	}
    }
}
