package lance5057.compendium.appendixes.carpentry.data.loottables;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;

public class CarpentryBlockLoot {
    public static void build(BlockLoot table) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {
	    if (mh.hasBase()) {
		CarpentryMaterialBasic.buildLootTable(mh.getBase(), table, mh.name);
	    }
	    if (mh.hasComponents()) {
		CarpentryMaterialComponents.buildLootTable(mh.getComponents(), table, mh.name);
	    }
	} 
    }
}
