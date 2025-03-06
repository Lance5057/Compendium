package com.lance5057.compendium;

import com.lance5057.compendium.blocks.ChairBlock;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerBlock;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackBlock;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxBlock;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationBlock;
import com.lance5057.compendium.workstations.sawbuck.SawBuckBlock;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableBlock;
import com.lance5057.compendium.workstations.workbench.WorkbenchBlock;

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

	public static final DeferredBlock<Block> SAW_BUCK = BLOCKS.register("saw_buck", SawBuckBlock::new);
	public static final DeferredBlock<Block> SCRAPPING_TABLE = BLOCKS.register("scrapping_table",
			ScrappingTableBlock::new);

	public static final DeferredBlock<Block> WORKBENCH = BLOCKS.register("workbench", WorkbenchBlock::new);

	public static final DeferredBlock<Block> TOOLRACK = BLOCKS.register("toolrack", ToolRackBlock::new);
	public static final DeferredBlock<Block> COMPONENT_DRAWER = BLOCKS.register("drawer", ComponentDrawerBlock::new);
	
	public static final DeferredBlock<Block> COSMETIC_TOOLBOX = BLOCKS.register("cosmetic_toolbox",
			() -> new CosmeticToolboxBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> CHAIR = BLOCKS.register("chair",
			() -> new ChairBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
}
