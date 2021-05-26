package lance5057.compendium.appendixes.construction.data;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.core.data.builders.TCRecipes;
import net.minecraft.data.IFinishedRecipe;

public class ConstructionRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {

	    if (mh.hasBase()) {
		ConstructionMaterialBasic.buildRecipes(mh.getBase(), recipes, consumer, mh.name);
	    }
	}
    }
}
