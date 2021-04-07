package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalMaterialBasic implements MaterialBase {

    public RegistryObject<Item> INGOT;
    public RegistryObject<Item> NUGGET;
    public RegistryObject<Block> STORAGE_BLOCK;
    public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;

    public MetalMaterialBasic(MetallurgyMaterialHelper metallurgyMaterialHelper) {
	INGOT = metallurgyMaterialHelper.ITEMS.register(metallurgyMaterialHelper.name + "ingot",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	NUGGET = metallurgyMaterialHelper.ITEMS.register(metallurgyMaterialHelper.name + "nugget",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	STORAGE_BLOCK = metallurgyMaterialHelper.BLOCKS.register(metallurgyMaterialHelper.name + "block", () -> new Block(Block.Properties.create(Material.IRON)
		.harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
	STORAGE_ITEMBLOCK = metallurgyMaterialHelper.ITEMS.register(metallurgyMaterialHelper.name + "itemblock", () -> new BlockNamedItem(STORAGE_BLOCK.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }
}
