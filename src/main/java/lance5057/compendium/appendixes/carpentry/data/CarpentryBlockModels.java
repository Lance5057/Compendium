package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryEmptyShingles;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class CarpentryBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {

	    if (mh.hasBase())
		CarpentryMaterialBasic.registerBlockModels(mh.getBase(), model, mh.name, mh.parentMod);
	    if (mh.hasShingles())
		CarpentryEmptyShingles.registerBlockModels(mh.getShingles(), model, mh.name, mh.parentMod);
	    if (mh.hasFurniture())
		CarpentryFurniture.registerBlockModels(mh.getFurniture(), model, mh.name, mh.parentMod);
	}
    }
}
