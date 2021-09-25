package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
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
import lance5057.compendium.core.data.builders.toolrecipes.HammerMainHandRecipes;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ConstructionMaterialShingles implements MaterialBase {

    RegistryObject<Block> SHINGLE_BLOCK;
    public List<RegistryObject<BlockShingles>> SHINGLES;
    public List<RegistryObject<BlockShingles>> SHINGLES_ALT;
    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS;
//    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS_ALT;

    RegistryObject<BlockNamedItem> SHINGLES_ITEMBLOCK;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_ALT;
    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS;
//    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS_ALT;

    public ConstructionMaterialShingles(ConstructionMaterialHelper cmh) {
	SHINGLES = new ArrayList<RegistryObject<BlockShingles>>();
	SHINGLES_ALT = new ArrayList<RegistryObject<BlockShingles>>();
	SHINGLES_CAPS = new ArrayList<RegistryObject<BlockShinglesCap>>();
//	SHINGLES_CAPS_ALT = new ArrayList<RegistryObject<BlockShinglesCap>>();

	SHINGLESITEM = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
	SHINGLESITEM_CAPS = new ArrayList<RegistryObject<BlockNamedItem>>();
//	SHINGLESITEM_CAPS_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
    }

    @Override
    public void setup(MaterialHelperBase cmh) {

	SHINGLE_BLOCK = cmh.BLOCKS.register(cmh.name + "_shinglesblock", () -> new Block(
		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

	SHINGLES_ITEMBLOCK = cmh.ITEMS.register(cmh.name + "_shingles_itemblock",
		() -> new BlockNamedItem(SHINGLE_BLOCK.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	for (MaterialHelperBase m : AppendixCarpentry.woods) {

	    RegistryObject<BlockShingles> SHINGLE = cmh.BLOCKS.register(cmh.name + "_" + m.name + "_shingles",
		    () -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(), Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockShingles> SHINGLE_ALT = cmh.BLOCKS.register(cmh.name + "_" + m.name + "_shinglesalt",
		    () -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(), Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockNamedItem> SHINGLEITEM = cmh.ITEMS.register(cmh.name + "_" + m.name + "_shinglesitem",
		    () -> new BlockNamedItem(SHINGLE.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockNamedItem> SHINGLEITEM_ALT = cmh.ITEMS
		    .register(cmh.name + "_" + m.name + "_shinglesitem_alt", () -> new BlockNamedItem(SHINGLE_ALT.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    RegistryObject<BlockShinglesCap> SHINGLE_CAP = cmh.BLOCKS
		    .register(cmh.name + "_" + m.name + "_shingles_cap", () -> new BlockShinglesCap(Block.Properties
			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	    RegistryObject<BlockNamedItem> SHINGLEITEM_CAP = cmh.ITEMS
		    .register(cmh.name + "_" + m.name + "_shinglesitem_cap", () -> new BlockNamedItem(SHINGLE_CAP.get(),
			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

//	    RegistryObject<BlockShinglesCap> SHINGLE_CAP_ALT = cmh.BLOCKS
//		    .register(cmh.name + "_" + m.name + "_shingles_cap_alt", () -> new BlockShinglesCap(Block.Properties
//			    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
//
//	    RegistryObject<BlockNamedItem> SHINGLEITEM_CAP_ALT = cmh.ITEMS.register(
//		    cmh.name + "_" + m.name + "_shinglesitem_cap_alt", () -> new BlockNamedItem(SHINGLE_CAP_ALT.get(),
//			    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	    SHINGLES.add(SHINGLE);
	    SHINGLES_ALT.add(SHINGLE_ALT);
	    SHINGLES_CAPS.add(SHINGLE_CAP);
//	    SHINGLES_CAPS_ALT.add(SHINGLE_CAP_ALT);

	    SHINGLESITEM_CAPS.add(SHINGLEITEM_CAP);
//	    SHINGLESITEM_CAPS_ALT.add(SHINGLEITEM_CAP_ALT);
	    SHINGLESITEM.add(SHINGLEITEM);
	    SHINGLESITEM_ALT.add(SHINGLEITEM_ALT);
	}
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(ConstructionMaterialShingles m, TCBlockModels model, String name) {
	model.simpleBlock(m.SHINGLE_BLOCK.get(),
		model.models().withExistingParent(name + "_shinglesblock", model.mcLoc("block/cube_all")).texture("all",
			"compendium:block/material/" + name + "/" + name + "shingles"));

	for (int i = 0; i < AppendixCarpentry.woods.size(); i++) {
	    // Shingles

	    TCBlockModels.shinglesModel(model, name + "_" + AppendixCarpentry.woods.get(i).name + "_",
		    "compendium:block/material/" + name + "/" + name + "shingles",
		    "minecraft:block/" + AppendixCarpentry.woods.get(i).name + "_" + AppendixCarpentry.woods.get(i).log,
		    "", "block/bases/shingles", m.SHINGLES.get(i).get());
	    TCBlockModels.shinglesModel(model, name + "_" + AppendixCarpentry.woods.get(i).name + "_",
		    "compendium:block/material/" + name + "/" + name + "shingles",
		    "minecraft:block/" + AppendixCarpentry.woods.get(i).name + "_" + AppendixCarpentry.woods.get(i).log,
		    "alt", "block/bases/shingles", m.SHINGLES_ALT.get(i).get());

	    TCBlockModels.shinglesCapModel(model, name + "_" + AppendixCarpentry.woods.get(i).name + "_",
		    "compendium:block/material/" + name + "/" + name + "shingles", "", "block/bases/shingles_cap",
		    m.SHINGLES_CAPS.get(i).get());

	}
    }

    public static void registerItemModels(ConstructionMaterialShingles m, TCItemModels model, String name) {
	model.forBlockItem(m.SHINGLES_ITEMBLOCK, name);

	for (int i = 0; i < AppendixCarpentry.woods.size(); i++) {

	    model.forBlockItem(m.SHINGLESITEM.get(i), name);
	    model.forBlockItem(m.SHINGLESITEM_ALT.get(i), name);
	    model.getBuilder(m.SHINGLESITEM_CAPS.get(i).getId().getPath())
		    .parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/shingles_cap_full")))
		    .texture("0", model.modLoc("block/material/" + name + "/" + name + "shingles"));
//	    model.forBlockItem(m.SHINGLESITEM_CAPS_ALT.get(i), name);
	}
    }

    public static void addTranslations(ConstructionMaterialShingles m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(ConstructionMaterialShingles m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionMaterialShingles b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.SHINGLE_BLOCK.get());

	for (int i = 0; i < AppendixCarpentry.woods.size(); i++) {
	    table.registerDropping(b.SHINGLES.get(i).get(),
		    ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MOD_ID, name + "plate")));// .registerDropSelfLootTable(b.SHINGLES.get(i).get());
	    table.registerDropSelfLootTable(b.SHINGLES_ALT.get(i).get());

	    table.registerDropSelfLootTable(b.SHINGLES_CAPS.get(i).get());
//	    table.registerDropSelfLootTable(b.SHINGLES_CAPS_ALT.get(i).get());
	}
    }

    public static void buildRecipes(ConstructionMaterialShingles m, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {

	HammerMainHandRecipes.createRecipe(name + "_shingles_block", new ItemStack(m.SHINGLE_BLOCK.get()),
		Ingredient.fromTag(ItemTags.PLANKS), Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)),
		consumer);

	for (int i = 0; i < AppendixCarpentry.woods.size(); i++) {
	    CarpentryMaterialComponents c = AppendixCarpentry.woods.get(i).getComponents();

	    HammerMainHandRecipes.createRecipe(name + "_" + AppendixCarpentry.woods.get(i).name + "_shingles",
		    new ItemStack(m.SHINGLES.get(i).get()), Ingredient.fromItems(c.SHINGLES.get()),
		    Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)), consumer);
	    HammerMainHandRecipes.createRecipe(name + "_" + AppendixCarpentry.woods.get(i).name + "_shingles_alt",
		    new ItemStack(m.SHINGLES_ALT.get(i).get()), Ingredient.fromItems(c.SHINGLES_ALT.get()),
		    Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)), consumer);

	    HammerMainHandRecipes.createRecipe(name + "_" + AppendixCarpentry.woods.get(i).name + "_shingles_cap",
		    new ItemStack(m.SHINGLES_CAPS.get(i).get()), Ingredient.fromItems(c.SHINGLES_CAP.get()),
		    Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)), consumer);
//	    HammerMainHandRecipes.createRecipe(name + "_" + AppendixCarpentry.woods.get(i).name + "_shingles_cap_alt",
//		    new ItemStack(m.SHINGLES_CAPS_ALT.get(i).get()), Ingredient.fromItems(c.SHINGLES_CAP_ALT.get()),
//		    Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)), consumer);

	}
    }

    public static void registerBlockTags(ConstructionMaterialShingles base, TCBlockTags btp, String name) {
	for (int i = 0; i < AppendixCarpentry.woods.size(); i++) {
	    btp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(base.SHINGLES_CAPS.get(i).get());
//	    btp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(base.SHINGLES_CAPS_ALT.get(i).get());
	}
    }

}
