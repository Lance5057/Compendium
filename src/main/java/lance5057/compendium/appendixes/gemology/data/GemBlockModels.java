package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.data.builders.TCBlockModels;

public class GemBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (_MaterialHelper mh : Appendix_.constructs) {

	    if (mh.hasBase())
		_MaterialBasic.registerBlockModels(mh.getBase(), model, mh.name);

	}
    }
}
