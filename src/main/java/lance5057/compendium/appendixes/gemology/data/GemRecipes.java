package lance5057.compendium.appendixes.gemology.data;

import java.util.function.Consumer;

import lance5057.compendium.appendixes._template.Appendix_;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.data.builders.TCRecipes;
import net.minecraft.data.IFinishedRecipe;

public class GemRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (_MaterialHelper mh : Appendix_.constructs) {

	    if (mh.hasBase()) {
		_MaterialBasic.buildRecipes(mh.getBase(), recipes, consumer, mh.name);
	    }
	}
    }
}
