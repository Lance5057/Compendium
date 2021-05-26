package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.core.blocks.BlockShingles;
import lance5057.compendium.core.blocks.BlockShinglesCap;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ConstructionMaterialShingles {

    public List<RegistryObject<Block>> SHINGLES_BLOCKS;
    public List<RegistryObject<BlockShingles>> SHINGLES;
    public List<RegistryObject<BlockShingles>> SHINGLES_ALT;
    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS;
    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS_ALT;

    public List<RegistryObject<BlockNamedItem>> SHINGLES_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_ALT;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS_ALT;

    public ConstructionMaterialShingles(ConstructionMaterialHelper cmh) {
	SHINGLES_BLOCKS = new ArrayList<RegistryObject<Block>>();
	SHINGLES = new ArrayList<RegistryObject<BlockShingles>>();
	SHINGLES_ALT = new ArrayList<RegistryObject<BlockShingles>>();
	SHINGLES_CAPS = new ArrayList<RegistryObject<BlockShinglesCap>>();
	SHINGLES_CAPS_ALT = new ArrayList<RegistryObject<BlockShinglesCap>>();

	SHINGLES_ITEMBLOCK = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM_CAPS = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM_CAPS_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();

	for (MaterialHelperBase m : AppendixConstruction.bases) {
	    RegistryObject<Block> SHINGLE_BLOCK = cmh.BLOCKS.register(m.name + "_" + cmh.name + "_shinglesblock",
		    () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F)
			    .sound(SoundType.METAL)));

	    RegistryObject<BlockShingles> SHINGLE = cmh.BLOCKS.register(m.name + "_" + cmh.name + "_shingles",
		    () -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(), Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockShingles> SHINGLE_ALT = cmh.BLOCKS.register(m.name + "_" + cmh.name + "_shinglesalt",
		    () -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(), Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockNamedItem> SHINGLE_ITEMBLOCK = cmh.ITEMS.register(
		    m.name + "_" + cmh.name + "_shingles_itemblock", () -> new BlockNamedItem(SHINGLE_BLOCK.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockNamedItem> SHINGLEITEM = cmh.ITEMS.register(m.name + "_" + cmh.name + "_shinglesitem",
		    () -> new BlockNamedItem(SHINGLE.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockNamedItem> SHINGLEITEM_ALT = cmh.ITEMS
		    .register(m.name + "_" + cmh.name + "_shinglesitem_alt", () -> new BlockNamedItem(SHINGLE_ALT.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockShinglesCap> SHINGLE_CAP = cmh.BLOCKS
		    .register(m.name + "_" + cmh.name + "_shingles_cap", () -> new BlockShinglesCap(Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockNamedItem> SHINGLEITEM_CAP = cmh.ITEMS
		    .register(m.name + "_" + cmh.name + "_shinglesitem_cap", () -> new BlockNamedItem(SHINGLE_CAP.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockShinglesCap> SHINGLE_CAP_ALT = cmh.BLOCKS
		    .register(m.name + "_" + cmh.name + "_shingles_cap_alt", () -> new BlockShinglesCap(Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockNamedItem> SHINGLEITEM_CAP_ALT = cmh.ITEMS.register(
		    m.name + "_" + cmh.name + "_shinglesitem_cap_alt", () -> new BlockNamedItem(SHINGLE_CAP_ALT.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    SHINGLES_BLOCKS.add(SHINGLE_BLOCK);
	    SHINGLES.add(SHINGLE);
	    SHINGLES_ALT.add(SHINGLE_ALT);
	    SHINGLES_CAPS.add(SHINGLE_CAP);
	    SHINGLES_CAPS_ALT.add(SHINGLE_CAP_ALT);

	    SHINGLESITEM_CAPS.add(SHINGLEITEM_CAP);
	    SHINGLESITEM_CAPS_ALT.add(SHINGLEITEM_CAP_ALT);
	    SHINGLES_ITEMBLOCK.add(SHINGLE_ITEMBLOCK);
	    SHINGLESITEM.add(SHINGLEITEM);
	    SHINGLESITEM_ALT.add(SHINGLEITEM_ALT);
	}
    }

    public static void registerBlockModels(ConstructionMaterialShingles m, TCBlockModels model, String name) {
	for (int i = 0; i < AppendixConstruction.bases.size(); i++) {
	    // Shingles
	    model.simpleBlock(m.SHINGLES_BLOCKS.get(i).get(), model.models()
			.withExistingParent(name + "_shingle_block", model.mcLoc("block/cube_all"))
			.texture("all", "compendium:block/material/" + name + "/" + name + "shingles"));
	    
	    TCBlockModels.shinglesModel(model, AppendixConstruction.bases.get(i).name + "_" + name,
		    "compendium:block/material/" + name + "/" + name + "shingles",
		    "minecraft:block/" + AppendixConstruction.bases.get(i).name + "_log", "", "block/bases/shingles",
		    m.SHINGLES.get(i).get());
	    TCBlockModels.shinglesModel(model, AppendixConstruction.bases.get(i).name + "_" + name,
		    "compendium:block/material/" + name + "/" + name + "shingles",
		    "minecraft:block/" + AppendixConstruction.bases.get(i).name + "_log", "alt", "block/bases/shingles",
		    m.SHINGLES_ALT.get(i).get());

	    TCBlockModels.shinglesCapModel(model, AppendixConstruction.bases.get(i).name + "_" + name,
		    "compendium:block/material/" + name + "/" + name + "shingles", "", "block/bases/shingles_cap",
		    m.SHINGLES_CAPS.get(i).get());

	}
    }

    public static void registerItemModels(ConstructionMaterialShingles m, TCItemModels model, String name) {
	for (int i = 0; i < AppendixConstruction.bases.size(); i++) {
	    model.forBlockItem(m.SHINGLES_ITEMBLOCK.get(i), name);
	    model.forBlockItem(m.SHINGLESITEM.get(i), name);
	    model.forBlockItem(m.SHINGLESITEM_ALT.get(i), name);
	}
    }

    public static void addTranslations(ConstructionMaterialShingles m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionMaterialShingles m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionMaterialShingles b, BlockLoot table, String name) {
	for (int i = 0; i < AppendixConstruction.bases.size(); i++) {
	    table.registerDropSelfLootTable(b.SHINGLES.get(i).get());
	    table.registerDropSelfLootTable(b.SHINGLES_ALT.get(i).get());
	    table.registerDropSelfLootTable(b.SHINGLES_BLOCKS.get(i).get());
	    table.registerDropSelfLootTable(b.SHINGLES_CAPS.get(i).get());
	    table.registerDropSelfLootTable(b.SHINGLES_CAPS_ALT.get(i).get());
	}
    }

    public static void buildRecipes(ConstructionMaterialShingles m, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {
    }

    public static void registerBlockTags(ConstructionMaterialShingles base, TCBlockTags btp, String name) {
	for (int i = 0; i < AppendixConstruction.bases.size(); i++) {
	    btp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(base.SHINGLES_CAPS.get(i).get());
	    btp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(base.SHINGLES_CAPS_ALT.get(i).get());
	}
    }

}
