package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.Reference;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class BasicMetalMaterial implements MaterialBase {

    public RegistryObject<Item> INGOT;
    public RegistryObject<Item> NUGGET;
    public RegistryObject<Block> STORAGE_BLOCK;
    public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;

    public BasicMetalMaterial(MaterialHelper mh) {
	INGOT = mh.ITEMS.register(mh.name + "ingot",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	NUGGET = mh.ITEMS.register(mh.name + "nugget",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "block", () -> new Block(Block.Properties.create(Material.IRON)
		.harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
	STORAGE_ITEMBLOCK = mh.ITEMS.register(mh.name + "itemblock", () -> new BlockNamedItem(STORAGE_BLOCK.get(),
		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

    }

    @Override
    public void setupClient(MaterialHelper mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }
}
