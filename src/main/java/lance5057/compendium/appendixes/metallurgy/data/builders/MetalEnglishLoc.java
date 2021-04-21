package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class MetalEnglishLoc {
    public static void addTranslations(TCEnglishLoc loc) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    String capName = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		loc.add(b.INGOT.get(), capName + " Ingot");
		loc.add(b.NUGGET.get(), capName + " Nugget");
		loc.add(b.STORAGE_ITEMBLOCK.get(), capName + " Block");
	    }

	    if (mh.hasVanillaTools()) {
		MetalVanillaTools b = mh.getVanillaTools();

		loc.add(b.AXE.get(), capName + " Axe");
		loc.add(b.HOE.get(), capName + " Hoe");
		loc.add(b.PICKAXE.get(), capName + " Pickaxe");
		loc.add(b.SHOVEL.get(), capName + " Shovel");
		loc.add(b.SWORD.get(), capName + " Sword");
	    }

	    if (mh.hasAdvancedTools()) {
		MetalAdvancedTools b = mh.getAdvancedTools();

		loc.add(b.BOW.get(), capName + " Bow");
		loc.add(b.HAMMER.get(), capName + " Hammer");
		loc.add(b.SAW.get(), capName + " Saw");
	    }

	    if (mh.hasComponents()) {
		MetalMaterialComponents b = mh.getComponents();

		loc.add(b.SETTING.get(), capName + " Setting");
		loc.add(b.JUMPRINGS.get(), capName + " Jumprings");
		loc.add(b.FILIGREE.get(), capName + " Filigrees");
		loc.add(b.FOIL.get(), capName + " Foil");
		loc.add(b.COIL.get(), capName + " Coil");
		loc.add(b.SPRING.get(), capName + " Spring");
		loc.add(b.CASING.get(), capName + " Casing");
		loc.add(b.WIRE.get(), capName + " Wire");
		loc.add(b.CLASP.get(), capName + " Clasp");
		loc.add(b.RINGSHANK.get(), capName + " Ring Shank");
		loc.add(b.RIVETS.get(), capName + " Rivets");
		loc.add(b.PLATE.get(), capName + " Plate");
		loc.add(b.COIN.get(), capName + " Coin");
		loc.add(b.GEAR.get(), capName + " Gear");
		loc.add(b.ROD.get(), capName + " Rod");
		loc.add(b.DUST.get(), capName + " Dust");
		loc.add(b.TINYDUST.get(), capName + " Tiny Dust");
		loc.add(b.KEY.get(), capName + " Key");
	    }
	}
    }
}
