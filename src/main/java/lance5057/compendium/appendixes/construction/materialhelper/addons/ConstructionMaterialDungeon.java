package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.blocks.BlockVerticalPlacement;
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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction.Axis;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ConstructionMaterialDungeon implements MaterialBase {

    public RegistryObject<Block> DUNGEON_BRICK;
    public RegistryObject<Block> DUNGEON_BRICK_BROKEN;
    public RegistryObject<Block> DUNGEON_BRICK_TRIM;
    public RegistryObject<RotatedPillarBlock> DUNGEON_PILLAR;
    public RegistryObject<Block> DUNGEON_SQUARE;
    public RegistryObject<Block> DUNGEON_LAMP;

    public RegistryObject<BlockNamedItem> DUNGEON_BRICK_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DUNGEON_BRICK_BROKEN_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DUNGEON_BRICK_TRIM_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DUNGEON_PILLAR_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DUNGEON_SQUARE_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> DUNGEON_LAMP_ITEMBLOCK;

    public ConstructionMaterialDungeon(ConstructionMaterialHelper cmh) {
	DUNGEON_BRICK = cmh.BLOCKS.register(cmh.name + "_dungeon_brick",
		() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE)));
	DUNGEON_BRICK_BROKEN = cmh.BLOCKS.register(cmh.name + "_dungeon_brick_broken",
		() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE)));
	DUNGEON_BRICK_TRIM = cmh.BLOCKS.register(cmh.name + "_dungeon_brick_trim",
		() -> new BlockVerticalPlacement(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE)));
	DUNGEON_PILLAR = cmh.BLOCKS.register(cmh.name + "_dungeon_brick_pillar",
		() -> new RotatedPillarBlock(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE)));
	DUNGEON_SQUARE = cmh.BLOCKS.register(cmh.name + "_dungeon_brick_square",
		() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE)));
	DUNGEON_LAMP = cmh.BLOCKS.register(cmh.name + "_dungeon_brick_light",
		() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(4)
			.hardnessAndResistance(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE).setLightLevel(b -> {
			    return 15;
			})));

	DUNGEON_BRICK_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_brick_itemblock",
		() -> new BlockNamedItem(DUNGEON_BRICK.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUNGEON_BRICK_BROKEN_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_brick_broken_itemblock",
		() -> new BlockNamedItem(DUNGEON_BRICK_BROKEN.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUNGEON_BRICK_TRIM_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_brick_trim_itemblock",
		() -> new BlockNamedItem(DUNGEON_BRICK_TRIM.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUNGEON_PILLAR_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_pillar_itemblock",
		() -> new BlockNamedItem(DUNGEON_PILLAR.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUNGEON_SQUARE_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_square_itemblock",
		() -> new BlockNamedItem(DUNGEON_SQUARE.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUNGEON_LAMP_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_dungeon_lamp_itemblock",
		() -> new BlockNamedItem(DUNGEON_LAMP.get(),
			new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(DUNGEON_LAMP.get(), cutout);
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(ConstructionMaterialDungeon m, TCBlockModels model, String name) {

	ModelFile brick1 = model.models()
		.withExistingParent(name + "dungeon_bricks_broken", model.mcLoc("block/cube_all"))
		.texture("all", "compendium:block/material/" + name + "/" + name + "dungeon_bricks_broken");
	ModelFile brick2 = model.models()
		.withExistingParent(name + "dungeon_bricks_broken_2", model.mcLoc("block/cube_all"))
		.texture("all", "compendium:block/material/" + name + "/" + name + "dungeon_bricks_broken_2");
	ModelFile brick3 = model.models()
		.withExistingParent(name + "dungeon_bricks_broken_3", model.mcLoc("block/cube_all"))
		.texture("all", "compendium:block/material/" + name + "/" + name + "dungeon_bricks_broken_3");

	model.getVariantBuilder(m.DUNGEON_BRICK_BROKEN.get()).partialState().addModels(new ConfiguredModel(brick1))
		.partialState().addModels(new ConfiguredModel(brick2)).partialState()
		.addModels(new ConfiguredModel(brick3));

	model.axisBlock(m.DUNGEON_PILLAR.get(),
		new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "dungeon_pillar"),
		new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "dungeon_square"));

	model.simpleBlock(m.DUNGEON_BRICK.get(), model.models().cubeAll(
		m.DUNGEON_BRICK.get().getRegistryName().getPath(),
		new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "dungeon_bricks")));

	model.simpleBlock(m.DUNGEON_SQUARE.get(), model.models().cubeAll(
		m.DUNGEON_SQUARE.get().getRegistryName().getPath(),
		new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "dungeon_square")));

	model.simpleBlock(m.DUNGEON_LAMP.get(),
		model.models().withExistingParent(name + "dungeon_lamp", model.modLoc("block/bases/2layerblock")).texture("0",
			model.mcLoc("block/redstone_lamp_on")).texture("1", model.modLoc("block/material/" + name + "/" + name + "dungeon_square_light")));

	ModelFile trim1 = model.models()
		.withExistingParent(name + "dungeon_trim_1", model.modLoc("block/bases/dungeon_trim_1"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim2 = model.models()
		.withExistingParent(name + "dungeon_trim_2", model.modLoc("block/bases/dungeon_trim_2"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim3 = model.models()
		.withExistingParent(name + "dungeon_trim_3", model.modLoc("block/bases/dungeon_trim_3"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim4 = model.models()
		.withExistingParent(name + "dungeon_trim_4", model.modLoc("block/bases/dungeon_trim_4"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");

	ModelFile trim_flip1 = model.models()
		.withExistingParent(name + "dungeon_trim_flip_1", model.modLoc("block/bases/dungeon_trim_flip_1"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim_flip2 = model.models()
		.withExistingParent(name + "dungeon_trim_flip_2", model.modLoc("block/bases/dungeon_trim_flip_2"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim_flip3 = model.models()
		.withExistingParent(name + "dungeon_trim_flip_3", model.modLoc("block/bases/dungeon_trim_flip_3"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");
	ModelFile trim_flip4 = model.models()
		.withExistingParent(name + "dungeon_trim_flip_4", model.modLoc("block/bases/dungeon_trim_flip_4"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "dungeon_bricks");

	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.BOTTOM)
		.addModels(new ConfiguredModel(trim1));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.BOTTOM)
		.addModels(new ConfiguredModel(trim2));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.BOTTOM)
		.addModels(new ConfiguredModel(trim3));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.BOTTOM)
		.addModels(new ConfiguredModel(trim4));

	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.TOP)
		.addModels(new ConfiguredModel(trim_flip1, 0, 0, true));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.TOP)
		.addModels(new ConfiguredModel(trim_flip2, 0, 0, true));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.TOP)
		.addModels(new ConfiguredModel(trim_flip3, 0, 0, true));
	model.getVariantBuilder(m.DUNGEON_BRICK_TRIM.get()).partialState().with(BlockStateProperties.HALF, Half.TOP)
		.addModels(new ConfiguredModel(trim_flip4, 0, 0, true));
    }

    public static void registerItemModels(ConstructionMaterialDungeon m, TCItemModels model, String name) {
	model.forBlockItem(m.DUNGEON_BRICK_BROKEN_ITEMBLOCK, name);
	model.forBlockItem(m.DUNGEON_BRICK_ITEMBLOCK, name);
	model.forBlockItem(m.DUNGEON_BRICK_TRIM_ITEMBLOCK, name);
	model.forBlockItem(m.DUNGEON_LAMP_ITEMBLOCK, name);
	model.forBlockItem(m.DUNGEON_PILLAR_ITEMBLOCK, name);
	model.forBlockItem(m.DUNGEON_SQUARE_ITEMBLOCK, name);
    }

    public static void addTranslations(ConstructionMaterialDungeon m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionMaterialDungeon m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionMaterialDungeon m, BlockLoot table, String name) {
	table.registerDropSelfLootTable(m.DUNGEON_BRICK.get());
	table.registerDropSelfLootTable(m.DUNGEON_BRICK_BROKEN.get());
	table.registerDropSelfLootTable(m.DUNGEON_BRICK_TRIM.get());
	table.registerDropSelfLootTable(m.DUNGEON_LAMP.get());
	table.registerDropSelfLootTable(m.DUNGEON_PILLAR.get());
	table.registerDropSelfLootTable(m.DUNGEON_SQUARE.get());
    }

    public static void buildRecipes(ConstructionMaterialDungeon m, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {
    }

}
