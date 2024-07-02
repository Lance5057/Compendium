package com.lance5057.compendium;

import com.lance5057.compendium.workstations.workstation.WorkbenchBlock;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Compendium.MOD_ID);
	
	public static final DeferredBlock<Block> WORKBENCH = BLOCKS.register("workbench", WorkbenchBlock::new);
}
