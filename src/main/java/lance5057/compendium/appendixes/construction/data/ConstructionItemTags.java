package lance5057.compendium.appendixes.construction.data;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.core.data.builders.TCItemTags;

public class ConstructionItemTags {

    public static void registerTags(TCItemTags itp) {

	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {

	    if (mh.hasBase())
		ConstructionMaterialBasic.registerTags(mh.getBase(), itp, mh.name);
	}
    }

}
