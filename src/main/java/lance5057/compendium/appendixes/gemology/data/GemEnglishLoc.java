package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class GemEnglishLoc {
    public static void addTranslations(TCEnglishLoc loc) {
	for (GemologyMaterialHelper mh :  AppendixGemology.gems) {
	    String capName = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);

	    if (mh.hasBase())
		BasicGemMaterial.addTranslations(mh.getBase(), loc, capName);

	}
    }
}
