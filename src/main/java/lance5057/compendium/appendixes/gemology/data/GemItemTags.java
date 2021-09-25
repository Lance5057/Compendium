package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.Compendium;
import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.TCItemTags;

public class GemItemTags {

    public static void registerTags(TCItemTags itp) {

	for (GemologyMaterialHelper mh : AppendixGemology.gems) {

	    if (mh.hasBase())
		BasicGemMaterial.registerTags(mh.getBase(), itp, mh.name);
	}
    }

}
