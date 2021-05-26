package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class MetalBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase())
		MetalMaterialBasic.registerBlockModels(mh.getBase(), model, mh.name);
	}
    }
}
