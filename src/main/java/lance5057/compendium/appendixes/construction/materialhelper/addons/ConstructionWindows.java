package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SixWayBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.fml.RegistryObject;

public class ConstructionWindows implements MaterialBase {

    public static String[] types = { "white", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray",
	    "cyan", "purple", "blue", "brown", "green", "red", "black" };

    public List<RegistryObject<GlassBlock>> STAINED_GLASS_DIAMOND;
    public List<RegistryObject<GlassBlock>> STAINED_GLASS_RUPEE;
    public List<RegistryObject<GlassBlock>> STAINED_GLASS_SMALL_DIAMOND;
    public List<RegistryObject<GlassBlock>> STAINED_GLASS_ARROW;

    public List<RegistryObject<StainedGlassPaneBlock>> STAINED_GLASS_DIAMOND_PANE;
    public List<RegistryObject<StainedGlassPaneBlock>> STAINED_GLASS_RUPEE_PANE;
    public List<RegistryObject<StainedGlassPaneBlock>> STAINED_GLASS_SMALL_DIAMOND_PANE;
    public List<RegistryObject<StainedGlassPaneBlock>> STAINED_GLASS_ARROW_PANE;

    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_DIAMOND_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_RUPEE_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_SMALL_DIAMOND_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_ARROW_ITEMBLOCK;

    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_DIAMOND_PANE_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_RUPEE_PANE_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_SMALL_DIAMOND_PANE_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> STAINED_GLASS_ARROW_PANE_ITEMBLOCK;

