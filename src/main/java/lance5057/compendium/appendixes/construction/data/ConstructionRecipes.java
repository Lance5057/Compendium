package lance5057.compendium.appendixes.construction.data;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionLighting;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialDungeon;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.core.data.builders.TCRecipes;
import net.minecraft.data.IFinishedRecipe;

public class ConstructionRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {

	    if (mh.hasBase()) {
		ConstructionMaterialBasic.buildRecipes(mh.getBase(), recipes, consumer, mh.name);
	    }
	    if (mh.hasDungeon()) {
		ConstructionMaterialDungeon.buildRecipes(mh.getDungeon(), recipes, consumer, mh.name);
	    }
	    if (mh.hasShingles()) {
		ConstructionMaterialShingles.buildRecipes(mh.getShingles(), recipes, consumer, mh.name);
	    }
	    if (mh.hasLighting()) {
		ConstructionLighting.buildRecipes(mh.getLighting(), recipes, consumer, mh.name);
	    }
	}
    }
}
