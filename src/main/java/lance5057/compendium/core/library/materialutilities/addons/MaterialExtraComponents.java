//package lance5057.compendium.core.library.materialutilities.addons;
//
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.core.blocks.BlockClimbable;
//import lance5057.compendium.core.blocks.BlockShingles;
//import lance5057.compendium.core.blocks.ComponentBarDoor;
//import lance5057.compendium.core.blocks.ComponentPane;
//import lance5057.compendium.core.blocks.ComponentSheet;
//import lance5057.compendium.core.blocks.ComponentStake;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
//import net.minecraft.block.Block;
//import net.minecraft.block.LadderBlock;
//import net.minecraft.block.SlabBlock;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.StairsBlock;
//import net.minecraft.block.WallBlock;
//import net.minecraft.block.material.Material;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.RenderTypeLookup;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.Item;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//
//public class MaterialExtraComponents implements MaterialBase {
//
//    String matName;
//    String parentMod;
//
//    public RegistryObject<Item> DUST;
//    // public RegistryObject<Item> SHARDS;
//    public RegistryObject<Item> PLATE;
//    public RegistryObject<Item> COIN;
//    public RegistryObject<Item> GEAR;
//    public RegistryObject<Item> ROD;
//
//    public RegistryObject<ComponentStake> STAKE;
//    public RegistryObject<Block> SHINGLES_BLOCK;
//    public RegistryObject<BlockShingles> SHINGLES;
//    public RegistryObject<BlockShingles> SHINGLES_ALT;
//    public RegistryObject<ComponentSheet> SHEET;
//    public RegistryObject<Block> SHEET_BLOCK;
//
//    public RegistryObject<ComponentPane> TRIMMED_WINDOW;
//    public RegistryObject<Block> TRIMMED_WINDOW_BLOCK;
//
//    public RegistryObject<BlockItem> ITEM_STAKE;
//    public RegistryObject<BlockItem> ITEM_SHINGLES_BLOCK;
//    public RegistryObject<BlockItem> ITEM_SHINGLES;
//    public RegistryObject<BlockItem> ITEM_SHINGLES_ALT;
//    public RegistryObject<BlockItem> ITEM_SHEET;
//    public RegistryObject<BlockItem> ITEM_SHEET_BLOCK;
//    public RegistryObject<BlockItem> ITEM_TRIMMED_WINDOW;
//    public RegistryObject<BlockItem> ITEM_TRIMMED_WINDOW_BLOCK;
//    public RegistryObject<BlockItem> ITEM_TILE_SLAB;
//    public RegistryObject<BlockItem> ITEM_TILE_STAIRS;
//
//    public MaterialExtraComponents(MaterialHelper mh) {
//	this.matName = mh.name;
//	this.parentMod = mh.parentMod;
//
//	STAKE = mh.BLOCKS.register(mh.name + "stake", () -> new ComponentStake());
//	SHINGLES_BLOCK = mh.BLOCKS.register(mh.name + "shinglesblock", () -> new Block(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
//	SHINGLES = mh.BLOCKS.register(mh.name + "shingles",
//		() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties
//			.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
//	SHINGLES_ALT = mh.BLOCKS.register(mh.name + "shinglesalt",
//		() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties
//			.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
//	SHEET = mh.BLOCKS.register(mh.name + "sheet", () -> new ComponentSheet(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
//	SHEET_BLOCK = mh.BLOCKS.register(mh.name + "sheetblock", () -> new Block(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
//
//	TRIMMED_WINDOW = mh.BLOCKS.register(mh.name + "windowpane", () -> new ComponentPane(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
//	TRIMMED_WINDOW_BLOCK = mh.BLOCKS.register(mh.name + "windowblock", () -> new Block(Block.Properties
//		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
//
//	ITEM_STAKE = mh.ITEMS.register(mh.name + "itemstake",
//		() -> new BlockItem(STAKE.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_SHINGLES_BLOCK = mh.ITEMS.register(mh.name + "itemshinglesblock", () -> new BlockItem(SHINGLES_BLOCK.get(),
//		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_SHINGLES = mh.ITEMS.register(mh.name + "itemshingles",
//		() -> new BlockItem(SHINGLES.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_SHINGLES_ALT = mh.ITEMS.register(mh.name + "itemshinglesalt",
//		() -> new BlockItem(SHINGLES_ALT.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_SHEET = mh.ITEMS.register(mh.name + "itemsheet",
//		() -> new BlockItem(SHEET.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_SHEET_BLOCK = mh.ITEMS.register(mh.name + "itemsheetblock",
//		() -> new BlockItem(SHEET_BLOCK.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_TRIMMED_WINDOW = mh.ITEMS.register(mh.name + "itemwindowpane", () -> new BlockItem(TRIMMED_WINDOW.get(),
//		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_TRIMMED_WINDOW_BLOCK = mh.ITEMS.register(mh.name + "itemwindow",
//		() -> new BlockItem(TRIMMED_WINDOW_BLOCK.get(),
//			new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//	DUST = mh.ITEMS.register(mh.name + "dust",
//		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	// SHARDS = mh.ITEMS.register(mh.name + "shards", () -> new Item(new
//	// Item.Properties().group(TCItems.TCITEMS)));;
//	PLATE = mh.ITEMS.register(mh.name + "plate",
//		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//	COIN = mh.ITEMS.register(mh.name + "coin",
//		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//	GEAR = mh.ITEMS.register(mh.name + "gear",
//		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//	ROD = mh.ITEMS.register(mh.name + "rod",
//		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//    }
//
//    @Override
//    public void setupClient(MaterialHelper mat) {
//	RenderType cutout = RenderType.getCutout();
//	RenderTypeLookup.setRenderLayer(this.SHINGLES.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.TRIMMED_WINDOW.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.TRIMMED_WINDOW_BLOCK.get(), cutout);
//    }
//
//    @Override
//    public void setup(FMLCommonSetupEvent event) {
//	// TODO Auto-generated method stub
//	
//    }
//
//}
