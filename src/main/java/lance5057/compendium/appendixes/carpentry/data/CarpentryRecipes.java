package lance5057.compendium.appendixes.carpentry.data;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryEmptyShingles;
import lance5057.compendium.core.data.builders.TCRecipes;
import net.minecraft.data.IFinishedRecipe;

public class CarpentryRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {

	    if (mh.hasBase()) {
		CarpentryMaterialBasic.buildRecipes(mh.getBase(), recipes, consumer, mh.name, mh.parentMod);
	    }
	    if (mh.hasShingles()) {
		CarpentryEmptyShingles.buildRecipes(mh.getShingles(), recipes, consumer, mh.name, mh.parentMod);
	    }
	    if (mh.hasFurniture())
		CarpentryFurniture.buildRecipes(mh.getFurniture(), recipes, consumer, mh.name, mh.parentMod);
	}
    }
}
