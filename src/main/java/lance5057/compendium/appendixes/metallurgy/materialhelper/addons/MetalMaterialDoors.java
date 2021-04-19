package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.blocks.ComponentDoor;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalMaterialDoors implements MaterialBase {

    public RegistryObject<Block> BASIC_DOOR;
    public RegistryObject<Block> BAR_DOOR;
    public RegistryObject<Block> LOCK_BLOCK_DOOR;
    
    public RegistryObject<BlockNamedItem> BASIC_DOOR_ITEM;
    public RegistryObject<BlockNamedItem> BAR_DOOR_ITEM;
    public RegistryObject<BlockNamedItem> LOCK_BLOCK_DOOR_ITEM;
    
    public MetalMaterialDoors(MetallurgyMaterialHelper metallurgyMaterialHelper) {
	BASIC_DOOR = metallurgyMaterialHelper.BLOCKS.register(metallurgyMaterialHelper.name + "door", () -> new ComponentDoor(Block.Properties.create(Material.IRON)
		.harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE).notSolid()));
	BASIC_DOOR_ITEM = metallurgyMaterialHelper.ITEMS.register(metallurgyMaterialHelper.name + "dooritem", () -> new BlockNamedItem(BASIC_DOOR.get(),
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
