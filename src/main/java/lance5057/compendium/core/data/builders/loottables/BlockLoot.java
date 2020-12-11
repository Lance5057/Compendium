package lance5057.compendium.core.data.builders.loottables;

import lance5057.compendium.TCBlocks;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;

public class BlockLoot extends BlockLootTables {
	{
		this.registerDropSelfLootTable(TCBlocks.HAMMERING_STATION.get());
		this.registerDropSelfLootTable(TCBlocks.CRAFTING_ANVIL.get());
		this.registerDropSelfLootTable(TCBlocks.SAWHORSE_STATION.get());
		
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();

				this.registerDropSelfLootTable(mm.STORAGE_BLOCK.get());
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();

				this.registerDropSelfLootTable(cm.STORAGE_BLOCK.get());
			}

			// Vanilla Component Materials
			if (mh.getVanillaComponents() != null) {
				MaterialVanillaComponents vc = mh.getVanillaComponents();

				this.registerDropSelfLootTable(vc.BARS.get());
				this.registerDropSelfLootTable(vc.DOOR.get());
				this.registerDropSelfLootTable(vc.LANTERN.get());
				this.registerDropSelfLootTable(vc.TRAPDOOR.get());
				this.registerDropSelfLootTable(vc.CHAIN.get());
			}

			// Extra Component Materials
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents me = mh.getExtraComponents();

				this.registerDropSelfLootTable(me.SHEET.get());
				this.registerDropSelfLootTable(me.SHINGLES.get());
				this.registerDropSelfLootTable(me.SHINGLES_ALT.get());
				this.registerDropSelfLootTable(me.SHINGLES_BLOCK.get());
				this.registerDropSelfLootTable(me.STAKE.get());
				this.registerDropSelfLootTable(me.BIGCHAIN.get());
				this.registerDropSelfLootTable(me.BRAZIER.get());
				this.registerDropSelfLootTable(me.SOUL_BRAZIER.get());
				this.registerDropSelfLootTable(me.CHAINLINK_BARS.get());
				this.registerDropSelfLootTable(me.CHAINLINK_BLOCK.get());
				this.registerDropSelfLootTable(me.LADDER.get());
				this.registerDropSelfLootTable(me.SHEET_BLOCK.get());
				this.registerDropSelfLootTable(me.TOP_BARS.get());
				this.registerDropSelfLootTable(me.TRIMMED_WINDOW.get());
				this.registerDropSelfLootTable(me.TRIMMED_WINDOW_BLOCK.get());
				this.registerDropSelfLootTable(me.WALL.get());
				this.registerDropSelfLootTable(me.SMALL_TILE.get());
				this.registerDropSelfLootTable(me.BAR_DOOR.get());
				this.registerDropSelfLootTable(me.DIAMONDBARS.get());
				this.registerDropSelfLootTable(me.DIAMONDBARSFLIP.get());
				this.registerDropSelfLootTable(me.DIAMONDBARSTOP.get());
				this.registerDropSelfLootTable(me.DUNGEON_TILE.get());
				this.registerDropSelfLootTable(me.ENCASED_GLOWSTONE.get());
			}
			if (mh.getOre() != null) {
				MaterialOre mo = mh.getOre();
				if (mh.getIngot() != null) {
					this.registerDropSelfLootTable(mo.ORE.get());
				}
				if (mh.getGem() != null) {
					this.registerLootTable(mo.ORE.get(),
							BlockLootTables.droppingWithSilkTouch(mo.ORE.get(),
									ItemLootEntry.builder(mh.getGem().GEM.get())
											.acceptFunction(SetCount.builder(RandomValueRange.of(1, 3)))
											.acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
				}
			}
		}
	}
}
