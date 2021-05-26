package lance5057.compendium.appendixes._template.data;

import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.data.builders.TCItemModels;

public class _ItemModels {
    public static void registerModels(TCItemModels model) {
	for (_MaterialHelper mh :  Appendix_.constructs) {

	    if (mh.hasBase())
		_MaterialBasic.registerItemModels(mh.getBase(), model, mh.name);

	}
    }
}
