package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.blocks.BlockChain;
import lance5057.compendium.core.blocks.BlockBigChain;
import lance5057.compendium.core.blocks.ComponentPane;
import lance5057.compendium.core.blocks.ComponentStake;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.fml.RegistryObject;

public class ConstructionBarsAndChains implements MaterialBase {

    public RegistryObject<BlockChain> CHAIN;
    public RegistryObject<BlockBigChain> BIG_CHAIN;
    public RegistryObject<ComponentStake> STAKE;
    public RegistryObject<ComponentPane> TOP_BARS;
    public RegistryObject<ComponentPane> CHAINLINK_BARS;
    public RegistryObject<Block> CHAINLINK_BLOCK;
    public RegistryObject<ComponentPane> DIAMONDBARS;
    public RegistryObject<ComponentPane> DIAMONDBARSFLIP;

    public RegistryObject<BlockNamedItem> CHAIN_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> BIG_CHAIN_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> STAKE_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> TOP_BARS_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> CHAINLINK_BARS_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> CHAINLINK_BLOCK_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DIAMONDBARS_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DIAMONDBARSFLIP_ITEMBLOCK;

    public ConstructionBarsAndChains(ConstructionMaterialHelper constructionMaterialHelper) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(CHAIN.get(), cutout);
	RenderTypeLookup.setRenderLayer(BIG_CHAIN.get(), cutout);
	RenderTypeLookup.setRenderLayer(TOP_BARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(CHAINLINK_BARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(CHAINLINK_BLOCK.get(), cutout);
	RenderTypeLookup.setRenderLayer(DIAMONDBARS.get(), cutout);
	RenderTypeLookup.setRenderLayer(DIAMONDBARSFLIP.get(), cutout);
    }

    @Override
    public void setup(MaterialHelperBase mat) {
	CHAIN = mat.BLOCKS.register(mat.name + "_chain", () -> new BlockChain(
		AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3F).sound(SoundType.METAL)));
	BIG_CHAIN = mat.BLOCKS.register(mat.name + "bigchain", () -> new BlockBigChain(
		AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3F).sound(SoundType.METAL)));

