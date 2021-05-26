package lance5057.compendium.appendixes.metallurgy.data.builders.loottables;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;

public class MetalBlockLoot {
    public static void build(BlockLoot table) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    if (mh.hasBase()) {
		MetalMaterialBasic.buildLootTable(mh.getBase(), table, mh.name);
	    }

	}
    }
}
