package lance5057.compendium.appendixes.gemology.materialhelper.addons.loottables;

import lance5057.compendium.CompendiumBlocks;
import net.minecraft.data.loot.BlockLootTables;

public class BlockLoot extends BlockLootTables {
    {
	BasicMetalMaterial();
    }

    void BasicMetalMaterial() {
	this.registerDropSelfLootTable(null);
    }
}
