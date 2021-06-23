package lance5057.compendium.appendixes.gemology.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class BasicGemMaterial implements MaterialBase {
    
    int drops = 1;

    public RegistryObject<Item> GEM;
    public RegistryObject<Item> SHARD;
    public RegistryObject<Block> STORAGE_BLOCK;
    public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;

    public BasicGemMaterial(GemologyMaterialHelper mh, int drops) {

	GEM = mh.ITEMS.register(mh.name + "gem",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	SHARD = mh.ITEMS.register(mh.name + "nugget",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "block", () -> new Block(Block.Properties.create(Material.IRON)
		.harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
	STORAGE_ITEMBLOCK = mh.ITEMS.register(mh.name + "itemblock", () -> new BlockNamedItem(STORAGE_BLOCK.get(),
		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	
	this.drops = drops;
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	// TODO Auto-generated method stub
	
    }
    
    public static void registerBlockModels(BasicGemMaterial m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(BasicGemMaterial m, TCItemModels model, String name) {
    }

    public static void addTranslations(BasicGemMaterial m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(BasicGemMaterial m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(BasicGemMaterial b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.STORAGE_BLOCK.get());
    }

    public static void buildRecipes(BasicGemMaterial m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }
}
