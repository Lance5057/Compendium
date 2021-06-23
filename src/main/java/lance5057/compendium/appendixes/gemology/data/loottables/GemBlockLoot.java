package lance5057.compendium.appendixes.gemology.data.loottables;

import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;

public class GemBlockLoot {
    public static void build(BlockLoot table) {
	for (GemologyMaterialHelper mh : AppendixGemology.gems) {
	    if (mh.hasBase()) {
		BasicGemMaterial.buildLootTable(mh.getBase(), table, mh.name);
	    }
	} 
    }
}
