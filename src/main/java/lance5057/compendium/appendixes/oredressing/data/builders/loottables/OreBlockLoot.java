package lance5057.compendium.appendixes.oredressing.data.builders.loottables;

import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialDenseOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialSparseStoneOre;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;

public class OreBlockLoot {
    public static void build(BlockLoot table) {
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {

	    if (mh.hasOre()) {
		MaterialOre b = mh.getOre();

		table.registerDropSelfLootTable(b.ORE.get());
	    }
	    
	    if (mh.hasDenseOre()) {
		MaterialDenseOre b = mh.getDenseOre();

		table.registerDropSelfLootTable(b.ORE.get());
	    }
	    
	    if (mh.hasSparseOre()) {
		MaterialSparseStoneOre b = mh.getSparseOre();

		table.registerDropSelfLootTable(b.ORE.get());
	    }

	}
    }
}
