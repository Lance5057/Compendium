package lance5057.compendium.appendixes.gemology.data;

import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class GemEnglishLoc {
    public static void addTranslations(TCEnglishLoc loc) {
	for (_MaterialHelper mh :  Appendix_.constructs) {
	    String capName = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);

	    if (mh.hasBase())
		_MaterialBasic.addTranslations(mh.getBase(), loc, capName);

	}
    }
}
