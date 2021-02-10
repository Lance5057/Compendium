package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.blocks.BlockClimbable;
import lance5057.compendium.core.blocks.ComponentBarDoor;
import lance5057.compendium.core.blocks.ComponentPane;
import lance5057.compendium.core.blocks.VaultBlock;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialAdvancedExtraComponents implements MaterialBase {

    String matName;
    String parentMod;

    public RegistryObject<VaultBlock> VAULT;
    public RegistryObject<BlockClimbable> BIGCHAIN;
    public RegistryObject<ComponentPane> TOP_BARS;
    public RegistryObject<ComponentPane> CHAINLINK_BARS;
    public RegistryObject<Block> CHAINLINK_BLOCK;
    public RegistryObject<Block> BRAZIER;
    public RegistryObject<Block> SOUL_BRAZIER;
    public RegistryObject<WallBlock> WALL;
    public RegistryObject<LadderBlock> LADDER;
    public RegistryObject<Block> SMALL_TILE;
    public RegistryObject<ComponentBarDoor> BAR_DOOR;
    public RegistryObject<Block> DUNGEON_TILE;
    public RegistryObject<Block> ENCASED_GLOWSTONE;
    public RegistryObject<ComponentPane> DIAMONDBARS;
    public RegistryObject<ComponentPane> DIAMONDBARSTOP;
    public RegistryObject<ComponentPane> DIAMONDBARSFLIP;
    public RegistryObject<SlabBlock> TILE_SLAB;
    public RegistryObject<StairsBlock> TILE_STAIRS;

    public RegistryObject<BlockNamedItem> VAULT_ITEMBLOCK;

    public RegistryObject<Item> SETTING;
    public RegistryObject<Item> JUMPRINGS;
    public RegistryObject<Item> FILIGREE;
    public RegistryObject<Item> FOIL;
    public RegistryObject<Item> COIL;
    public RegistryObject<Item> SPRING;
    public RegistryObject<Item> CASING;
    public RegistryObject<Item> WIRE;
    public RegistryObject<Item> CLASP;
    public RegistryObject<Item> RINGSHANK;
    public RegistryObject<Item> RIVETS;

    public RegistryObject<BlockItem> ITEM_BIGCHAIN;
    public RegistryObject<BlockItem> ITEM_TOP_BARS;
    public RegistryObject<BlockItem> ITEM_CHAINLINK_BARS;
    public RegistryObject<BlockItem> ITEM_CHAINLINK_BLOCK;
    public RegistryObject<BlockItem> ITEM_BRAZIER;
    public RegistryObject<BlockItem> ITEM_SOUL_BRAZIER;
    public RegistryObject<BlockItem> ITEM_WALL;
    public RegistryObject<BlockItem> ITEM_SMALL_TILE;
    public RegistryObject<BlockItem> ITEM_BAR_DOOR;
    public RegistryObject<BlockItem> ITEM_DUNGEON_TILE;
    public RegistryObject<BlockItem> ITEM_ENCASED_GLOWSTONE;
    public RegistryObject<BlockItem> ITEM_DIAMONDBARS;
    public RegistryObject<BlockItem> ITEM_DIAMONDBARSTOP;
    public RegistryObject<BlockItem> ITEM_DIAMONDBARSFLIP;
    public RegistryObject<BlockItem> ITEM_LADDER;

    public MaterialAdvancedExtraComponents(MaterialHelper mh) {
	this.matName = mh.name;
	this.parentMod = mh.parentMod;

	VAULT = mh.BLOCKS.register(mh.name + "vault", () -> new VaultBlock());

	BIGCHAIN = mh.BLOCKS.register(mh.name + "bigchain", () -> new BlockClimbable(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	TOP_BARS = mh.BLOCKS.register(mh.name + "topbars", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	CHAINLINK_BARS = mh.BLOCKS.register(mh.name + "chainlink", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	CHAINLINK_BLOCK = mh.BLOCKS.register(mh.name + "chainlinkblock", () -> new Block(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	BRAZIER = mh.BLOCKS.register(mh.name + "brazier", () -> new Block(Block.Properties.create(Material.IRON)
		.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).setLightLevel((state) -> {
		    return 15;
		})));
	SOUL_BRAZIER = mh.BLOCKS.register(mh.name + "soulbrazier", () -> new Block(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).setLightLevel((state) -> {
		    return 15;
		})));
	WALL = mh.BLOCKS.register(mh.name + "wall", () -> new WallBlock(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	LADDER = mh.BLOCKS.register(mh.name + "ladder", () -> new LadderBlock(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	SMALL_TILE = mh.BLOCKS.register(mh.name + "smalltile", () -> new Block(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	BAR_DOOR = mh.BLOCKS.register(mh.name + "bardoor", () -> new ComponentBarDoor(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	DUNGEON_TILE = mh.BLOCKS.register(mh.name + "dungeontile", () -> new Block(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	ENCASED_GLOWSTONE = mh.BLOCKS.register(mh.name + "encasedglowstone", () -> new Block(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).setLightLevel((state) -> {
		    return 15;
		})));
	DIAMONDBARS = mh.BLOCKS.register(mh.name + "diamondbars", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	DIAMONDBARSTOP = mh.BLOCKS.register(mh.name + "diamondbarstop", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	DIAMONDBARSFLIP = mh.BLOCKS.register(mh.name + "diamondbarsflip", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

	VAULT_ITEMBLOCK = mh.ITEMS.register(mh.name + "vault_itemblock",
		() -> new BlockNamedItem(VAULT.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_BIGCHAIN = mh.ITEMS.register(mh.name + "itembigchain",
		() -> new BlockItem(BIGCHAIN.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_TOP_BARS = mh.ITEMS.register(mh.name + "itemtopbars",
		() -> new BlockItem(TOP_BARS.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_CHAINLINK_BARS = mh.ITEMS.register(mh.name + "itemchainlink", () -> new BlockItem(CHAINLINK_BARS.get(),
		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_CHAINLINK_BLOCK = mh.ITEMS.register(mh.name + "itemchainlinkblock",
		() -> new BlockItem(CHAINLINK_BLOCK.get(),
			new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_BRAZIER = mh.ITEMS.register(mh.name + "itembrazier",
		() -> new BlockItem(BRAZIER.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_SOUL_BRAZIER = mh.ITEMS.register(mh.name + "itemsoulbrazier",
		() -> new BlockItem(SOUL_BRAZIER.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_WALL = mh.ITEMS.register(mh.name + "itemwall",
		() -> new BlockItem(WALL.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_SMALL_TILE = mh.ITEMS.register(mh.name + "itemsmalltile",
		() -> new BlockItem(SMALL_TILE.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_BAR_DOOR = mh.ITEMS.register(mh.name + "itembardoor",
		() -> new BlockItem(BAR_DOOR.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_DUNGEON_TILE = mh.ITEMS.register(mh.name + "itemdungeontile",
		() -> new BlockItem(DUNGEON_TILE.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_ENCASED_GLOWSTONE = mh.ITEMS.register(mh.name + "itemencasedglowstone",
		() -> new BlockItem(ENCASED_GLOWSTONE.get(),
			new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_DIAMONDBARS = mh.ITEMS.register(mh.name + "itemdiamondbars",
		() -> new BlockItem(DIAMONDBARS.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_DIAMONDBARSTOP = mh.ITEMS.register(mh.name + "itemdiamondbarstop",
		() -> new BlockItem(DIAMONDBARSTOP.get(),
			new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_DIAMONDBARSFLIP = mh.ITEMS.register(mh.name + "itemdiamondbarsflip",
		() -> new BlockItem(DIAMONDBARSFLIP.get(),
			new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ITEM_LADDER = mh.ITEMS.register(mh.name + "itemladder", () -> new BlockItem(LADDER.get(),
		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	COIL = mh.ITEMS.register(mh.name + "coil",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	SPRING = mh.ITEMS.register(mh.name + "spring",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	CASING = mh.ITEMS.register(mh.name + "casing",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	WIRE = mh.ITEMS.register(mh.name + "wire",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	CLASP = mh.ITEMS.register(mh.name + "clasp",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	RINGSHANK = mh.ITEMS.register(mh.name + "ringshank",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	RIVETS = mh.ITEMS.register(mh.name + "rivets",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	SETTING = mh.ITEMS.register(mh.name + "setting",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	JUMPRINGS = mh.ITEMS.register(mh.name + "jumprings",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	FILIGREE = mh.ITEMS.register(mh.name + "filigree",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	FOIL = mh.ITEMS.register(mh.name + "foil",
		() -> new Item(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
    }

    @Override
    public void setupClient(MaterialHelper mat) {
	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(this.BIGCHAIN.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.BRAZIER.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.SOUL_BRAZIER.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.CHAINLINK_BARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.CHAINLINK_BLOCK.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.LADDER.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.TOP_BARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.BAR_DOOR.get(), RenderType.getTranslucent());
	RenderTypeLookup.setRenderLayer(this.DIAMONDBARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.DIAMONDBARSTOP.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.DIAMONDBARSFLIP.get(), cutout);
	RenderTypeLookup.setRenderLayer(this.ENCASED_GLOWSTONE.get(), cutout);
    }

    @Override
    public void setupModels(MaterialHelper mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setupItems(MaterialHelper mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setupBlocks(MaterialHelper mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

}
