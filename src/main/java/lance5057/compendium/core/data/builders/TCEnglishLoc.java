package lance5057.compendium.core.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaTools;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TCEnglishLoc extends LanguageProvider {

	public TCEnglishLoc(DataGenerator gen) {
		super(gen, Reference.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			String name = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);
			
			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();
				
				this.add(mm.INGOT.get(), name + " Ingot");
				this.add(mm.NUGGET.get(), name + " Nugget");
				this.add(mm.STORAGE_ITEMBLOCK.get(), name + " Block");
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();
				
				this.add(cm.GEM.get(), name + " Gem");
				this.add(cm.NUGGET.get(), name + " Nugget");
				this.add(cm.STORAGE_ITEMBLOCK.get(), name + " Block");
			}

			// Vanilla Component Materials
			if (mh.getVanillaComponents() != null) {
				MaterialVanillaComponents vc = mh.getVanillaComponents();
				
				this.add(vc.ITEM_BARS.get(), name + " Bars");
				this.add(vc.ITEM_DOOR.get(), name + " Door");
				this.add(vc.ITEM_LANTERN.get(), name + " Lantern");
				this.add(vc.ITEM_TRAPDOOR.get(), name + " Trapdoor");
			}

			// Extra Component Materials
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents me = mh.getExtraComponents();
				
				this.add(me.CASING.get(), name + " Casing");
				this.add(me.COIL.get(), name + " Coil");
				this.add(me.COIN.get(), name + " Coin");
				this.add(me.DUST.get(), name + " Dust");
				this.add(me.GEAR.get(), name + " Dust");
				this.add(me.ITEM_SHEET.get(), name + " Sheet");
				this.add(me.ITEM_SHINGLES.get(), name + " Shingles");
				this.add(me.ITEM_SHINGLES_ALT.get(), name + " Joisted Shingles");
				this.add(me.ITEM_SHINGLES_BLOCK.get(), name + " Shingles Block");
				this.add(me.ITEM_STAKE.get(), name + " Stake");
				this.add(me.PLATE.get(), name + " Plate");
				this.add(me.ROD.get(), name + " Rod");
				this.add(me.SPRING.get(), name + " Spring");
				this.add(me.WIRE.get(), name + " Wire");
			}

			// Vanilla Tools Materials
			if (mh.getVanillaTools() != null) {
				MaterialVanillaTools vt = mh.getVanillaTools();
				
				this.add(vt.AXE.get(), name + " Axe");
				this.add(vt.HOE.get(), name + " Hoe");
				this.add(vt.PICKAXE.get(), name + " Pickaxe");
				this.add(vt.SHOVEL.get(), name + " Shovel");
				this.add(vt.SWORD.get(), name + " Sword");
			}
		}
	}

}
