package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes._template.data.loottables._BlockLoot;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.data.IFinishedRecipe;

public class ConstructionStorage implements MaterialBase {

    public ConstructionStorage(_MaterialHelper cmh) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(ConstructionStorage m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(ConstructionStorage m, TCItemModels model, String name) {
    }

    public static void addTranslations(ConstructionStorage m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionStorage m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionStorage b, _BlockLoot table, String name) {
    }

    public static void buildRecipes(ConstructionStorage m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }

}
