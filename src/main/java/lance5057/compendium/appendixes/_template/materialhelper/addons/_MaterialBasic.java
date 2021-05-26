package lance5057.compendium.appendixes._template.materialhelper.addons;

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
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class _MaterialBasic implements MaterialBase {

    public _MaterialBasic(_MaterialHelper cmh) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(_MaterialBasic m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(_MaterialBasic m, TCItemModels model, String name) {
    }

    public static void addTranslations(_MaterialBasic m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(_MaterialBasic m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(_MaterialBasic b, _BlockLoot table,
	    String name) {
    }

    public static void buildRecipes(_MaterialBasic m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }
}
