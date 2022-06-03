package lance5057.compendium.core.data.builders.loottables;

import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.loot.BlockLoot;

public class TCBlockLoot extends BlockLoot {
	{
		
		for(MaterialHelper m : CompendiumMaterials.materials)
		{
			m.addBlockLoot(this);
		}
		
//		this.dropSelf(CompendiumBlocks.HAMMERING_STATION.get());
//		this.dropSelf(CompendiumBlocks.CRAFTING_ANVIL.get());
//		this.dropSelf(CompendiumBlocks.SAWHORSE_STATION.get());
//		this.dropSelf(CompendiumBlocks.SCRAPPING_TABLE.get());
//		this.dropSelf(CompendiumBlocks.DRYLAKEBED.get());

//	this.dropSelf(CompendiumBlocks.SHINGLES.get());
//	this.dropSelf(CompendiumBlocks.SHINGLES_ALT.get());
//	this.dropSelf(CompendiumBlocks.SHINGLES_BLOCK.get());

//		MetalBlockLoot.build(this);
//		OreBlockLoot.build(this);
//		ConstructionBlockLoot.build(this);
//		CarpentryBlockLoot.build(this);
//		GemBlockLoot.build(this);

		// this.dropSelf(CompendiumBlocks.VAULT.get());

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    // Meltable Materials
//	    if (mh.getPremade() != null) {
//		PremadeMaterial mm = mh.getPremade();
//
//		if (mm.STORAGE_BLOCK != null)
//		    this.dropSelf(mm.STORAGE_BLOCK.get());
//	    }
//
//	    // Meltable Materials
//	    if (mh.getIngot() != null) {
//		BasicMetalMaterial mm = mh.getIngot();
//
//		this.dropSelf(mm.STORAGE_BLOCK.get());
//	    }
//
//	    // Craftable Materials
//	    if (mh.getGem() != null) {
//		BasicGemMaterial cm = mh.getGem();
//
//		this.dropSelf(cm.STORAGE_BLOCK.get());
//	    }
//
//	    // Vanilla Component Materials
//	    if (mh.getVanillaComponents() != null) {
//		MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//		this.dropSelf(vc.BARS.get());
//		this.registerLootTable(vc.DOOR.get(), BlockLootTables::registerDoor);
//		this.dropSelf(vc.LANTERN.get());
//		this.dropSelf(vc.TRAPDOOR.get());
//		this.dropSelf(vc.CHAIN.get());
//	    }
//
//	    // Extra Component Materials
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents me = mh.getExtraComponents();
//
//		this.dropSelf(me.SHEET.get());
//		this.dropSelf(me.SHINGLES.get());
//		this.dropSelf(me.SHINGLES_ALT.get());
//		this.dropSelf(me.SHINGLES_BLOCK.get());
//		this.dropSelf(me.STAKE.get());
//		this.dropSelf(me.SHEET_BLOCK.get());
//		this.dropSelf(me.TRIMMED_WINDOW.get());
//		this.dropSelf(me.TRIMMED_WINDOW_BLOCK.get());
//	    }
//
//	    // Advanced Extra Component Materials
//	    if (mh.getAdvancedComponents() != null) {
//		MaterialAdvancedExtraComponents me = mh.getAdvancedComponents();
//
//		this.dropSelf(me.BIGCHAIN.get());
//		this.dropSelf(me.BRAZIER.get());
//		this.dropSelf(me.SOUL_BRAZIER.get());
//		this.dropSelf(me.CHAINLINK_BARS.get());
//		this.dropSelf(me.CHAINLINK_BLOCK.get());
//		this.dropSelf(me.LADDER.get());
//		this.dropSelf(me.TOP_BARS.get());
//		this.dropSelf(me.WALL.get());
//		this.dropSelf(me.SMALL_TILE.get());
//		this.registerLootTable(me.BAR_DOOR.get(), BlockLootTables::registerDoor);
//		this.dropSelf(me.DIAMONDBARS.get());
//		this.dropSelf(me.DIAMONDBARSFLIP.get());
//		this.dropSelf(me.DIAMONDBARSTOP.get());
//		this.dropSelf(me.DUNGEON_TILE.get());
//		this.dropSelf(me.ENCASED_GLOWSTONE.get());
//		this.dropSelf(me.VAULT.get());
//		this.dropSelf(me.STATUE.get());
//	    }
//
//	    if (mh.getOre() != null) {
//		MaterialOre mo = mh.getOre();
//		if (mh.getIngot() != null) {
//		    this.dropSelf(mo.ORE.get());
//		}
//		if (mh.getGem() != null) {
//		    this.registerLootTable(mo.ORE.get(),
//			    BlockLootTables.droppingWithSilkTouch(mo.ORE.get(),
//				    ItemLootEntry.builder(mh.getGem().GEM.get())
//					    .acceptFunction(SetCount.builder(RandomValueBounds.of(1, 3)))
//					    .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
//		}
//	    }
//	}
	}
}
