package com.lance5057.compendium;

import com.lance5057.compendium.blocks.ChairBlock;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Compendium.MOD_ID);

//	public static final DeferredBlock<Block> WORKBENCH = BLOCKS.register("workbench", WorkbenchBlock::new);
	public static final DeferredBlock<Block> HAMMERING_STATION = BLOCKS.register("hammering_station",
			HammeringStationBlock::new);

	public static final DeferredBlock<Block> CHAIR = BLOCKS.register("chair",
			() -> new ChairBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
}
