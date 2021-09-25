package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes._template.data.loottables._BlockLoot;
import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.data.IFinishedRecipe;

public class ConstructionDecorations implements MaterialBase {

    public ConstructionDecorations(ConstructionMaterialHelper constructionMaterialHelper) {
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

    public static void registerBlockModels(ConstructionDecorations m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(ConstructionDecorations m, TCItemModels model, String name) {
    }

    public static void addTranslations(ConstructionDecorations m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionDecorations m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionDecorations b, BlockLoot table, String name) {
    }

    public static void buildRecipes(ConstructionDecorations m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }

}
