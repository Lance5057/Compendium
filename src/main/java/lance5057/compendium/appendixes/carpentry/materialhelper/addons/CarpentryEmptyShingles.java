package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
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
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class CarpentryEmptyShingles implements MaterialBase {

    String logName;

//    public RegistryObject<Block> SHINGLES_BLOCK;
    public RegistryObject<BlockShingles> SHINGLES;
    public RegistryObject<BlockShingles> SHINGLES_ALT;

    public RegistryObject<BlockShinglesCap> SHINGLES_CAP;
    public RegistryObject<BlockShinglesCap> SHINGLES_CAP_ALT;

//    public RegistryObject<BlockNamedItem> SHINGLES_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> SHINGLESITEM;
    public RegistryObject<BlockNamedItem> SHINGLESITEM_ALT;

    public RegistryObject<BlockNamedItem> SHINGLESITEM_CAP;
    public RegistryObject<BlockNamedItem> SHINGLESITEM_CAP_ALT;

    public CarpentryEmptyShingles(CarpentryMaterialHelper cmh, String log) {
	this.logName = log;
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
//	SHINGLES_BLOCK = mat.BLOCKS.register(mat.name + "_empty_shinglesblock", () -> new Block(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

	SHINGLES = mat.BLOCKS.register(mat.name + "_empty_shingles",
		() -> new BlockShingles(() -> Blocks.OAK_WOOD.getDefaultState(), Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

	SHINGLES_ALT = mat.BLOCKS.register(mat.name + "_empty_shinglesalt",
		() -> new BlockShingles(() -> Blocks.OAK_WOOD.getDefaultState(), Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

//	SHINGLES_ITEMBLOCK = mat.ITEMS.register(mat.name + "_shingles_itemblock",
//		() -> new BlockNamedItem(SHINGLES_BLOCK.get(),
//			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SHINGLESITEM = mat.ITEMS.register(mat.name + "_shinglesitem", () -> new BlockNamedItem(SHINGLES.get(),
		new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SHINGLESITEM_ALT = mat.ITEMS.register(mat.name + "_shinglesitem_alt",
		() -> new BlockNamedItem(SHINGLES_ALT.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	SHINGLES_CAP = mat.BLOCKS.register(mat.name + "_empty_shingles_cap", () -> new BlockShinglesCap(Block.Properties
		.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
	SHINGLESITEM_CAP = mat.ITEMS.register(mat.name + "_shinglesitem_cap",
		() -> new BlockNamedItem(SHINGLES_CAP.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

	SHINGLES_CAP_ALT = mat.BLOCKS.register(mat.name + "_empty_shingles_cap_alt",
		() -> new BlockShinglesCap(Block.Properties.create(Material.WOOD).hardnessAndResistance(5F, 10F)
			.sound(SoundType.METAL).notSolid()));
	SHINGLESITEM_CAP_ALT = mat.ITEMS.register(mat.name + "_shinglesitem_cap_alt",
		() -> new BlockNamedItem(SHINGLES_CAP_ALT.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
    }

    public static void registerBlockModels(CarpentryEmptyShingles m, TCBlockModels model, String name,
	    String parentMod) {
	// Shingles
	TCBlockModels.shinglesModel(model, name + "_empty_", parentMod + ":block/" + name + "_planks",
		"minecraft:block/" + name + "_" + m.logName, "", "block/bases/empty_shingles", m.SHINGLES.get());
	TCBlockModels.shinglesModel(model, name + "_empty_", parentMod + ":block/" + name + "_planks",
		"minecraft:block/" + name + "_" + m.logName, "alt", "block/bases/empty_shingles", m.SHINGLES_ALT.get());
	TCBlockModels.shinglesCapModel(model, name + "_empty_", parentMod + ":block/" + name + "_planks",
		"minecraft:block/" + name + "_" + m.logName, "", "block/bases/empty_shingles_cap",
		m.SHINGLES_CAP.get());
	TCBlockModels.shinglesCapModel(model, name + "_empty_", parentMod + ":block/" + name + "_planks",
		"minecraft:block/" + name + "_" + m.logName, "_alt", "block/bases/empty_shingles_cap",
		m.SHINGLES_CAP_ALT.get());
    }

    public static void registerItemModels(CarpentryEmptyShingles m, TCItemModels model, String name, String parentMod) {
	// model.forBlockItem(m.SHINGLES_ITEMBLOCK, name);
	model.forBlockItem(m.SHINGLESITEM, name);
	model.forBlockItem(m.SHINGLESITEM_ALT, name);

	model.getBuilder(m.SHINGLESITEM_CAP.getId().getPath())
		.parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/empty_shingles_cap_full")))
		.texture("0", model.mcLoc("block/" + name + "_planks"))
		.texture("2", model.mcLoc("block/" + name + "_" + m.logName))
		.texture("1", model.mcLoc("block/" + name + "_" + m.logName + "_top"));

	model.getBuilder(m.SHINGLESITEM_CAP_ALT.getId().getPath())
		.parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/empty_shingles_cap_alt_full")))
		.texture("0", new ResourceLocation(parentMod, "block/" + name + "_planks"))
		.texture("2", new ResourceLocation(parentMod, "block/" + name + "_" + m.logName))
		.texture("1", new ResourceLocation(parentMod, "block/" + name + "_" + m.logName + "_top"));
    }

    public static void addTranslations(CarpentryEmptyShingles m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(CarpentryEmptyShingles m, TCItemTags itp, String name) {
    }

    public static void registerBlockTags(CarpentryEmptyShingles m, TCBlockTags itp, String name) {
	itp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(m.SHINGLES_CAP.get());
	itp.getOrCreateBuilder(CompendiumTags.SHINGLESCAP).add(m.SHINGLES_CAP_ALT.get());
    }

    public static void buildLootTable(CarpentryEmptyShingles b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.SHINGLES.get());
	table.registerDropSelfLootTable(b.SHINGLES_ALT.get());
	// table.registerDropSelfLootTable(b.SHINGLES_BLOCK.get());
	table.registerDropSelfLootTable(b.SHINGLES_CAP.get());
	table.registerDropSelfLootTable(b.SHINGLES_CAP_ALT.get());
    }

    public static void buildRecipes(CarpentryEmptyShingles m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name, String parentMod) {
	ShapedRecipeBuilder.shapedRecipe(m.SHINGLES.get(), 4).key('s', Items.STICK)
		.key('p', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_planks")))
		.key('l', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_log")))
		.patternLine("  s").patternLine(" sp").patternLine("spl")
		.addCriterion(name + "shingle",
			RecipeProvider.hasItem(
				ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_planks"))))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "shingle"));

	ShapedRecipeBuilder.shapedRecipe(m.SHINGLES_ALT.get(), 4).key('s', Items.STICK)
		.key('p', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_planks")))
		.key('l', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_log")))
		.patternLine("  s").patternLine(" sl").patternLine("slp")
		.addCriterion(name + "shingle_alt",
			RecipeProvider.hasItem(
				ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_planks"))))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "shingle_alt"));

	ShapedRecipeBuilder.shapedRecipe(m.SHINGLES_CAP.get(), 4).key('s', Items.STICK)
		.key('l', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_plank")))
		.patternLine(" s ").patternLine("sls")
		.addCriterion(name + "shingle_cap",
			RecipeProvider.hasItem(
				ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_plank"))))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "shingle_cap"));

	ShapedRecipeBuilder.shapedRecipe(m.SHINGLES_CAP_ALT.get(), 4).key('s', Items.STICK)
		.key('l', ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_log")))
		.patternLine(" s ").patternLine("sls")
		.addCriterion(name + "shingle_cap_alt",
			RecipeProvider.hasItem(
				ForgeRegistries.ITEMS.getValue(new ResourceLocation(parentMod, name + "_log"))))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "shingle_cap_alt"));
    }

}
