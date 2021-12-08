package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryEmptyShingles;
import lance5057.compendium.core.data.builders.TCItemModels;

public class CarpentryItemModels {
    public static void registerModels(TCItemModels model) {
	for (CarpentryMaterialHelper mh :  AppendixCarpentry.woods) {

	    if (mh.hasBase())
		CarpentryMaterialBasic.registerItemModels(mh.getBase(), model, mh.name, mh.parentMod);
	    if (mh.hasShingles())
		CarpentryEmptyShingles.registerItemModels(mh.getShingles(), model, mh.name, mh.parentMod);
	    if (mh.hasFurniture())
		CarpentryFurniture.registerItemModels(mh.getFurniture(), model, mh.name, mh.parentMod);
	}
    }
}
