package lance5057.compendium.appendixes.construction.data;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialDungeon;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.core.data.builders.TCItemModels;

public class ConstructionItemModels {
    public static void registerModels(TCItemModels model) {
	for (ConstructionMaterialHelper mh :  AppendixConstruction.constructs) {

	    if (mh.hasBase())
		ConstructionMaterialBasic.registerItemModels(mh.getBase(), model, mh.name);
	    if (mh.hasDungeon()) {
		ConstructionMaterialDungeon.registerItemModels(mh.getDungeon(), model, mh.name);
	    }
	    if (mh.hasShingles()) {
		ConstructionMaterialShingles.registerItemModels(mh.getShingles(), model, mh.name);
	    }
	}
    }
}
