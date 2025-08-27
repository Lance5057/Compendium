package com.lance5057.compendium;

import com.lance5057.compendium.blocks.BasicDecorativeBlock;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerBlock;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackBlock;
import com.lance5057.compendium.blocks.bed.FancyBedBlock;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableBlock;
import com.lance5057.compendium.blocks.fence.FancyFenceBlock;
import com.lance5057.compendium.blocks.shingles.slanted.ShinglesSlantedBlock;
import com.lance5057.compendium.blocks.shingles.slanted.cap.ShinglesCapSlanted;
import com.lance5057.compendium.blocks.table.TableBlock;
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

	public static final DeferredBlock<Block> TABLE = BLOCKS.register("table",
			() -> new TableBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> FANCY_BED = BLOCKS.register("fancy_bed",
			() -> new FancyBedBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> FANCY_FENCE = BLOCKS.register("fancy_fence",
			() -> new FancyFenceBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> CLOTHED_TABLE = BLOCKS.register("clothed_table",
			() -> new ClothedTableBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> WINDOW = BLOCKS.register("window",
			() -> new BasicDecorativeBlock(Properties.ofFullCopy(Blocks.GLASS)));

	public static final DeferredBlock<Block> SHINGLES_SLANTED = BLOCKS.register("shingles_slanted",
			() -> new ShinglesSlantedBlock(Blocks.TERRACOTTA.defaultBlockState(),
					Properties.ofFullCopy(Blocks.TERRACOTTA).noOcclusion()));

	public static final DeferredBlock<Block> SHINGLES_CAP_SLANTED = BLOCKS.register("shingles_cap_slanted",
			() -> new ShinglesCapSlanted(Properties.ofFullCopy(Blocks.TERRACOTTA).noOcclusion()));

}
