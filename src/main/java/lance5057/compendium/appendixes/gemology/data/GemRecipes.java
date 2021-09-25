package lance5057.compendium.appendixes.gemology.data;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.gemology.AppendixGemology;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.core.data.builders.TCRecipes;
import net.minecraft.data.IFinishedRecipe;

public class GemRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (GemologyMaterialHelper mh :  AppendixGemology.gems) {

	    if (mh.hasBase())
	    {
		BasicGemMaterial.buildRecipes(mh.getBase(), recipes, consumer, mh.name);
	    }
	}
    }
}
