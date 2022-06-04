package lance5057.compendium.core.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TCEnglishLoc extends LanguageProvider {

	public TCEnglishLoc(DataGenerator gen) {
		super(gen, Reference.MOD_ID, "en_us");
		Compendium.logger.info("\t - EN_US Localization");
	}

	@Override
	protected void addTranslations() {

		for(MaterialHelper m : CompendiumMaterials.materials)
		{
			m.addTranslations(this);
		}
		
		this.add("itemGroup.compendium.materials", "Compendium Materials");
//		this.add("CreativeModeTab.compendium.workstations", "Compendium Workstations");
//
//		this.add(CompendiumItems.CRAFTING_ANVIL_ITEMBLOCK.get(), "Crafting Anvil");
//		this.add(CompendiumItems.HAMMERING_STATION_ITEMBLOCK.get(), "Hammering Station");
//		this.add(CompendiumItems.SAWHORSE_STATION_ITEMBLOCK.get(), "Sawbuck");
//
//		this.add(CompendiumItems.CRUDE_HAMMER.get(), "Crude Hammer");
//		this.add(CompendiumItems.MINER_GRENADE.get(), "Mining Grenade");
//
//		MetalEnglishLoc.addTranslations(this);
//		GemEnglishLoc.addTranslations(this);
//		OreEnglishLoc.addTranslations(this);
//		ConstructionEnglishLoc.addTranslations(this);
//		CarpentryEnglishLoc.addTranslations(this);

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//
//	    String name = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);
//
//	    // Premade Materials
//	    if (mh.getPremade() != null) {
//		PremadeMaterial pm = mh.getPremade();
//
//		if (pm.INGOT != null)
//		    this.add(pm.INGOT.get(), name + " Ingot");
//		if (pm.NUGGET != null)
//		    this.add(pm.NUGGET.get(), name + " Nugget");
//		if (pm.STORAGE_ITEMBLOCK != null)
//		    this.add(pm.STORAGE_ITEMBLOCK.get(), name + " Block");
//	    }
//
//	    // Meltable Materials
//	    if (mh.getIngot() != null) {
//		BasicMetalMaterial mm = mh.getIngot();
//
//		this.add(mm.INGOT.get(), name + " Ingot");
//		this.add(mm.NUGGET.get(), name + " Nugget");
//		this.add(mm.STORAGE_ITEMBLOCK.get(), name + " Block");
//	    }
//
//	    // Craftable Materials
//	    if (mh.getGem() != null) {
//		BasicGemMaterial cm = mh.getGem();
//
//		this.add(cm.GEM.get(), name + " Gem");
//		this.add(cm.SHARD.get(), name + " Nugget");
//		this.add(cm.STORAGE_ITEMBLOCK.get(), name + " Block");
//	    }
//
//	    // Vanilla Component Materials
//	    if (mh.getVanillaComponents() != null) {
//		MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//		this.add(vc.ITEM_BARS.get(), name + " Bars");
//		this.add(vc.ITEM_DOOR.get(), name + " Door");
//		this.add(vc.ITEM_LANTERN.get(), name + " Lantern");
//		this.add(vc.ITEM_TRAPDOOR.get(), name + " Trapdoor");
//		this.add(vc.ITEM_CHAIN.get(), name + " Chain");
//	    }
//
//	    // Extra Component Materials
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents me = mh.getExtraComponents();
//
//		this.add(me.COIN.get(), name + " Coin");
//		this.add(me.DUST.get(), name + " Dust");
//		this.add(me.GEAR.get(), name + " Gear");
//
//		this.add(me.ITEM_SHEET.get(), name + " Sheet");
//		this.add(me.ITEM_SHEET_BLOCK.get(), name + " Sheet Block");
//		this.add(me.ITEM_SHINGLES.get(), name + " Shingles");
//		this.add(me.ITEM_SHINGLES_ALT.get(), name + " Joisted Shingles");
//		this.add(me.ITEM_SHINGLES_BLOCK.get(), name + " Shingles Block");
//		this.add(me.ITEM_STAKE.get(), name + " Stake");
//
//		this.add(me.PLATE.get(), name + " Plate");
//
//		this.add(me.ROD.get(), name + " Rod");
//
//		this.add(me.ITEM_TRIMMED_WINDOW.get(), name + " Trimmed Window Pane");
//		this.add(me.ITEM_TRIMMED_WINDOW_BLOCK.get(), name + " Trimmed Window Block");
//	    }
//
//	    // Advanced Extra Component Materials
//	    if (mh.getAdvancedComponents() != null) {
//		MaterialAdvancedExtraComponents me = mh.getAdvancedComponents();
//
//		this.add(me.CASING.get(), name + " Casing");
//		this.add(me.CLASP.get(), name + " Clasp");
//		this.add(me.COIL.get(), name + " Coil");
//		this.add(me.FILIGREE.get(), name + " Filigree");
//		this.add(me.FOIL.get(), name + " Foil");
//		this.add(me.JUMPRINGS.get(), name + " Jump Rings");
//		this.add(me.ITEM_BAR_DOOR.get(), name + " Bar Door");
//		this.add(me.ITEM_BIGCHAIN.get(), name + " Big Chain");
//		this.add(me.ITEM_BRAZIER.get(), name + " Brazier");
//		this.add(me.ITEM_SOUL_BRAZIER.get(), name + " Soul Brazier");
//		this.add(me.ITEM_DUNGEON_TILE.get(), name + " Dungeon Tile");
//		this.add(me.ITEM_DIAMONDBARS.get(), name + " Diamond Patterned Bars");
//		this.add(me.ITEM_DIAMONDBARSFLIP.get(), name + " Arrow Patterned Bars");
//		this.add(me.ITEM_DIAMONDBARSTOP.get(), name + " Diamond Patterned Bars Top");
//		this.add(me.ITEM_ENCASED_GLOWSTONE.get(), name + " Encased Glowstone");
//		this.add(me.ITEM_CHAINLINK_BARS.get(), name + " Chainlink Fence");
//		this.add(me.ITEM_CHAINLINK_BLOCK.get(), name + " Chainlink Block");
//		this.add(me.ITEM_SMALL_TILE.get(), name + " Small Tiles");
//		this.add(me.VAULT_ITEMBLOCK.get(), name + " Vault");
//		this.add(me.RINGSHANK.get(), name + " Ring Shank");
//		this.add(me.SETTING.get(), name + " Setting");
//		this.add(me.SPRING.get(), name + " Spring");
//		this.add(me.WIRE.get(), name + " Wire");
//		this.add(me.ITEM_WALL.get(), name + " Wall");
//		this.add(me.RIVETS.get(), name + " Casing");
//		this.add(me.ITEM_TOP_BARS.get(), name + " Bar Spikes");
//		this.add(me.ITEM_LADDER.get(), name + " Ladder");
//		this.add(me.VAULT.get(), name + " Vault");
//	    }
//
//	    // Vanilla Tools Materials
//	    if (mh.getVanillaTools() != null) {
//		MaterialVanillaTools vt = mh.getVanillaTools();
//
//		this.add(vt.AXE.get(), name + " Axe");
//		this.add(vt.HOE.get(), name + " Hoe");
//		this.add(vt.PICKAXE.get(), name + " Pickaxe");
//		this.add(vt.SHOVEL.get(), name + " Shovel");
//		this.add(vt.SWORD.get(), name + " Sword");
//	    }
//
//	    // Vanilla Tools Materials
//	    if (mh.getExtraTools() != null) {
//		MaterialExtraTools et = mh.getExtraTools();
//
//		this.add(et.HAMMER.get(), name + " Hammer");
//		this.add(et.SAW.get(), name + " Saw");
//	    }
//	}
	}

}
