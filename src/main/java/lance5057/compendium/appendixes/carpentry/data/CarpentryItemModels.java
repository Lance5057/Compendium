package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
import lance5057.compendium.core.data.builders.TCItemModels;

public class CarpentryItemModels {
    public static void registerModels(TCItemModels model) {
	for (CarpentryMaterialHelper mh :  AppendixCarpentry.woods) {

	    if (mh.hasBase())
		CarpentryMaterialBasic.registerItemModels(mh.getBase(), model, mh.name);
	    if (mh.hasComponents())
		CarpentryMaterialComponents.registerItemModels(mh.getComponents(), model, mh.name);
	}
    }
}
