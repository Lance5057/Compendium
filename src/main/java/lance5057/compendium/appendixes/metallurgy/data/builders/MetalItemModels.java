package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCItemModels;

public class MetalItemModels {
    public static void registerModels(TCItemModels model) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase())
		MetalMaterialBasic.registerItemModels(mh.getBase(), model, mh.name);

	    if (mh.hasVanillaTools())
		MetalVanillaTools.registerItemModels(mh.getVanillaTools(), model, mh.name);

	    if (mh.hasComponents())
		MetalMaterialComponents.registerItemModels(mh.getComponents(), model, mh.name);

	    if (mh.hasAdvancedTools())
		MetalAdvancedTools.registerItemModels(mh.getAdvancedTools(), model, mh.name);

	}
    }
}
