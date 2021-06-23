package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.Compendium;
import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.data.builders.TCItemTags;

public class GemItemTags {

    public static void registerTags(TCItemTags itp) {
	Compendium.logger.info("\t - Item Tags");

	for (_MaterialHelper mh : Appendix_.constructs) {

	    if (mh.hasBase())
		_MaterialBasic.registerTags(mh.getBase(), itp, mh.name);
	}
    }

}
