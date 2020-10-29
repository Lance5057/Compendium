package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.TCItems;
import lance5057.compendium.core.blocks.BlockClimbable;
import lance5057.compendium.core.blocks.BlockShingles;
import lance5057.compendium.core.blocks.ComponentSheet;
import lance5057.compendium.core.blocks.ComponentStake;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialExtraComponents implements MaterialBase {

	String matName;
	String parentMod;

	public RegistryObject<Item> DUST;
	//public RegistryObject<Item> SHARDS;
	public RegistryObject<Item> PLATE;
	public RegistryObject<Item> COIN;
	public RegistryObject<Item> GEAR;
	public RegistryObject<Item> ROD;
	public RegistryObject<Item> COIL;
	public RegistryObject<Item> SPRING;
	public RegistryObject<Item> CASING;
	public RegistryObject<Item> WIRE;

	public RegistryObject<ComponentStake> STAKE;
	public RegistryObject<Block> SHINGLES_BLOCK;
	public RegistryObject<BlockShingles> SHINGLES;
	public RegistryObject<BlockShingles> SHINGLES_ALT;
	public RegistryObject<ComponentSheet> SHEET;
	public RegistryObject<BlockClimbable> BIGCHAIN;

	public RegistryObject<BlockItem> ITEM_STAKE;
	public RegistryObject<BlockItem> ITEM_SHINGLES_BLOCK;
	public RegistryObject<BlockItem> ITEM_SHINGLES;
	public RegistryObject<BlockItem> ITEM_SHINGLES_ALT;
	public RegistryObject<BlockItem> ITEM_SHEET;
	public RegistryObject<BlockItem> ITEM_BIGCHAIN;

	public MaterialExtraComponents(MaterialHelper mh) {
		this.matName = mh.name;
		this.parentMod = mh.parentMod;

		STAKE = mh.BLOCKS.register(mh.name + "stake", () -> new ComponentStake());
		SHINGLES_BLOCK = mh.BLOCKS.register(mh.name + "shinglesblock", () -> new Block(
				Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
		SHINGLES = mh.BLOCKS.register(mh.name + "shingles",
				() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties.create(Material.IRON)
						.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
		SHINGLES_ALT = mh.BLOCKS.register(mh.name + "shinglesalt",
				() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties.create(Material.IRON)
						.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
		SHEET = mh.BLOCKS.register(mh.name + "sheet", () -> new ComponentSheet(Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
		BIGCHAIN = mh.BLOCKS.register(mh.name + "bigchain", () -> new BlockClimbable(Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

		ITEM_STAKE = mh.ITEMS.register(mh.name + "itemstake",
				() -> new BlockItem(STAKE.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES_BLOCK = mh.ITEMS.register(mh.name + "itemshinglesblock",
				() -> new BlockItem(SHINGLES_BLOCK.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES = mh.ITEMS.register(mh.name + "itemshingles",
				() -> new BlockItem(SHINGLES.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES_ALT = mh.ITEMS.register(mh.name + "itemshinglesalt",
				() -> new BlockItem(SHINGLES_ALT.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHEET = mh.ITEMS.register(mh.name + "itemsheet",
				() -> new BlockItem(SHEET.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_BIGCHAIN = mh.ITEMS.register(mh.name + "itembigchain",
				() -> new BlockItem(BIGCHAIN.get(), new Item.Properties().group(TCItems.TCITEMS)));

		DUST = mh.ITEMS.register(mh.name + "dust", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		//SHARDS = mh.ITEMS.register(mh.name + "shards", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		PLATE = mh.ITEMS.register(mh.name + "plate", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		COIN = mh.ITEMS.register(mh.name + "coin", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		GEAR = mh.ITEMS.register(mh.name + "gear", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		ROD = mh.ITEMS.register(mh.name + "rod", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		COIL = mh.ITEMS.register(mh.name + "coil", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		SPRING = mh.ITEMS.register(mh.name + "spring", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		CASING = mh.ITEMS.register(mh.name + "casing", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		WIRE = mh.ITEMS.register(mh.name + "wire", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(this.SHINGLES.get(), cutout);
		RenderTypeLookup.setRenderLayer(this.BIGCHAIN.get(), cutout);
	}

	@Override
	public void setupModels(MaterialHelper mat) {

	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
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

}
