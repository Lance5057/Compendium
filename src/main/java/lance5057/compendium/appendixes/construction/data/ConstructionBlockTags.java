package lance5057.compendium.appendixes.construction.data;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionBarsAndChains;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionLighting;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.core.data.builders.TCBlockTags;

public class ConstructionBlockTags {
    public static void registerTags(TCBlockTags btp) {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {

	    if (mh.hasBase())
		ConstructionMaterialBasic.registerBlockTags(mh.getBase(), btp, mh.name);
	    if(mh.hasShingles())
		ConstructionMaterialShingles.registerBlockTags(mh.getShingles(), btp, mh.name);
	    if (mh.hasLighting()) {
		ConstructionLighting.registerBlockTags(mh.getLighting(), btp, mh.name);
	    }
	    if (mh.hasBarsAndChains()) {
		ConstructionBarsAndChains.registerBlockTags(mh.getBarsAndChains(), btp, mh.name);
	    }
	}
    }
}
