package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
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
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class CarpentryMaterialComponents {

    public RegistryObject<Block> SHINGLES_BLOCK;
    public RegistryObject<BlockShingles> SHINGLES;
    public RegistryObject<BlockShingles> SHINGLES_ALT;

    public RegistryObject<BlockShinglesCap> SHINGLES_CAP;
    public RegistryObject<BlockShinglesCap> SHINGLES_CAP_ALT;

    public RegistryObject<BlockNamedItem> SHINGLES_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> SHINGLESITEM;
    public RegistryObject<BlockNamedItem> SHINGLESITEM_ALT;

    public RegistryObject<BlockNamedItem> SHINGLESITEM_CAP;
    public RegistryObject<BlockNamedItem> SHINGLESITEM_CAP_ALT;

    public CarpentryMaterialComponents(CarpentryMaterialHelper cmh) {
	SHINGLES_BLOCK = cmh.BLOCKS.register(cmh.name + "_empty_shinglesblock", () -> new Block(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

	SHINGLES = cmh.BLOCKS.register(cmh.name + "_empty_shingles",
		() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties
			.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	SHINGLES_ALT = cmh.BLOCKS.register(cmh.name + "_empty_shinglesalt",
		() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties
			.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	SHINGLES_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_shingles_itemblock",
		() -> new BlockNamedItem(SHINGLES_BLOCK.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SHINGLESITEM = cmh.ITEMS.register(cmh.name + "_shinglesitem", () -> new BlockNamedItem(SHINGLES.get(),
		new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SHINGLESITEM_ALT = cmh.ITEMS.register(cmh.name + "_shinglesitem_alt",
		() -> new BlockNamedItem(SHINGLES_ALT.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	
	SHINGLES_CAP = cmh.BLOCKS.register(cmh.name + "_empty_shingles_cap",
		() -> new BlockShinglesCap(Block.Properties
			.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	SHINGLESITEM_CAP = cmh.ITEMS.register(cmh.name + "_shinglesitem_cap",
		() -> new BlockNamedItem(SHINGLES_CAP.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	
	SHINGLES_CAP_ALT = cmh.BLOCKS.register(cmh.name + "_empty_shingles_cap_alt",
		() -> new BlockShinglesCap(Block.Properties
			.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	SHINGLESITEM_CAP_ALT = cmh.ITEMS.register(cmh.name + "_shinglesitem_cap_alt",
		() -> new BlockNamedItem(SHINGLES_CAP_ALT.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
    }

    public static void registerBlockModels(CarpentryMaterialComponents m, TCBlockModels model, String name) {
	// Shingles
	TCBlockModels.shinglesModel(model, name + "empty_", "minecraft:block/" + name + "_planks",
		"minecraft:block/" + name + "_log", "", "block/bases/empty_shingles", m.SHINGLES.get());
	TCBlockModels.shinglesModel(model, name + "empty_", "minecraft:block/" + name + "_planks",
		"minecraft:block/" + name + "_log", "alt", "block/bases/empty_shingles", m.SHINGLES_ALT.get());
	TCBlockModels.shinglesCapModel(model, name + "empty_", "minecraft:block/" + name + "_planks",
		"minecraft:block/" + name + "_log", "", "block/bases/empty_shingles_cap", m.SHINGLES_CAP.get());
	TCBlockModels.shinglesCapModel(model, name + "empty_", "minecraft:block/" + name + "_planks",
		"minecraft:block/" + name + "_log", "_alt", "block/bases/empty_shingles_cap", m.SHINGLES_CAP_ALT.get());
    }

    public static void registerItemModels(CarpentryMaterialComponents m, TCItemModels model, String name) {
	model.forBlockItem(m.SHINGLES_ITEMBLOCK, name);
	model.forBlockItem(m.SHINGLESITEM, name);
	model.forBlockItem(m.SHINGLESITEM_ALT, name);
	model.forBlockItem(m.SHINGLESITEM_CAP, name);
	model.forBlockItem(m.SHINGLESITEM_CAP_ALT, name);
    }

    public static void addTranslations(CarpentryMaterialComponents m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(CarpentryMaterialComponents m, TCItemTags itp, String name) {
    }
    
    public static void registerBlockTags(CarpentryMaterialComponents m, TCBlockTags itp, String name) {
	itp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(m.SHINGLES_CAP.get());
	itp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(m.SHINGLES_CAP_ALT.get());
    }

    public static void buildLootTable(CarpentryMaterialComponents b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.SHINGLES.get());
	table.registerDropSelfLootTable(b.SHINGLES_ALT.get());
	table.registerDropSelfLootTable(b.SHINGLES_BLOCK.get());
	table.registerDropSelfLootTable(b.SHINGLES_CAP.get());
	table.registerDropSelfLootTable(b.SHINGLES_CAP_ALT.get());
    }

    public static void buildRecipes(CarpentryMaterialComponents m, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {
    }
 
}
