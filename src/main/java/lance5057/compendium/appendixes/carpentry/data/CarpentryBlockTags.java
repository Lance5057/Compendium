package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryEmptyShingles;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.core.data.builders.TCBlockTags;

public class CarpentryBlockTags {
    public static void registerTags(TCBlockTags btp) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {
	    if (mh.hasBase())
		CarpentryMaterialBasic.registerBlockTags(mh.getBase(), btp, mh.name);
	    if (mh.hasShingles())
		CarpentryEmptyShingles.registerBlockTags(mh.getShingles(), btp, mh.name);
	    if (mh.hasFurniture())
		CarpentryFurniture.registerBlockTags(mh.getFurniture(), btp, mh.name);
	}
    }
}
