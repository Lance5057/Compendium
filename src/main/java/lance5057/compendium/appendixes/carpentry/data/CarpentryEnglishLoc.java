package lance5057.compendium.appendixes.carpentry.data;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class CarpentryEnglishLoc {
    public static void addTranslations(TCEnglishLoc loc) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {
	    String capName = mh.name.substring(0, 1).toUpperCase() + mh.name.substring(1);

	    if (mh.hasBase())
		CarpentryMaterialBasic.addTranslations(mh.getBase(), loc, capName);
	    if (mh.hasComponents())
		CarpentryMaterialComponents.addTranslations(mh.getComponents(), loc, capName);
	}
    }
}
