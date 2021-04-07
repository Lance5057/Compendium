//package lance5057.compendium.core.library.materialutilities.addons;
//
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
//import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.item.BlockNamedItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//
//public class PremadeMaterial implements MaterialBase {
//
//    public RegistryObject<Item> INGOT;
//    public RegistryObject<Item> NUGGET;
//    public RegistryObject<Block> STORAGE_BLOCK;
//    public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;
//
//    public Ingredient PRESET_INGOT;
//    public Ingredient PRESET_NUGGET;
//    public Ingredient PRESET_STORAGEBLOCK;
//
//    public PremadeMaterial(MaterialHelper mh, Ingredient ingot, Ingredient nugget, Ingredient storage) {
//
//	if (ingot == null)
//	    INGOT = mh.ITEMS.register(mh.name + "ingot",
//		    () -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	else
//	    this.PRESET_INGOT = ingot;
//
//	if (nugget == null)
//	    NUGGET = mh.ITEMS.register(mh.name + "nugget",
//		    () -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	else
//	    this.PRESET_NUGGET = nugget;
//
//	if (storage == null) {
//	    STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "block", () -> new Block(Block.Properties.create(Material.IRON)
//		    .harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
//	    STORAGE_ITEMBLOCK = mh.ITEMS.register(mh.name + "itemblock", () -> new BlockNamedItem(STORAGE_BLOCK.get(),
//		    new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	} else
//	    this.PRESET_STORAGEBLOCK = storage;
//    }
//
//    @Override
//    public void setupClient(MaterialHelperBase mat) {
//	// TODO Auto-generated method stub
//	
//    }
//
//    @Override
//    public void setup(FMLCommonSetupEvent event) {
//	// TODO Auto-generated method stub
//	
//    }
//
//}
