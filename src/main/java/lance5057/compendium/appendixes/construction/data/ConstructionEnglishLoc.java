package lance5057.compendium.appendixes.construction.data;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class ConstructionEnglishLoc {
    public static void addTranslations(TCEnglishLoc loc) {
	for (ConstructionMaterialHelper mh :  AppendixConstruction.constructs) {
	    String capName = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);

	    if (mh.hasBase())
		ConstructionMaterialBasic.addTranslations(mh.getBase(), loc, capName);

	}
    }
}
