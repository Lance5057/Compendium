package lance5057.compendium.appendixes._template.data.loottables;

import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;

public class _BlockLoot {
    public static void build(_BlockLoot table) {
	for (_MaterialHelper mh : Appendix_.constructs) {
	    if (mh.hasBase()) {
		_MaterialBasic.buildLootTable(mh.getBase(), table, mh.name);
	    }
	} 
    }
}
