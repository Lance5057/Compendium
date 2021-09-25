package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.core.data.builders.TCItemTags;

public class CarpentryItemTags {

    public static void registerTags(TCItemTags itp) {

	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {

	    if (mh.hasBase())
		CarpentryMaterialBasic.registerTags(mh.getBase(), itp, mh.name);
	    if (mh.hasFurniture())
		CarpentryFurniture.registerTags(mh.getFurniture(), itp, mh.name);
	}
    }

}
