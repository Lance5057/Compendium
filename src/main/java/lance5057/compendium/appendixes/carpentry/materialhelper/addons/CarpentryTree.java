package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraftforge.fml.RegistryObject;

public class CarpentryTree implements MaterialBase {
    public RegistryObject<RotatedPillarBlock> LOG;
    public RegistryObject<BlockNamedItem> LOG_ITEMBLOCK;

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(CarpentryTree m, TCBlockModels model, String name) {
	model.axisBlock(m.LOG.get(), model.modLoc("block/material/carpentry/" + name + "/" + name + "_log"),
		model.modLoc("block/material/carpentry/" + name + "/" + name + "_log_top"));
    }

    public static void registerItemModels(CarpentryTree m, TCItemModels model, String name) {
	model.forBlockItem(m.LOG_ITEMBLOCK, name);
    }

    public static void addTranslations(CarpentryTree m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(CarpentryTree m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(CarpentryTree b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.LOG.get());
    }

    public static void buildRecipes(CarpentryTree m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }
}
