package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.TCItemModels;

public class GemItemModels {
    public static void registerModels(TCItemModels model) {
	for (GemologyMaterialHelper mh :  AppendixGemology.gems) {

	    if (mh.hasBase())
		BasicGemMaterial.registerItemModels(mh.getBase(), model, mh.name);

	}
    }
}
