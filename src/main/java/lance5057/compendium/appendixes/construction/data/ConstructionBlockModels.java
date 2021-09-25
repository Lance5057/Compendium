package lance5057.compendium.appendixes.construction.data;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionBarsAndChains;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionDecorations;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionDoorsAndGates;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionLighting;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialDungeon;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionWindows;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class ConstructionBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {

	    if (mh.hasBase())
		ConstructionMaterialBasic.registerBlockModels(mh.getBase(), model, mh.name);
	    if (mh.hasDungeon()) {
		ConstructionMaterialDungeon.registerBlockModels(mh.getDungeon(), model, mh.name);
	    }
	    if (mh.hasShingles()) {
		ConstructionMaterialShingles.registerBlockModels(mh.getShingles(), model, mh.name);
	    }
	    if (mh.hasLighting()) {
		ConstructionLighting.registerBlockModels(mh.getLighting(), model, mh.name);
	    }
	    if (mh.hasBarsAndChains()) {
		ConstructionBarsAndChains.registerBlockModels(mh.getBarsAndChains(), model, mh.name);
	    }
	    if (mh.hasDoorsAndGates())
		ConstructionDoorsAndGates.registerBlockModels(mh.getDoorsAndGates(), model, mh.name);
	    if (mh.hasDecorations())
		ConstructionDecorations.registerBlockModels(mh.getDecorations(), model, mh.name);
	    if (mh.hasWindows())
		ConstructionWindows.registerBlockModels(mh.getWindows(), model, mh.name);
	}
    }
}
