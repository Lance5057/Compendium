package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class CarpentryBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {

	    if (mh.hasBase())
		CarpentryMaterialBasic.registerBlockModels(mh.getBase(), model, mh.name);
	    if (mh.hasComponents())
		CarpentryMaterialComponents.registerBlockModels(mh.getComponents(), model, mh.name);
	}
    }
}
