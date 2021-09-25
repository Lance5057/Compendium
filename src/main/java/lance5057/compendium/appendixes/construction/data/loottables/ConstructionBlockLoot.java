package lance5057.compendium.appendixes.construction.data.loottables;

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
import lance5057.compendium.core.data.builders.loottables.BlockLoot;

public class ConstructionBlockLoot {
    public static void build(BlockLoot table) {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {
	    if (mh.hasBase()) {
		ConstructionMaterialBasic.buildLootTable(mh.getBase(), table, mh.name);
	    }

	    if (mh.hasDungeon()) {
		ConstructionMaterialDungeon.buildLootTable(mh.getDungeon(), table, mh.name);
	    }
	    if (mh.hasShingles()) {
		ConstructionMaterialShingles.buildLootTable(mh.getShingles(), table, mh.name);
	    }
	    if (mh.hasLighting()) {
		ConstructionLighting.buildLootTable(mh.getLighting(), table, mh.name);
	    }
	    if (mh.hasBarsAndChains()) {
		ConstructionBarsAndChains.buildLootTable(mh.getBarsAndChains(), table, mh.name);
	    }
	    if (mh.hasDoorsAndGates())
		ConstructionDoorsAndGates.buildLootTable(mh.getDoorsAndGates(), table, mh.name);
	    if (mh.hasDecorations())
		ConstructionDecorations.buildLootTable(mh.getDecorations(), table, mh.name);
	    if (mh.hasWindows())
		ConstructionWindows.buildLootTable(mh.getWindows(), table, mh.name);
	}
    }
}