    public ConstructionWindows(ConstructionMaterialHelper constructionMaterialHelper) {
	STAINED_GLASS_DIAMOND = new ArrayList<RegistryObject<GlassBlock>>();
	STAINED_GLASS_RUPEE = new ArrayList<RegistryObject<GlassBlock>>();
	STAINED_GLASS_SMALL_DIAMOND = new ArrayList<RegistryObject<GlassBlock>>();
	STAINED_GLASS_ARROW = new ArrayList<RegistryObject<GlassBlock>>();

	STAINED_GLASS_DIAMOND_PANE = new ArrayList<RegistryObject<StainedGlassPaneBlock>>();
	STAINED_GLASS_RUPEE_PANE = new ArrayList<RegistryObject<StainedGlassPaneBlock>>();
	STAINED_GLASS_SMALL_DIAMOND_PANE = new ArrayList<RegistryObject<StainedGlassPaneBlock>>();
	STAINED_GLASS_ARROW_PANE = new ArrayList<RegistryObject<StainedGlassPaneBlock>>();

	STAINED_GLASS_DIAMOND_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_RUPEE_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_SMALL_DIAMOND_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_ARROW_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();

	STAINED_GLASS_DIAMOND_PANE_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_RUPEE_PANE_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_SMALL_DIAMOND_PANE_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	STAINED_GLASS_ARROW_PANE_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	RenderType trans = RenderType.getTranslucent();

	for (int i = 0; i < types.length; i++) {
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_ARROW.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_DIAMOND.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_RUPEE.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_SMALL_DIAMOND.get(i).get(), trans);

	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_ARROW_PANE.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_DIAMOND_PANE.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_RUPEE_PANE.get(i).get(), trans);
	    RenderTypeLookup.setRenderLayer(this.STAINED_GLASS_SMALL_DIAMOND_PANE.get(i).get(), trans);
	}
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
	return false;
    }

    @Override
    public void setup(MaterialHelperBase mat) {
	for (String s : types) {
	    RegistryObject<GlassBlock> DIAMOND = mat.BLOCKS.register(mat.name + "_stained_glass_diamond_" + s,
		    () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
			    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
			    .setSuffocates(ConstructionWindows::isntSolid)
			    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<GlassBlock> RUPEE = mat.BLOCKS.register(mat.name + "_stained_glass_rupees_" + s,
		    () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
			    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
			    .setSuffocates(ConstructionWindows::isntSolid)
			    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<GlassBlock> SMALL_DIAMOND = mat.BLOCKS.register(
		    mat.name + "_stained_glass_small_diamond_" + s,
		    () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
			    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
			    .setSuffocates(ConstructionWindows::isntSolid)
			    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<GlassBlock> ARROW = mat.BLOCKS.register(mat.name + "_stained_glass_arrows_" + s,
		    () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
			    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
			    .setSuffocates(ConstructionWindows::isntSolid)
			    .setBlocksVision(ConstructionWindows::isntSolid)));

	    RegistryObject<BlockNamedItem> DIAMOND_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_diamond_" + s + "_itemblock", () -> new BlockNamedItem(DIAMOND.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> RUPEE_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_rupees_" + s + "_itemblock", () -> new BlockNamedItem(RUPEE.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> SMALL_DIAMOND_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_small_diamond_" + s + "_itemblock",
		    () -> new BlockNamedItem(SMALL_DIAMOND.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> ARROW_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_arrows_" + s + "_itemblock", () -> new BlockNamedItem(ARROW.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	    RegistryObject<StainedGlassPaneBlock> DIAMOND_PANE = mat.BLOCKS.register(
		    mat.name + "stained_glass_diamond_pane_" + s,
		    () -> new StainedGlassPaneBlock(DyeColor.WHITE,
			    AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
				    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
				    .setSuffocates(ConstructionWindows::isntSolid)
				    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<StainedGlassPaneBlock> RUPEE_PANE = mat.BLOCKS.register(
		    mat.name + "stained_glass_rupees_pane_" + s,
		    () -> new StainedGlassPaneBlock(DyeColor.WHITE,
			    AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
				    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
				    .setSuffocates(ConstructionWindows::isntSolid)
				    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<StainedGlassPaneBlock> SMALL_DIAMOND_PANE = mat.BLOCKS.register(
		    mat.name + "stained_glass_small_diamond_pane_" + s,
		    () -> new StainedGlassPaneBlock(DyeColor.WHITE,
			    AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
				    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
				    .setSuffocates(ConstructionWindows::isntSolid)
				    .setBlocksVision(ConstructionWindows::isntSolid)));
	    RegistryObject<StainedGlassPaneBlock> ARROW_PANE = mat.BLOCKS.register(
		    mat.name + "stained_glass_arrows_pane_" + s,
		    () -> new StainedGlassPaneBlock(DyeColor.WHITE,
			    AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1F)
				    .sound(SoundType.GLASS).notSolid().setOpaque(ConstructionWindows::isntSolid)
				    .setSuffocates(ConstructionWindows::isntSolid)
				    .setBlocksVision(ConstructionWindows::isntSolid)));

	    RegistryObject<BlockNamedItem> DIAMOND_PANE_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_diamond_pane_" + s + "_itemblock",
		    () -> new BlockNamedItem(DIAMOND_PANE.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> RUPEE_PANE_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_rupees_pane_" + s + "_itemblock",
		    () -> new BlockNamedItem(RUPEE_PANE.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> SMALL_DIAMOND_PANE_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_small_diamond_pane_" + s + "_itemblock",
		    () -> new BlockNamedItem(SMALL_DIAMOND_PANE.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	    RegistryObject<BlockNamedItem> ARROW_PANE_ITEMBLOCK = mat.ITEMS.register(
		    mat.name + "stained_glass_arrows_pane_" + s + "_itemblock",
		    () -> new BlockNamedItem(ARROW_PANE.get(),
			    new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	    STAINED_GLASS_DIAMOND.add(DIAMOND);
	    STAINED_GLASS_RUPEE.add(RUPEE);
	    STAINED_GLASS_SMALL_DIAMOND.add(SMALL_DIAMOND);
	    STAINED_GLASS_ARROW.add(ARROW);

	    STAINED_GLASS_DIAMOND_ITEMBLOCK.add(DIAMOND_ITEMBLOCK);
	    STAINED_GLASS_RUPEE_ITEMBLOCK.add(RUPEE_ITEMBLOCK);
	    STAINED_GLASS_SMALL_DIAMOND_ITEMBLOCK.add(SMALL_DIAMOND_ITEMBLOCK);
	    STAINED_GLASS_ARROW_ITEMBLOCK.add(ARROW_ITEMBLOCK);

	    STAINED_GLASS_DIAMOND_PANE.add(DIAMOND_PANE);
	    STAINED_GLASS_RUPEE_PANE.add(RUPEE_PANE);
	    STAINED_GLASS_SMALL_DIAMOND_PANE.add(SMALL_DIAMOND_PANE);
	    STAINED_GLASS_ARROW_PANE.add(ARROW_PANE);

	    STAINED_GLASS_DIAMOND_PANE_ITEMBLOCK.add(DIAMOND_PANE_ITEMBLOCK);
	    STAINED_GLASS_RUPEE_PANE_ITEMBLOCK.add(RUPEE_PANE_ITEMBLOCK);
	    STAINED_GLASS_SMALL_DIAMOND_PANE_ITEMBLOCK.add(SMALL_DIAMOND_PANE_ITEMBLOCK);
	    STAINED_GLASS_ARROW_PANE_ITEMBLOCK.add(ARROW_PANE_ITEMBLOCK);
	}
    }

    public static void registerBlockModels(ConstructionWindows m, TCBlockModels model, String name) {
//	ModelFile window1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_1", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_2", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window3 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_3", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_3")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window4 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_4", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_4")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window5 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_5", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_5")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window6 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_6", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_6")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window7 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_7", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_7")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window8 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_8", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_8")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window9 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_9", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_9")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window10 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_10", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_10")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window11 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_11", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_11")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window12 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_12", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_12")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window13 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_13", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_13")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window14 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_14", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_14")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window15 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_15", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_15")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//	ModelFile window16 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_16", model.modLoc("block/bases/2layerblock"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_16")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	model.getVariantBuilder(m.STAINED_GLASS_DIAMOND.get(0).get()).partialState()
//		.addModels(new ConfiguredModel(window1)).partialState().addModels(new ConfiguredModel(window2))
//		.partialState().addModels(new ConfiguredModel(window3)).partialState()
//		.addModels(new ConfiguredModel(window4)).partialState().addModels(new ConfiguredModel(window5))
//		.partialState().addModels(new ConfiguredModel(window6)).partialState()
//		.addModels(new ConfiguredModel(window7)).partialState().addModels(new ConfiguredModel(window8))
//		.partialState().addModels(new ConfiguredModel(window9)).partialState()
//		.addModels(new ConfiguredModel(window10)).partialState().addModels(new ConfiguredModel(window11))
//		.partialState().addModels(new ConfiguredModel(window12)).partialState()
//		.addModels(new ConfiguredModel(window13)).partialState().addModels(new ConfiguredModel(window14))
//		.partialState().addModels(new ConfiguredModel(window15)).partialState()
//		.addModels(new ConfiguredModel(window16));

	// paneBlockRandom(name, model, m.STAINED_GLASS_DIAMOND_PANE.get(0).get());

	for (int i = 0; i < types.length; i++) {
	    model.simpleBlock(m.STAINED_GLASS_ARROW.get(i).get(),
		    model.models()
			    .withExistingParent(name + "_stained_glass_arrows_" + types[i],
				    model.modLoc("block/bases/2layerblock"))
			    .texture("0", "compendium:block/" + types[i] + "_stained_glass")
			    .texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_arrows"));

	    model.simpleBlock(m.STAINED_GLASS_DIAMOND.get(i).get(),
		    model.models()
			    .withExistingParent(name + "_stained_glass_diamond_" + types[i],
				    model.modLoc("block/bases/2layerblock"))
			    .texture("0", "compendium:block/" + types[i] + "_stained_glass").texture("1",
				    "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond"));

	    model.simpleBlock(m.STAINED_GLASS_RUPEE.get(i).get(),
		    model.models()
			    .withExistingParent(name + "_stained_glass_rupees_" + types[i],
				    model.modLoc("block/bases/2layerblock"))
			    .texture("0", "compendium:block/" + types[i] + "_stained_glass")
			    .texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_rupees"));

	    model.simpleBlock(m.STAINED_GLASS_SMALL_DIAMOND.get(i).get(),
		    model.models()
			    .withExistingParent(name + "_stained_glass_small_diamond_" + types[i],
				    model.modLoc("block/bases/2layerblock"))
			    .texture("0", "compendium:block/" + types[i] + "_stained_glass")
			    .texture("1", "compendium:block/material/" + name + "/" + name + "chainlink"));

	    ConstructionWindows.layeredPaneBlock(name, types[i], "stainedglass_arrows", model,
		    m.STAINED_GLASS_ARROW_PANE.get(i).get());
	    ConstructionWindows.layeredPaneBlock(name, types[i], "stainedglass_bigdiamond", model,
		    m.STAINED_GLASS_DIAMOND_PANE.get(i).get());
	    ConstructionWindows.layeredPaneBlock(name, types[i], "stainedglass_rupees", model,
		    m.STAINED_GLASS_RUPEE_PANE.get(i).get());
	    ConstructionWindows.layeredPaneBlock(name, types[i], "chainlink", model,
		    m.STAINED_GLASS_SMALL_DIAMOND_PANE.get(i).get());
	}
    }

    public static void layeredPaneBlock(String material, String color, String type, TCBlockModels model,
	    PaneBlock block) {
	ModelFile post1 = model.models()
		.withExistingParent(material + "_stained_glass_pane_" + type + "_" + color,
			model.modLoc("block/bases/2layer_glass_pane_post"))
		.texture("0", "compendium:block/" + color + "_stained_glass")
		.texture("1", "compendium:block/material/" + material + "/" + material + type);

	ModelFile side1 = model.models()
		.withExistingParent(material + "_stained_glass_pane_" + type + "_" + color + "_side_1",
			model.modLoc("block/bases/2layer_glass_pane_side"))
		.texture("0", "compendium:block/" + color + "_stained_glass")
		.texture("1", "compendium:block/material/" + material + "/" + material + type);

	ModelFile sideAlt1 = model.models()
		.withExistingParent(material + "_stained_glass_pane_" + type + "_" + color + "_side_alt_1",
			model.modLoc("block/bases/2layer_glass_pane_side_alt"))
		.texture("0", "compendium:block/" + color + "_stained_glass")
		.texture("1", "compendium:block/material/" + material + "/" + material + type);

	ModelFile noSide1 = model.models()
		.withExistingParent(material + "_stained_glass_pane_" + type + "_" + color + "_noside_1",
			model.modLoc("block/bases/2layer_glass_pane_noside"))
		.texture("0", "compendium:block/" + color + "_stained_glass")
		.texture("1", "compendium:block/material/" + material + "/" + material + type);

	ModelFile noSideAlt1 = model.models()
		.withExistingParent(material + "_stained_glass_pane_" + type + "_" + color + "_noside_alt_1",
			model.modLoc("block/bases/2layer_glass_pane_noside_alt"))
		.texture("0", "compendium:block/" + color + "_stained_glass")
		.texture("1", "compendium:block/material/" + material + "/" + material + type);

	MultiPartBlockStateBuilder builder = model.getMultipartBuilder(block).part().modelFile(post1).addModel().end();

	SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().forEach(e -> {
	    Direction dir = e.getKey();
	    if (dir.getAxis().isHorizontal()) {
		boolean alt = dir == Direction.SOUTH;
		builder.part().modelFile(alt || dir == Direction.WEST ? sideAlt1 : side1)
			.rotationY(dir.getAxis() == Axis.X ? 90 : 0).addModel().condition(e.getValue(), true).end()
			.part().modelFile(alt || dir == Direction.EAST ? noSideAlt1 : noSide1)
			.rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
			.condition(e.getValue(), false);
	    }
	});
    }

//    public static void paneBlockRandom(String name, TCBlockModels model, PaneBlock block) {
//
//	ModelFile post1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_post_1",
//			model.modLoc("block/bases/2layer_glass_pane_post"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile side1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_side_1",
//			model.modLoc("block/bases/2layer_glass_pane_side"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile sideAlt1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_side_alt_1",
//			model.modLoc("block/bases/2layer_glass_pane_side_alt"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile noSide1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_noside_1",
//			model.modLoc("block/bases/2layer_glass_pane_noside"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile noSideAlt1 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_noside_alt_1",
//			model.modLoc("block/bases/2layer_glass_pane_noside_alt"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_1")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile post2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_post_2",
//			model.modLoc("block/bases/2layer_glass_pane_post"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile side2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_side_2",
//			model.modLoc("block/bases/2layer_glass_pane_side"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile sideAlt2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_side_alt_2",
//			model.modLoc("block/bases/2layer_glass_pane_side_alt"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile noSide2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_noside_2",
//			model.modLoc("block/bases/2layer_glass_pane_noside"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	ModelFile noSideAlt2 = model.models()
//		.withExistingParent(name + "_stained_glass_diamond_random_noside_alt_2",
//			model.modLoc("block/bases/2layer_glass_pane_noside_alt"))
//		.texture("0", "compendium:block/stainedglass_bigdiamonds_2")
//		.texture("1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
//
//	MultiPartBlockStateBuilder builder = model.getMultipartBuilder(block).part().modelFile(post1).addModel().end();
//	SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().forEach(e -> {
//	    Direction dir = e.getKey();
//	    if (dir.getAxis().isHorizontal()) {
//		boolean alt = dir == Direction.SOUTH;
//		builder.part().modelFile(alt || dir == Direction.WEST ? sideAlt1 : side1)
//			.rotationY(dir.getAxis() == Axis.X ? 90 : 0).addModel().condition(e.getValue(), true).end()
//			.part().modelFile(alt || dir == Direction.EAST ? noSideAlt1 : noSide1)
//			.rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
//			.condition(e.getValue(), false);
//	    }
//	});
//    }

    public static void registerItemModels(ConstructionWindows m, TCItemModels model, String name) {
	for (int i = 0; i < types.length; i++) {
	    model.forBlockItem(m.STAINED_GLASS_ARROW_ITEMBLOCK.get(i), name);
	    model.forBlockItem(m.STAINED_GLASS_DIAMOND_ITEMBLOCK.get(i), name);
	    model.forBlockItem(m.STAINED_GLASS_RUPEE_ITEMBLOCK.get(i), name);
	    model.forBlockItem(m.STAINED_GLASS_SMALL_DIAMOND_ITEMBLOCK.get(i), name);

	    model.withExistingParent(m.STAINED_GLASS_ARROW_PANE_ITEMBLOCK.get(i).getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", "compendium:block/" + types[i] + "_stained_glass")
		    .texture("layer1", "compendium:block/material/" + name + "/" + name + "stainedglass_arrows");
	    model.withExistingParent(m.STAINED_GLASS_DIAMOND_PANE_ITEMBLOCK.get(i).getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", "compendium:block/" + types[i] + "_stained_glass")
		    .texture("layer1", "compendium:block/material/" + name + "/" + name + "stainedglass_bigdiamond");
	    model.withExistingParent(m.STAINED_GLASS_RUPEE_PANE_ITEMBLOCK.get(i).getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", "compendium:block/" + types[i] + "_stained_glass")
		    .texture("layer1", "compendium:block/material/" + name + "/" + name + "stainedglass_rupees");
	    model.withExistingParent(m.STAINED_GLASS_SMALL_DIAMOND_PANE_ITEMBLOCK.get(i).getId().getPath(),
		    model.mcLoc("item/handheld")).texture("layer0", "compendium:block/" + types[i] + "_stained_glass")
		    .texture("layer1", "compendium:block/material/" + name + "/" + name + "chainlink");

//	    model.forItem(m.STAINED_GLASS_ARROW_PANE_ITEMBLOCK.get(i), name);
//	    model.forItem(m.STAINED_GLASS_DIAMOND_PANE_ITEMBLOCK.get(i), name);
//	    model.forItem(m.STAINED_GLASS_RUPEE_PANE_ITEMBLOCK.get(i), name);
//	    model.forItem(m.STAINED_GLASS_SMALL_DIAMOND_PANE_ITEMBLOCK.get(i), name);
	}
    }

    public static void addTranslations(ConstructionWindows m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionWindows m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionWindows b, BlockLoot table, String name) {
	for (int i = 0; i < types.length; i++) {
	    table.registerDropSelfLootTable(b.STAINED_GLASS_ARROW.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_DIAMOND.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_RUPEE.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_SMALL_DIAMOND.get(i).get());

	    table.registerDropSelfLootTable(b.STAINED_GLASS_ARROW_PANE.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_DIAMOND_PANE.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_RUPEE_PANE.get(i).get());
	    table.registerDropSelfLootTable(b.STAINED_GLASS_SMALL_DIAMOND_PANE.get(i).get());
	}
    }

    public static void buildRecipes(ConstructionWindows m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
    }

}
