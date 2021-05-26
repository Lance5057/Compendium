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

	    if (mh.hasBase())
		MetalMaterialBasic.addTranslations(mh.getBase(), loc, capName);

	    if (mh.hasVanillaTools())
		MetalVanillaTools.addTranslations(mh.getVanillaTools(), loc, capName);

	    if (mh.hasAdvancedTools())
		MetalAdvancedTools.addTranslations(mh.getAdvancedTools(), loc, capName);

	    if (mh.hasComponents())
		MetalMaterialComponents.addTranslations(mh.getComponents(), loc, capName);

	}
    }
}
