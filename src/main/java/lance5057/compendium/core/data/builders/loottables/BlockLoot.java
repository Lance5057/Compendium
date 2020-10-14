package lance5057.compendium.core.data.builders.loottables;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.SetCount;

public class BlockLoot extends BlockLootTables {
	{
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
			}

			// Extra Component Materials
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents me = mh.getExtraComponents();

				this.registerDropSelfLootTable(me.SHEET.get());
				this.registerDropSelfLootTable(me.SHINGLES.get());
				this.registerDropSelfLootTable(me.SHINGLES_ALT.get());
				this.registerDropSelfLootTable(me.SHINGLES_BLOCK.get());
				this.registerDropSelfLootTable(me.STAKE.get());
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