	TOP_BARS = mat.BLOCKS.register(mat.name + "topbars", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	CHAINLINK_BARS = mat.BLOCKS.register(mat.name + "chainlink", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	CHAINLINK_BLOCK = mat.BLOCKS.register(mat.name + "chainlinkblock", () -> new Block(Block.Properties
		.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	DIAMONDBARS = mat.BLOCKS.register(mat.name + "diamondbars", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
	DIAMONDBARSFLIP = mat.BLOCKS.register(mat.name + "diamondbarsflip", () -> new ComponentPane(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

	STAKE = mat.BLOCKS.register(mat.name + "stake", () -> new ComponentStake());

	CHAIN_ITEMBLOCK = mat.ITEMS.register(mat.name + "_chain_itemblock", () -> new BlockNamedItem(CHAIN.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	BIG_CHAIN_ITEMBLOCK = mat.ITEMS.register(mat.name + "_big_chain_itemblock",
		() -> new BlockNamedItem(BIG_CHAIN.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	TOP_BARS_ITEMBLOCK = mat.ITEMS.register(mat.name + "topbars_itemblock", () -> new BlockNamedItem(TOP_BARS.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	CHAINLINK_BARS_ITEMBLOCK = mat.ITEMS.register(mat.name + "chainlink_itemblock",
		() -> new BlockNamedItem(CHAINLINK_BARS.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	CHAINLINK_BLOCK_ITEMBLOCK = mat.ITEMS.register(mat.name + "chainlinkblock_itemblock",
		() -> new BlockNamedItem(CHAINLINK_BLOCK.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	DIAMONDBARS_ITEMBLOCK = mat.ITEMS.register(mat.name + "diamondbars_itemblock",
		() -> new BlockNamedItem(DIAMONDBARS.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DIAMONDBARSFLIP_ITEMBLOCK = mat.ITEMS.register(mat.name + "diamondbarsflip_itemblock",
		() -> new BlockNamedItem(DIAMONDBARSFLIP.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	STAKE_ITEMBLOCK = mat.ITEMS.register(mat.name + "stake_itemblock", () -> new BlockNamedItem(STAKE.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(ConstructionBarsAndChains m, TCBlockModels model, String name) {
	ModelFile chain = model.models().withExistingParent(name + "chain", model.modLoc("block/bases/chain"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "chain");
	ModelFile chain2 = model.models().withExistingParent(name + "chain2", model.modLoc("block/bases/chain2"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "chain");

	MultiPartBlockStateBuilder bld = model.getMultipartBuilder(m.CHAIN.get());

	bld.part().modelFile(chain2).uvLock(false).rotationY(0).addModel().condition(BlockStateProperties.NORTH, true);
	bld.part().modelFile(chain).uvLock(false).rotationY(0).addModel().condition(BlockStateProperties.SOUTH, true);
	bld.part().modelFile(chain).uvLock(false).rotationY(90).addModel().condition(BlockStateProperties.WEST, true);
	bld.part().modelFile(chain2).uvLock(false).rotationY(90).addModel().condition(BlockStateProperties.EAST, true);
	bld.part().modelFile(chain).uvLock(false).rotationY(0).rotationX(90).addModel()
		.condition(BlockStateProperties.UP, true);
	bld.part().modelFile(chain2).uvLock(false).rotationY(0).rotationX(90).addModel()
		.condition(BlockStateProperties.DOWN, true);

	bld.part().modelFile(chain).uvLock(false).rotationY(0).rotationX(90).addModel()
		.condition(BlockStateProperties.DOWN, false).condition(BlockStateProperties.UP, false)
		.condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.WEST, false)
		.condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.NORTH, false);

	bld.part().modelFile(chain2).uvLock(false).rotationY(0).rotationX(90).addModel()
		.condition(BlockStateProperties.DOWN, false).condition(BlockStateProperties.UP, false)
		.condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.WEST, false)
		.condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.NORTH, false);

	model.stakeModel(name, m.STAKE.get());

	model.axisBlock(m.BIG_CHAIN.get(),
		model.models().withExistingParent(name + "bigchain", model.modLoc("block/bases/bigchain"))
			.texture("all", model.modLoc("block/material/" + name + "/" + name + "bigchain")),
		model.models().withExistingParent(name + "bigchain", model.modLoc("block/bases/bigchain"))
			.texture("all", model.modLoc("block/material/" + name + "/" + name + "bigchain")));

	// Top Bars
	model.paneBlock(m.TOP_BARS.get(), model.modLoc("block/material/" + name + "/" + name + "topbars"),
		model.modLoc("block/material/" + name + "/" + name + "topbars"));

	// Chainlink
	model.paneBlock(m.CHAINLINK_BARS.get(), model.modLoc("block/material/" + name + "/" + name + "chainlink"),
		model.modLoc("block/material/" + name + "/" + name + "chainlink"));
	model.simpleBlock(m.CHAINLINK_BLOCK.get(),
		model.models().cubeAll(m.CHAINLINK_BLOCK.get().getRegistryName().getPath(),
			model.modLoc("block/material/" + name + "/" + name + "chainlink")));

	model.paneBlock(m.DIAMONDBARS.get(), model.modLoc("block/material/" + name + "/" + name + "diamondbars"),
		model.modLoc("block/material/" + name + "/" + name + "diamondbars"));
	model.paneBlock(m.DIAMONDBARSFLIP.get(),
		model.modLoc("block/material/" + name + "/" + name + "diamondbarsflip"),
		model.modLoc("block/material/" + name + "/" + name + "diamondbarsflip"));
    }

    public static void registerItemModels(ConstructionBarsAndChains m, TCItemModels model, String name) {
	model.withExistingParent(m.CHAIN_ITEMBLOCK.getId().getPath(), model.modLoc("block/bases/chain_inventory"))
		.texture("0", model.modLoc("block/material/" + name + "/" + name + "chain"));

	model.withExistingParent(m.BIG_CHAIN_ITEMBLOCK.getId().getPath(), model.modLoc("block/bases/bigchain"))
	.texture("0", model.modLoc("block/material/" + name + "/" + name + "bigchain"));
	
	model.forBlockItem(m.STAKE_ITEMBLOCK, name);
	model.forBlockItem(m.BIG_CHAIN_ITEMBLOCK, name);
	
	model.withExistingParent(m.CHAINLINK_BARS_ITEMBLOCK.getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", model.modLoc("block/material/" + name + "/" + name + "chainlink"));
	
	model.withExistingParent(m.DIAMONDBARS_ITEMBLOCK.getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", model.modLoc("block/material/" + name + "/" + name + "diamondbars"));
	
	model.withExistingParent(m.DIAMONDBARSFLIP_ITEMBLOCK.getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", model.modLoc("block/material/" + name + "/" + name + "diamondbarsflip"));
	
	model.withExistingParent(m.TOP_BARS_ITEMBLOCK.getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", model.modLoc("block/material/" + name + "/" + name + "topbars"));
	
	//model.forBlockItem(m.CHAINLINK_BARS_ITEMBLOCK, name);
	model.forBlockItem(m.CHAINLINK_BLOCK_ITEMBLOCK, name);
//	model.forBlockItem(m.DIAMONDBARS_ITEMBLOCK, name);
//	model.forBlockItem(m.DIAMONDBARSFLIP_ITEMBLOCK, name);
    }

    public static void addTranslations(ConstructionBarsAndChains m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionBarsAndChains m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionBarsAndChains b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.BIG_CHAIN.get());
	table.registerDropSelfLootTable(b.CHAIN.get());
	table.registerDropSelfLootTable(b.TOP_BARS.get());
	table.registerDropSelfLootTable(b.CHAINLINK_BARS.get());
	table.registerDropSelfLootTable(b.CHAINLINK_BLOCK.get());
	table.registerDropSelfLootTable(b.DIAMONDBARS.get());
	table.registerDropSelfLootTable(b.DIAMONDBARSFLIP.get());
	table.registerDropSelfLootTable(b.STAKE.get());
    }

    public static void buildRecipes(ConstructionBarsAndChains constructionLighting, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {
    }

    public static void registerBlockTags(ConstructionBarsAndChains base, TCBlockTags btp, String name) {
	// TODO Auto-generated method stub

    }
}
