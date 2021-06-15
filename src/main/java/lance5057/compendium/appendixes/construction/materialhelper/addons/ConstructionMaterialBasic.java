package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ConstructionMaterialBasic implements MaterialBase {

    public ConstructionMaterialBasic(ConstructionMaterialHelper cmh) {
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

    public static void registerBlockModels(ConstructionMaterialBasic m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(ConstructionMaterialBasic m, TCItemModels model, String name) {
    }

    public static void addTranslations(ConstructionMaterialBasic m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionMaterialBasic m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionMaterialBasic b, BlockLoot table,
	    String name) {
	//table.registerDropSelfLootTable(b.STORAGE_BLOCK.get());
    }

    public static void buildRecipes(ConstructionMaterialBasic m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }

    public static void registerBlockTags(ConstructionMaterialBasic base, TCBlockTags btp, String name) {
	// TODO Auto-generated method stub
	
    }
}
