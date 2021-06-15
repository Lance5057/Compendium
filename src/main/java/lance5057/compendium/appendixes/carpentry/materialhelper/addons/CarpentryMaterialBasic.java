package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CarpentryMaterialBasic implements MaterialBase {

    public RegistryObject<RotatedPillarBlock> LOG;
    public RegistryObject<Block> PLANK;
    public RegistryObject<BlockNamedItem> LOG_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> PLANK_ITEMBLOCK;

    public CarpentryMaterialBasic(CarpentryMaterialHelper cmh) {

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	LOG = mat.BLOCKS.register(mat.name + "_log", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD)
		.harvestLevel(3).hardnessAndResistance(3, 4).harvestTool(ToolType.AXE)));
	LOG_ITEMBLOCK = mat.ITEMS.register(mat.name + "_log_itemblock",
		() -> new BlockNamedItem(LOG.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	PLANK = mat.BLOCKS.register(mat.name + "_plank", () -> new Block(Block.Properties.create(Material.WOOD)
		.harvestLevel(3).hardnessAndResistance(3, 4).harvestTool(ToolType.AXE)));
	PLANK_ITEMBLOCK = mat.ITEMS.register(mat.name + "_plank_itemblock",
		() -> new BlockNamedItem(PLANK.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
    }

    public static void registerBlockModels(CarpentryMaterialBasic m, TCBlockModels model, String name) {
	model.axisBlock(m.LOG.get(), model.modLoc("block/material/carpentry/" + name + "/" + name + "_log"),
		model.modLoc("block/material/carpentry/" + name + "/" + name + "_log_top"));
    }

    public static void registerItemModels(CarpentryMaterialBasic m, TCItemModels model, String name) {
	model.forBlockItem(m.LOG_ITEMBLOCK, name);
	model.forBlockItem(m.PLANK_ITEMBLOCK, name);
    }

    public static void addTranslations(CarpentryMaterialBasic m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(CarpentryMaterialBasic m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(CarpentryMaterialBasic b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.LOG.get());
	table.registerDropSelfLootTable(b.PLANK.get());
    }

    public static void buildRecipes(CarpentryMaterialBasic m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }
}
