package lance5057.compendium.core.data.builders.loottables;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.core.workstations.workstation.WorkstationBlock;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.storage.loot.LootTable;

public class TCBlockLoot extends BlockLoot {
	{
		this.dropSelf(CompendiumBlocks.CRAFTING_ANVIL.get());
		this.dropSelf(CompendiumBlocks.WORKSTATION.get());
		this.add(CompendiumBlocks.WORKSTATION.get(), TCBlockLoot::createWorkbench);
		this.dropSelf(CompendiumBlocks.TEST.get());
		this.dropSelf(CompendiumBlocks.SAW_BUCK.get());
		this.dropSelf(CompendiumBlocks.HAMMERING_STATION.get());
	}

	public static LootTable.Builder createWorkbench(Block p_124138_) {
		return createSinglePropConditionTable(p_124138_, WorkstationBlock.HALF, Half.TOP);
	}
}
